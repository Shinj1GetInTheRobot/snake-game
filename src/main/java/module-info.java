module zen {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;

    opens zen.controllers to javafx.fxml;

    exports zen;
}
