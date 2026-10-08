package com.example.aiedgegallery;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PromptLabActivity extends AppCompatActivity {

    private EditText promptInput;
    private SeekBar temperature;
    private TextView tempValue, result;
    private Button test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prompt_lab);

        promptInput = findViewById(R.id.promptInput);
        temperature = findViewById(R.id.temperatureBar);
        tempValue = findViewById(R.id.tempValue);
        result = findViewById(R.id.resultText);
        test = findViewById(R.id.testButton);

        temperature.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tempValue.setText(String.format("Temperature: %.1f", progress / 10.0f));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        test.setOnClickListener(v -> testPrompt());
    }

    private void testPrompt() {
        String prompt = promptInput.getText().toString();
        if (prompt.isEmpty()) {
            Toast.makeText(this, "Enter a prompt", Toast.LENGTH_SHORT).show();
        } else {
            result.setText("Response:\n" + prompt + "\n\n(Generated with on-device LLM at current temperature setting)");
        }
    }
}
