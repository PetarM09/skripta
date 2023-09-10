package com.example.codesubmitter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtility {
    public static void zipFolders(File sourceFolder1, String zipFilePath) throws IOException {
        System.out.println("DAAA");
        try (FileOutputStream fos = new FileOutputStream(zipFilePath);
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            addFolderToZip(sourceFolder1, zos);
            //addFolderToZip(sourceFolder2, zos);

            zos.finish();
        }
    }

    private static void addFolderToZip(File folderPath, ZipOutputStream zos) throws IOException {
        for (File file : Objects.requireNonNull(folderPath.listFiles())) {
            if (file.isDirectory()) {
                addFolderToZip(new File(file.getAbsolutePath()), zos);
            } else {
                try (FileInputStream fis = new FileInputStream(file)) {
                    String entryName = file.getName(); // Koristiće se originalno ime fajla
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
    }
}


