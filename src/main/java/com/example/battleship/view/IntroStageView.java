package com.example.battleship.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase que representa la vista inicial del juego Battleship.
 *
 * <p>Esta clase extiende {@link Stage} y se encarga de cargar y mostrar la interfaz
 * gráfica de la introducción del juego utilizando JavaFX.</p>
 *
 * @author Juan Pablo Charry Ramirez
 * @author Juan Esteban Rodriguez Valencia
 * @version 1.0
 */
public class IntroStageView extends Stage {

    /**
     * Constructor de la clase {@code IntroStageView}.
     *
     * <p>Inicializa la vista cargando el archivo FXML {@code intro-game-view.fxml} y aplicando
     * la hoja de estilos {@code GameStyles.css}. Configura el título, evita que la ventana sea
     * redimensionable y la muestra en pantalla.</p>
     *
     * @throws IOException si ocurre un error al cargar los recursos FXML o CSS
     */
    public IntroStageView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/battleship/intro-game-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/com/example/battleship/GameStyles.css").toExternalForm());
        setScene(scene);
        setTitle("Battleship Game");
        setResizable(false);
        show();
    }

    /**
     * Clase estática interna que implementa el patrón Singleton para {@link IntroStageView}.
     */
    private static class IntroStageHolder {
        /**
         * Instancia única de {@link IntroStageView}.
         */
        private static IntroStageView INSTANCE;
    }

    /**
     * Obtiene la instancia única de {@link IntroStageView}.
     *
     * <p>Si no existe una instancia previa, se crea una nueva y se almacena en el Singleton.
     * Si ya existe, se retorna la instancia existente.</p>
     *
     * @return la instancia única de {@link IntroStageView}
     * @throws IOException si ocurre un error al crear la instancia inicial
     */
    public static IntroStageView getInstance() throws IOException {
        IntroStageView.IntroStageHolder.INSTANCE = IntroStageView.IntroStageHolder.INSTANCE != null ? IntroStageView.IntroStageHolder.INSTANCE : new IntroStageView();
        return IntroStageHolder.INSTANCE;
    }

    /**
     * Elimina la instancia actual de {@link IntroStageView}.
     *
     * <p>Cierra la ventana asociada a la instancia y libera la referencia del Singleton.</p>
     */
    public static void deleteInstance() {
        IntroStageView.IntroStageHolder.INSTANCE.close();
    }
}
