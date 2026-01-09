module org.isep.financialproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens org.isep.financialproject to javafx.fxml;
    exports org.isep.financialproject;
}