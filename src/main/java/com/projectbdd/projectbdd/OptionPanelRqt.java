package com.projectbdd.projectbdd;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

public class OptionPanelRqt implements Initializable {
    static ObservableList<Etudiant> Obs;
    static Statement stmt = null;
    static ResultSet rs = null;
    static FXMLLoader fxmlLoader;
    static Stage stage;
    static Scene scene;
    @FXML
    MenuButton selector;
    @FXML
    Label msgerror;
    @FXML
    Label rqst1;
    @FXML
    Label rqst2;
    @FXML
    TableView<Etudiant> Tbl;
    @FXML
    TableColumn<Etudiant,String> colnometu;
    @FXML
    TableColumn<Etudiant,String> colprenometu;

    @FXML
    private void retourner() throws IOException {
        scene.getWindow().hide();
        BddController.fxmlLoader = new FXMLLoader(PremiereInterface.class.getResource("bdd.fxml"));
        BddController.scene = new Scene(BddController.fxmlLoader.load(), 1100, 600);
        BddController.stage = new Stage();
        BddController.stage.setTitle("Base de Données");
        BddController.stage.setScene(BddController.scene);
        BddController.stage.setResizable(false);
        BddController.stage.show();
    }
    @FXML
    protected void requestchange1(){
        selector.setText(rqst1.getText());
    }
    @FXML
    protected void requestchange2(){
        selector.setText(rqst2.getText());
    }
    @FXML
    protected void executerequest(){
        if(Objects.equals(selector.getText(), "Choisir la requette à exécuter")){
            msgerror.setText("Veuillez choisir une requette.");
        }
        else {
            try {
                if(Objects.equals(selector.getText(), rqst1.getText()))
                    rs = stmt.executeQuery("SELECT NOM_ETU, PRENOM_ETU FROM ETUDIANT WHERE MATRICULE_ETU IN (SELECT MATRICULE_ETU FROM ETUDIANTUNITE WHERE NOTE_EXAMEN=20)");
                else
                    rs = stmt.executeQuery("SELECT NOM_ETU,PRENOM_ETU FROM ETUDIANT WHERE MATRICULE_ETU NOT IN (SELECT MATRICULE_ETU FROM ETUDIANTUNITE WHERE CODE_UNITE IN ( SELECT CODE_UNITE FROM UNITE WHERE LIBELLE='POO' ))");
                Obs.removeAll(Obs);
                while(rs.next()){
                    Obs.add(new Etudiant(rs.getString(1), rs.getString(2)));
                }
                Tbl.getItems().clear();
                Tbl.getItems().addAll(Obs);
                msgerror.setText("");
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
    private void setCellTable(){
        colprenometu.setCellValueFactory(new PropertyValueFactory<>("prenom_etu"));
        colnometu.setCellValueFactory(new PropertyValueFactory<>("nom_etu"));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Obs = FXCollections.observableArrayList();
        try{
            stmt = HelloController.conn.createStatement();
            Tbl.getItems().clear();
        }
        catch(SQLException x){
            System.out.println("PERMISSION NOT GRANTED.");
        }
        setCellTable();
    }

}