module org.isep.financialproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.fasterxml.jackson.databind;
    requires javafx.graphics;
    requires javafx.base;

    opens org.isep.financialproject to javafx.fxml;
    exports org.isep.financialproject;
}