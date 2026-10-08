package com.example.aiedgegallery;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private EditText input;
    private Button send;
    private RecyclerView list;
    private ChatAdapter adapter;
    private final List<ChatMessage> messages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        input = findViewById(R.id.chatInput);
        send = findViewById(R.id.sendButton);
        list = findViewById(R.id.chatList);

        adapter = new ChatAdapter(messages);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        messages.add(new ChatMessage("AI", "Hello! I am your AI assistant. Ask me anything.", false));
        adapter.notifyItemInserted(0);

        send.setOnClickListener(v -> sendMessage());
    }

    private void sendMessage() {
        String text = input.getText().toString().trim();
        if (TextUtils.isEmpty(text)) {
            Toast.makeText(this, "Type a message", Toast.LENGTH_SHORT).show();
            return;
        }

        messages.add(new ChatMessage("You", text, true));
        adapter.notifyItemInserted(messages.size() - 1);

        String reply = generateReply(text);
        messages.add(new ChatMessage("AI", reply, false));
        adapter.notifyItemInserted(messages.size() - 1);

        list.scrollToPosition(messages.size() - 1);
        input.setText("");
    }

    private String generateReply(String input) {
        String lower = input.toLowerCase();
        if (lower.contains("hello") || lower.contains("hi")) {
            return "Hello! How can I help you today?";
        }
        if (lower.contains("how are you")) {
            return "I'm doing great! Ready to assist you with any task.";
        }
        if (lower.contains("what is")) {
            return "That's a great question! I can provide information about various topics.";
        }
        if (lower.contains("help")) {
            return "Of course! I'm here to help. What do you need?";
        }
        return "I understand: " + input + ". This is an offline AI assistant running on your device.";
    }
}
