package com.example.battleship.model;

import java.io.Serializable;

/**
 * Clase que representa a un jugador en el juego Battleship.
 * Implementa {@link Serializable} para permitir la serialización del estado del jugador.
 *
 * <p>Cada jugador tiene un nombre de usuario (nickname) y un tablero asociado.</p>
 *
 * @author Juan Pablo Charry Ramirez
 * @author Juan Esteban Rodriguez Valencia
 * @version 1.0
 */
public class Player implements Serializable {
    /**
     * Nombre del jugador.
     */
    private String nickname;

    /**
     * Tablero asociado al jugador.
     */
    private Board board;

    /**
     * Constructor de la clase {@code Player}.
     * Inicializa el jugador con un nombre de usuario y crea un tablero vacío asociado.
     *
     * @param nickname el nombre del jugador
     */
    public Player(String nickname) {
        this.nickname = nickname;
        this.board = new Board();
    }

    /**
     * Obtiene el nombre del jugador.
     *
     * @return el nombre del jugador
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Establece el nombre del jugador.
     *
     * @param nickname el nuevo nombre del jugador
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Obtiene el tablero asociado al jugador.
     *
     * @return el tablero del jugador
     */
    public Board getBoard() {
        return board;
    }
}
