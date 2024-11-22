package com.example.battleship.model;

import java.io.Serializable;

/**
 * Clase que representa un barco en el juego Battleship.
 * Implementa {@link Serializable} para permitir la serialización del estado del barco.
 * Cada barco tiene una longitud, una orientación y una posición en el tablero.
 *
 * @author Juan Pablo Charry Ramirez
 * @author Juan Esteban Rodriguez Valencia
 * @version 1.0
 */
public class Boat implements Serializable {
    /**
     * Longitud del barco.
     */
    private int lenght;

    /**
     * Coordenada X donde está ubicado el barco en el tablero.
     */
    private int placementX;

    /**
     * Coordenada Y donde está ubicado el barco en el tablero.
     */
    private int placementY;

    /**
     * Indica si el barco está colocado de forma vertical.
     * {@code true} para orientación vertical, {@code false} para horizontal.
     */
    private boolean vertical;

    /**
     * Constructor de la clase {@code Boat}.
     * Inicializa un barco con la longitud especificada, orientado verticalmente y sin una posición definida.
     *
     * @param lenght la longitud del barco
     */
    public Boat(int lenght) {
        this.lenght = lenght;
        this.vertical = true;
        this.placementX = -1;
        this.placementY = -1;
    }

    /**
     * Establece la posición del barco en el tablero.
     *
     * @param placementX coordenada X donde se colocará el barco
     * @param placementY coordenada Y donde se colocará el barco
     */
    public void setPlacementX(int placementX, int placementY) {
        this.placementX = placementX;
        this.placementY = placementY;
    }

    /**
     * Obtiene la coordenada X donde está ubicado el barco.
     *
     * @return la coordenada X de la posición del barco
     */
    public int getPlacementX() {
        return placementX;
    }

    /**
     * Obtiene la coordenada Y donde está ubicado el barco.
     *
     * @return la coordenada Y de la posición del barco
     */
    public int getPlacementY() {
        return placementY;
    }

    /**
     * Establece la orientación del barco como vertical.
     */
    public void setVertical() {
        this.vertical = true;
    }

    /**
     * Establece la orientación del barco como horizontal.
     */
    public void setHorizontal() {
        this.vertical = false;
    }

    /**
     * Establece manualmente la orientación del barco.
     *
     * @param valueVertical {@code true} para orientación vertical, {@code false} para horizontal
     */
    public void setValueVertical(boolean valueVertical) {
        this.vertical = valueVertical;
    }

    /**
     * Verifica si el barco está orientado de forma vertical.
     *
     * @return {@code true} si el barco está orientado verticalmente, {@code false} en caso contrario
     */
    public boolean isVertical() {
        return vertical;
    }

    /**
     * Obtiene la longitud del barco.
     *
     * @return la longitud del barco
     */
    public int getLenght() {
        return lenght;
    }
}
