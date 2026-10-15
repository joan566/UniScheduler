package org.unischeduler.ui.app;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.unischeduler.ui.layouts.MainLayoutController;

import java.io.IOException;


public final class AppNavigator {

    private static Stage primaryStage;

    private AppNavigator() {}

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    public static void navigateToLogin() {
        navigateTo("/ui/fxml/pages/auth/LoginView.fxml");
    }

    public static void navigateToMainLayout() {
        navigateTo("/ui/fxml/layouts/MainLayout.fxml");
    }

    private static void navigateTo(String fxmlPath) {

        try {

            Parent root = FXMLLoader.load(
                    AppNavigator.class.getResource(fxmlPath)
            );

            primaryStage.setScene(
                    new Scene(root, 1400, 800)
            );

            primaryStage.show();

        } catch (IOException e) {
            throw new RuntimeException(
                    "Error cargando vista: " + fxmlPath,
                    e
            );
        }
    }
}