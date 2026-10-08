package com.example.aiedgegallery;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ModelManagerActivity extends AppCompatActivity {

    private RecyclerView modelList;
    private Button download;
    private Button callAssistantButton;
    private TextView storage;
    private final OfflineModelManager modelManager = new OfflineModelManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_manager);

        modelList = findViewById(R.id.modelList);
        download = findViewById(R.id.downloadButton);
        callAssistantButton = findViewById(R.id.callAssistantButton);
        storage = findViewById(R.id.storageInfo);

        List<OfflineModel> models = modelManager.getAvailableModels();
        ModelAdapter adapter = new ModelAdapter(models, model -> {
            Toast.makeText(this, "Model selected: " + model.getName(), Toast.LENGTH_SHORT).show();
        });

        modelList.setLayoutManager(new LinearLayoutManager(this));
        modelList.setAdapter(adapter);

        storage.setText("Storage Used: 4.5 GB / 128 GB available");
        download.setOnClickListener(v -> downloadSelectedModel(models, adapter));
        callAssistantButton.setOnClickListener(v -> {
            startActivity(new android.content.Intent(this, CallAssistantActivity.class));
        });
    }

    private void downloadSelectedModel(List<OfflineModel> models, ModelAdapter adapter) {
        for (OfflineModel model : models) {
            if (!model.isDownloaded()) {
                modelManager.downloadModel(model, () -> {
                    Toast.makeText(this, "Downloaded: " + model.getName(), Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
                });
                return;
            }
        }
        Toast.makeText(this, "All models already downloaded.", Toast.LENGTH_SHORT).show();
    }
}

class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.ViewHolder> {
    private final List<OfflineModel> models;
    private final OnModelClickListener listener;

    interface OnModelClickListener { void onClick(OfflineModel model); }

    ModelAdapter(List<OfflineModel> models, OnModelClickListener listener) {
        this.models = models;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(android.view.ViewGroup parent, int viewType) {
        android.view.View view = android.view.LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_model, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        OfflineModel model = models.get(position);
        holder.modelName.setText(model.getName() + " - " + model.getSize() + (model.isDownloaded() ? " (Downloaded)" : " (Not downloaded)"));
        holder.itemView.setOnClickListener(v -> listener.onClick(model));
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        android.widget.TextView modelName;
        ViewHolder(android.view.View v) {
            super(v);
            modelName = v.findViewById(R.id.modelName);
        }
    }
}
