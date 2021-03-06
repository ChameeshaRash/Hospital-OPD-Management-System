package lk.usj.OPD_Management.java.dao.custom.Impl;

import lk.usj.OPD_Management.java.dao.custom.ReceptionistDAO;
import lk.usj.OPD_Management.java.entity.Admin;
import lk.usj.OPD_Management.java.entity.Doctor;
import lk.usj.OPD_Management.java.entity.Receptionist;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReceptionistDAOImpl implements ReceptionistDAO {
    @Override
    public boolean save(Receptionist var1) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = format.format(var1.getDateOfBirth());
        String joinDate = format.format(var1.getDateOfJoin());

        String wantedLine = var1.getUsername()+"#"+var1.getName()+"#"+var1.getGender()+"#"+var1.getPhoneNumber()+"#"+var1.getIdCard()+"#"+
                strDate+"#"+var1.getAddress()+"#"+var1.getMaritalStatus()+"#"+var1.getPassword()+"#"+var1.getStaffID()+"#"+
                var1.getStaffEmail()+"#"+joinDate+"#"+var1.getPhotograph()+"#"+var1.getDocument()+"#"+var1.getNote();
        File file = new File("Receptionist.txt");
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
    public boolean update(Receptionist var1) throws Exception {

        try{
            String username, name, gender, phoneNumber, idCard,dateOfBirth, address, maritalStatus, password;
            String staffId;
            String staffEmail;
            String dateOfJoin;
            String photograph;
            String document;
            String note;
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            File file = new File("Receptionist.txt");
            if (!file.exists()) {//checking the is given file exists

                file.createNewFile();//creating new file
                Exception fileError =new IOException("File is not founded");
                System.out.println(fileError);
            }
            Scanner scanner =new Scanner(file);

            List<Receptionist> receptionists = new ArrayList<>();

            while(scanner.hasNextLine()){
                String line =scanner.nextLine();
                String[] details = line.split("#");
                username=details[0];
                name=details[1];
                gender=details[2];
                phoneNumber=details[3];
                idCard=details[4];
                dateOfBirth=details[5];
                address=details[6];
                maritalStatus=details[7];
                password=details[8];
                staffId=details[9];
                staffEmail=details[10];
                dateOfJoin=details[11];
                photograph=details[12];
                document=details[13];
                note =details[14];


                String[] dateArray = dateOfBirth.split("/");
                Date birthDate = new GregorianCalendar(Integer.parseInt(dateArray[2]), Integer.parseInt(dateArray[1]) - 1, Integer.parseInt(dateArray[0])).getTime();
                String[] joinDateArray = dateOfJoin.split("/");
                Date joinDate = new GregorianCalendar(Integer.parseInt(joinDateArray[2]), Integer.parseInt(joinDateArray[1]) - 1, Integer.parseInt(joinDateArray[0])).getTime();
                Receptionist receptionist= new Receptionist(username, name, gender, phoneNumber, idCard,birthDate, address, maritalStatus, password,staffId,staffEmail,joinDate,photograph,document,note);
                receptionists.add(receptionist);

            }

            for (Receptionist r:receptionists){
                if(r.getUsername().equals(var1.getUsername())){
                    r.setName(var1.getName());
                    r.setGender(var1.getGender());
                    r.setPhoneNumber(var1.getPhoneNumber());
                    r.setDateOfBirth(var1.getDateOfBirth());
                    r.setAddress(var1.getAddress());
                    r.setMaritalStatus(var1.getMaritalStatus());
                    r.setPassword(var1.getPassword());
                    r.setStaffID(var1.getStaffID());
                    r.setStaffEmail(var1.getStaffEmail());
                    r.setNote(var1.getNote());
                    r.setPhotograph(var1.getPhotograph());
                    r.setDocument(var1.getDocument());
                }
            }

            PrintWriter writer = new PrintWriter(file);
            writer.print("");
            writer.close();

            if (!file.exists()) {//checking the is given file exists

                file.createNewFile();//creating new file
                Exception fileError =new IOException("File is not founded");
                System.out.println(fileError);
            }

            FileWriter fw = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fw);


            for (Receptionist r:receptionists){
                String strDate = format.format(r.getDateOfBirth());
                String joinDate = format.format(r.getDateOfJoin());

                String wantedLine = r.getUsername()+"#"+r.getName()+"#"+r.getGender()+"#"+r.getPhoneNumber()+"#"+r.getIdCard()+"#"+
                        strDate+"#"+r.getAddress()+"#"+r.getMaritalStatus()+"#"+r.getPassword()+"#"+r.getStaffID()+"#"+
                        r.getStaffEmail()+"#"+joinDate+"#"+r.getPhotograph()+"#"+r.getDocument()+"#"+r.getNote();
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
            String username, name, gender, phoneNumber, idCard,dateOfBirth, address, maritalStatus, password;
            String staffId;
            String staffEmail;
            String dateOfJoin;
            String photograph;
            String document;
            String note;
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            File file = new File("Receptionist.txt");
            if (!file.exists()) {//checking the is given file exists

                file.createNewFile();//creating new file
                Exception fileError =new IOException("File is not founded");
                System.out.println(fileError);
            }
            Scanner scanner =new Scanner(file);

            List<Receptionist> receptionists = new ArrayList<>();

            while(scanner.hasNextLine()){
                String line =scanner.nextLine();
                String[] details = line.split("#");
                username=details[0];
                name=details[1];
                gender=details[2];
                phoneNumber=details[3];
                idCard=details[4];
                dateOfBirth=details[5];
                address=details[6];
                maritalStatus=details[7];
                password=details[8];
                staffId=details[9];
                staffEmail=details[10];
                dateOfJoin=details[11];
                photograph=details[12];
                document=details[13];
                note =details[14];


                String[] dateArray = dateOfBirth.split("/");
                Date birthDate = new GregorianCalendar(Integer.parseInt(dateArray[2]), Integer.parseInt(dateArray[1]) - 1, Integer.parseInt(dateArray[0])).getTime();
                String[] joinDateArray = dateOfJoin.split("/");
                Date joinDate = new GregorianCalendar(Integer.parseInt(joinDateArray[2]), Integer.parseInt(joinDateArray[1]) - 1, Integer.parseInt(joinDateArray[0])).getTime();
                Receptionist receptionist= new Receptionist(username, name, gender, phoneNumber, idCard,birthDate, address, maritalStatus, password,staffId,staffEmail,joinDate,photograph,document,note);
                receptionists.add(receptionist);

            }

            PrintWriter writer = new PrintWriter(file);
            writer.print("");
            writer.close();

            if (!file.exists()) {//checking the is given file exists

                file.createNewFile();//creating new file
                Exception fileError =new IOException("File is not founded");
                System.out.println(fileError);
            }

            FileWriter fw = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fw);


            for (Receptionist r:receptionists){
                if (r.getUsername().equals(var1)){
                    continue;
                }else {
                    String strDate = format.format(r.getDateOfBirth());
                    String joinDate = format.format(r.getDateOfJoin());

                    String wantedLine = r.getUsername() + "#" + r.getName() + "#" + r.getGender() + "#" + r.getPhoneNumber() + "#" + r.getIdCard() + "#" +
                            strDate + "#" + r.getAddress() + "#" + r.getMaritalStatus() + "#" + r.getPassword() + "#" + r.getStaffID() + "#" +
                            r.getStaffEmail() + "#" + joinDate + "#" + r.getPhotograph() + "#" + r.getDocument() + "#" + r.getNote();
                    bw.write(wantedLine);
                    bw.newLine();
                }
            }

            bw.close();
            return true;



        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Receptionist search(String var1) throws Exception {
        try{
            String username, name, gender, phoneNumber, idCard,dateOfBirth, address, maritalStatus, password;
            String staffId;
            String staffEmail;
            String dateOfJoin;
            String photograph;
            String document;
            String note;
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            File file = new File("Receptionist.txt");
            if (!file.exists()) {//checking the is given file exists

                file.createNewFile();//creating new file
                Exception fileError =new IOException("File is not founded");
                System.out.println(fileError);
            }
            Scanner scanner =new Scanner(file);

            List<Receptionist> receptionists = new ArrayList<>();

            while(scanner.hasNextLine()){
                String line =scanner.nextLine();
                String[] details = line.split("#");
                username=details[0];
                name=details[1];
                gender=details[2];
                phoneNumber=details[3];
                idCard=details[4];
                dateOfBirth=details[5];
                address=details[6];
                maritalStatus=details[7];
                password=details[8];
                staffId=details[9];
                staffEmail=details[10];
                dateOfJoin=details[11];
                photograph=details[12];
                document=details[13];
                note =details[14];

                try {
                    Date birthDate =format.parse(dateOfBirth);
                    Date joinDate =format.parse(dateOfJoin);
                    Receptionist receptionist= new Receptionist(username, name, gender, phoneNumber, idCard,birthDate, address, maritalStatus, password,staffId,staffEmail,joinDate,photograph,document,note);
                    receptionists.add(receptionist);
                }catch (ParseException e){
                    e.printStackTrace();
                }
            }
            for (Receptionist r:receptionists){
                if(r.getUsername().equals(var1)){
                    return r;
                }
            }

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Receptionist> getAll() throws Exception {
        try{
            String username, name, gender, phoneNumber, idCard,dateOfBirth, address, maritalStatus, password;
            String staffId;
            String staffEmail;
            String dateOfJoin;
            String photograph;
            String document;
            String note;
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            File file = new File("Receptionist.txt");
            if (!file.exists()) {//checking the is given file exists

                file.createNewFile();//creating new file
                Exception fileError =new IOException("File is not founded");
                System.out.println(fileError);
            }
            Scanner scanner =new Scanner(file);

            ArrayList<Receptionist> receptionists = new ArrayList<>();

            while(scanner.hasNextLine()){
                String line =scanner.nextLine();
                String[] details = line.split("#");
                username=details[0];
                name=details[1];
                gender=details[2];
                phoneNumber=details[3];
                idCard=details[4];
                dateOfBirth=details[5];
                address=details[6];
                maritalStatus=details[7];
                password=details[8];
                staffId=details[9];
                staffEmail=details[10];
                dateOfJoin=details[11];
                photograph=details[12];
                document=details[13];
                note =details[14];


                try {
                    Date birthDate =format.parse(dateOfBirth);
                    Date joinDate =format.parse(dateOfJoin);
                    Receptionist receptionist= new Receptionist(username, name, gender, phoneNumber, idCard,birthDate, address, maritalStatus, password,staffId,staffEmail,joinDate,photograph,document,note);
                    receptionists.add(receptionist);
                }catch (ParseException e){
                    e.printStackTrace();
                }
            }
            return receptionists;

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getLastReceptionistID() throws Exception {
        try{
            String staffId;
            File file = new File("Receptionist.txt");
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
                staffId=details[9];
                return Integer.parseInt(staffId.substring(1));
            }



        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return 0;
    }
}
