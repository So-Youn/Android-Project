package multi.android.among.page.chatting.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.text.SimpleDateFormat;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import multi.android.among.R;

import multi.android.among.page.chatting.model.Chat;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ChatHolder> {

    private ArrayList<Chat> ChatList;

    private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd hh:mm");
    //객체 생성되었을 때 ArrayList 생성
    com.example.among.function.ChattingFragment chattingFragment = new com.example.among.function.ChattingFragment();
    public ChatListAdapter() {
       ChatList = new ArrayList<>();
   }
    public void setFragment(com.example.among.function.ChattingFragment chattingFragment){
        this.chattingFragment = chattingFragment;
    }
    public void addItem(Chat chat){
        ChatList.add(chat); //객체 추가
        notifyDataSetChanged();
    }
    public void removeItem(Chat chat){
        int position = getItemPosition(chat.getChatId());
        ChatList.remove(position);
        notifyDataSetChanged();
    }
    public void updateItem(Chat chat){
        int changedItemPosition = getItemPosition(chat.getChatId());
        ChatList.set(getItemPosition(chat.getChatId()),chat);
        notifyItemChanged(changedItemPosition);
    }
    private int getItemPosition(String chatId) {
        int position = 0;
        for ( Chat currItem : ChatList ) {
            if ( currItem.getChatId().equals(chatId)) {
                return position;
            }
            position++;
        }
        return -1;
    }
    public Chat getItem(int position){
        return this.ChatList.get(position);
    }
    public Chat getItem(String chatId) {
        return getItem(getItemPosition(chatId));
    }

    @NonNull
    @Override
    public ChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_chat_item,parent,false);
        return new ChatHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatHolder holder, int position) {
        final Chat item = getItem(position);
        Log.d("msg", "item" + item.toString());
        if(item.getLastMessage()!=null){
            holder.lastMessageView.setText(item.getLastMessage().getMessageText());
            holder.lastMsgDateView.setText(sdf.format(item.getLastMessage().getMessageDate()));
        }

        holder.titleView.setText(item.getTitle());
        holder.rootView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(chattingFragment !=null){
                    chattingFragment.leaveChat(item);
                }
                return true;
            }
        });

        if (item.getTotalUnreadCount() > 0) {
            holder.totalUnreadCountView.setText(String.valueOf(item.getTotalUnreadCount())); //int 형 String으로 바꿔주기
        }else{
            holder.totalUnreadCountView.setText("");
        }

    }

    @Override
    public int getItemCount() {
        return ChatList.size();
    }

    public static class ChatHolder extends RecyclerView.ViewHolder{
        CircleImageView ChatThumbView;
        TextView titleView;
        TextView lastMessageView;
        TextView totalUnreadCountView;
        TextView lastMsgDateView;
        LinearLayout rootView;
        public ChatHolder(View v){
            super(v);
            ChatThumbView = v.findViewById(R.id.thumb);
            titleView = v.findViewById(R.id.title);
            lastMessageView = v.findViewById(R.id.lastMessage);
            totalUnreadCountView = v.findViewById(R.id.totalUnreadCount);
            lastMsgDateView = v.findViewById(R.id.lastMsgDate);
            rootView = v.findViewById(R.id.rootView);
        }

    }
}
