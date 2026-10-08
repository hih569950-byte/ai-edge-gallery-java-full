package com.example.aiedgegallery;

import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;
import java.util.List;

public class OfflineModelManager {
    private final List<OfflineModel> modelList = new ArrayList<>();
    private final Handler uiHandler = new Handler(Looper.getMainLooper());

    public OfflineModelManager() {
        modelList.add(new OfflineModel("Gemma 2B", "2.5 GB", "Fast local assistant model", false));
        modelList.add(new OfflineModel("Phi-3 Mini", "3.9 GB", "Reasoning + chat + local QA", true));
        modelList.add(new OfflineModel("Mistral 7B", "7.1 GB", "High quality general AI", false));
        modelList.add(new OfflineModel("Qwen 2.5 3B", "2.1 GB", "Low latency model", false));
        modelList.add(new OfflineModel("Llama 3.2 1B", "1.4 GB", "Very fast on-device model", true));
    }

    public List<OfflineModel> getAvailableModels() {
        return new ArrayList<>(modelList);
    }

    public void downloadModel(OfflineModel model, Runnable onComplete) {
        model.setDownloaded(true);
        uiHandler.post(onComplete);
    }
}
