package com.example.battleship.model;

import javafx.scene.control.Alert;

public class AlertHandler {
    public AlertHandler() {}

    public Alert coordenadasInvalidasOutOfBounds() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Advertencia");
        alert.setHeaderText("Cuidado");
        alert.setContentText("Coordenadas invalidas, barco sale de los limites");
        alert.showAndWait();
        return alert;
    }

    public Alert coordenadasInvalidasBoatOverBoat() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Advertencia");
        alert.setHeaderText("Cuidado");
        alert.setContentText("Coordenadas invalidas, ya hay un bote en las proximidades");
        return alert;
    }

    public Alert coordenadasNoValidas() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Advertencia");
        alert.setHeaderText("Cuidado");
        alert.setContentText("Coordenadas invalidas");
        return alert;
    }

    public Alert instrucciones() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Instrucciones de juego");
        alert.setContentText(
                "1. Empiece por posicionar todos los barcos en la matriz inferior, seleccionando el tipo de barco que quiere escoger, las coordenas y la direccion en " +
                        "la que desea posicionarlo, Vertical o Horizontal, luego unda el boton de la dercha para posicionarlo, haga esto con todos los botes" +
                        "2. Una vez posicionados todos los barcos, en la parte superior derecha, ingrese las coordenadas a las que quiere disparar" +
                        "la maquina respondera disparando de vuelta, dispare hasta acabar con todos los barcos" +
                        "3. Debajo del radar, puede usar el boton para ver la posicion de los botes del enemigo");
        return alert;
    }
}

