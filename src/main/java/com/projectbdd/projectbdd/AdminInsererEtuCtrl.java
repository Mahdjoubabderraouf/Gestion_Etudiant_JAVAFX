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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminInsererEtuCtrl implements Initializable {
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
    TextField ajoutnom;
    @FXML
    TextField ajoutprenom;
    @FXML
    TextField ajoutdate;
    @FXML
    TextField ajoutadr;
    @FXML
    Label msgerror;

    static ObservableList<Etudiant> ObsEtu;
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
    private void inserer(){
        if(Objects.equals(ajoutmat.getText(), "") || Objects.equals(ajoutnom.getText(), "") || Objects.equals(ajoutprenom.getText(), "") || Objects.equals(ajoutdate.getText(), "") || Objects.equals(ajoutadr.getText(), "")){
            msgerror.setText("Veuillez remplir tous les champs.");
        }
        else{
            try {
                stmt.executeQuery("insert into etudiant values ('"+ajoutmat.getText()+"','"+ajoutnom.getText()+"','"+ajoutprenom.getText()+"',TO_DATE ('"+ajoutdate.getText().replaceAll("\\s", "-")+"', 'DD-MM-YYYY'),'"+ajoutadr.getText()+"')");
                ObsEtu.add(new Etudiant(ajoutmat.getText(),ajoutnom.getText(),ajoutprenom.getText(), ajoutdate.getText().replaceAll("\\s", "-"),ajoutadr.getText()));
                TblEtu.getItems().addAll(ObsEtu);
                ObsEtu.removeAll(ObsEtu);
                msgerror.setText("");
            } catch(SQLDataException e){
                System.out.println(e);
                msgerror.setText("Erreur d'ajout, vérifiez votre saisie (format de date par exemple)");
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