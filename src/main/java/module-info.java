module com.example.algos1projetgroupe {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.algos1projetgroupe to javafx.fxml;
    exports com.example.algos1projetgroupe;
}