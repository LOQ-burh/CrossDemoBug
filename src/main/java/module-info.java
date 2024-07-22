module com.demo.mainapp.crossyroaddemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires annotations;
    requires javafx.media;
    requires jdk.jfr;

    opens com.demo.mainapp.crossyroaddemo to javafx.fxml;
    exports com.demo.mainapp.crossyroaddemo;
    exports com.demo.mainapp.crossyroaddemo.ui;
    opens com.demo.mainapp.crossyroaddemo.ui to javafx.fxml;
    exports com.demo.mainapp.crossyroaddemo.Score;
    opens com.demo.mainapp.crossyroaddemo.Score to javafx.fxml;
    exports com.demo.mainapp.crossyroaddemo.Config;
    opens com.demo.mainapp.crossyroaddemo.Config to javafx.fxml;
}