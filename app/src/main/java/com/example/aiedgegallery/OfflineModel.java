package com.example.aiedgegallery;

public class OfflineModel {
    private final String name;
    private final String size;
    private final String description;
    private boolean downloaded;

    public OfflineModel(String name, String size, String description, boolean downloaded) {
        this.name = name;
        this.size = size;
        this.description = description;
        this.downloaded = downloaded;
    }

    public String getName() { return name; }
    public String getSize() { return size; }
    public String getDescription() { return description; }
    public boolean isDownloaded() { return downloaded; }
    public void setDownloaded(boolean downloaded) { this.downloaded = downloaded; }
}
