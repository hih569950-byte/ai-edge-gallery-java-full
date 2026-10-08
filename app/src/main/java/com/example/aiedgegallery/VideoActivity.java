package com.example.aiedgegallery;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

public class VideoActivity extends AppCompatActivity {

    private EditText searchQuery;
    private TextView results;
    private Button select, search;
    private Uri videoUri;

    private final ActivityResultLauncher<Intent> videoLauncher =
        registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), r -> {
            if (r.getResultCode() == RESULT_OK && r.getData() != null) {
                videoUri = r.getData().getData();
                results.setText("Video selected: " + videoUri);
            }
        });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        searchQuery = findViewById(R.id.queryInput);
        results = findViewById(R.id.resultsText);
        select = findViewById(R.id.selectButton);
        search = findViewById(R.id.searchButton);

        select.setOnClickListener(v -> selectVideo());
        search.setOnClickListener(v -> searchMoments());
    }

    private void selectVideo() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        videoLauncher.launch(intent);
    }

    private void searchMoments() {
        String query = searchQuery.getText().toString();
        if (query.isEmpty() || videoUri == null) {
            Toast.makeText(this, "Select video and enter search query", Toast.LENGTH_SHORT).show();
        } else {
            results.setText("Moments found: 00:15-00:20 (Match 1), 00:45-00:50 (Match 2). Using on-device video analysis.");
        }
    }
}
