package com.example.battleship.control;

import com.example.battleship.model.BattleShip;
import com.example.battleship.model.Player;
import com.example.battleship.model.SerializableFileHandler;
import com.example.battleship.view.GameStageView;
import com.example.battleship.view.IntroStageView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador para la vista inicial del juego Battleship.
 *
 * <p>Gestiona la lógica de los botones y la interacción de usuario con los elementos de la vista
 * {@link IntroStageView}, como la creación de nuevas partidas y la carga de partidas guardadas.</p>
 *
 * @author Juan Pablo Charry Ramirez
 * @author Juan Esteban Rodriguez Valencia
 * @version 1.0
 */
public class IntroStageController {

    /**
     * Manejador para la serialización y deserialización de archivos.
     */
    private SerializableFileHandler serializableFileHandler;

    /**
     * Lista de nombres de archivos correspondientes a partidas guardadas.
     */
    private List<String> fileNames;

    /**
     * Selección actual del usuario en el combo box de partidas guardadas.
     */
    private String selection;

    /**
     * ComboBox para mostrar las partidas guardadas.
     */
    @FXML
    ComboBox<String> partidasGuardadas;

    /**
     * Campo de texto para que el usuario ingrese su nombre de usuario.
     */
    @FXML
    TextField usernameField;

    /**
     * Inicializador del controlador.
     * <p>
     * Este método se ejecuta automáticamente al cargar la vista y configura
     * el manejador de archivos y las partidas guardadas en el ComboBox.
     * </p>
     */
    @FXML
    private void initialize() {
        serializableFileHandler = new SerializableFileHandler();
        fileNames = getFileNames("savedgames");
        fileNames.removeIf(name -> name.endsWith("Machine"));
        partidasGuardadas.getItems().addAll(fileNames);
    }

    /**
     * Maneja la acción del botón "Nueva Partida".
     * <p>
     * Verifica que el campo de nombre de usuario no esté vacío y configura el nombre
     * en la instancia de {@link BattleShip} a través del controlador.
     * </p>
     *
     * @param event el evento asociado al botón
     */
    @FXML
    void newGameButton(ActionEvent event) {
        String username = usernameField.getText();
        if (username == null || username.trim().isEmpty()) {
            System.out.println("El nombre de usuario no puede estar vacío.");
            return;
        }

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

    /**
     * Maneja la acción del botón "Continuar Partida".
     * <p>
     * Carga las partidas guardadas seleccionadas desde el ComboBox y actualiza
     * el estado de {@link BattleShip} con los datos del jugador y la máquina.
     * </p>
     *
     * @param event el evento asociado al botón
     */
    @FXML
    void continueButton(ActionEvent event) {
        selection = partidasGuardadas.getValue();
        if (selection == null) {
            System.out.println("El nombre de usuario no puede estar vacío.");
            return;
        } else {
            try {
                GameStageView gameStage = GameStageView.getInstance();
                Player playerGame = (Player) serializableFileHandler.deserialize(selection);
                Player machineGame = (Player) serializableFileHandler.deserialize(selection + "Machine");
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
                System.out.println("Ocurrió un error al cargar la partida guardada.");
            }
        }
    }

    /**
     * Obtiene la lista de nombres de archivos dentro de un directorio especificado.
     *
     * @param folderPath la ruta del directorio donde buscar archivos
     * @return una lista de nombres de archivos en el directorio
     */
    public static List<String> getFileNames(String folderPath) {
        List<String> fileNames = new ArrayList<>();
        File folder = new File(folderPath);

        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
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
