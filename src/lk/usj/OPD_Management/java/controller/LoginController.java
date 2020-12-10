package lk.usj.OPD_Management.java.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.usj.OPD_Management.java.common.Common;
import lk.usj.OPD_Management.java.common.tool.ButtonFireForEnterSetter;
import lk.usj.OPD_Management.java.common.tool.GlobalBoolean;
import lk.usj.OPD_Management.java.dto.AdminDTO;
import lk.usj.OPD_Management.java.dto.UserDTO;
import lk.usj.OPD_Management.java.service.BOFactory;
import lk.usj.OPD_Management.java.service.custom.LoginBO;

public class LoginController implements Initializable{

    private LoginBO loginBO;
    @FXML
    private HBox root;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField usernameTxt1;

    @FXML
    private JFXPasswordField passwordTxt1;

    @FXML
    private JFXButton loginButton1;

    @FXML
    void usernameTxt1_onAction(ActionEvent event){
        passwordTxt1.requestFocus();
    }

    @FXML
    void passwordTxt1_onAction(ActionEvent event){
        loginButton1.requestFocus();
    }

    @FXML
    void loginButton1_onAction(ActionEvent event){
        try {
            if (loginBO.isValidPassword(new AdminDTO(usernameTxt1.getText(), passwordTxt1.getText()))) {
                try {
                    Parent parent = FXMLLoader.load(this.getClass().getResource("/lk/usj/OPD_Management/resources/view/admin_base.fxml"));
                    Scene scene = new Scene(parent);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Dashboard");
                    stage.centerOnScreen();
                    stage.setResizable(false);
                    //stage.setMaximized(true);
                    stage.show();

                    ((Node)(event.getSource())).getScene().getWindow().hide();


                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Common.showError("Invalid Email name or password.");
            }
        } catch (NullPointerException e) {
            Common.showMessage("This Email is no longer available.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GlobalBoolean.setLock(false);
        ButtonFireForEnterSetter.setGlobalEventHandler(root);
        usernameTxt1.clear();
        passwordTxt1.clear();
        loginBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.LOG_IN);

        FadeTransition fade = new FadeTransition(Duration.seconds(3), root);

        fade.setFromValue(0);
        fade.setToValue(2);
        fade.play();
    }
}
