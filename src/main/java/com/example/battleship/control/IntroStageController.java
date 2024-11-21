package com.example.battleship.control;

import com.example.battleship.model.BattleShip;
import com.example.battleship.model.Player;
import com.example.battleship.model.SerializableFileHandler;
import com.example.battleship.view.GameStageView;
import com.example.battleship.view.IntroStageView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IntroStageController {
    private SerializableFileHandler serializableFileHandler;
    private List<String> fileNames;
    private String selection;

    @FXML
    ComboBox<String> partidasGuardadas;

    @FXML
    TextField usernameField;

    @FXML
    private void initialize() {
        serializableFileHandler = new SerializableFileHandler();
        fileNames = getFileNames("savedgames");
        fileNames.removeIf(name -> name.endsWith("Machine"));
        partidasGuardadas.getItems().addAll(fileNames);
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
        selection = partidasGuardadas.getValue();
        if (selection == null) {
            System.out.println("El nombre de usuario no puede estar vacío.");
            return;
        }  else {
            try {
                GameStageView gameStage = GameStageView.getInstance();
                Player playerGame = (Player) serializableFileHandler.deserialize(selection);
                Player machineGame = (Player) serializableFileHandler.deserialize(selection+"Machine");
                if (gameStage != null && gameStage.getGameController() != null && gameStage.getGameController().battleShip != null) {
                    gameStage.getGameController().setPlayers(playerGame, machineGame);
                    gameStage.getGameController().showBoats();
                    gameStage.getGameController().showHits();
                    gameStage.getGameController().setAmountBoatsLabels();
                    IntroStageView.deleteInstance();
                } else {
                    System.out.println("No se pudo acceder a GameStageView, GameController o BattleShip.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("Ocurrió un error al cargar la");
            }
        }

    }


    public static List<String> getFileNames(String folderPath) {
        List<String> fileNames = new ArrayList<>();
        File folder = new File(folderPath);

        // Verificar si la ruta es válida y es una carpeta
        if (folder.exists() && folder.isDirectory()) {
            // Obtener la lista de archivos y carpetas dentro del directorio
            File[] files = folder.listFiles();

            if (files != null) {
                // Recorrer los archivos y agregar sus nombres a la lista
                for (File file : files) {
                    if (file.isFile()) { // Solo añadir archivos, no carpetas
                        fileNames.add(file.getName());
                    }
                }
            }
        } else {
            System.out.println("La ruta especificada no es una carpeta válida.");
        }

        return fileNames;
    }

}
