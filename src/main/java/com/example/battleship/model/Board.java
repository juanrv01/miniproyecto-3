package com.example.battleship.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;

public class Board {

    private int[][] board;
    private Boat portaAviones, submarino1, submarino2, destructor1, destructor2, destructor3, fragata1, fragata2, fragata3, fragata4;
    IntegerProperty portaavionesCount,submarinoCount,destructorCount,fragataCount;

    public Board() {
        this.board = new int[10][10];
        fillZeros();
        this.portaAviones = new Boat(4);
        this.submarino1 = new Boat(3);
        this.submarino2 = new Boat(3);
        this.destructor1 = new Boat(2);
        this.destructor2 = new Boat(2);
        this.destructor3 = new Boat(2);
        this.fragata1 = new Boat(1);
        this.fragata2 = new Boat(1);
        this.fragata3 = new Boat(1);
        this.fragata4 = new Boat(1);
        this.portaavionesCount = new SimpleIntegerProperty(1);
        this.submarinoCount = new SimpleIntegerProperty(2);
        this.destructorCount = new SimpleIntegerProperty(3);
        this.fragataCount = new SimpleIntegerProperty(4);
    }

    public int[][] getBoard() {
        return board;
    }

    public IntegerProperty getPortaavionesCountLabel() {
        return portaavionesCount;
    }

    public IntegerProperty getSubmarinoCountLabel() {
        return submarinoCount;
    }

    public IntegerProperty getDestructorCountLabel() {
        return destructorCount;
    }
    public IntegerProperty getFragataCountLabel() {
        return fragataCount;
    }

    public int getPortaavionesCount() {
        return portaavionesCount.intValue();
    }

    public int getSubmarinoCount() {
        return submarinoCount.intValue();
    }

    public int getDestructorCount() {
        return destructorCount.intValue();
    }
    public int getFragataCount() {
        return fragataCount.intValue();
    }

    public void fillZeros () {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = 0;
            }
        }
    }

    public boolean checkBoardSpace (boolean vertical,int positionX,int positionY, int lenght) {
        if (vertical==true) {
            for (int i = positionY; i < (positionY+lenght); i++) {
                if (board[i][positionX]==1) {
                    return false;
                }
            }
        } else {
            for (int i = positionX; i < positionX+lenght; i++) {
                if (board[positionX][i] == 1){
                   return false;
                }
            }
        }
        return true;
    }

    public boolean checkCoordinates (boolean vertical,int positionX,int positionY, int lenght) {
        if ((vertical==true && positionY+lenght > 10)||(vertical==false && positionX+lenght > 10)) {
                return false;
        } else {
            return true;
        }
    }

    public void markOcuppiedSpaces(Boat selectedBoat) {
        if (selectedBoat.isVertical()==true) {
            for (int i = selectedBoat.getPlacementY(); i < (selectedBoat.getPlacementX()+ selectedBoat.getLenght()); i++) {
                board[i][selectedBoat.getPlacementY()] = 1; //Marcar posicion como ocupada
            }
        } else {
            for (int i = selectedBoat.getPlacementX(); i < selectedBoat.getPlacementX()+ selectedBoat.getLenght(); i++) {
                board[selectedBoat.getPlacementX()][i] = 1; //Marcar posicion como ocpada
            }
        }
        identifyCounterToReduct(selectedBoat.getLenght());
    }

    public void markShoots(int positionX,int positionY) {
        if (board[positionY][positionX] == 1) {
            board[positionY][positionX] = 2; //Tiro Acertado
        } else {
            board[positionY][positionX] = 3; //Tiro Fallado
        }
    }


    public void printBoard() {
        for (int row = 0; row < board.length; row++) {
            System.out.println("\n");
            for (int col = 0; col < board[row].length; col++) {
                System.out.print(board[row][col] + " ");
            }
        }
    }

    public Boat getPortaAviones() {
        return portaAviones;
    }
    public Boat getSubmarino1() {
        return submarino1;
    }
    public Boat getSubmarino2() {
        return submarino2;
    }
    public Boat getFragata1() {
        return fragata1;
    }
    public Boat getFragata2() {
        return fragata2;
    }
    public Boat getFragata3() {
        return fragata3;
    }
    public Boat getFragata4() {
        return fragata4;
    }
    public Boat getDestructor1() {
        return destructor1;
    }
    public Boat getDestructor2() {
        return destructor2;
    }
    public Boat getDestructor3() {
        return destructor3;
    }

    public void reduceFragataCount(){
        int intFragataCount = fragataCount.get();
        fragataCount.set(intFragataCount-1);
    }

    public void reduceDestructor() {
        int intDestructorCount = destructorCount.get();
        destructorCount.set(intDestructorCount-1);
    }

    public void reduceSubmarinoCount(){
        int intSubmarinoCount = submarinoCount.get();
        submarinoCount.set(intSubmarinoCount-1);
    }

    public void reducePortaAvionesCount(){
        int intPortaAvionesCount = portaavionesCount.get();
        portaavionesCount.set(intPortaAvionesCount-1);
    }

    public void identifyCounterToReduct(int lenght) {
        switch (lenght) {
            case 1:
                 reduceFragataCount();
                 break;
            case 2:
                reduceDestructor();
                break;
            case 3:
                reduceSubmarinoCount();
                break;
            case 4:
                reducePortaAvionesCount();
                break;
        }
    }


}
