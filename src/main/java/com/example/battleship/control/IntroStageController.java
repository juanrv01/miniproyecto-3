package com.example.battleship.control;

import com.example.battleship.view.GameStageView;
import com.example.battleship.view.IntroStageView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class IntroStageController {

    private boolean NewGamePressed = false;

    @FXML
    ComboBox partidasGuardadas;

    @FXML
    TextField usernameField;

    @FXML
    private void initialize() {
    }

    @FXML
    void newGameButton(ActionEvent event) {
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
    }

    @FXML
    void continueButton(ActionEvent event) {

    }
    
}
