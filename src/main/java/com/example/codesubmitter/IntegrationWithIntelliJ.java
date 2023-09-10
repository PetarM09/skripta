package com.example.codesubmitter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;




public class IntegrationWithIntelliJ {
    private static IntegrationWithIntelliJ instance = null;
    private File tempProjectDir;
    private File desktopDir;

    public static IntegrationWithIntelliJ getInstance(){
        if(instance == null)
            instance = new IntegrationWithIntelliJ();
        return instance;
    }
    public void startIntelliJIDEA(String firstName, String lastName) {
        try {
            desktopDir = new File("");
            tempProjectDir = new File("");
            // Putanja do izvršne datoteke IntelliJ IDEA na učenikovom računaru
            String intelliJPath = "/Applications/IntelliJ IDEA.app/Contents/MacOS/idea";

            // Kreirajte direktorijum sa imenom i prezimenom učenika na Desktopu
            desktopDir = new File(System.getProperty("user.home"), "Desktop");
            tempProjectDir = createTempProjectDir(desktopDir, firstName, lastName);

            // Preuzmite PDF fajl i sačuvajte ga u isti direktorijum kao projekat
            String pdfUrl = "https://drive.google.com/uc?id=1Eu2U2r9OOMu-3vt4HQxh3OEr_EFkQgW6"; // Zamijenite VAŠ_ID_FAJLA sa stvarnim ID-em fajla
            downloadPDF(pdfUrl, tempProjectDir);

            // Pokreni IntelliJ IDEA sa praznim projektom
            ProcessBuilder processBuilder = new ProcessBuilder(intelliJPath, tempProjectDir.getAbsolutePath());
            Process process = processBuilder.start();

            // Sačekajte da se proces završi, ako je potrebno
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("IntelliJ IDEA je uspešno pokrenut sa praznim projektom na Desktopu.");
            } else {
                System.err.println("Greška prilikom pokretanja IntelliJ IDEA.");
            }

            // Opciono: Očistite privremeni direktorijum nakon što završite s radom
            //deleteTempProjectDir(tempProjectDir);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static File createTempProjectDir(File parentDir, String firstName, String lastName) throws IOException {
        // Kreirajte privremeni direktorijum za prazan projekat na Desktopu
        String folderName = firstName + "_" + lastName;
        File userDir = new File(parentDir, folderName);
        if (!userDir.exists()) {
            userDir.mkdir();
            File srcDir = new File(userDir, "src");
            srcDir.mkdir();
        }

        return userDir;
    }

    public void deleteTempProjectDir(File dir) {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    deleteTempProjectDir(file);
                }
            }
        }
        dir.delete();
    }

    public void zipThenDelete(File dir){
        try {
            ZipUtility.zipFolders(dir, desktopDir.getAbsolutePath() + File.separator + "zipovan_folder.zip");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        deleteTempProjectDir(dir);
    }

    private static void downloadPDF(String pdfUrl, File destinationDir) throws IOException {
        try {
            URL url = new URL(pdfUrl);
            try (InputStream in = url.openStream()) {
                String fileName = "Grupa A.pdf"; // Naziv fajla koji želite da sačuvate
                Path destination = Paths.get(destinationDir.getAbsolutePath(), fileName);
                Files.copy(in, destination, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("PDF fajl je uspešno preuzet.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Greška prilikom preuzimanja PDF fajla.");
        }
    }
    public File getDir(){
        return tempProjectDir;
    }
}
