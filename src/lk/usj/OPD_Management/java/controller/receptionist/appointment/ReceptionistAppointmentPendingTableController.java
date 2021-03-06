package lk.usj.OPD_Management.java.controller.receptionist.appointment;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.usj.OPD_Management.java.common.Common;
import lk.usj.OPD_Management.java.controller.receptionist.appointment.ReceptionistAppointmentEditAppointmentController;
import lk.usj.OPD_Management.java.dto.AppointmentDTO;
import lk.usj.OPD_Management.java.service.custom.AppointmentBO;
import lk.usj.OPD_Management.java.service.custom.impl.AppointmentBOImpl;

public class ReceptionistAppointmentPendingTableController implements Initializable {
    private AppointmentBO appointmentBO=new AppointmentBOImpl();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<AppointmentDTO> pendingAppointmentTable;

    @FXML
    void pendingAppointmentTable_MouseEvent(MouseEvent event) throws Exception{
        AppointmentDTO appointmentDTO=(pendingAppointmentTable.getSelectionModel().getSelectedItem());
        if(appointmentDTO == null){
            Common.showWarning("Please select Appointment records");
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/usj/OPD_Management/resources/view/receptionist/receptionist_appointments_editAppointment.fxml"));
        Parent root = loader.load();
        ReceptionistAppointmentEditAppointmentController receptionistAppointmentEditAppointmentController = loader.getController();
        receptionistAppointmentEditAppointmentController.transferMessage(appointmentDTO);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        //stage.setTitle("");
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();


        Pane pane= FXMLLoader.load(this.getClass().getResource("/lk/usj/OPD_Management/resources/view/receptionist/receptionist_appointment_pendingTable.fxml"));
        this.root.getChildren().setAll(pane);

    }


    private void loadPendingAppointmentTable() throws Exception {
        pendingAppointmentTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        pendingAppointmentTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("patientName"));
        pendingAppointmentTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("doctorName"));
        pendingAppointmentTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("appointmentDate"));
        pendingAppointmentTable.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("appointmentTime"));
        pendingAppointmentTable.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("patientPhone"));
        pendingAppointmentTable.setItems(FXCollections.observableArrayList(appointmentBO.getPendingAppointmentList()));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadPendingAppointmentTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
