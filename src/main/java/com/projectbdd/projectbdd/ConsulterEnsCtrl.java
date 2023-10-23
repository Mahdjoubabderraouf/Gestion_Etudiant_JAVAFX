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

public class ConsulterEnsCtrl implements Initializable {
    @FXML
    ImageView retour;
    @FXML
    TableView<Enseignant> TblEns;
    @FXML
    TableColumn<Enseignant,String> colnomens;
    @FXML
    TableColumn<Enseignant,String> colprenomens;
    @FXML
    TableColumn<Enseignant,String> colmatens;
    static ObservableList<Enseignant> ObsEns;
    static Statement stmt = null;
    static ResultSet rs = null;
    static FXMLLoader fxmlLoader;
    static Stage stage;
    static Scene scene;


    private void setCellTable(){
        colprenomens.setCellValueFactory(new PropertyValueFactory<>("prenom_ens"));
        colnomens.setCellValueFactory(new PropertyValueFactory<>("nom_ens"));
        colmatens.setCellValueFactory(new PropertyValueFactory<>("matricule"));
    }
    private void loadData(){
        ObsEns.removeAll(ObsEns);
        TblEns.getItems().addAll(ObsEns);
    }
    @FXML
    private void retourner() throws IOException {
        scene.getWindow().hide();
        EnseignantController.fxmlLoader = new FXMLLoader(PremiereInterface.class.getResource("enseignant.fxml"));
        EnseignantController.scene = new Scene(EnseignantController.fxmlLoader.load(), 1100, 600);
        EnseignantController.stage = new Stage();
        EnseignantController.stage.setTitle("Base de Données");
        EnseignantController.stage.setScene(EnseignantController.scene);
        EnseignantController.stage.setResizable(false);
        EnseignantController.stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObsEns = FXCollections.observableArrayList();
        try{
            stmt = HelloController.conn.createStatement();
            rs = stmt.executeQuery("Select * from bddadmin.enseignant");
            ObsEns.removeAll(ObsEns);
            while(rs.next()){
                ObsEns.add(new Enseignant(rs.getString(2), rs.getString(3), rs.getString(1)));
            }
            TblEns.getItems().clear();
            TblEns.getItems().addAll(ObsEns);
        }
        catch(SQLException x){
            System.out.println("PERMISSION NOT GRANTED.");
        }
        setCellTable();
        loadData();
    }

}