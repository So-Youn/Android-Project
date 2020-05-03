package multi.android.among.page.chatting.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.among.R;
import com.example.among.chatting.model.Chat;
import com.example.among.chatting.model.Message;
import com.example.among.chatting.model.PhotoMessage;
import com.example.among.chatting.model.TextMessage;
import com.example.among.chatting.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    private String mChatId;
    ImageView sendBtn;
    EditText mMessageText;
    FirebaseDatabase firebaseDB;
    DatabaseReference chatRef;
    DatabaseReference chatMemberRef;
    DatabaseReference chatMessageRef;
    DatabaseReference mUserRef;
    FirebaseUser firebaseUser;
    TextView toolbar;
    RecyclerView chatRecyclerView;
    MessageListAdapter messageListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        sendBtn = findViewById(R.id.senderBtn);
        mMessageText = findViewById(R.id.edtContent);
        toolbar = findViewById(R.id.toolbar);
        chatRecyclerView = findViewById(R.id.chat_rec_view);

        mChatId = getIntent().getStringExtra("chat_Id");
        firebaseDB = FirebaseDatabase.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        mUserRef = firebaseDB.getReference("users");
        Log.d("msgTestttt","ChatId"+mChatId);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mChatId != null) {
                    sendMessage();
                } else {
                    createChat();
                }
            }
        });

        if (mChatId != null) {
            chatRef = firebaseDB.getReference("users").child(firebaseUser.getUid()).child("chats").child(mChatId);
            chatMessageRef = firebaseDB.getReference("chat_messages").child(mChatId);
            chatMemberRef = firebaseDB.getReference("chat_members").child(mChatId);
            chatRef.child("title").addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String title = dataSnapshot.getValue(String.class);
                    //Log.d("msg", "toolbar::" + title);
                    toolbar.setText(title);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            initTotalUnreadCount();
        } else {
            chatRef = firebaseDB.getReference("users").child(firebaseUser.getUid()).child("chats");
        }
        messageListAdapter = new MessageListAdapter();
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatRecyclerView.setAdapter(messageListAdapter);

    }

    private void initTotalUnreadCount() {
        chatRef.child("totalUnreadCount").setValue(0);
    }
    ChildEventListener mMessageEventListener = new ChildEventListener() {


        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            // 신규메세지
            //Log.d("msgTest","리스너 chatMessageRef"+chatMessageRef);
            Message item = dataSnapshot.getValue(Message.class);
            //chat_message>chat-id> message-id>ReadUserList----???
            List<String> readUserIDList = item.getReadUserList();
            if (readUserIDList != null) {
                if (!readUserIDList.contains(firebaseUser.getUid())) {
                    dataSnapshot.getRef().runTransaction(new Transaction.Handler() {


                        @Override
                        public Transaction.Result doTransaction(MutableData mutableData) {
                            Message mutableMessage = mutableData.getValue(Message.class);
                            //readUser에 내 Id추가
                            //UnreadCount --
                            List<String> mutableReadUserList = mutableMessage.getReadUserList();
                            mutableReadUserList.add(firebaseUser.getUid()); // 내가 읽었음을 표시
                            int mutableUnreadCount = mutableMessage.getUnreadCount() - 1;
                            if (mutableMessage.getMessageType() == Message.MessageType.PHOTO) {
                                PhotoMessage mutablePhotoMessage = mutableData.getValue(PhotoMessage.class);
                                mutablePhotoMessage.setReadUserList(mutableReadUserList);
                                mutablePhotoMessage.setUnreadCount(mutableUnreadCount);
                                mutableData.setValue(mutablePhotoMessage);

                            } else {
                                TextMessage mutableTextMessage = mutableData.getValue(TextMessage.class);
                                mutableTextMessage.setReadUserList(mutableReadUserList);
                                mutableTextMessage.setUnreadCount(mutableUnreadCount);
                                mutableData.setValue(mutableTextMessage);
                            }

                            return Transaction.success(mutableData);
                        }

                        @Override
                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {
                            //작업이 완료되면 unReadCount 0만들기
                            initTotalUnreadCount();
                        }
                    });
                }

            }
            //ui
            if (item.getMessageType() == Message.MessageType.TEXT) {
                TextMessage textMessage = dataSnapshot.getValue(TextMessage.class);
                messageListAdapter.addItem(textMessage);
            } else if (item.getMessageType() == Message.MessageType.PHOTO) {
                PhotoMessage photoMessage = dataSnapshot.getValue(PhotoMessage.class);
                messageListAdapter.addItem(photoMessage);
            }
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            //변경된 메시지 - UnreadCount
            //adapter에 변경된 메시지 데이터 전달하고 메시지 id번호로
            // 해당 메시지의 위치를 알아내서
            //알아낸 위치 값을 이용해서 메시지 리스트의 값 변경
            Message item = dataSnapshot.getValue(Message.class);
            if (item.getMessageType() == Message.MessageType.TEXT) {
                TextMessage textMessage = dataSnapshot.getValue(TextMessage.class);
                messageListAdapter.updateItem(textMessage);
            } else if (item.getMessageType() == Message.MessageType.PHOTO) {
                PhotoMessage photoMessage = dataSnapshot.getValue(PhotoMessage.class);
                messageListAdapter.updateItem(photoMessage);
            }
        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };


    //액티비티 비활성화 - 리스너가 끊겨도 리스닝을 계속 하고있어야 하기 때문
    @Override
    protected void onPause() {
        super.onPause();
        if (mChatId != null) {
            removeMessageListener();
        }
    }


    //액티비티 재개
    @Override
    protected void onResume() {
        super.onResume();
        if (mChatId != null) {
            addMessageListener(); //채팅 아이디가 있을 때만
        }
    }

    private void addMessageListener () {
            chatMessageRef.addChildEventListener(mMessageEventListener);
    }


    private void removeMessageListener() {
        chatMessageRef.removeEventListener(mMessageEventListener);
    }

        public void sendMessage () {
            //메시지 키 생성

            chatMessageRef = firebaseDB.getReference("chat_messages").child(mChatId);
            //chat_message - {chat-id}-{messageId}-messageInfo
            String messageId = chatMessageRef.push().getKey();
            String messageText = mMessageText.getText().toString();
            String lastMessage = mMessageText.getText().toString();
            if (messageText.isEmpty()) {
                //메시지를 입력하지 않으면 전송이 되지 않도록
                return;
            }
            final TextMessage textMessage = new TextMessage();
            textMessage.setMessageText(messageText);
            textMessage.setMessageDate(new Date());
            textMessage.setChatId(mChatId);
            textMessage.setMessageId(messageId);
            textMessage.setMessageType(Message.MessageType.TEXT);
            textMessage.setMessageUser(new User(firebaseUser.getUid(), firebaseUser.getEmail(),
                    firebaseUser.getDisplayName(), firebaseUser.getPhotoUrl().toString()));
            textMessage.setReadUserList(Arrays.asList(new String[]{firebaseUser.getUid()}));
            String[] uids = getIntent().getExtras().getStringArray("uids"); // 신규 방인 경우에만
            //읽지 않은 사람 수
            if (uids != null) {
                textMessage.setUnreadCount(uids.length - 1);
            }
            mMessageText.setText(""); // 채팅창 공백으로 비우기
            chatMemberRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                    //unReadCount 셋팅하기 위한 대화 상대의 수 가져오기
                    long memberCount = dataSnapshot.getChildrenCount(); //mChatID 갯수
                    textMessage.setUnreadCount((int) memberCount - 1);
                    chatMessageRef.child(textMessage.getMessageId()).setValue(textMessage, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable final DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                            Iterator<DataSnapshot> memberIterator = dataSnapshot.getChildren().iterator();
                            while (memberIterator.hasNext()) {
                                User chatMember = memberIterator.next().getValue(User.class); //user 정보 꺼내오기

                                mUserRef
                                            .child(chatMember.getUid())
                                            .child("chats")
                                            .child(mChatId)
                                            .child("lastMessage")
                                            .setValue(textMessage);

                                if (!chatMember.getUid().equals(firebaseUser.getUid())) {
                                    mUserRef
                                            .child(chatMember.getUid())
                                            .child("chats")
                                            .child(mChatId)
                                            .child("totalUnreadCount")
                                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    long totalUnreadCount = dataSnapshot.getValue(long.class);
                                                    dataSnapshot.getRef().setValue(totalUnreadCount + 1);
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                }
                            }
                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
        private boolean isSentMessage = false;
        public void createChat () {
            //방 생성

            final Chat chat = new Chat();
            // 채팅 주소 얻어오기
            chatRef = firebaseDB.getReference("users").child(firebaseUser.getUid()).child("chats");
            mChatId = chatRef.push().getKey();
            chatMemberRef = firebaseDB.getReference("chat_members").child(mChatId);
            chat.setChatId(mChatId);
            chat.setCreateDate(new Date()); // 생성 날짜
            String uid = getIntent().getStringExtra("uid");
            String[] uids = getIntent().getExtras().getStringArray("uids");
            if (uid != null) {
                //1:1
                uids = new String[]{uid};

            }
            List<String> uidList = new ArrayList<>(Arrays.asList(uids));
            uidList.add(firebaseUser.getUid());
            //채팅방 고유 키 얻어오기

            //사용자 1, 사용자 2 출력...

            for (String userId : uidList) {
                //uid - userInfo
                mUserRef.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                        User member = dataSnapshot.getValue(User.class);
                                //firebase db에 있는 정보 User 형으로 가져오기
                           /* titleBuffer.append(member.getName());
                            titleBuffer.append(",");*/

                        chatMemberRef.child(member.getUid()).
                                setValue(member, new DatabaseReference.CompletionListener() {
                                    @Override
                                    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                                        //방정보 Users-uid-chats-{chat-id}-chatInfo
                                        dataSnapshot.getRef().child("chats").child(mChatId).setValue(chat);
                                        if (!isSentMessage) {
                                            sendMessage();
                                            addMessageListener();
                                           // Log.d("send","sendMessage");
                                            isSentMessage = true;
                                        }
                                    }
                                });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        }
    }

