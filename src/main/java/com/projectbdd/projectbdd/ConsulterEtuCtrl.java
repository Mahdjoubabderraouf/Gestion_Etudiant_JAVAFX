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
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class ConsulterEtuCtrl implements Initializable {
    @FXML
    TableView<Etudiant> TblEtu;
    @FXML
    TableColumn<Etudiant,String> colnometu;
    @FXML
    TableColumn<Etudiant,String> colprenometu;
    @FXML
    TableColumn<Etudiant,String> colmatetu;
    @FXML
    TableColumn<Etudiant,String> coldate;
    @FXML
    TableColumn<Etudiant,String> coladr;
    @FXML
    TextField matinfo;
    @FXML
    Label msgerror;



    static ObservableList<Etudiant> ObsEns;
    static Statement stmt = null;
    static ResultSet rs = null;
    static FXMLLoader fxmlLoader;
    static Stage stage;
    static Scene scene;


    private void setCellTable(){
        colprenometu.setCellValueFactory(new PropertyValueFactory<>("prenom_etu"));
        colnometu.setCellValueFactory(new PropertyValueFactory<>("nom_etu"));
        colmatetu.setCellValueFactory(new PropertyValueFactory<>("matricule_etu"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        coladr.setCellValueFactory(new PropertyValueFactory<>("adresse"));
    }
    @FXML
    private void retourner() throws IOException {
        scene.getWindow().hide();
        EtudiantController.fxmlLoader = new FXMLLoader(PremiereInterface.class.getResource("etudiant.fxml"));
        EtudiantController.scene = new Scene(EtudiantController.fxmlLoader.load(), 1100, 600);
        EtudiantController.stage = new Stage();
        EtudiantController.stage.setTitle("Base de Données");
        EtudiantController.stage.setScene(EtudiantController.scene);
        EtudiantController.stage.setResizable(false);
        EtudiantController.stage.show();

    }
    @FXML
    private void chercheretu(){
        if(Objects.equals(matinfo.getText(), "")){
            msgerror.setText("Veuillez remplir le champ.");
        }
        else {
            try {
                rs = stmt.executeQuery("select * from bddadmin.etudiant where matricule_etu = '" + matinfo.getText() + "' ");
                ObsEns.removeAll(ObsEns);
                if(rs.next()) {
                    do {
                        ObsEns.add(new Etudiant(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4).toString(), rs.getString(5)));
                    } while (rs.next());
                }
                else{
                    msgerror.setText("Matricule Inexistant");
                }
                TblEtu.getItems().clear();
                TblEtu.getItems().addAll(ObsEns);
            }
            catch(SQLSyntaxErrorException e){
                msgerror.setText("Matricule Inexistant");
                System.out.println(e);
            }
            catch (SQLException e) {
                msgerror.setText("ERREUR CRITIQUE");
                System.out.println(e);
                throw new RuntimeException(e);
            }

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObsEns = FXCollections.observableArrayList();
        try{
            stmt = HelloController.conn.createStatement();
            TblEtu.getItems().clear();
        }
        catch(SQLException x){
            System.out.println("PERMISSION NOT GRANTED.");
        }
        setCellTable();
    }

}