package com.example.battleship.control;

import com.example.battleship.view.GameStageView;
import com.example.battleship.view.IntroStageView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class IntroStageController {

    private boolean NewGamePressed = false;

    @FXML
    VBox introScreenContainer;

    @FXML
    private void initialize() {
        createOpeningStage();
    }


    private void createOpeningStage() {
        Label Title = new Label("Battleship");
        Title.setId("titleLabel");
        Button newGameButton = new Button("New Game");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Ingrese su nombre de usuario");
        usernameField.setVisible(false);
        Button startGameButton = new Button("Start");
        startGameButton.setVisible(false);

        Button closeButton = new Button("Close");
        Button continueButton = new Button("Continue");
        introScreenContainer.getChildren().addAll(Title, newGameButton,usernameField,startGameButton, continueButton,closeButton);

        newGameButton.setOnAction(e -> {
            if (!NewGamePressed) {
                usernameField.setVisible(true);
                startGameButton.setVisible(true);
                NewGamePressed = true;
            }   else {
                usernameField.setVisible(false);
                startGameButton.setVisible(false);
                NewGamePressed = false;
            }
        });

        startGameButton.setOnAction(e -> {
            String username = usernameField.getText();
            if (username == null || username.trim().isEmpty()) {
                System.out.println("El nombre de usuario no puede estar vacío.");
                return;
            }

            // Configurar el nombre en el objeto BattleShip a través del controlador
            try {
                GameStageView gameStage = GameStageView.getInstance();
                if (gameStage != null && gameStage.getGameController() != null && gameStage.getGameController().battleShip != null) {
                    gameStage.getGameController().battleShip.setName(username);
                    IntroStageView.deleteInstance();
                    System.out.println("Nombre configurado correctamente: " + username);
                } else {
                    System.out.println("No se pudo acceder a GameStageView, GameController o BattleShip.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("Ocurrió un error al configurar el nombre del usuario.");
            }
        });
    }
}
