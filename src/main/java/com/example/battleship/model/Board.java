package com.example.battleship.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa el tablero de juego en Battleship.
 * Administra la disposición de barcos, el estado de las celdas y las acciones relacionadas con el juego.
 * Implementa {@link Serializable} para permitir la serialización del estado del tablero.
 *
 * @author Juan Pablo Charry Ramirez
 * @author Juan Esteban Rodriguez Valencia
 * @version 1.0
 */
public class Board implements Serializable {

    /**
     * Matriz que representa el estado del tablero.
     * Los valores pueden ser:
     * 0 - Vacío,
     * 1 - Ocupado por un barco,
     * 2 - Tiro acertado,
     * 3 - Tiro fallado.
     */
    private int[][] board;

    // Instancias de los barcos del juego.
    private Boat portaAviones, submarino1, submarino2, destructor1, destructor2, destructor3, fragata1, fragata2, fragata3, fragata4;

    // Contadores de barcos por tipo.
    private int portaavionesCountValue; // Guardar valor serializable
    private int submarinoCountValue;
    private int destructorCountValue;
    private int fragataCountValue;

    /**
     * Constructor que inicializa el tablero y los barcos del juego.
     * Configura los contadores de barcos por tipo.
     */
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

    /**
     * Devuelve la matriz que representa el tablero.
     * @return el tablero como una matriz bidimensional de enteros
     */
    public int[][] getBoard() {
        return board;
    }

    /**
     * Obtiene la cantidad de portaaviones restantes.
     * @return la cantidad de portaaviones
     */
    public int getPortaavionesCount() {
        return portaavionesCountValue;
    }

    /**
     * Obtiene la cantidad de submarinos restantes.
     * @return la cantidad de submarinos
     */
    public int getSubmarinoCount() {
        return submarinoCountValue;
    }

    /**
     * Obtiene la cantidad de destructores restantes.
     * @return la cantidad de destructores
     */
    public int getDestructorCount() {
        return destructorCountValue;
    }

    /**
     * Obtiene la cantidad de fragatas restantes.
     * @return la cantidad de fragatas
     */
    public int getFragataCount() {
        return fragataCountValue;
    }

    /**
     * Llena el tablero con ceros, indicando que todas las celdas están vacías.
     */
    public void fillZeros() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = 0;
            }
        }
    }

    /**
     * Comprueba si hay espacio en el tablero para colocar un barco.
     * @param vertical indica si el barco se colocará verticalmente
     * @param positionX posición X inicial
     * @param positionY posición Y inicial
     * @param lenght longitud del barco
     * @return {@code true} si hay espacio disponible, {@code false} en caso contrario
     */
    public boolean checkBoardSpace(boolean vertical, int positionX, int positionY, int lenght) {
        if (vertical) {
            if (positionY + lenght > board.length) return false;
            for (int i = positionY; i < positionY + lenght; i++) {
                if (board[i][positionX] == 1) return false;
            }
        } else {
            if (positionX + lenght > board[0].length) return false;
            for (int i = positionX; i < positionX + lenght; i++) {
                if (board[positionY][i] == 1) return false;
            }
        }
        return true;
    }

    /**
     * Verifica si las coordenadas del barco están dentro de los límites del tablero.
     * @param vertical indica si el barco se colocará verticalmente
     * @param positionX posición X inicial
     * @param positionY posición Y inicial
     * @param lenght longitud del barco
     * @return {@code true} si las coordenadas son válidas, {@code false} en caso contrario
     */
    public boolean checkCoordinates(boolean vertical, int positionX, int positionY, int lenght) {
        return (vertical && positionY + lenght <= 10) || (!vertical && positionX + lenght <= 10);
    }

    /**
     * Marca las celdas ocupadas por un barco en el tablero.
     * @param selectedBoat el barco que se colocará
     */
    public void markOcuppiedSpaces(Boat selectedBoat) {
        int x = selectedBoat.getPlacementX(), y = selectedBoat.getPlacementY(), l = selectedBoat.getLenght();
        if (selectedBoat.isVertical()) {
            for (int i = y; i < (y + l); i++) {
                board[i][x] = 1;
            }
        } else {
            for (int i = x; i < x + l; i++) {
                board[y][i] = 1;
            }
        }
        identifyCounterToReduct(selectedBoat.getLenght());
    }

    /**
     * Marca un disparo en el tablero y actualiza el estado de la celda correspondiente.
     * @param positionX posición X del disparo
     * @param positionY posición Y del disparo
     * @return {@code true} si el disparo acertó, {@code false} si falló
     */
    public boolean markShoots(int positionX, int positionY) {
        if (board[positionY][positionX] == 1) {
            board[positionY][positionX] = 2;
            return true;
        } else if (board[positionY][positionX] == 2) {
            board[positionY][positionX] = 2;
            return true;
        } else {
            board[positionY][positionX] = 3;
            return false;
        }
    }

    /**
     * Imprime el tablero en la consola.
     */
    public void printBoard() {
        for (int row = 0; row < board.length; row++) {
            System.out.println("\n");
            for (int col = 0; col < board[row].length; col++) {
                System.out.print(board[row][col] + " ");
            }
        }
    }

    /**
     * Obtiene todos los barcos del tablero.
     * @return una lista de todos los barcos
     */
    public ArrayList<Boat> getAllBoats() {
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

    // Métodos para obtener instancias de barcos.
    public Boat getPortaAviones() { return portaAviones; }
    public Boat getSubmarino1() { return submarino1; }
    public Boat getSubmarino2() { return submarino2; }
    public Boat getFragata1() { return fragata1; }
    public Boat getFragata2() { return fragata2; }
    public Boat getFragata3() { return fragata3; }
    public Boat getFragata4() { return fragata4; }
    public Boat getDestructor1() { return destructor1; }
    public Boat getDestructor2() { return destructor2; }
    public Boat getDestructor3() { return destructor3; }

    // Métodos para reducir los contadores de barcos.
    public void reduceFragataCount() { fragataCountValue--; }
    public void reduceDestructor() { destructorCountValue--; }
    public void reduceSubmarinoCount() { submarinoCountValue--; }
    public void reducePortaAvionesCount() { portaavionesCountValue--; }

    /**
     * Identifica el tipo de barco según su longitud y reduce su contador correspondiente.
     * @param lenght la longitud del barco
     */
    public void identifyCounterToReduct(int lenght) {
        switch (lenght) {
            case 1: reduceFragataCount(); break;
            case 2: reduceDestructor(); break;
            case 3: reduceSubmarinoCount(); break;
            case 4: reducePortaAvionesCount(); break;
        }
    }
}
