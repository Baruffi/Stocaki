package Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginController {
    public LoginController() { }
    public void showGerenteHomeView() throws Exception {
        Stage HomeStage = new Stage();
        Parent home = FXMLLoader.load(getClass().getResource("/View/GerenteHomeView.fxml"));
        HomeStage.setTitle("Gerente Home");
        HomeStage.setScene(new Scene(home, 800, 800));
        HomeStage.show();
    }
}
