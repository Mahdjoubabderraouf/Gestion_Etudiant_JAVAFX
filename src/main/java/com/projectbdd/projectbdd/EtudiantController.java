package com.projectbdd.projectbdd;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class EtudiantController {
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
        ConsulterEtuCtrl.fxmlLoader = new FXMLLoader(PremiereInterface.class.getResource("consulteretu.fxml"));
        ConsulterEtuCtrl.scene = new Scene(ConsulterEtuCtrl.fxmlLoader.load(), 1100, 600);
        ConsulterEtuCtrl.stage = new Stage();
        ConsulterEtuCtrl.stage.setTitle("Base de Données");
        ConsulterEtuCtrl.stage.setScene(ConsulterEtuCtrl.scene);
        ConsulterEtuCtrl.stage.setResizable(false);
        ConsulterEtuCtrl.stage.show();
    }


}