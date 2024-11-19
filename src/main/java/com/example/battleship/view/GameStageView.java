package com.example.battleship.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameStageView extends Stage {

    public GameStageView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/battleship/battleship-game-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/com/example/battleship/GameStyles.css").toExternalForm());
        setScene(scene);
        setTitle("Battleship Game");
        setResizable(false);
        show();
    }
}
