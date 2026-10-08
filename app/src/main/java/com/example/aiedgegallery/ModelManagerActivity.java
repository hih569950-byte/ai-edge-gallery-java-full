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
    private TextView storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_manager);

        modelList = findViewById(R.id.modelList);
        download = findViewById(R.id.downloadButton);
        storage = findViewById(R.id.storageInfo);

        List<String> models = new ArrayList<>();
        models.add("Gemma 2B - 2.5 GB (Not downloaded)");
        models.add("Gemma 7B - 7.2 GB (Not downloaded)");
        models.add("Mistral 7B - 7.1 GB (Not downloaded)");
        models.add("Phi 3 - 3.8 GB (Downloaded)");

        ModelAdapter adapter = new ModelAdapter(models);
        modelList.setLayoutManager(new LinearLayoutManager(this));
        modelList.setAdapter(adapter);

        storage.setText("Storage Used: 3.8 GB / 128 GB available");
        download.setOnClickListener(v -> downloadModel());
    }

    private void downloadModel() {
        Toast.makeText(this, "Downloading model... (simulated)", Toast.LENGTH_SHORT).show();
    }
}

class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.ViewHolder> {
    private final List<String> models;

    ModelAdapter(List<String> models) {
        this.models = models;
    }

    @Override
    public ViewHolder onCreateViewHolder(android.view.ViewGroup parent, int viewType) {
        android.view.View view = android.view.LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_model, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.modelName.setText(models.get(position));
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
