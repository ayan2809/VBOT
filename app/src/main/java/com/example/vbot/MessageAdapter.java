package com.example.vbot;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static com.example.vbot.R.id.textMessage;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.CustomViewHolder> {
    class CustomViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public CustomViewHolder(View itemView){
            super(itemView);
            textView = itemView.findViewById(textMessage);
        }
    }
    List<ResponseMesssage> responseMessageList;




    public MessageAdapter(List <ResponseMesssage> responseMessageList)
    {
        this.responseMessageList=responseMessageList;
    }

    @Override
    public int getItemViewType(int position) {
        if(responseMessageList.get(position).isMe()){
            return R.layout.me_bubble;
        }

        return R.layout.bot_bubble;
    }

    @NonNull
    @Override
    public MessageAdapter.CustomViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(parent.getContext()).inflate(viewType,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.CustomViewHolder holder, int position) {
        holder.textView.setText(responseMessageList.get(position).getTextMessage());
    }

    @Override
    public int getItemCount() {

        return responseMessageList.size();
    }
}
