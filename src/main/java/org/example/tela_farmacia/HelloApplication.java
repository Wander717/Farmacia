package org.example.tela_farmacia;

import atlantafx.base.theme.Dracula;
import atlantafx.base.theme.PrimerLight;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import atlantafx.base.theme.PrimerDark;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/Tela_Menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Farmácia");
        stage.setScene(scene);
        stage.show();
    }
}
