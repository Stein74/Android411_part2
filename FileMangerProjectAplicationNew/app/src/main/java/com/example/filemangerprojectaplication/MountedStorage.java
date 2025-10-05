package com.example.filemangerprojectaplication;

import java.io.File;

public class MountedStorage {

    private String name;
    private File path;

    public String getName() {
        return name;
    }

    public File getPath() {
        return path;
    }

    public MountedStorage(String name, File path) {
        this.name = name;
        this.path = path;
    }
}
