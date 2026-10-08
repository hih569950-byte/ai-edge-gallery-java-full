package com.example.aiedgegallery;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MobileActionsActivity extends AppCompatActivity {

    private TextView actionLog;
    private Button timer, alarm, sms, call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_actions);

        actionLog = findViewById(R.id.actionLog);
        timer = findViewById(R.id.timerButton);
        alarm = findViewById(R.id.alarmButton);
        sms = findViewById(R.id.smsButton);
        call = findViewById(R.id.callButton);

        actionLog.setText("Mobile Actions:\n• Set timers\n• Set alarms\n• Send SMS\n• Make calls\n• Open apps\n\nPowered by on-device AI.");

        timer.setOnClickListener(v -> setTimer());
        alarm.setOnClickListener(v -> setAlarm());
        sms.setOnClickListener(v -> sendSMS());
        call.setOnClickListener(v -> makeCall());
    }

    private void setTimer() {
        Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER);
        intent.putExtra(AlarmClock.EXTRA_LENGTH, 10);
        intent.putExtra(AlarmClock.EXTRA_SKIP_UI, true);
        startActivity(intent);
    }

    private void setAlarm() {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
        intent.putExtra(AlarmClock.EXTRA_HOUR, 7);
        intent.putExtra(AlarmClock.EXTRA_MINUTES, 30);
        startActivity(intent);
    }

    private void sendSMS() {
        Toast.makeText(this, "SMS action: Ready to send", Toast.LENGTH_SHORT).show();
    }

    private void makeCall() {
        Toast.makeText(this, "Call action: Ready to call", Toast.LENGTH_SHORT).show();
    }
}
