module com.example.corenexus {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.corenexus to javafx.fxml;
    exports com.example.corenexus;
}