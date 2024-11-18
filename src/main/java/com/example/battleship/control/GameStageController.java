package com.example.battleship.control;

import com.example.battleship.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;




public class GameStageController {
    BattleShip battleShip = new BattleShip();
    Boat selectedBoat;
    AlertHandler alertHandler = new AlertHandler();

    @FXML
    TextField PlaceBoatXCoordenate,PlaceBoatYCoordenate,ShootXCoordenate,ShootYCoordenate;

    @FXML
    Label fragataLabel,destructorLabel,submarinoLabel,portaAvionesLabel;

    @FXML
    GridPane playerBoatsGrid;

    @FXML
    HBox coordinatePlaceHbox,ShootContainerHBox;


    @FXML
    void initialize() {
        fragataLabel.textProperty().bind(battleShip.getPlayer().getBoard().getFragataCountLabel().asString());
        destructorLabel.textProperty().bind(battleShip.getPlayer().getBoard().getDestructorCountLabel().asString());
        submarinoLabel.textProperty().bind(battleShip.getPlayer().getBoard().getSubmarinoCountLabel().asString());
        portaAvionesLabel.textProperty().bind(battleShip.getPlayer().getBoard().getPortaavionesCountLabel().asString());

        PlaceBoatXCoordenate = createCoordinatesTxtf();
        PlaceBoatYCoordenate= createCoordinatesTxtf();
        ShootXCoordenate= createCoordinatesTxtf();
        ShootYCoordenate= createCoordinatesTxtf();

        coordinatePlaceHbox.getChildren().add(0,PlaceBoatXCoordenate);
        coordinatePlaceHbox.getChildren().add(1,PlaceBoatYCoordenate);// Añadir al layout
        ShootContainerHBox.getChildren().add(0,ShootXCoordenate);
        ShootContainerHBox.getChildren().add(1,ShootYCoordenate);
    }

    private TextField createCoordinatesTxtf() {
        TextField coordinatesTxt = new TextField();
        coordinatesTxt.setPrefWidth(60);
        coordinatesTxt.setPrefHeight(30);
        coordinatesTxt.setOnKeyReleased(event -> {
            String input = coordinatesTxt.getText(); // Obtener el valor del TextField

            // Verificar si el valor ingresado es un número válido entre 1 y 6
            coordinatesTxt.setTextFormatter(new TextFormatter<>(change -> {
                String newText = change.getControlNewText();

                // Permitir sólo números del 1 al 10
                if (newText.matches("[1-9]|10|")) {
                    return change; // Aceptar el cambio
                }
                return null; // Rechazar el cambio
            }));
        });
        return coordinatesTxt;
    }


    @FXML
    void ShootButton(ActionEvent event) {
        int x,y;
        String aux;
        aux= ShootXCoordenate.getText();
        x= Integer.parseInt(aux);
        aux= ShootYCoordenate.getText();
        y= Integer.parseInt(aux);
        battleShip.shoot(x,y);
        battleShip.getMachine().getBoard().printBoard();
    }

    @FXML
    void selectPortaAviones(ActionEvent event) {
        int count = battleShip.getPlayer().getBoard().getPortaavionesCount();
        if (count==1) {
            selectedBoat = battleShip.getPlayer().getBoard().getPortaAviones();
        }
    }

    @FXML
    void selectSubmarino(ActionEvent event) {
        int count = battleShip.getPlayer().getBoard().getSubmarinoCount();
        switch (count) {
            case 1:
                selectedBoat = battleShip.getPlayer().getBoard().getSubmarino1();
                break;
            case 2:
                selectedBoat = battleShip.getPlayer().getBoard().getSubmarino2();
                break;
        }
    }

    @FXML
    void selectDestructor(ActionEvent event) {
        int count = battleShip.getPlayer().getBoard().getDestructorCount();
        switch (count) {
            case 1:
                selectedBoat = battleShip.getPlayer().getBoard().getDestructor1();
                break;
            case 2:
                selectedBoat = battleShip.getPlayer().getBoard().getDestructor2();
                break;
            case 3:
                selectedBoat = battleShip.getPlayer().getBoard().getDestructor3();
        }
    }

    @FXML
    void selectFragata(ActionEvent event) {
        int count = battleShip.getPlayer().getBoard().getFragataCount();
        switch (count) {
            case 1:
                selectedBoat = battleShip.getPlayer().getBoard().getFragata1();
                break;
            case 2:
                selectedBoat = battleShip.getPlayer().getBoard().getFragata2();
                break;
            case 3:
                selectedBoat = battleShip.getPlayer().getBoard().getFragata3();
                break;
            case 4:
                selectedBoat = battleShip.getPlayer().getBoard().getFragata4();
                break;
        }
    }

    @FXML
    void setVertical(ActionEvent event) {
        if (selectedBoat != null){
            selectedBoat.setVertical();
        }
    }

    @FXML
    void setHorizontal(ActionEvent event) {
        if (selectedBoat != null){
            selectedBoat.setHorizontal();
        }
    }

    @FXML
    void placeButton(ActionEvent event) {
        if (selectedBoat != null){
            int x,y,responseFromFunction;
            String aux;
            aux= PlaceBoatXCoordenate.getText();
            x= Integer.parseInt(aux);
            aux= PlaceBoatYCoordenate.getText();
            y= Integer.parseInt(aux);
            if (x<=10 && y<=10) {
                responseFromFunction = battleShip.placeBoat(selectedBoat.isVertical(),x-1,y-1,selectedBoat.getLenght(),selectedBoat);
                switch (responseFromFunction){
                    case 0:
                        selectedBoat=null;
                        break;
                    case 1:
                        alertHandler.coordenadasInvalidasOutOfBounds();
                        break;
                    case 2:
                        alertHandler.coordenadasInvalidasBoatOverBoat();
                        break;
                }
                battleShip.getPlayer().getBoard().printBoard();
            } else {
                alertHandler.coordenadasNoValidas();
            }
        }
    }


}
