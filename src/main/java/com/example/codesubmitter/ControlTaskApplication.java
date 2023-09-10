package com.example.codesubmitter;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ControlTaskApplication extends BorderPane {
    private Button startButton;
    private Button submitButton;
    private TextField firstNameField;
    private TextField lastNameField;
    private ComboBox<String> termComboBox;

    public ControlTaskApplication(Stage primaryStage) {
        initializeUI();
    }

    public void initializeUI() {
        // Kreiranje elemenata korisničkog interfejsa
        Label nameLabel = new Label("Unesite ime:");
        firstNameField = new TextField();
        Label lastNameLabel = new Label("Unesite prezime:");
        lastNameField = new TextField();
        Label termLabel = new Label("Odaberite termin:");
        termComboBox = new ComboBox<>();
        termComboBox.getItems().addAll("Termin 1", "Termin 2");
        startButton = new Button("Otpocni rad");
        submitButton = new Button("Predaj rad");
        submitButton.setDisable(true); // Onemogući dugme za predaju na početku

        // Dodavanje akcija na dugmadi
        startButton.setOnAction(e -> handleStartButtonClick());
        submitButton.setOnAction(e -> handleSubmitButtonClick());

        // Kreiranje rasporeda i postavljanje elemenata na ekran
        VBox layout = new VBox(10);
        layout.getChildren().addAll(
                nameLabel, firstNameField,
                lastNameLabel, lastNameField,
                termLabel, termComboBox,
                createButtonBox()
        );
        layout.setAlignment(Pos.CENTER);
        setCenter(layout);
    }

    private HBox createButtonBox() {
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(startButton, submitButton);
        return buttonBox;
    }

    private void handleStartButtonClick() {
        // Dobijte ime i prezime iz polja za unos
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();

        // Proverite da li su ime i prezime uneti
        if (!firstName.isEmpty() && !lastName.isEmpty()) {
            // Ako su uneti, omogućite dugme za predaju i onemogućite dugme za otpočinjanje rada
            startButton.setDisable(true);
            submitButton.setDisable(false);

            // Pozovite drugu klasu i prosledite ime i prezime
            IntegrationWithIntelliJ.getInstance().startIntelliJIDEA(firstName, lastName);
        } else {
            // Ako nisu uneti, prikažite upozorenje
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Upozorenje");
            alert.setHeaderText(null);
            alert.setContentText("Unesite svoje ime i prezime pre otpočinjanja rada.");

            alert.showAndWait();
        }

    }


    private void handleSubmitButtonClick() {
        // Implementirajte šta treba da se desi kada se klikne na "Predaj rad"
        // Na primer, sakupite podatke iz polja i odabrane vrednosti iz ComboBox-a.
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String selectedTerm = termComboBox.getValue();
        IntegrationWithIntelliJ.getInstance().deleteTempProjectDir(IntegrationWithIntelliJ.getInstance().getDir());

        // Ovde možete dodati kod za pakovanje i slanje podataka na Google Drive.
        // Takođe, možete ponovo onemogućiti dugme za predaju ako je potrebno.
    }


}
