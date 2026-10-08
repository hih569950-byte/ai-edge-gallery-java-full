package com.example.aiedgegallery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class CallAssistantActivity extends AppCompatActivity {
    private TextView callStatus;
    private Button startCall, listen, speak;
    private final VoiceAssistantService assistantService = new VoiceAssistantService(this);

    private final ActivityResultLauncher<Intent> speechLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    List<String> matches = result.getData().getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    if (matches != null && !matches.isEmpty()) {
                        String userText = matches.get(0);
                        callStatus.setText("You: " + userText);
                        String answer = assistantService.processVoice(userText);
                        callStatus.append("\nAI: " + answer);
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_assistant);

        callStatus = findViewById(R.id.callStatus);
        startCall = findViewById(R.id.startCallButton);
        listen = findViewById(R.id.listenButton);
        speak = findViewById(R.id.speakButton);

        startCall.setOnClickListener(v -> startCall());
        listen.setOnClickListener(v -> listenForVoice());
        speak.setOnClickListener(v -> assistantService.speak("Hello! I am your AI companion. Ask me anything."));
    }

    private void startCall() {
        callStatus.setText("Call started. AI is ready to talk.");
        assistantService.speak("Call started. I am ready to talk with you.");
    }

    private void listenForVoice() {
        speechLauncher.launch(assistantService.buildSpeechIntent());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        assistantService.shutdown();
    }
}
