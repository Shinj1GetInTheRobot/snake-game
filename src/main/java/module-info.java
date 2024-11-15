module zen {
    requires javafx.controls;
    requires javafx.fxml;

    opens zen to javafx.fxml;
    exports zen;
}
