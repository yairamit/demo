module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires json.simple;
    requires java.desktop;

    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    exports com.example.demo.model;
    opens com.example.demo.model to javafx.fxml;
    exports com.example.demo.client.view;
    opens com.example.demo.client.view to javafx.fxml;
    exports com.example.demo.server;
    opens com.example.demo.server to javafx.fxml;
    exports com.example.demo.client;
    opens com.example.demo.client to javafx.fxml;
    exports com.example.demo.server.controller;
    opens com.example.demo.server.controller to javafx.fxml;
    exports com.example.demo.client.model;
    opens com.example.demo.client.model to javafx.fxml;
    exports com.example.demo.server.model;
    opens com.example.demo.server.model to javafx.fxml;
}