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

public class AgentSkillsActivity extends AppCompatActivity {

    private RecyclerView skillsList;
    private Button enable;
    private TextView skillInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_skills);

        skillsList = findViewById(R.id.skillsList);
        enable = findViewById(R.id.enableButton);
        skillInfo = findViewById(R.id.skillInfo);

        List<String> skills = new ArrayList<>();
        skills.add("Wikipedia - Fact grounding");
        skills.add("Calculator - Math operations");
        skills.add("Web Search - Query information");
        skills.add("Weather - Local conditions");
        skills.add("Unit Converter - Conversions");

        SkillAdapter adapter = new SkillAdapter(skills);
        skillsList.setLayoutManager(new LinearLayoutManager(this));
        skillsList.setAdapter(adapter);

        skillInfo.setText("Skills enhance AI capabilities. Enable the ones you need.");
        enable.setOnClickListener(v -> Toast.makeText(this, "Skills enabled!", Toast.LENGTH_SHORT).show());
    }
}

class SkillAdapter extends RecyclerView.Adapter<SkillAdapter.ViewHolder> {
    private final List<String> skills;

    SkillAdapter(List<String> skills) {
        this.skills = skills;
    }

    @Override
    public ViewHolder onCreateViewHolder(android.view.ViewGroup parent, int viewType) {
        android.view.View view = android.view.LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_skill, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.skillName.setText(skills.get(position));
    }

    @Override
    public int getItemCount() {
        return skills.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        android.widget.TextView skillName;
        ViewHolder(android.view.View v) {
            super(v);
            skillName = v.findViewById(R.id.skillName);
        }
    }
}
