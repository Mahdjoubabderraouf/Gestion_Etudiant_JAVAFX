package com.projectbdd.projectbdd;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
public class OptionPanelEtu{
    static FXMLLoader fxmlLoader;
    static Stage stage;
    static Scene scene;

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
    private void removeens() throws IOException {
        scene.getWindow().hide();
        AdminSupprimerEtuCtrl.fxmlLoader = new FXMLLoader(PremiereInterface.class.getResource("adminsupprimerretu.fxml"));
        AdminSupprimerEtuCtrl.scene = new Scene(AdminSupprimerEtuCtrl.fxmlLoader.load(), 1100, 600);
        AdminSupprimerEtuCtrl.stage = new Stage();
        AdminSupprimerEtuCtrl.stage.setTitle("Base de Données");
        AdminSupprimerEtuCtrl.stage.setScene(AdminSupprimerEtuCtrl.scene);
        AdminSupprimerEtuCtrl.stage.setResizable(false);
        AdminSupprimerEtuCtrl.stage.show();

    }
    @FXML
    private void insertens() throws IOException {
        scene.getWindow().hide();
        AdminInsererEtuCtrl.fxmlLoader = new FXMLLoader(PremiereInterface.class.getResource("admininsereretu.fxml"));
        AdminInsererEtuCtrl.scene = new Scene(AdminInsererEtuCtrl.fxmlLoader.load(), 1100, 600);
        AdminInsererEtuCtrl.stage = new Stage();
        AdminInsererEtuCtrl.stage.setTitle("Base de Données");
        AdminInsererEtuCtrl.stage.setScene(AdminInsererEtuCtrl.scene);
        AdminInsererEtuCtrl.stage.setResizable(false);
        AdminInsererEtuCtrl.stage.show();

    }
    @FXML
    private void showtbl() throws IOException {
        scene.getWindow().hide();
        AdminConsulterEtuCtrl.fxmlLoader = new FXMLLoader(PremiereInterface.class.getResource("adminconsulteretu.fxml"));
        AdminConsulterEtuCtrl.scene = new Scene(AdminConsulterEtuCtrl.fxmlLoader.load(), 1100, 600);
        AdminConsulterEtuCtrl.stage = new Stage();
        AdminConsulterEtuCtrl.stage.setTitle("Base de Données");
        AdminConsulterEtuCtrl.stage.setScene(AdminConsulterEtuCtrl.scene);
        AdminConsulterEtuCtrl.stage.setResizable(false);
        AdminConsulterEtuCtrl.stage.show();
    }
}