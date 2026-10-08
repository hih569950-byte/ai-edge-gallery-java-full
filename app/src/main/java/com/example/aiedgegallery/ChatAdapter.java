package com.example.aiedgegallery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<ChatMessage> messages;
    private static final int TYPE_USER = 1, TYPE_AI = 0;

    public ChatAdapter(List<ChatMessage> messages) {
        this.messages = messages;
    }

    @Override
    public int getItemViewType(int position) {
        return messages.get(position).isUser() ? TYPE_USER : TYPE_AI;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == TYPE_USER) {
            return new UserHolder(inflater.inflate(R.layout.item_chat_right, parent, false));
        } else {
            return new AiHolder(inflater.inflate(R.layout.item_chat_left, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatMessage msg = messages.get(position);
        if (holder instanceof UserHolder) {
            ((UserHolder) holder).bind(msg);
        } else {
            ((AiHolder) holder).bind(msg);
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    static class UserHolder extends RecyclerView.ViewHolder {
        TextView sender, text;
        UserHolder(@NonNull View v) {
            super(v);
            sender = v.findViewById(R.id.senderText);
            text = v.findViewById(R.id.messageText);
        }
        void bind(ChatMessage msg) {
            sender.setText(msg.getSender());
            text.setText(msg.getText());
        }
    }

    static class AiHolder extends RecyclerView.ViewHolder {
        TextView sender, text;
        AiHolder(@NonNull View v) {
            super(v);
            sender = v.findViewById(R.id.senderText);
            text = v.findViewById(R.id.messageText);
        }
        void bind(ChatMessage msg) {
            sender.setText(msg.getSender());
            text.setText(msg.getText());
        }
    }
}
