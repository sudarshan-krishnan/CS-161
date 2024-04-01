module main {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;

    opens lab to javafx.fxml;
    exports lab;
}
