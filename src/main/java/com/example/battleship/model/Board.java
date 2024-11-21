package com.example.battleship.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Board implements Serializable {

    private int[][] board;
    private Boat portaAviones, submarino1, submarino2, destructor1, destructor2, destructor3, fragata1, fragata2, fragata3, fragata4;

    private int portaavionesCountValue; // Guardar valor serializable
    private int submarinoCountValue;
    private int destructorCountValue;
    private int fragataCountValue;


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
        this.portaavionesCountValue = 1;
        this.submarinoCountValue = 2;
        this.destructorCountValue = 3;
        this.fragataCountValue = 4;
    }

    public int[][] getBoard() {
        return board;
    }


    public int getPortaavionesCount() {
        return portaavionesCountValue;
    }

    public int getSubmarinoCount() {
        return submarinoCountValue;
    }

    public int getDestructorCount() {
        return destructorCountValue;
    }
    public int getFragataCount() {
        return fragataCountValue;
    }

    public void fillZeros () {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = 0;
            }
        }
    }

    public boolean checkBoardSpace (boolean vertical,int positionX,int positionY, int lenght) {
        if (vertical) {
            // Comprueba si el barco excede los límites del tablero verticalmente
            if (positionY + lenght > board.length) return false;

            // Itera por las celdas verticales
            for (int i = positionY; i < positionY + lenght; i++) {
                if (board[i][positionX] == 1) { // Comprueba si alguna celda está ocupada
                    return false;
                }
            }
        } else {
            if (positionX + lenght > board[0].length) return false;

            // Itera por las celdas horizontales
            for (int i = positionX; i < positionX + lenght; i++) {
                if (board[positionY][i] == 1) { // Corrige el índice para usar la fila fija
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
        int x= selectedBoat.getPlacementX(), y= selectedBoat.getPlacementY(), l= selectedBoat.getLenght();
        if (selectedBoat.isVertical()==true) {
            for (int i = y; i < (y+ l); i++) {
                board[i][x] = 1; //Marcar posicion como ocupada
            }
        } else {
            for (int i = x; i < x+l; i++) {
                board[y][i] = 1; //Marcar posicion como ocpada
            }
        }
        identifyCounterToReduct(selectedBoat.getLenght());
    }

    public boolean markShoots(int positionX,int positionY) {
        if (board[positionY][positionX] == 1) {
            board[positionY][positionX] = 2; //Tiro Acertado
            return true;
        } else if (board[positionY][positionX] == 2) {
            board[positionY][positionX] = 2; //Tiro Acertado
            return true;
        } else {
            board[positionY][positionX] = 3; //Tiro Fallado
            return false;
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
        fragataCountValue--;
    }

    public void reduceDestructor() {
        destructorCountValue--;
    }

    public void reduceSubmarinoCount(){
        submarinoCountValue--;
    }

    public void reducePortaAvionesCount(){
        portaavionesCountValue--;
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

    public ArrayList<Boat> getAllBoats () {
        ArrayList<Boat> allBoats = new ArrayList<>();
        allBoats.add(getPortaAviones());
        allBoats.add(getSubmarino1());
        allBoats.add(getSubmarino2());
        allBoats.add(getDestructor1());
        allBoats.add(getDestructor2());
        allBoats.add(getDestructor3());
        allBoats.add(getFragata1());
        allBoats.add(getFragata2());
        allBoats.add(getFragata3());
        allBoats.add(getFragata4());
        return allBoats;
    }

}
