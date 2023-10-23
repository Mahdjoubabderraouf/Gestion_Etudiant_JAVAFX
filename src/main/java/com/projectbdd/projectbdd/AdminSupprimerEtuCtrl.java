package com.projectbdd.projectbdd;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminSupprimerEtuCtrl implements Initializable {
    @FXML
    ImageView retour;
    @FXML
    TableView<Etudiant> TblEtu;
    @FXML
    TableColumn<Etudiant,String> colnometu;
    @FXML
    TableColumn<Etudiant,String> colprenometu;
    @FXML
    TableColumn<Etudiant,String> colmatetu;
    @FXML
    TableColumn<Etudiant,String> coladr;
    @FXML
    TableColumn<Etudiant,String> coldate;
    @FXML
    TextField ajoutmat;
    @FXML
    Label msgerror;

    static ObservableList<Etudiant> ObsEtu;
    static Statement stmt = null;
    static ResultSet rs = null;
    static FXMLLoader fxmlLoader;
    static Stage stage;
    static Scene scene;
    @FXML
    DialogPane dialog;


    private void setCellTable(){
        colprenometu.setCellValueFactory(new PropertyValueFactory<>("prenom_etu"));
        colnometu.setCellValueFactory(new PropertyValueFactory<>("nom_etu"));
        colmatetu.setCellValueFactory(new PropertyValueFactory<>("matricule_etu"));
        coladr.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
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
    private void fermer(){
        dialog.setVisible(false);
    }

    @FXML
    private void supprimer(){
        if(Objects.equals(ajoutmat.getText(), "")){
            msgerror.setText("Veuillez remplir tous les champs.");
        }
        else{
            try {
                rs = stmt.executeQuery("select * from etudiant where matricule_etu='"+ajoutmat.getText()+"'");
                if(rs.next()) {
                    do {
                        ObsEtu.add(new Etudiant(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4).toString(), rs.getString(5)));
                    } while (rs.next());
                    stmt.executeQuery("delete from etudiant where matricule_etu='"+ajoutmat.getText()+"'");
                    msgerror.setText("");
                    TblEtu.getItems().addAll(ObsEtu);
                    ObsEtu.removeAll(ObsEtu);
                }
                else{
                    msgerror.setText("Matricule Inexistant");
                }
            } catch(SQLDataException e){
                System.out.println(e);
                msgerror.setText("Erreur d'ajout, longueur de caractères dépasse la limite.");
            }
            catch(SQLIntegrityConstraintViolationException e){
                System.out.println(e);
                dialog.setVisible(true);

            }
            catch (SQLException e) {
                System.out.println(e);
                msgerror.setText("Erreur d'ajout, vérifiez que vous avez les permissions.");

            }


        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObsEtu = FXCollections.observableArrayList();
        setCellTable();
        TblEtu.getItems().addAll(ObsEtu);
        try {
            stmt = HelloController.conn.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ObsEtu.removeAll(ObsEtu);

    }


}