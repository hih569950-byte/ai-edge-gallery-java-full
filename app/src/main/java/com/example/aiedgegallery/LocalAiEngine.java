package com.example.aiedgegallery;

import java.util.HashMap;
import java.util.Map;

public class LocalAiEngine {
    private final Map<String, String> memory = new HashMap<>();

    public String answer(String question) {
        String q = question.toLowerCase().trim();
        memory.put("name", "AI Companion");

        if (q.contains("hello") || q.contains("hi")) return "Hello! I am your offline AI assistant. How may I help you today?";
        if (q.contains("how are you")) return "I am fine and ready to help. I can chat, answer, and help with tasks offline.";
        if (q.contains("voice") || q.contains("calling")) return "Voice calling mode is available. I can listen, speak, and respond naturally.";
        if (q.contains("model") || q.contains("download")) return "You can download local models from the model manager. Once downloaded, they are used offline.";
        if (q.contains("love") || q.contains("girlfriend") || q.contains("relationship")) return "I can be a friendly, caring companion. I will listen and respond respectfully and warmly.";
        if (q.contains("joke")) return "Why did the AI go to the park? Because it wanted to process some fresh thoughts!";
        if (q.contains("time")) return "I can help with your reminders, tasks, and general questions in real time.";
        if (q.contains("photo") || q.contains("image")) return "Image understanding can be enabled with vision mode and on-device analysis.";
        if (q.contains("video")) return "Video search and moment detection can be processed with your local media library.";

        return "I understand your request: '" + question + "'. This offline AI can answer questions, assist in conversation, and support voice interactions.";
    }
}
