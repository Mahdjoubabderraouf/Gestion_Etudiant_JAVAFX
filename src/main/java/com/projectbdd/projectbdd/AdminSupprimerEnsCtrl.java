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

public class AdminSupprimerEnsCtrl implements Initializable {
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
    Label msgerror;

    static ObservableList<Enseignant> ObsEns;
    static Statement stmt = null;
    static ResultSet rs = null;
    static FXMLLoader fxmlLoader;
    static Stage stage;
    static Scene scene;
    @FXML
    DialogPane dialog;


    private void setCellTable(){
        colprenomens.setCellValueFactory(new PropertyValueFactory<>("prenom_ens"));
        colnomens.setCellValueFactory(new PropertyValueFactory<>("nom_ens"));
        colmatens.setCellValueFactory(new PropertyValueFactory<>("matricule"));
    }
    @FXML
    private void retourner() throws IOException {
        scene.getWindow().hide();
        OptionPanelEns.fxmlLoader = new FXMLLoader(PremiereInterface.class.getResource("PanneauEns.fxml"));
        OptionPanelEns.scene = new Scene(OptionPanelEns.fxmlLoader.load(), 1100, 600);
        OptionPanelEns.stage = new Stage();
        OptionPanelEns.stage.setTitle("Base de Données");
        OptionPanelEns.stage.setScene(OptionPanelEns.scene);
        OptionPanelEns.stage.setResizable(false);
        OptionPanelEns.stage.show();

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
                rs = stmt.executeQuery("select * from enseignant where matricule_ens='"+ajoutmat.getText()+"'");
                if(rs.next()) {
                    do {
                        ObsEns.add(new Enseignant(rs.getString(2), rs.getString(3), rs.getString(1)));
                    } while (rs.next());
                    stmt.executeQuery("delete from enseignant where matricule_ens='"+ajoutmat.getText()+"'");
                    TblEns.getItems().addAll(ObsEns);
                    ObsEns.removeAll(ObsEns);
                    msgerror.setText("");
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