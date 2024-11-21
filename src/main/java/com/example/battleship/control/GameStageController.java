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
    ArrayList<Boat> playerBoats = new ArrayList<>();

    private SerializableFileHandler serializableFileHandler;


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
        serializableFileHandler = new SerializableFileHandler();
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
    }



    public void setPlayers (Player player, Player machine) {
        battleShip.setPlayer(player);
        battleShip.setMachine(machine);
    }

    public void showBoats() {
        playerBoats =battleShip.getPlayer().getBoard().getAllBoats();
        for (Boat boat : playerBoats) {
            if (boat.getPlacementX()>=0 && boat.getPlacementY()>=0){
                drawBoat(boat,playerBoatsGrid);
            }
        }
    }

    public void showHits() {
        int[][] machine= battleShip.getMachine().getBoard().getBoard();
        int[][] player= battleShip.getPlayer().getBoard().getBoard();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (machine[i][j]==2){
                    drawShoot(true,machineBoard,j,i);
                }  else if (machine[i][j]==3){
                    drawShoot(false,machineBoard,j,i);
                }

                if (player[i][j]==2){
                    drawShoot(true,playerBoatsGrid,j,i);
                }  else if (player[i][j]==3){
                    drawShoot(false,playerBoatsGrid,j,i);
                }
            }
        }
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

    boolean allBoatsPlaces(){
        int [][] tableroJugador = battleShip.getPlayer().getBoard().getBoard();
        int count =0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(tableroJugador[i][j]==1) {
                    count++;
                }
            }
        }

        if (count==20) {
            serialize();
            return true;
        }
        return false;
    }

    @FXML
    void ShootButton(ActionEvent event) throws InterruptedException {
        Random random = new Random();


        if (ShootXCoordenate.getText().isEmpty()==false && ShootYCoordenate.getText().isEmpty()==false && allBoatsPlaces()==true) {
            int x,y;
            String aux;
            aux= ShootXCoordenate.getText();
            x= Integer.parseInt(aux);
            aux= ShootYCoordenate.getText();
            y= Integer.parseInt(aux);
            boolean machineBoatHit= battleShip.shoot(x-1,y-1,battleShip.getMachine());
            drawShoot(machineBoatHit,machineBoard,x-1,y-1);

            if (battleShip.gameOver(battleShip.getMachine())==false) {
                waitFor(Duration.seconds(1), () ->{
                    int w = random.nextInt(9);
                    int z = random.nextInt(9);
                    boolean playerBoatHit= battleShip.shoot(w+1,z+1,battleShip.getPlayer());
                    drawShoot(playerBoatHit,playerBoatsGrid,w+1,z+1);
                    battleShip.getMachine().getBoard().printBoard();
                    serialize();
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
                        battleShip.getPlayer().getBoard().printBoard();
                        serialize();
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
       drawBoat2D(boat,grid);
    }

    private void drawShoot(boolean hit,GridPane grid, int x, int y) {
        if (hit==true) {
            drawHit(x,y,grid);
        } else {
            drawWater(x,y,grid);
        }
    }
    private Color getBoatColor(Boat boat) {
        switch (boat.getLenght()) {
            case 4: return Color.DARKBLUE; // Portaaviones
            case 3: return Color.GREEN; // Submarino
            case 2: return Color.BROWN; // Destructor
            case 1: return Color.YELLOW; // Fragata
            default: return Color.GRAY; // Color genérico
        }
    }

    private void drawBoat2D(Boat boat, GridPane grid) {
        // Determinar orientación
        boolean isVertical = boat.isVertical();
        int length = boat.getLenght();
        int x = boat.getPlacementX();
        int y = boat.getPlacementY();

        // Crear la figura base del barco (rectángulo alargado)
        Rectangle body = new Rectangle(
                isVertical ? 18 : 18 * length,  // Ancho del cuerpo
                isVertical ? 18 * length : 18  // Alto del cuerpo
        );
        body.setFill(getBoatColor(boat)); // Asignar color según el tipo
        body.setStroke(Color.BLACK); // Borde para visibilidad

        // Proa (frontal del barco)
        Circle bow = new Circle(9); // Proa redondeada
        bow.setFill(Color.DARKGRAY); // Color de la proa
        bow.setStroke(Color.BLACK); // Borde

        // Ajustar la posición de la proa
        if (isVertical) {
            grid.add(bow, x + 1, y + 1); // Proa arriba
        } else {
            grid.add(bow, x + 1, y + 1); // Proa izquierda
        }

        // Popa (trasera del barco)
        Circle stern = new Circle(9); // Popa redondeada
        stern.setFill(Color.DARKGRAY); // Color de la popa
        stern.setStroke(Color.BLACK); // Borde

        // Ajustar la posición de la popa
        if (isVertical) {
            grid.add(stern, x + 1, y + length); // Popa abajo
        } else {
            grid.add(stern, x + length, y + 1); // Popa derecha
        }

        // Agregar ventanas (opcional, para barcos grandes)
        if (length >= 3) {
            for (int i = 1; i < length - 1; i++) {
                Rectangle window = new Rectangle(6, 6); // Tamaño de la ventana
                window.setFill(Color.LIGHTBLUE); // Color de la ventana
                window.setStroke(Color.BLACK); // Borde

                // Posicionar ventanas a lo largo del cuerpo
                if (isVertical) {
                    grid.add(window, x + 1, y + 1 + i);
                } else {
                    grid.add(window, x + 1 + i, y + 1);
                }
            }
        }

        // Agregar el cuerpo al tablero
        grid.add(body, x + 1, y + 1, isVertical ? 1 : length, isVertical ? length : 1);
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

    private void serialize() {
        serializableFileHandler.serialize(battleShip.getPlayer().getNickname(),battleShip.getPlayer());
        serializableFileHandler.serialize(battleShip.getPlayer().getNickname()+"Machine",battleShip.getMachine());
    }


}
