package com.example.battleship.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class IntroStageView extends Stage {

    public IntroStageView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/battleship/intro-game-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/com/example/battleship/GameStyles.css").toExternalForm());
        setScene(scene);
        setTitle("Battleship Game");
        setResizable(false);
        show();
    }

    private static class IntroStageHolder {
        private static IntroStageView INSTANCE;
    }

    public static IntroStageView getInstance() throws IOException {
        IntroStageView.IntroStageHolder.INSTANCE = IntroStageView.IntroStageHolder.INSTANCE != null ? IntroStageView.IntroStageHolder.INSTANCE : new IntroStageView();
        return IntroStageHolder.INSTANCE;
    }

    public static void  deleteInstance() {
        IntroStageView.IntroStageHolder.INSTANCE.close();
    }

}
