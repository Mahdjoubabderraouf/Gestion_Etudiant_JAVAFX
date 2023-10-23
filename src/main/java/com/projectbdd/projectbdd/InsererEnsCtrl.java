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

public class InsererEnsCtrl implements Initializable {
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
    @FXML
    TextField ajoutmat;
    @FXML
    TextField ajoutnom;
    @FXML
    TextField ajoutprenom;
    @FXML
    Label msgerror;

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
    @FXML
    private void inserer(){
        if(Objects.equals(ajoutmat.getText(), "") || Objects.equals(ajoutnom.getText(), "") || Objects.equals(ajoutprenom.getText(), "")){
            msgerror.setText("Veuillez remplir tous les champs.");
        }
        else{
            try {
                stmt.executeQuery("insert into bddadmin.enseignant values ('"+ajoutmat.getText()+"','"+ajoutnom.getText()+"','"+ajoutprenom.getText()+"')");
                ObsEns.add(new Enseignant(ajoutnom.getText(),ajoutprenom.getText(),ajoutmat.getText()));
                TblEns.getItems().addAll(ObsEns);
                ObsEns.removeAll(ObsEns);
                msgerror.setText("");
            } catch(SQLDataException e){
                System.out.println(e);
                msgerror.setText("Erreur d'ajout, longueur de caractères dépasse la limite.");
            }
            catch(SQLIntegrityConstraintViolationException e){
                System.out.println(e);
                msgerror.setText("Erreur d'ajout, répétition non autorisée.");
            }
            catch (SQLException e) {
                System.out.println(e);
                msgerror.setText("Erreur d'ajout, vérifiez que vous avez les permissions.");
            }


        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObsEns = FXCollections.observableArrayList();
        setCellTable();
        TblEns.getItems().addAll(ObsEns);
        try {
            stmt = HelloController.conn.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ObsEns.removeAll(ObsEns);

    }


}