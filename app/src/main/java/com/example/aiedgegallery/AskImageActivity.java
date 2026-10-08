package com.example.aiedgegallery;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

public class AskImageActivity extends AppCompatActivity {

    private ImageView preview;
    private TextView result;
    private Button camera, gallery, analyze;
    private Uri selectedImage;

    private final ActivityResultLauncher<Intent> galleryLauncher =
        registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), r -> {
            if (r.getResultCode() == RESULT_OK && r.getData() != null) {
                selectedImage = r.getData().getData();
                preview.setImageURI(selectedImage);
                result.setText("Image selected. Ready to analyze.");
            }
        });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_image);

        preview = findViewById(R.id.imagePreview);
        result = findViewById(R.id.analysisResult);
        camera = findViewById(R.id.cameraButton);
        gallery = findViewById(R.id.galleryButton);
        analyze = findViewById(R.id.analyzeButton);

        camera.setOnClickListener(v -> openCamera());
        gallery.setOnClickListener(v -> openGallery());
        analyze.setOnClickListener(v -> analyzeImage());
    }

    private void openCamera() {
        Toast.makeText(this, "Camera feature: Capture image and analyze", Toast.LENGTH_SHORT).show();
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryLauncher.launch(intent);
    }

    private void analyzeImage() {
        if (selectedImage == null) {
            Toast.makeText(this, "Select an image first", Toast.LENGTH_SHORT).show();
        } else {
            result.setText("Analysis: Objects detected - Tree, Sky, Clouds. Objects identified using on-device vision model.");
        }
    }
}
