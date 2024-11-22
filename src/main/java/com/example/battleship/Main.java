package com.example.battleship;

import com.example.battleship.view.IntroStageView;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase principal de la aplicación Battleship.
 * Extiende la clase {@link Application} de JavaFX para gestionar el ciclo de vida de la aplicación.
 *
 * <p>Esta clase inicia la aplicación y carga la vista inicial {@link IntroStageView}.</p>
 *
 * @author Juan Pablo Charry Ramirez
 * @author Juan Esteban Rodriguez Valencia
 * @version 1.0
 */
public class Main extends Application {

    /**
     * Método principal de la aplicación.
     *
     * <p>Este método sirve como punto de entrada para la aplicación Java.</p>
     *
     * @param args argumentos de línea de comandos pasados al programa
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Método que se ejecuta al iniciar la aplicación.
     *
     * <p>Este método es parte del ciclo de vida de JavaFX. Carga la instancia inicial de
     * la vista principal utilizando {@link IntroStageView#getInstance()}.</p>
     *
     * @param primaryStage la ventana principal proporcionada por JavaFX
     * @throws IOException si ocurre un error al cargar la vista inicial
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        IntroStageView.getInstance();
    }
}

