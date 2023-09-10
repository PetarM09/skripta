package com.example.codesubmitter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtility {
    public static void zipFolders(File sourceFolder, String outputZipFilePath) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(outputZipFilePath);
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            addFolderToZip(sourceFolder, sourceFolder.getName(), zos);

            zos.finish();
        }
    }


    private static void addFolderToZip(File folder, String parentPath, ZipOutputStream zos) throws IOException {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    addFolderToZip(file, parentPath + "/" + file.getName(), zos);
                } else {
                    try (FileInputStream fis = new FileInputStream(file)) {
                        String entryName = parentPath + "/" + file.getName();
                        ZipEntry zipEntry = new ZipEntry(entryName);
                        zos.putNextEntry(zipEntry);

                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = fis.read(buffer)) > 0) {
                            zos.write(buffer, 0, length);
                        }

                        zos.closeEntry();
                    }
                }
            }
        } else {
            System.err.println("Nema fajlova u direktorijumu: " + folder.getAbsolutePath());
        }
    }


}


