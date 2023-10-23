package com.projectbdd.projectbdd;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.sql.*;
import java.util.Objects;
public class HelloController {
    static Connection conn=null;
    static Statement stmt = null;
    static FXMLLoader fxmlLoader;
    static Stage stage;

    static Scene scene;

    @FXML
    private Label msgcnx;
    @FXML
    private TextField utilisateur;
    @FXML
    private PasswordField mdp;
    @FXML
    protected void handleKeyPress(KeyEvent event) {
          if (event.getCode() == KeyCode.ENTER) {
            if (event.getSource() == utilisateur) {
              mdp.requestFocus(); // Move focus to the password field
         } else {
        SeConnecter();
        }
         }
    }
    @FXML
    public void initialize() {
        utilisateur.setOnKeyPressed(this::handleKeyPress);
        mdp.setOnKeyPressed(this::handleKeyPress);
    }
    @FXML
    protected void SeConnecter() {
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:workr",utilisateur.getText(),mdp.getText());
            stmt = conn.createStatement();
            msgcnx.setText("Connexion réussi!");

            //fermer la première fenêtre
            if(Objects.equals(utilisateur.getText().toLowerCase(), "bddadmin")) {
                mdp.getScene().getWindow().hide();
                BddController.fxmlLoader = new FXMLLoader(PremiereInterface.class.getResource("bdd.fxml"));
                BddController.scene = new Scene(BddController.fxmlLoader.load(), 1100, 600);
                BddController.stage = new Stage();
                BddController.stage.setTitle("Base de Données");
                BddController.stage.setScene(BddController.scene);
                BddController.stage.setResizable(false);
                BddController.stage.show();
            }
            else if(Objects.equals(utilisateur.getText().toLowerCase(), "enseignant")){
                mdp.getScene().getWindow().hide();
                //interface enseignant
                EnseignantController.fxmlLoader = new FXMLLoader(PremiereInterface.class.getResource("enseignant.fxml"));
                EnseignantController.scene = new Scene(EnseignantController.fxmlLoader.load(), 1100, 600);
                EnseignantController.stage = new Stage();
                EnseignantController.stage.setTitle("Base de Données");
                EnseignantController.stage.setScene(EnseignantController.scene);
                EnseignantController.stage.setResizable(false);
                EnseignantController.stage.show();
            }
            else if(Objects.equals(utilisateur.getText().toLowerCase(), "etudiant")){
                mdp.getScene().getWindow().hide();
                //interface etudiant
                EtudiantController.fxmlLoader = new FXMLLoader(PremiereInterface.class.getResource("etudiant.fxml"));
                EtudiantController.scene = new Scene(EtudiantController.fxmlLoader.load(), 1100, 600);
                EtudiantController.stage = new Stage();
                EtudiantController.stage.setTitle("Base de Données");
                EtudiantController.stage.setScene(EtudiantController.scene);
                EtudiantController.stage.setResizable(false);
                EtudiantController.stage.show();

            }else {
                throw new SQLException();
            }

        }
        catch(SQLException e){
            msgcnx.setText("Mot de passe ou utilisateur erronée.");
            System.out.println("MDP INCORRECT");
        } catch(Exception e){
            e.printStackTrace();
        }
    }

}