package com.example.aiedgegallery;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TinyGardenActivity extends AppCompatActivity {

    private TextView gardenStatus;
    private Button plant, harvest, water;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiny_garden);

        gardenStatus = findViewById(R.id.gardenStatus);
        plant = findViewById(R.id.plantButton);
        harvest = findViewById(R.id.harvestButton);
        water = findViewById(R.id.waterButton);

        gardenStatus.setText("🌱 Garden Status:\nTomatoes: Growing\nCucumber: Seedling\nBasil: Ready to harvest\n\nSpeak to interact with your garden!");

        plant.setOnClickListener(v -> Toast.makeText(this, "🌿 Plant added! Say 'plant tomato' to add more.", Toast.LENGTH_SHORT).show());
        harvest.setOnClickListener(v -> Toast.makeText(this, "✂️ Harvested! Say 'harvest' to get rewards.", Toast.LENGTH_SHORT).show());
        water.setOnClickListener(v -> Toast.makeText(this, "💧 Watered! Plants are growing.", Toast.LENGTH_SHORT).show());
    }
}
