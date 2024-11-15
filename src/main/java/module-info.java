module zen {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens zen to javafx.fxml;
    exports zen;
}
