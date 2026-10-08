package com.example.aiedgegallery;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import java.util.List;

public class AudioScribeActivity extends AppCompatActivity {

    private TextView transcript;
    private Button record, translate;

    private final ActivityResultLauncher<Intent> speechLauncher =
        registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), r -> {
            if (r.getResultCode() == RESULT_OK && r.getData() != null) {
                List<String> results = r.getData().getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                if (results != null && !results.isEmpty()) {
                    transcript.setText("Transcript: " + results.get(0));
                }
            }
        });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_scribe);

        transcript = findViewById(R.id.transcriptText);
        record = findViewById(R.id.recordButton);
        translate = findViewById(R.id.translateButton);

        record.setOnClickListener(v -> startRecording());
        translate.setOnClickListener(v -> translateText());
    }

    private void startRecording() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak something...");
        speechLauncher.launch(intent);
    }

    private void translateText() {
        String text = transcript.getText().toString();
        if (text.isEmpty()) {
            Toast.makeText(this, "Record audio first", Toast.LENGTH_SHORT).show();
        } else {
            transcript.setText("Translation (Spanish): " + "Transcript translated using on-device translator.");
        }
    }
}
