package com.example.battleship.model;

import java.io.Serializable;


public class Player implements Serializable {
    private String nickname;
    private Board board;

    public Player(String nickname) {
        this.nickname = nickname;
        this.board = new Board();

    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Board getBoard() {
        return board;
    }



}