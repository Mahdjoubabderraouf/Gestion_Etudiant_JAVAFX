package com.projectbdd.projectbdd;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminConsulterEtuCtrl implements Initializable {
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
        OptionPanelEtu.fxmlLoader = new FXMLLoader(PremiereInterface.class.getResource("PanneauEtu.fxml"));
        OptionPanelEtu.scene = new Scene(OptionPanelEtu.fxmlLoader.load(), 1100, 600);
        OptionPanelEtu.stage = new Stage();
        OptionPanelEtu.stage.setTitle("Base de Données");
        OptionPanelEtu.stage.setScene(OptionPanelEtu.scene);
        OptionPanelEtu.stage.setResizable(false);
        OptionPanelEtu.stage.show();

    }
    @FXML
    private void chercheretu(){
            try {
                rs = stmt.executeQuery("select * from etudiant");
                ObsEns.removeAll(ObsEns);
                if(rs.next()) {
                    do {
                        ObsEns.add(new Etudiant(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4).toString(), rs.getString(5)));
                    } while (rs.next());
                }
                TblEtu.getItems().clear();
                TblEtu.getItems().addAll(ObsEns);
            }
            catch(SQLSyntaxErrorException e){
                System.out.println(e);
            }
            catch (SQLException e) {
                System.out.println(e);
                throw new RuntimeException(e);
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
        chercheretu();
    }


}