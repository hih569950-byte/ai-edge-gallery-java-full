package com.example.aiedgegallery;

public class FeatureCard {
    private final String title;
    private final String subtitle;

    public FeatureCard(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }

    public String getTitle() { return title; }
    public String getSubtitle() { return subtitle; }
}
