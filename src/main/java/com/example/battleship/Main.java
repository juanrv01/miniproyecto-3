package com.example.battleship;


import com.example.battleship.view.IntroStageView;
import javafx.application.Application;

import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        IntroStageView.getInstance();
    }
}
