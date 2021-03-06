package lk.usj.OPD_Management.java.service.custom;

import lk.usj.OPD_Management.java.dto.AppointmentDTO;
import lk.usj.OPD_Management.java.dto.DoctorDTO;
import lk.usj.OPD_Management.java.service.SuperBO;

import java.util.ArrayList;
import java.util.Date;

public interface AppointmentBO extends SuperBO {
    int getNextAppointmentID()throws Exception;

    boolean addAppointment(AppointmentDTO appointmentDTO)throws Exception;

    ArrayList<AppointmentDTO> getPendingAppointmentList()throws Exception;

    int getNextAppointmentNo(String doctorUsername) throws Exception;

    void approveAppointment(String appointmentID,String appointmentNo) throws Exception;

    ArrayList<AppointmentDTO> getApprovedAppointmentList()throws Exception;

    boolean updateAppointment(AppointmentDTO appointmentDTO)throws Exception;

    ArrayList<AppointmentDTO> getAppointmentList() throws Exception;

    boolean deleteAppointment(String appointmentId)throws Exception;

    int countTodayAppointments() throws Exception;

    ArrayList<AppointmentDTO> getApprovedAppointmentListUsingDoctorUsername(String username)throws Exception;

    void completeAppointment(String text) throws Exception;

    int countPendingAppointmentsUsingDoctorUsername(String doctorUserName)throws Exception;

    int countApproveAppointmentsUsingDoctorUsername(String doctorUserName)throws Exception;

    int countCompletedAppointmentsUsingDoctorUsername(String doctorUserName) throws Exception;

    ArrayList<AppointmentDTO> loadThisDoctorAppointmentTable(String doctorUserName) throws Exception;

    ArrayList<AppointmentDTO> getTodayAppointmentListUsingPatientUsername(String patientUsername) throws Exception;

    ArrayList<AppointmentDTO> getAppointmentUsingDateRange(Date fromDate, Date toDate) throws Exception;

    ArrayList<AppointmentDTO> getAppointmentListUsingPatientUsername(String patientUsername)throws Exception;
}
