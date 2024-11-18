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
}

