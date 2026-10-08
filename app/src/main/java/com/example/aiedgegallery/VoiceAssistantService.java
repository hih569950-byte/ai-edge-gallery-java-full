package com.example.aiedgegallery;

import android.content.Context;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class VoiceAssistantService implements TextToSpeech.OnInitListener {
    private final Context context;
    private final TextToSpeech tts;
    private final LocalAiEngine aiEngine = new LocalAiEngine();

    public VoiceAssistantService(Context context) {
        this.context = context;
        this.tts = new TextToSpeech(context, this);
    }

    public void speak(String text) {
        if (tts != null) {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "voice_assistant");
        }
    }

    public String processVoice(String spokenText) {
        String answer = aiEngine.answer(spokenText);
        speak(answer);
        return answer;
    }

    public Intent buildSpeechIntent() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to your AI friend");
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        return intent;
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            tts.setLanguage(Locale.US);
        }
    }

    public void shutdown() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
    }
}
