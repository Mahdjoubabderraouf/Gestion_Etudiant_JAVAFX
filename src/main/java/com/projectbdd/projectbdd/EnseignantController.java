package com.projectbdd.projectbdd;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;

public class EnseignantController {
    static Statement stmt = null;
    static ResultSet rs = null;
    static FXMLLoader fxmlLoader;
    static Stage stage;
    static Scene scene;

    @FXML
    Button disconnect;

    @FXML
    protected void deconnection() throws SQLException, IOException {
        HelloController.conn.close();
        disconnect.getScene().getWindow().hide();
        HelloController.fxmlLoader = new FXMLLoader(PremiereInterface.class.getResource("hello-view.fxml"));
        HelloController.scene = new Scene(HelloController.fxmlLoader.load(), 905, 588);
        HelloController.stage = new Stage();
        HelloController.stage.setTitle("Base de Données");
        HelloController.stage.setScene(HelloController.scene);
        HelloController.stage.show();
    }
    @FXML
    protected void consulter() throws IOException {
        scene.getWindow().hide();
        ConsulterEnsCtrl.fxmlLoader = new FXMLLoader(PremiereInterface.class.getResource("consulterens.fxml"));
        ConsulterEnsCtrl.scene = new Scene(ConsulterEnsCtrl.fxmlLoader.load(), 1100, 600);
        ConsulterEnsCtrl.stage = new Stage();
        ConsulterEnsCtrl.stage.setTitle("Base de Données");
        ConsulterEnsCtrl.stage.setScene(ConsulterEnsCtrl.scene);
        ConsulterEnsCtrl.stage.setResizable(false);
        ConsulterEnsCtrl.stage.show();
    }

    @FXML
    protected void inserer() throws IOException{
        scene.getWindow().hide();
        InsererEnsCtrl.fxmlLoader = new FXMLLoader(PremiereInterface.class.getResource("insererens.fxml"));
        InsererEnsCtrl.scene = new Scene(InsererEnsCtrl.fxmlLoader.load(), 1100, 600);
        InsererEnsCtrl.stage = new Stage();
        InsererEnsCtrl.stage.setTitle("Base de Données");
        InsererEnsCtrl.stage.setScene(InsererEnsCtrl.scene);
        InsererEnsCtrl.stage.setResizable(false);
        InsererEnsCtrl.stage.show();
    }

}