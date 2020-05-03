package com.example.among.function;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import multi.android.among.R;
import multi.android.among.page.chatting.activity.ChatActivity;
import multi.android.among.page.chatting.adapter.ChatListAdapter;
import multi.android.among.page.chatting.model.Chat;
import multi.android.among.page.chatting.model.Message;
import multi.android.among.page.chatting.model.RecyclerViewItemClickListener;
import multi.android.among.page.chatting.model.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChattingFragment extends Fragment {
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDB;
    DatabaseReference mChatRef;
    DatabaseReference mChatMemberRef;
    RecyclerView chatRecyclerView;
    ChatListAdapter chatListAdapter;

    public ChattingFragment() {
    }
    public static String joinChatId = "";
    public static final int JOINROOM_REQUESTCODE=100;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chatting, container, false);
        chatRecyclerView = view.findViewById(R.id.ChatRecyclerView);


        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        firebaseDB = FirebaseDatabase.getInstance();
        mChatRef = firebaseDB.getReference("users").child(firebaseUser.getUid()).child("chats");
        mChatMemberRef = firebaseDB.getReference("chat_members");


        chatListAdapter = new ChatListAdapter();
        //chatListAdapter = new ChatListAdapter(getActivity(),R.layout.fragment_chat_item,new ArrayList<Chat>());
        chatListAdapter.setFragment(this);//현재 프래그먼트는 chat이기 때문에
        LinearLayoutManager manager = new LinearLayoutManager(getContext());

        chatRecyclerView.setHasFixedSize(true);
        chatRecyclerView.setAdapter(chatListAdapter);
        chatRecyclerView.setLayoutManager(manager);

        chatRecyclerView.addOnItemTouchListener(new RecyclerViewItemClickListener(getContext(), new RecyclerViewItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Chat chat = chatListAdapter.getItem(position);
                Intent chatIntent = new Intent(getActivity(), ChatActivity.class);
                chatIntent.putExtra("chat_Id", chat.getChatId());
                joinChatId = chat.getChatId();
                startActivityForResult(chatIntent,JOINROOM_REQUESTCODE);

            }
        }));
        addChatListener();
        return view;
    }

    public void addChatListener() {
        //실시간 감지가 필요
        mChatRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull final DataSnapshot ChatDataSnapshot, @Nullable String s) {
                //방에 대한 정보를 얻어오고 UI 갱신 - 방의 정보 전달
                final Chat chatRoom = ChatDataSnapshot.getValue(Chat.class);
                mChatMemberRef.child(chatRoom.getChatId()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //넘어온 친구의 정보 확인
                        long memberCount = dataSnapshot.getChildrenCount();
                        Iterator<DataSnapshot> memberIterator = dataSnapshot.getChildren().iterator();
                        StringBuffer memberSb = new StringBuffer();

                        int loopCount = 1;
                        while (memberIterator.hasNext()) {
                            User member = memberIterator.next().getValue(User.class);
                            if (!firebaseUser.getUid().equals(member.getUid())) {
                                memberSb.append(member.getName());
                                if (memberCount - loopCount > 1) {
                                    memberSb.append(", ");
                                }
                            }
                            if (loopCount == memberCount) {
                                // users/uid/chats/{chat_id}/title
                                String title = memberSb.toString();
                                if (chatRoom.getTitle() == null) {
                                    //처음 생성 시 null이기 때문에
                                    ChatDataSnapshot.getRef().child("title").setValue(title);
                                } else if (!chatRoom.getTitle().equals(title)) {
                                    ChatDataSnapshot.getRef().child("title").setValue(title);
                                }
                                chatRoom.setTitle(title);
                                drawUI(chatRoom);
                            }
                            loopCount++;
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //변경된 방의 정보 수신, 내가 보낸 미시지가 아닌 경우, 마지막 메시지가 수정이 되었는지 확인
                //변경된 포지션 확인, (채팅방 아이디로 기존의 포지션을 확인)
                //포지션의 item중 unreadCount 가 변경이 되었다면 unreadCount 변경 x
                //lastMessage 의 시각과 변경된 메시지의 lastMessage 시간이 다르다면 -> 새로운 메시지 Noti 출력
                //방에 대한 전체 정보를 가지고 있는 곳
                //현재 액티비티가 ChatActivity 이고 chat_id가 같다면 노티 ㄴㄴ
                Chat updatedChat = dataSnapshot.getValue(Chat.class);
                //첫메시지는 oldChat역시 null일 수도 있기 때문에 oldmessage역시 null 처리
                Chat oldChat = chatListAdapter.getItem(updatedChat.getChatId());
                if( updatedChat.getLastMessage()==null){
                    return;
                }
                chatListAdapter.updateItem(updatedChat);
                if(!updatedChat.getLastMessage().getMessageUser().getUid().equals(firebaseUser.getUid())){
                    if (updatedChat.getLastMessage().getMessageDate().getTime() > oldChat.getLastMessage().getMessageDate().getTime()) {

                        if (!updatedChat.getChatId().equals(joinChatId)) {
                            //노티피케이션 알림
                            Snackbar.make(getView(), updatedChat.getLastMessage().getMessageText(), Snackbar.LENGTH_LONG).show();
                        }
                    }
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                //방의 실시간 삭제
                Chat item = dataSnapshot.getValue(Chat.class);
                chatListAdapter.removeItem(item);

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void drawUI(Chat chat) {
        chatListAdapter.addItem(chat);
    }

    public void leaveChat(final Chat chat) {
        Snackbar.make(getView(), "선택된 대화방을 나가시겠습니까?", Snackbar.LENGTH_LONG)
                .setAction("예", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //나의 대화방 목록에서 제거
                        //users/{uid}/chats
                        mChatRef.child(chat.getChatId()).removeValue(new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                                //chatting 멤버 목록에서 제거
                                //chat_members/{chat_id}/user_id 제거
                                mChatMemberRef.
                                        child(chat.getChatId()).
                                        child(firebaseUser.getUid()).removeValue(new DatabaseReference.CompletionListener() {
                                    @Override
                                    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                                        //채팅방 목록에서 나를 지운 상태
                                        //getReadUserList에 내가 있다면 pass
                                        //없다면 unReadCount -1
                                        //messages-{chat_id}의 모든 메시지를 가져와서
                                        //loop 통해서 내가 읽었는 지 여부 판단.
                                        firebaseDB.getReference("messages").child(chat.getChatId()).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                Iterator<DataSnapshot> messageIterator = dataSnapshot.getChildren().iterator();
                                                while (messageIterator.hasNext()) {

                                                    DataSnapshot messageSnapshot = messageIterator.next();
                                                    Message currentMessage = messageSnapshot.getValue(Message.class);
                                                    if (!currentMessage.getReadUserList().contains(firebaseUser.getUid())) {
                                                        //읽은 사람 목록에 내가 없다면
                                                        messageSnapshot.child("unreadCount").getRef().setValue(currentMessage.getUnreadCount() - 1);

                                                    }
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });
                                    }
                                });
                            }
                        });


                        //메시지 unreadcount 에서도 제거거
                    }
                }).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == JOINROOM_REQUESTCODE){
            joinChatId = "";
        }
    }
}