package com.example.battleship.model;

import javafx.scene.control.Alert;

/**
 * Clase que gestiona la creación y configuración de alertas en la aplicación Battleship.
 *
 * <p>Proporciona métodos para generar diferentes tipos de alertas relacionadas con las
 * validaciones de coordenadas y las instrucciones del juego.</p>
 *
 * @author Juan Pablo Charry Ramirez
 * @author Juan Esteban Rodriguez Valencia
 * @version 1.0
 */
public class AlertHandler {

    /**
     * Constructor vacío de la clase {@code AlertHandler}.
     *
     * <p>Se utiliza para crear una instancia de la clase sin configuraciones iniciales.</p>
     */
    public AlertHandler() {}

    /**
     * Crea y muestra una alerta de error cuando las coordenadas ingresadas están fuera de los límites.
     *
     * @return una instancia de {@link Alert} configurada como un error con el mensaje correspondiente
     */
    public Alert coordenadasInvalidasOutOfBounds() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Advertencia");
        alert.setHeaderText("Cuidado");
        alert.setContentText("Coordenadas inválidas, el barco sale de los límites");
        alert.showAndWait();
        return alert;
    }

    /**
     * Crea una alerta de error cuando las coordenadas ingresadas interfieren con otro barco.
     *
     * @return una instancia de {@link Alert} configurada como un error con el mensaje correspondiente
     */
    public Alert coordenadasInvalidasBoatOverBoat() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Advertencia");
        alert.setHeaderText("Cuidado");
        alert.setContentText("Coordenadas inválidas, ya hay un barco en las proximidades");
        return alert;
    }

    /**
     * Crea una alerta de error genérica para indicar que las coordenadas ingresadas no son válidas.
     *
     * @return una instancia de {@link Alert} configurada como un error con el mensaje correspondiente
     */
    public Alert coordenadasNoValidas() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Advertencia");
        alert.setHeaderText("Cuidado");
        alert.setContentText("Coordenadas inválidas");
        return alert;
    }

    /**
     * Crea una alerta informativa que muestra las instrucciones del juego.
     *
     * <p>Proporciona información detallada sobre cómo posicionar barcos, realizar disparos
     * y utilizar las herramientas del juego.</p>
     *
     * @return una instancia de {@link Alert} configurada como informativa con las instrucciones del juego
     */
    public Alert instrucciones() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Instrucciones de juego");
        alert.setContentText(
                "1. Empiece por posicionar todos los barcos en la matriz inferior, seleccionando el tipo de barco que desea escoger, las coordenadas y la dirección en " +
                        "la que desea posicionarlo, Vertical o Horizontal. Luego haga clic en el botón de la derecha para posicionarlo. Haga esto con todos los barcos. " +
                        "2. Una vez posicionados todos los barcos, en la parte superior derecha, ingrese las coordenadas a las que quiere disparar. " +
                        "La máquina responderá disparando de vuelta. Dispare hasta acabar con todos los barcos. " +
                        "3. Debajo del radar, puede usar el botón para ver la posición de los barcos del enemigo.");
        return alert;
    }
}
