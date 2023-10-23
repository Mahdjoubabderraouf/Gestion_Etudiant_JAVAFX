package com.projectbdd.projectbdd;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
public class OptionPanelEns{
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
        AdminSupprimerEnsCtrl.fxmlLoader = new FXMLLoader(PremiereInterface.class.getResource("adminsupprimerrens.fxml"));
        AdminSupprimerEnsCtrl.scene = new Scene(AdminSupprimerEnsCtrl.fxmlLoader.load(), 1100, 600);
        AdminSupprimerEnsCtrl.stage = new Stage();
        AdminSupprimerEnsCtrl.stage.setTitle("Base de Données");
        AdminSupprimerEnsCtrl.stage.setScene(AdminSupprimerEnsCtrl.scene);
        AdminSupprimerEnsCtrl.stage.setResizable(false);
        AdminSupprimerEnsCtrl.stage.show();

    }
    @FXML
    private void insertens() throws IOException {
        scene.getWindow().hide();
        AdminInsererEnsCtrl.fxmlLoader = new FXMLLoader(PremiereInterface.class.getResource("admininsererens.fxml"));
        AdminInsererEnsCtrl.scene = new Scene(AdminInsererEnsCtrl.fxmlLoader.load(), 1100, 600);
        AdminInsererEnsCtrl.stage = new Stage();
        AdminInsererEnsCtrl.stage.setTitle("Base de Données");
        AdminInsererEnsCtrl.stage.setScene(AdminInsererEnsCtrl.scene);
        AdminInsererEnsCtrl.stage.setResizable(false);
        AdminInsererEnsCtrl.stage.show();

    }
    @FXML
    private void showtbl() throws IOException {
        scene.getWindow().hide();
        AdminConsulterEnsCtrl.fxmlLoader = new FXMLLoader(PremiereInterface.class.getResource("adminconsulterens.fxml"));
        AdminConsulterEnsCtrl.scene = new Scene(AdminConsulterEnsCtrl.fxmlLoader.load(), 1100, 600);
        AdminConsulterEnsCtrl.stage = new Stage();
        AdminConsulterEnsCtrl.stage.setTitle("Base de Données");
        AdminConsulterEnsCtrl.stage.setScene(AdminConsulterEnsCtrl.scene);
        AdminConsulterEnsCtrl.stage.setResizable(false);
        AdminConsulterEnsCtrl.stage.show();
    }
}