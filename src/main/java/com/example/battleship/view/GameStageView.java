package com.example.battleship.view;

import com.example.battleship.control.GameStageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameStageView extends Stage {

    private GameStageController controller;

    public GameStageView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/battleship/battleship-game-view.fxml"));
        Parent root = fxmlLoader.load();
        controller = fxmlLoader.getController();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/com/example/battleship/GameStyles.css").toExternalForm());
        setScene(scene);
        setTitle("Battleship Game");
        setResizable(false);
        show();
    }

    public GameStageController getGameController() {
        return controller;
    }

    private static class GameStageViewHolder {
        private static GameStageView INSTANCE;
    }

    public static GameStageView getInstance() throws IOException {
        GameStageView.GameStageViewHolder.INSTANCE = GameStageView.GameStageViewHolder.INSTANCE != null ? GameStageView.GameStageViewHolder.INSTANCE : new GameStageView();
        return GameStageView.GameStageViewHolder.INSTANCE;
    }

    public static void  deleteInstance() {
        GameStageView.GameStageViewHolder.INSTANCE.close();
    }

}
