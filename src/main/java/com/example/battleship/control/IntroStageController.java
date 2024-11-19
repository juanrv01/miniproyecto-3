package com.example.battleship.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class IntroStageController {

    @FXML
    VBox introScreenContainer;

    @FXML
    private void initialize() {

    }

    @FXML
    void newGameButton(ActionEvent event) {
        
    }

    private void createOpeningStage() {
        Label Title = new Label("Battleship");
        Button newGameButton = new Button("New Game");
        Button closeButton = new Button("Close");
        Button continueButton = new Button("Continue");

        introScreenContainer.getChildren().addAll(Title, newGameButton, closeButton, continueButton);
    }
}
