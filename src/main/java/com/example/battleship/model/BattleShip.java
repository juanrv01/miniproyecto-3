package com.example.battleship.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BattleShip implements Serializable {

    private Player machine;
    private Player player;

    public BattleShip() {
        this.machine = new Player("machine");
        this.player = new Player("player");
    }

    public Player getMachine() {
        return machine;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setMachine(Player machine) {
        this.machine = machine;
    }

    public boolean shoot(int positionX, int positionY,Player defaultPlayer) {
        return defaultPlayer.getBoard().markShoots(positionX, positionY);
    }


    public void setName(String nickname) {
        player.setNickname(nickname);
    }


    public int placeBoat(boolean vertical,int positionX,int positionY, int lenght, Boat boat) {
        if (player.getBoard().checkCoordinates(vertical, positionX, positionY, lenght)==false) {
            return 1;
        } else if (player.getBoard().checkBoardSpace(vertical, positionX, positionY, lenght)==false) {
            return 2;
        } else {
            boat.setPlacementX(positionX,positionY);
            boat.setValueVertical(vertical);
            player.getBoard().markOcuppiedSpaces(boat);
            return 0;
        }
    }

    public void placeBoatsMachine(){
        Random random = new Random();
        ArrayList<Boat> boats = machine.getBoard().getAllBoats();
        for (Boat boat : boats) {
            int x,y,l = boat.getLenght();
            boolean sense,firstCon,secondCon;
            do {
                x = random.nextInt(10);
                y = random.nextInt(10);
                l = boat.getLenght();
                sense = random.nextBoolean();
                System.out.println("x:"+x+" y:"+y);
                firstCon =machine.getBoard().checkBoardSpace(sense,x,y,l);
                secondCon = machine.getBoard().checkCoordinates(sense,x,y,l);
            } while (firstCon==false || secondCon == false);
            boat.setPlacementX(x,y);
            boat.setValueVertical(sense);
            machine.getBoard().markOcuppiedSpaces(boat);
            System.out.println("boat placed ");
        }
    }

    public boolean gameOver(Player defaultPlayer) {
        int[][] matriz = defaultPlayer.getBoard().getBoard();
        int shotsHit=0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (matriz[i][j] == 2) {
                    shotsHit++;
                }
            }
        }
        if (shotsHit==20) {
            return true;
        }
        return false;
    }

}
