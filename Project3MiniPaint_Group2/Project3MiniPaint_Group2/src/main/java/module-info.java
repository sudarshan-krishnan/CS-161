module main {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.desktop;
	requires javafx.graphics;

    opens project to javafx.fxml;
    exports project;
}
