module com.mycompany.proyectadconectores {
    requires javafx.controls;
    requires javafx.fxml;
				requires java.desktop;

    opens controladores to javafx.fxml;
    exports controladores;
}
