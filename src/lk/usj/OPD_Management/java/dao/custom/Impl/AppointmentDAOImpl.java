package lk.usj.OPD_Management.java.dao.custom.Impl;

import lk.usj.OPD_Management.java.dao.DAOFactory;
import lk.usj.OPD_Management.java.dao.custom.AppointmentDAO;
import lk.usj.OPD_Management.java.dao.custom.DoctorDAO;
import lk.usj.OPD_Management.java.dao.custom.PatientDAO;
import lk.usj.OPD_Management.java.entity.Appointment;
import lk.usj.OPD_Management.java.entity.Doctor;
import lk.usj.OPD_Management.java.entity.Patient;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class AppointmentDAOImpl implements AppointmentDAO {
    private PatientDAO patientDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PATIENT);
    private DoctorDAO doctorDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.DOCTOR);
    @Override
    public boolean save(Appointment var1) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = format.format(var1.getAppointmentDate());

        String wantedLine = var1.getAppointmentId()+"#"+var1.getPatient().getUsername()+"#"+var1.getDoctor().getUsername()+"#"+var1.getAppointmentNo()+"#"+strDate+"#"+
                var1.getAppointmentTime()+"#"+var1.getSymptoms()+"#"+var1.getStatus();
        File file = new File("Appointment.txt");
        if (!file.exists()) {//checking the is given file exists

            file.createNewFile();//creating new file
            Exception fileError =new IOException("File is not founded");
            System.out.println(fileError);
        }
        FileWriter fw = new FileWriter(file,true);

        BufferedWriter bw = new BufferedWriter(fw);

        try {
            bw.write(wantedLine);
            bw.newLine();
            bw.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Appointment var1) throws Exception {
        try{
            String appointmentID, patient, doctor, appointmentNo, date, time, symptoms, currentStatus;
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            File file = new File("Appointment.txt");
            if (!file.exists()) {//checking the is given file exists

                file.createNewFile();//creating new file
                Exception fileError = new IOException("File is not founded");
                System.out.println(fileError);
            }
            Scanner scanner = new Scanner(file);

            ArrayList<Appointment> appointments = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] details = line.split("#");
                appointmentID = details[0];
                patient = details[1];
                doctor = details[2];
                appointmentNo = details[3];
                date = details[4];
                time = details[5];
                symptoms = details[6];
                currentStatus = details[7];


                String[] dateArray = date.split("/");
                Date appointmentDate = new GregorianCalendar(Integer.parseInt(dateArray[2]), Integer.parseInt(dateArray[1]) - 1, Integer.parseInt(dateArray[0])).getTime();
                int intAppointmentNo = Integer.parseInt(appointmentNo);
                Patient patient1 = patientDAO.search(patient);
                Doctor doctor1 = doctorDAO.search(doctor);

                Appointment appointment = new Appointment(appointmentID, patient1, doctor1, intAppointmentNo, appointmentDate, time, symptoms, currentStatus);
                appointments.add(appointment);
            }
            for (Appointment appointment : appointments) {
                if (appointment.getAppointmentId().equals(var1.getAppointmentId())) {
                    appointment.setAppointmentDate(var1.getAppointmentDate());
                    appointment.setAppointmentTime(var1.getAppointmentTime());
                    appointment.setDoctor(var1.getDoctor());
                    appointment.setSymptoms(var1.getSymptoms());
                }
            }

            PrintWriter writer = new PrintWriter(file);
            writer.print("");
            writer.close();

            File file1 = new File("Appointment.txt");

            if (!file1.exists()) {//checking the is given file exists

                file1.createNewFile();//creating new file
                Exception fileError = new IOException("File is not founded");
                System.out.println(fileError);
            }

            FileWriter fw = new FileWriter(file1, true);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Appointment a : appointments) {
                String strDate = format.format(a.getAppointmentDate());

                String wantedLine = a.getAppointmentId() + "#" + a.getPatient().getUsername() + "#" + a.getDoctor().getUsername() + "#" + a.getAppointmentNo() + "#" + strDate + "#" +
                        a.getAppointmentTime() + "#" + a.getSymptoms() + "#" + a.getStatus();

                bw.write(wantedLine);
                bw.newLine();

            }
            bw.close();
            return true;

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String var1) throws Exception {
        try{
            String appointmentID, patient, doctor, appointmentNo, date, time, symptoms, currentStatus;
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            File file = new File("Appointment.txt");
            if (!file.exists()) {//checking the is given file exists

                file.createNewFile();//creating new file
                Exception fileError = new IOException("File is not founded");
                System.out.println(fileError);
            }
            Scanner scanner = new Scanner(file);

            ArrayList<Appointment> appointments = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] details = line.split("#");
                appointmentID = details[0];
                patient = details[1];
                doctor = details[2];
                appointmentNo = details[3];
                date = details[4];
                time = details[5];
                symptoms = details[6];
                currentStatus = details[7];


                if (var1.equals(appointmentID)){
                    continue;
                }else {
                    String[] dateArray = date.split("/");
                    Date appointmentDate = new GregorianCalendar(Integer.parseInt(dateArray[2]), Integer.parseInt(dateArray[1]) - 1, Integer.parseInt(dateArray[0])).getTime();
                    int intAppointmentNo = Integer.parseInt(appointmentNo);
                    Patient patient1 = patientDAO.search(patient);
                    Doctor doctor1 = doctorDAO.search(doctor);

                    Appointment appointment = new Appointment(appointmentID, patient1, doctor1, intAppointmentNo, appointmentDate, time, symptoms, currentStatus);
                    appointments.add(appointment);
                }

            }

            PrintWriter writer = new PrintWriter(file);
            writer.print("");
            writer.close();

            File file1 = new File("Appointment.txt");

            if (!file1.exists()) {//checking the is given file exists

                file1.createNewFile();//creating new file
                Exception fileError = new IOException("File is not founded");
                System.out.println(fileError);
            }

            FileWriter fw = new FileWriter(file1, true);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Appointment a : appointments) {
                String strDate = format.format(a.getAppointmentDate());

                String wantedLine = a.getAppointmentId() + "#" + a.getPatient().getUsername() + "#" + a.getDoctor().getUsername() + "#" + a.getAppointmentNo() + "#" + strDate + "#" +
                        a.getAppointmentTime() + "#" + a.getSymptoms() + "#" + a.getStatus();

                bw.write(wantedLine);
                bw.newLine();

            }
            bw.close();
            return true;

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Appointment search(String var1) throws Exception {
        return null;
    }

    @Override
    public ArrayList<Appointment> getAll() throws Exception {
        try{
            String appointmentID, patient, doctor, appointmentNo, date,time, symptoms,currentStatus;
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            File file = new File("Appointment.txt");
            if (!file.exists()) {//checking the is given file exists

                file.createNewFile();//creating new file
                Exception fileError =new IOException("File is not founded");
                System.out.println(fileError);
            }
            Scanner scanner =new Scanner(file);

            ArrayList<Appointment> appointments = new ArrayList<>();

            while(scanner.hasNextLine()){
                String line =scanner.nextLine();
                String[] details = line.split("#");
                appointmentID=details[0];
                patient=details[1];
                doctor=details[2];
                appointmentNo=details[3];
                date=details[4];
                time=details[5];
                symptoms=details[6];
                currentStatus=details[7];



                String[] dateArray = date.split("/");
                Date appointmentDate = new GregorianCalendar(Integer.parseInt(dateArray[2]), Integer.parseInt(dateArray[1]) - 1, Integer.parseInt(dateArray[0])).getTime();
                int intAppointmentNo= Integer.parseInt(appointmentNo);
                Patient patient1= patientDAO.search(patient);
                Doctor doctor1=doctorDAO.search(doctor);

                Appointment appointment= new Appointment(appointmentID,patient1,doctor1,intAppointmentNo,appointmentDate,time,symptoms,currentStatus);
                appointments.add(appointment);

            }
            return appointments;

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getLastAppointmentID() throws Exception {
        try{
            String appointmentId;
            File file = new File("Appointment.txt");
            if (!file.exists()) {//checking the is given file exists

                file.createNewFile();//creating new file
                Exception fileError =new IOException("File is not founded");
                System.out.println(fileError);
            }
            Scanner scanner =new Scanner(file);

            String last = null;

            while(scanner.hasNextLine()){
                last=scanner.nextLine();
            }
            if (last == null){
                return 0;
            }else {
                String[] details = last.split("#");
                appointmentId=details[0];
                return Integer.parseInt(appointmentId.substring(1));
            }



        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public ArrayList<Appointment> getAllAppointmentUsingStatus(String status) throws Exception {
        try{
            String appointmentID, patient, doctor, appointmentNo, date,time, symptoms,currentStatus;
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            File file = new File("Appointment.txt");
            if (!file.exists()) {//checking the is given file exists

                file.createNewFile();//creating new file
                Exception fileError =new IOException("File is not founded");
                System.out.println(fileError);
            }
            Scanner scanner =new Scanner(file);

            ArrayList<Appointment> appointments = new ArrayList<>();

            while(scanner.hasNextLine()){
                String line =scanner.nextLine();
                String[] details = line.split("#");
                appointmentID=details[0];
                patient=details[1];
                doctor=details[2];
                appointmentNo=details[3];
                date=details[4];
                time=details[5];
                symptoms=details[6];
                currentStatus=details[7];


                if (currentStatus.equals(status)){
                    String[] dateArray = date.split("/");
                    Date appointmentDate = new GregorianCalendar(Integer.parseInt(dateArray[2]), Integer.parseInt(dateArray[1]) - 1, Integer.parseInt(dateArray[0])).getTime();
                    int intAppointmentNo= Integer.parseInt(appointmentNo);
                    Patient patient1= patientDAO.search(patient);
                    Doctor doctor1=doctorDAO.search(doctor);

                    Appointment appointment= new Appointment(appointmentID,patient1,doctor1,intAppointmentNo,appointmentDate,time,symptoms,currentStatus);
                    appointments.add(appointment);
                }else {
                    continue;
                }
            }
            return appointments;

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getNextAppointmentNo(String doctorUsername) throws Exception {
        int count =1;
        try{
            Doctor doc = doctorDAO.search(doctorUsername);
            String appointmentID, patient, doctor, appointmentNo, date,time, symptoms,currentStatus;
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            File file = new File("Appointment.txt");
            if (!file.exists()) {//checking the is given file exists

                file.createNewFile();//creating new file
                Exception fileError =new IOException("File is not founded");
                System.out.println(fileError);
            }
            Scanner scanner =new Scanner(file);

            ArrayList<Appointment> appointments = new ArrayList<>();

            while(scanner.hasNextLine()){
                String line =scanner.nextLine();
                String[] details = line.split("#");
                appointmentID=details[0];
                patient=details[1];
                doctor=details[2];
                appointmentNo=details[3];
                date=details[4];
                time=details[5];
                symptoms=details[6];
                currentStatus=details[7];


                if (doc.getUsername().equals(doctor) && currentStatus.equals("Approved")){
                    count++;
                }else {
                    continue;
                }
            }
            return count;

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public void approveAppointment(String currentAppointmentID,String currentAppointmentNo) throws Exception {
        try {
            String appointmentID, patient, doctor, appointmentNo, date, time, symptoms, currentStatus;
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            File file = new File("Appointment.txt");
            if (!file.exists()) {//checking the is given file exists

                file.createNewFile();//creating new file
                Exception fileError = new IOException("File is not founded");
                System.out.println(fileError);
            }
            Scanner scanner = new Scanner(file);

            ArrayList<Appointment> appointments = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] details = line.split("#");
                appointmentID = details[0];
                patient = details[1];
                doctor = details[2];
                appointmentNo = details[3];
                date = details[4];
                time = details[5];
                symptoms = details[6];
                currentStatus = details[7];


                String[] dateArray = date.split("/");
                Date appointmentDate = new GregorianCalendar(Integer.parseInt(dateArray[2]), Integer.parseInt(dateArray[1]) - 1, Integer.parseInt(dateArray[0])).getTime();
                int intAppointmentNo = Integer.parseInt(appointmentNo);
                Patient patient1 = patientDAO.search(patient);
                Doctor doctor1 = doctorDAO.search(doctor);

                Appointment appointment = new Appointment(appointmentID, patient1, doctor1, intAppointmentNo, appointmentDate, time, symptoms, currentStatus);
                appointments.add(appointment);
            }
            for (Appointment appointment : appointments) {
                if (appointment.getAppointmentId().equals(currentAppointmentID)) {
                    appointment.setStatus("Approve");
                    appointment.setAppointmentNo(Integer.parseInt(currentAppointmentNo));
                }
            }

            PrintWriter writer = new PrintWriter(file);
            writer.print("");
            writer.close();

            File file1 = new File("Appointment.txt");

            if (!file1.exists()) {//checking the is given file exists

                file1.createNewFile();//creating new file
                Exception fileError = new IOException("File is not founded");
                System.out.println(fileError);
            }

            FileWriter fw = new FileWriter(file1, true);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Appointment a : appointments) {
                String strDate = format.format(a.getAppointmentDate());

                String wantedLine = a.getAppointmentId() + "#" + a.getPatient().getUsername() + "#" + a.getDoctor().getUsername() + "#" + a.getAppointmentNo() + "#" + strDate + "#" +
                        a.getAppointmentTime() + "#" + a.getSymptoms() + "#" + a.getStatus();

                bw.write(wantedLine);
                bw.newLine();

            }
            bw.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public int countTodayAppointments() throws Exception {
        try{
            int count =0;
            String date;
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date today = new Date();
            String todayDate = format.format(today);
            File file = new File("Appointment.txt");
            if (!file.exists()) {//checking the is given file exists

                file.createNewFile();//creating new file
                Exception fileError =new IOException("File is not founded");
                System.out.println(fileError);
            }
            Scanner scanner =new Scanner(file);

            ArrayList<Appointment> appointments = new ArrayList<>();

            while(scanner.hasNextLine()){
                String line =scanner.nextLine();
                String[] details = line.split("#");
                date=details[4];

                if (date.equals(todayDate)){
                    count++;
                }

            }
            return count;

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void completeAppointment(String text) throws Exception {
        try {
            String appointmentID, patient, doctor, appointmentNo, date, time, symptoms, currentStatus;
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            File file = new File("Appointment.txt");
            if (!file.exists()) {//checking the is given file exists

                file.createNewFile();//creating new file
                Exception fileError = new IOException("File is not founded");
                System.out.println(fileError);
            }
            Scanner scanner = new Scanner(file);

            ArrayList<Appointment> appointments = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] details = line.split("#");
                appointmentID = details[0];
                patient = details[1];
                doctor = details[2];
                appointmentNo = details[3];
                date = details[4];
                time = details[5];
                symptoms = details[6];
                currentStatus = details[7];


                String[] dateArray = date.split("/");
                Date appointmentDate = new GregorianCalendar(Integer.parseInt(dateArray[2]), Integer.parseInt(dateArray[1]) - 1, Integer.parseInt(dateArray[0])).getTime();
                int intAppointmentNo = Integer.parseInt(appointmentNo);
                Patient patient1 = patientDAO.search(patient);
                Doctor doctor1 = doctorDAO.search(doctor);

                Appointment appointment = new Appointment(appointmentID, patient1, doctor1, intAppointmentNo, appointmentDate, time, symptoms, currentStatus);
                appointments.add(appointment);
            }
            for (Appointment appointment : appointments) {
                if (appointment.getAppointmentId().equals(text)) {
                    appointment.setStatus("Completed");
                }
            }

            PrintWriter writer = new PrintWriter(file);
            writer.print("");
            writer.close();

            File file1 = new File("Appointment.txt");

            if (!file1.exists()) {//checking the is given file exists

                file1.createNewFile();//creating new file
                Exception fileError = new IOException("File is not founded");
                System.out.println(fileError);
            }

            FileWriter fw = new FileWriter(file1, true);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Appointment a : appointments) {
                String strDate = format.format(a.getAppointmentDate());

                String wantedLine = a.getAppointmentId() + "#" + a.getPatient().getUsername() + "#" + a.getDoctor().getUsername() + "#" + a.getAppointmentNo() + "#" + strDate + "#" +
                        a.getAppointmentTime() + "#" + a.getSymptoms() + "#" + a.getStatus();

                bw.write(wantedLine);
                bw.newLine();

            }
            bw.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int countPendingAppointmentsUsingDoctorUsername(String doctorUserName, String status) throws Exception {
        try{
            int count=0;
            String doctor,currentStatus;
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            File file = new File("Appointment.txt");
            if (!file.exists()) {//checking the is given file exists

                file.createNewFile();//creating new file
                Exception fileError =new IOException("File is not founded");
                System.out.println(fileError);
            }
            Scanner scanner =new Scanner(file);

            while(scanner.hasNextLine()){
                String line =scanner.nextLine();
                String[] details = line.split("#");
                doctor=details[2];
                currentStatus=details[7];

                if (doctor.equals(doctorUserName) && currentStatus.equals(status)){
                    count++;
                }
            }
            return count;

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return 0;
    }

}
