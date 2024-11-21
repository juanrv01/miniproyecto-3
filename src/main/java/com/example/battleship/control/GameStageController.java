package com.example.battleship.control;

import com.example.battleship.model.*;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;


public class GameStageController {
    BattleShip battleShip = new BattleShip();
    Boat selectedBoat;
    AlertHandler alertHandler = new AlertHandler();
    Boolean showingOpponentBoats = false;

    @FXML
    TextField PlaceBoatXCoordenate,PlaceBoatYCoordenate,ShootXCoordenate,ShootYCoordenate;

    @FXML
    Label fragataLabel,destructorLabel,submarinoLabel,portaAvionesLabel;

    @FXML
    GridPane playerBoatsGrid,machineBoard;

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

        createCoordinateLabels(playerBoatsGrid);
        createCoordinateLabels(machineBoard);
        battleShip.placeBoatsMachine();
        battleShip.getMachine().getBoard().printBoard();
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

    void createCoordinateLabels(GridPane grid) {
        for (int j = 0; j < 10; j++) {
            Label label = new Label(String.valueOf(j+1));
            label.getStyleClass().add("cordinateLabel");
            grid.add(label,0,j+1);
        }

        for (int j = 0; j < 10; j++) {
            Label label = new Label(String.valueOf(j+1));
            label.getStyleClass().add("cordinateLabel");
            grid.add(label,j+1,0);
        }
    }

    @FXML
    void ShootButton(ActionEvent event) throws InterruptedException {
        Random random = new Random();
        if (ShootXCoordenate.getText().isEmpty()==false && ShootYCoordenate.getText().isEmpty()==false) {
            int x,y;
            String aux;
            aux= ShootXCoordenate.getText();
            x= Integer.parseInt(aux);
            aux= ShootYCoordenate.getText();
            y= Integer.parseInt(aux);
            boolean machineBoatHit= battleShip.shoot(x-1,y-1,battleShip.getMachine());
            drawShoot(machineBoatHit,machineBoard,x,y);

            if (battleShip.gameOver(battleShip.getMachine())==false) {
                waitFor(Duration.seconds(1), () ->{
                    int w = random.nextInt(9);
                    int z = random.nextInt(9);
                    boolean playerBoatHit= battleShip.shoot(w,z,battleShip.getPlayer());
                    drawShoot(playerBoatHit,playerBoatsGrid,w,z);
                });
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Game Over");
                alert.setHeaderText(null);
                alert.setContentText("Game Over");
                alert.showAndWait();

            }

        }
    }

    public void waitFor(Duration duration, Runnable afterWait) {
        PauseTransition pause = new PauseTransition(duration);
        pause.setOnFinished(e -> afterWait.run());
        pause.play();
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
            x= Integer.parseInt(aux)-1;
            aux= PlaceBoatYCoordenate.getText();
            y= Integer.parseInt(aux)-1;
            if (x<=10 && y<=10) {
                responseFromFunction = battleShip.placeBoat(selectedBoat.isVertical(),x,y,selectedBoat.getLenght(),selectedBoat);
                switch (responseFromFunction){
                    case 0:
                        drawBoat(selectedBoat,playerBoatsGrid);
                        selectedBoat=null;
                        break;
                    case 1:
                        alertHandler.coordenadasInvalidasOutOfBounds();
                        break;
                    case 2:
                        alertHandler.coordenadasInvalidasBoatOverBoat();
                        break;
                }
            } else {
                alertHandler.coordenadasNoValidas();
            }
        }
    }

    @FXML
    void seeOpponetBoats(ActionEvent event) {
        if (!showingOpponentBoats) {
            ArrayList<Boat> machineBoatsArray = battleShip.getMachine().getBoard().getAllBoats();
            for (Boat machineBoat : machineBoatsArray) {
                drawBoat(machineBoat,machineBoard);
            }
            showingOpponentBoats = true;
        } else {
            machineBoard.getChildren().removeIf(node -> node instanceof Rectangle);
            showingOpponentBoats = false;
        }

    }

    private void drawBoat(Boat boat, GridPane grid) {
        // Crear un nodo gráfico para el barco
        for (int i = 0; i < boat.getLenght(); i++) {
            Rectangle cell = new Rectangle(18, 18); // Tamaño de cada celda
            cell.setFill(Color.GRAY); // Color del barco
            cell.setStroke(Color.BLACK); // Borde para mejor visibilidad

            // Calcular la posición del segmento del barco
            int x = boat.getPlacementX() + (boat.isVertical() ? 1 : i+1);
            int y = boat.getPlacementY() + (boat.isVertical() ? i+1 : 1);

            // Añadir el nodo al GridPane
            grid.add(cell, x, y);
        }
    }

    private void drawShoot(boolean hit,GridPane grid, int x, int y) {
        if (hit==true) {
            Rectangle cell = new Rectangle(18, 18); // Tamaño de cada celda
            cell.setFill(Color.RED); // Color del barco
            cell.setStroke(Color.BLACK); // Borde para mejor visibilidad
            grid.add(cell, x, y);
        } else {
            Rectangle cell = new Rectangle(18, 18); // Tamaño de cada celda
            cell.setFill(Color.BLUE); // Color del barco
            cell.setStroke(Color.BLACK); // Borde para mejor visibilidad
            grid.add(cell, x, y);
        }
    }
    private void drawBoat2D(Boat boat, GridPane grid) {
        for (int i = 0; i < boat.getLenght(); i++) {
            // Crear el segmento del barco
            Rectangle bodySegment = new Rectangle(18, 18); // Segmento principal del barco
            bodySegment.setFill(Color.GRAY); // Color del cuerpo
            bodySegment.setStroke(Color.BLACK); // Borde

            // Parte adicional: proa o popa (dependiendo de la posición)
            Circle bowOrStern = null;
            if (i == 0 || i == boat.getLenght() - 1) { // Proa o popa
                bowOrStern = new Circle(9); // Radio para las esquinas redondeadas
                bowOrStern.setFill(Color.DARKGRAY); // Color más oscuro para contraste
                bowOrStern.setStroke(Color.BLACK); // Borde
            }

            // Calcular la posición del segmento
            int x = boat.getPlacementX() + (boat.isVertical() ? 0 : i);
            int y = boat.getPlacementY() + (boat.isVertical() ? i : 0);

            // Agregar el segmento al tablero
            grid.add(bodySegment, x + 1, y + 1);

            // Agregar la proa o popa si corresponde
            if (bowOrStern != null) {
                grid.add(bowOrStern, x + 1, y + 1);
            }
        }
    }

    // Dibujar "agua" (disparo fallido)
    private void drawWater(int x, int y, GridPane grid) {
        Circle waterMarker = new Circle(9); // Radio del círculo
        waterMarker.setFill(Color.BLUE); // Color azul para agua
        waterMarker.setStroke(Color.BLACK); // Borde para visibilidad
        grid.add(waterMarker, x + 1, y + 1); // Posicionar en el tablero
    }

    // Dibujar "tocado" (disparo acertado en barco)
    private void drawHit(int x, int y, GridPane grid) {
        Circle hitMarker = new Circle(9); // Radio del círculo
        hitMarker.setFill(Color.ORANGE); // Color naranja para tocado
        hitMarker.setStroke(Color.BLACK); // Borde para visibilidad
        grid.add(hitMarker, x + 1, y + 1); // Posicionar en el tablero
    }

    // Dibujar "hundido" (barco completamente destruido)
    private void drawSunk(Boat boat, GridPane grid) {
        for (int i = 0; i < boat.getLenght(); i++) {
            Rectangle sunkSegment = new Rectangle(18, 18); // Tamaño del segmento
            sunkSegment.setFill(Color.RED); // Color rojo para barco hundido
            sunkSegment.setStroke(Color.BLACK); // Borde para visibilidad

            // Calcular la posición de cada segmento
            int x = boat.getPlacementX() + (boat.isVertical() ? 0 : i);
            int y = boat.getPlacementY() + (boat.isVertical() ? i : 0);

            // Agregar al tablero
            grid.add(sunkSegment, x + 1, y + 1);
        }
    }



}
