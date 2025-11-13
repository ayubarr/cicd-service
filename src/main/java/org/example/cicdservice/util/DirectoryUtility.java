package org.example.cicdservice.util;

import lombok.experimental.UtilityClass;

import java.io.File;

@UtilityClass
public class DirectoryUtility {
    @SuppressWarnings("all")
    public void deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        directoryToBeDeleted.delete();
    }
}
