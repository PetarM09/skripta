package com.example.codesubmitter;

public class ActivityMonitor {
    /*private Robot robot;
    private String currentApp;
    private User32 user32;
    private User32.HOOK hook;

    public void start(Stage primaryStage) {
        robot = new Robot();
        currentApp = "";
        user32 = User32.INSTANCE;


        // Postavite globalni hook za praćenje promene fokusa prozora
        hook = user32.SetWinEventHook(User32.EVENT_SYSTEM_FOREGROUND, User32.EVENT_SYSTEM_FOREGROUND, null, (hwnd, event, hwnd2, idObject, idChild, dwEventThread, dwmsEventTime) -> {
            String newApp = getActiveApplication();
            if (!newApp.equals(currentApp)) {
                currentApp = newApp;
                captureScreenshot(currentApp);
            }
        }, 0, 0, User32.WINEVENT_OUTOFCONTEXT);

        // Zatvorite hook kada se aplikacija zatvori
        primaryStage.setOnCloseRequest(event -> {
            user32.UnhookWinEvent(hook);
            Platform.exit();
        });

        // Pokrenite aplikaciju
        primaryStage.setTitle("Screen Activity Monitor");
        primaryStage.show();
    }

    private String getActiveApplication() {
        char[] buffer = new char[1024];
        WinDef.HWND hwnd = user32.GetForegroundWindow();
        user32.GetWindowText(hwnd, buffer, buffer.length);
        return Native.toString(buffer);
    }

    private void captureScreenshot(String appName) {
        // Uhvatite trenutni sadržaj ekrana
        WritableImage screenshot = robot.getScreenCapture(Screen.getPrimary().getBounds());

        // Kreirajte folder za screenshot-ove ako ne postoji
        File folder = new File("screenshots");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // Generišite ime fajla za screenshot na osnovu vremena i aplikacije
        String fileName = appName + "_" + System.currentTimeMillis() + ".png";

        // Snimite screenshot u odgovarajući folder
        File screenshotFile = new File(folder, fileName);

        // Implementirajte logiku za snimanje screenshot-a
        // Na primer, možete koristiti ImageIO za snimanje slike u datoteku
        // Ovde je samo prikaz kako biste to mogli uraditi:
        /*
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(screenshot, null), "png", screenshotFile);
            System.out.println("Screenshot je uspešno snimljen: " + screenshotFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Greška prilikom snimanja screenshot-a.");
        }

    }*/
}
