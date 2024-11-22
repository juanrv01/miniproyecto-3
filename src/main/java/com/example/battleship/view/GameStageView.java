package com.example.battleship.view;

import com.example.battleship.control.GameStageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase que representa la vista principal del juego Battleship.
 *
 * <p>Extiende {@link Stage} y se encarga de inicializar y mostrar la interfaz gráfica
 * del juego utilizando JavaFX. También gestiona la relación con el controlador
 * {@link GameStageController}.</p>
 *
 * @author Juan Pablo Charry Ramirez
 * @author Juan Esteban Rodriguez Valencia
 * @version 1.0
 */
public class GameStageView extends Stage {

    /**
     * Controlador asociado a la vista del juego.
     */
    private GameStageController controller;

    /**
     * Constructor de la clase {@code GameStageView}.
     *
     * <p>Inicializa la vista cargando el archivo FXML {@code battleship-game-view.fxml},
     * enlaza el controlador asociado y aplica la hoja de estilos {@code GameStyles.css}.
     * Configura el título de la ventana, evita que sea redimensionable y la muestra en pantalla.</p>
     *
     * @throws IOException si ocurre un error al cargar los recursos FXML o CSS
     */
    public GameStageView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/battleship/battleship-game-view.fxml"));
        Parent root = fxmlLoader.load();
        controller = fxmlLoader.getController();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/com/example/battleship/GameStyles.css").toExternalForm());
        setScene(scene);
        setTitle("Battleship Game");
        setResizable(false);
        show();
    }

    /**
     * Devuelve el controlador de la vista del juego.
     *
     * <p>Permite acceder a la lógica del juego gestionada por {@link GameStageController}.</p>
     *
     * @return el controlador de la vista del juego
     */
    public GameStageController getGameController() {
        return controller;
    }

    /**
     * Clase estática interna que implementa el patrón Singleton para {@link GameStageView}.
     */
    private static class GameStageViewHolder {
        /**
         * Instancia única de {@link GameStageView}.
         */
        private static GameStageView INSTANCE;
    }

    /**
     * Obtiene la instancia única de {@link GameStageView}.
     *
     * <p>Si no existe una instancia previa, se crea una nueva y se almacena en el Singleton.
     * Si ya existe, se retorna la instancia existente.</p>
     *
     * @return la instancia única de {@link GameStageView}
     * @throws IOException si ocurre un error al crear la instancia inicial
     */
    public static GameStageView getInstance() throws IOException {
        GameStageView.GameStageViewHolder.INSTANCE = GameStageView.GameStageViewHolder.INSTANCE != null ? GameStageView.GameStageViewHolder.INSTANCE : new GameStageView();
        return GameStageView.GameStageViewHolder.INSTANCE;
    }

    /**
     * Elimina la instancia actual de {@link GameStageView}.
     *
     * <p>Cierra la ventana asociada a la instancia y libera la referencia del Singleton.</p>
     */
    public static void deleteInstance() {
        GameStageView.GameStageViewHolder.INSTANCE.close();
    }
}
