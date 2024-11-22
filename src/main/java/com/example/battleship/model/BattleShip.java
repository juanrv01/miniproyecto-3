package com.example.battleship.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Clase principal que representa el juego de Battleship.
 * Gestiona las interacciones entre los jugadores (humano y máquina), el tablero de juego y las reglas principales.
 * Esta clase implementa {@link Serializable} para permitir su serialización.
 *
 * @author Autor1
 * @author Autor2
 * @version 1.0
 */
public class BattleShip implements Serializable {

    /**
     * Jugador controlado por la máquina.
     */
    private Player machine;

    /**
     * Jugador controlado por el usuario.
     */
    private Player player;

    /**
     * Constructor de la clase {@code BattleShip}.
     * Inicializa los jugadores humano y máquina con sus respectivos nombres.
     */
    public BattleShip() {
        this.machine = new Player("machine");
        this.player = new Player("player");
    }

    /**
     * Obtiene el jugador controlado por la máquina.
     * @return el jugador de la máquina
     */
    public Player getMachine() {
        return machine;
    }

    /**
     * Obtiene el jugador controlado por el usuario.
     * @return el jugador humano
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Establece el jugador controlado por el usuario.
     * @param player el jugador humano
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Establece el jugador controlado por la máquina.
     * @param machine el jugador de la máquina
     */
    public void setMachine(Player machine) {
        this.machine = machine;
    }

    /**
     * Realiza un disparo en el tablero de un jugador.
     * @param positionX la posición X del disparo
     * @param positionY la posición Y del disparo
     * @param defaultPlayer el jugador cuyo tablero está siendo atacado
     * @return {@code true} si el disparo impactó un barco, {@code false} en caso contrario
     */
    public boolean shoot(int positionX, int positionY, Player defaultPlayer) {
        return defaultPlayer.getBoard().markShoots(positionX, positionY);
    }

    /**
     * Establece el nombre del jugador humano.
     * @param nickname el nombre del jugador humano
     */
    public void setName(String nickname) {
        player.setNickname(nickname);
    }

    /**
     * Intenta colocar un barco en el tablero del jugador humano.
     * @param vertical indica si el barco se colocará verticalmente
     * @param positionX la posición X inicial
     * @param positionY la posición Y inicial
     * @param lenght la longitud del barco
     * @param boat el barco que se desea colocar
     * @return {@code 0} si el barco fue colocado exitosamente, {@code 1} si las coordenadas están fuera de los límites, {@code 2} si las coordenadas interfieren con otros barcos
     */
    public int placeBoat(boolean vertical, int positionX, int positionY, int lenght, Boat boat) {
        if (player.getBoard().checkCoordinates(vertical, positionX, positionY, lenght) == false) {
            return 1;
        } else if (player.getBoard().checkBoardSpace(vertical, positionX, positionY, lenght) == false) {
            return 2;
        } else {
            boat.setPlacementX(positionX, positionY);
            boat.setValueVertical(vertical);
            player.getBoard().markOcuppiedSpaces(boat);
            return 0;
        }
    }

    /**
     * Coloca los barcos de la máquina de forma aleatoria.
     * Genera posiciones y orientaciones aleatorias hasta encontrar una configuración válida para cada barco en el tablero de la máquina.
     */
    public void placeBoatsMachine() {
        Random random = new Random();
        ArrayList<Boat> boats = machine.getBoard().getAllBoats();
        for (Boat boat : boats) {
            int x, y, l = boat.getLenght();
            boolean sense, firstCon, secondCon;
            do {
                x = random.nextInt(10);
                y = random.nextInt(10);
                l = boat.getLenght();
                sense = random.nextBoolean();
                firstCon = machine.getBoard().checkBoardSpace(sense, x, y, l);
                secondCon = machine.getBoard().checkCoordinates(sense, x, y, l);
            } while (firstCon == false || secondCon == false);
            boat.setPlacementX(x, y);
            boat.setValueVertical(sense);
            machine.getBoard().markOcuppiedSpaces(boat);
        }
    }

    /**
     * Verifica si el juego ha terminado para un jugador.
     * El juego termina si se han alcanzado todos los barcos del jugador.
     * @param defaultPlayer el jugador cuyo tablero se evalúa
     * @return {@code true} si todos los barcos del jugador han sido destruidos, {@code false} en caso contrario
     */
    public boolean gameOver(Player defaultPlayer) {
        int[][] matriz = defaultPlayer.getBoard().getBoard();
        int shotsHit = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (matriz[i][j] == 2) {
                    shotsHit++;
                }
            }
        }
        if (shotsHit == 20) {
            return true;
        }
        return false;
    }
}
