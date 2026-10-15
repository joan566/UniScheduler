package org.unischeduler.ui.components.sidebar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.unischeduler.ui.app.AppContext;
import org.unischeduler.ui.app.AppNavigator;
import org.unischeduler.ui.app.SceneManager;

public class AdminSidebarController {

    @FXML
    private void goToDashboard(ActionEvent event) {

        SceneManager.loadPage(
                "/ui/fxml/pages/dashboard/AdminDashboardView.fxml"
        );
    }

    @FXML
    private void goToAcademicPeriods(ActionEvent event) {

        SceneManager.loadPage(
                "/ui/fxml/pages/academic_period/AcademicPeriodView.fxml"
        );
    }

    @FXML
    private void goToCourses(ActionEvent event) {

        SceneManager.loadPage(
                "/ui/fxml/pages/course/CourseView.fxml"
        );
    }

    @FXML
    private void goToGroups(ActionEvent event) {

        SceneManager.loadPage(
                "/ui/fxml/pages/group/GroupView.fxml"
        );
    }

    @FXML
    private void goToStudents(ActionEvent event) {

        SceneManager.loadPage(
                "/ui/fxml/pages/student/StudentView.fxml"
        );
    }

    @FXML
    private void goToProfile(ActionEvent event) {

        SceneManager.loadPage(
                "/ui/fxml/pages/profile/AdminProfileView.fxml"
        );
    }

    @FXML
    private void goToLogin(ActionEvent event) {
        AppContext.setCurrentUser(null);
        AppNavigator.navigateToLogin();
    }
}