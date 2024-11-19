package com.example.battleship.model;

import java.util.ArrayList;
import java.util.List;

public class BattleShip {

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

    public void shoot(int positionX, int positionY) {
        player.getBoard().markShoots(positionX, positionY);
    }


    public void setName(String nickname) {
        player.setNickname(nickname);
    }
}
