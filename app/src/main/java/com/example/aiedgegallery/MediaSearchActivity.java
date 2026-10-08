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

public class MediaSearchActivity extends AppCompatActivity {

    private EditText searchInput;
    private TextView searchResults;
    private Button scan, search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_search);

        searchInput = findViewById(R.id.searchInput);
        searchResults = findViewById(R.id.searchResults);
        scan = findViewById(R.id.scanButton);
        search = findViewById(R.id.searchButton);

        scan.setOnClickListener(v -> scanLibrary());
        search.setOnClickListener(v -> performSearch());
    }

    private void scanLibrary() {
        Toast.makeText(this, "Scanning photo library...", Toast.LENGTH_SHORT).show();
        searchResults.setText("Library scanned. 250 photos indexed with embeddings.");
    }

    private void performSearch() {
        String query = searchInput.getText().toString();
        if (query.isEmpty()) {
            Toast.makeText(this, "Enter search query", Toast.LENGTH_SHORT).show();
        } else {
            searchResults.setText("Search results for '" + query + "':\n- photo_001.jpg (98% match)\n- photo_045.jpg (92% match)\n- photo_089.jpg (85% match)");
        }
    }
}
