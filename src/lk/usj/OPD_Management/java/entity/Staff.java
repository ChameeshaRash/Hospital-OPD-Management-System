package lk.usj.OPD_Management.java.entity;

import java.util.Date;

public abstract class Staff extends User{
    private String staffID;
    private String staffEmail;
    private Date dateOfJoin;
    private String photograph;
    private String document;

    public Staff() {
    }

    public Staff(String username, String name, String gender, String phoneNumber, String idCard, Date dateOfBirth, String address, String maritalStatus, String password, String staffID, String staffEmail, Date dateOfJoin, String photograph, String document) {
        super(username, name, gender, phoneNumber, idCard, dateOfBirth, address, maritalStatus, password);
        this.staffID = staffID;
        this.staffEmail = staffEmail;
        this.dateOfJoin = dateOfJoin;
        this.photograph = photograph;
        this.document = document;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getStaffEmail() {
        return staffEmail;
    }

    public void setStaffEmail(String staffEmail) {
        this.staffEmail = staffEmail;
    }

    public Date getDateOfJoin() {
        return dateOfJoin;
    }

    public void setDateOfJoin(Date dateOfJoin) {
        this.dateOfJoin = dateOfJoin;
    }

    public String getPhotograph() {
        return photograph;
    }

    public void setPhotograph(String photograph) {
        this.photograph = photograph;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    @Override
    public String toString() {
        return "StaffDTO{" +
                "staffID='" + staffID + '\'' +
                ", staffEmail='" + staffEmail + '\'' +
                ", dateOfJoin=" + dateOfJoin +
                ", photograph='" + photograph + '\'' +
                ", document='" + document + '\'' +
                '}';
    }
}
