module com.projectbdd.projectbdd {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires ojdbc6;


    opens com.projectbdd.projectbdd to javafx.fxml;
    exports com.projectbdd.projectbdd;
}