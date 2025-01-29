module org.example.appweather {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires jdk.jsobject;
    requires okhttp3;
    requires com.fasterxml.jackson.databind;
    requires org.json;
    requires annotations;
    requires java.desktop;
    requires jdk.management.jfr;
    requires org.junit.jupiter.api;

    opens org.app_weather to javafx.fxml;
    exports org.app_weather;
}