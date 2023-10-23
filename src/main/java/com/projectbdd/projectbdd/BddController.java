package com.projectbdd.projectbdd;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class BddController {

    static FXMLLoader fxmlLoader;
    static Stage stage;
    static Scene scene;

    @FXML
    Button disconnect;

    @FXML
    protected void optionpaneletu() throws IOException {
        scene.getWindow().hide();
        OptionPanelEtu.fxmlLoader = new FXMLLoader(PremiereInterface.class.getResource("PanneauEtu.fxml"));
        OptionPanelEtu.scene = new Scene(OptionPanelEtu.fxmlLoader.load(), stage.getWidth(), stage.getHeight());
        OptionPanelEtu.stage = new Stage();
        OptionPanelEtu.stage.setTitle("Base de Données");
        OptionPanelEtu.stage.setScene(OptionPanelEtu.scene);
        OptionPanelEtu.stage.setResizable(false);
        OptionPanelEtu.stage.show();
    }
    @FXML
    protected void optionpanelens() throws IOException {
        scene.getWindow().hide();
        OptionPanelEns.fxmlLoader = new FXMLLoader(PremiereInterface.class.getResource("PanneauEns.fxml"));
        OptionPanelEns.scene = new Scene(OptionPanelEns.fxmlLoader.load(), stage.getWidth(), stage.getHeight());
        OptionPanelEns.stage = new Stage();
        OptionPanelEns.stage.setTitle("Base de Données");
        OptionPanelEns.stage.setScene(OptionPanelEns.scene);
        OptionPanelEns.stage.setResizable(false);
        OptionPanelEns.stage.show();

    }
    @FXML
    protected void optionpanelrqt() throws IOException {
        scene.getWindow().hide();
        OptionPanelRqt.fxmlLoader = new FXMLLoader(PremiereInterface.class.getResource("PanneauRqt.fxml"));
        OptionPanelRqt.scene = new Scene(OptionPanelRqt.fxmlLoader.load(), stage.getWidth(), stage.getHeight());
        OptionPanelRqt.stage = new Stage();
        OptionPanelRqt.stage.setTitle("Base de Données");
        OptionPanelRqt.stage.setScene(OptionPanelRqt.scene);
        OptionPanelRqt.stage.setResizable(false);
        OptionPanelRqt.stage.show();
    }

    @FXML
    protected void deconnection() throws SQLException, IOException {
        HelloController.conn.close();
        disconnect.getScene().getWindow().hide();
        HelloController.fxmlLoader = new FXMLLoader(PremiereInterface.class.getResource("hello-view.fxml"));
        HelloController.scene = new Scene(HelloController.fxmlLoader.load(), 905, 588);
        HelloController.stage = new Stage();
        HelloController.stage.setTitle("Base de Données");
        HelloController.stage.setScene(HelloController.scene);
        HelloController.stage.setResizable(false);
        HelloController.stage.show();
    }
}