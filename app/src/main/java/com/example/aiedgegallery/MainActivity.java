package com.example.aiedgegallery;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView featureGrid;
    private final List<FeatureCard> cards = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        featureGrid = findViewById(R.id.featureGrid);

        cards.add(new FeatureCard("AI Chat", "Multi-turn conversations"));
        cards.add(new FeatureCard("Ask Image", "Camera + Image analysis"));
        cards.add(new FeatureCard("Audio Scribe", "Voice transcription"));
        cards.add(new FeatureCard("Video Finder", "Search moments"));
        cards.add(new FeatureCard("Media Search", "Photo library"));
        cards.add(new FeatureCard("Prompt Lab", "Test prompts"));
        cards.add(new FeatureCard("Model Manager", "Download models"));
        cards.add(new FeatureCard("Agent Skills", "Tools + Wikipedia"));
        cards.add(new FeatureCard("Tiny Garden", "AI game"));
        cards.add(new FeatureCard("Mobile Actions", "Device control"));

        FeatureAdapter adapter = new FeatureAdapter(cards, this::openFeature);
        featureGrid.setLayoutManager(new GridLayoutManager(this, 2));
        featureGrid.setAdapter(adapter);
    }

    private void openFeature(int position) {
        Intent intent = null;
        switch (position) {
            case 0: intent = new Intent(this, ChatActivity.class); break;
            case 1: intent = new Intent(this, AskImageActivity.class); break;
            case 2: intent = new Intent(this, AudioScribeActivity.class); break;
            case 3: intent = new Intent(this, VideoActivity.class); break;
            case 4: intent = new Intent(this, MediaSearchActivity.class); break;
            case 5: intent = new Intent(this, PromptLabActivity.class); break;
            case 6: intent = new Intent(this, ModelManagerActivity.class); break;
            case 7: intent = new Intent(this, AgentSkillsActivity.class); break;
            case 8: intent = new Intent(this, TinyGardenActivity.class); break;
            case 9: intent = new Intent(this, MobileActionsActivity.class); break;
            default:
                Toast.makeText(this, "Feature not available", Toast.LENGTH_SHORT).show();
                return;
        }
        startActivity(intent);
    }
}
