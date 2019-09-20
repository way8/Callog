package sp9gi.callog.beans;


import com.sun.javafx.binding.StringFormatter;
import sp9gi.callog.ejb.CallSignsDAO;
import sp9gi.callog.jpa.CallSignsDB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@ManagedBean
@SessionScoped
public class NewCallBean implements Serializable {

    private static final long serialVersionUID = 94235189;

    private int id;
    private String call_sign;
    private String operator_name;
    private String band;
    private String raport_send;
    private String raport_received;
    private String mail;
    private String password;
    private Date date;
    private String mode;
    private String confirmation;
    private String note;
    private int removeId;
    private static Logger LOGGER = Logger.getLogger("NewCallBean");

    //przesyła dane do DAO i otwiera stronę z listą
    public String addData() {

        CallSignsDAO add = new CallSignsDAO();

        add.addData2(this.call_sign, this.operator_name, this.band, this.raport_send, this.raport_received, this.mail, this.password, this.date, this.mode, this.confirmation, this.note);
        return "logbook";
    }

    //method to fill in the from in updateCall.xhtml
    public void showCall(){
        CallSignsDAO show = new CallSignsDAO();
        LOGGER.info("ustawiono id w showcall na " + id);
        //we get only one position form DB, so we always want to get index 0 from the list
        call_sign = show.getSingleCallSign(id).get(0).getCall_sign();
        operator_name = show.getSingleCallSign(id).get(0).getOperator_name();
        band = show.getSingleCallSign(id).get(0).getBand();
        raport_send = show.getSingleCallSign(id).get(0).getRaport_send();
        raport_received = show.getSingleCallSign(id).get(0).getRaport_received();
        mail = show.getSingleCallSign(id).get(0).getMail();
        date = show.getSingleCallSign(id).get(0).getDate();
        mode = show.getSingleCallSign(id).get(0).getMode();
        confirmation = show.getSingleCallSign(id).get(0).getConfirmation();
        note = show.getSingleCallSign(id).get(0).getNote();
        LOGGER.info("pobrano " + id + " " + call_sign + " " + operator_name);


    }

    public String updateCall() {
        CallSignsDAO update = new CallSignsDAO();
        update.updateDB(this.id, this.call_sign, this.operator_name, this.band, this.raport_send, this.raport_received, this.mail, this.date, this.mode, this.confirmation, this.note);

        return "logbook";
    }

    public void deleteCall() {
        CallSignsDAO delete = new CallSignsDAO();
        delete.deleteCall(this.id);
    }
//ustawia wartośc id dla strony updateCall ()
    public String updateId (int idUpd){
        this.id = idUpd;
        LOGGER.info("ustawiono id na " + id);
        return "updateCall";
    }

    //Getters and setters

    public int getRemoveId() {
        return removeId;
    }

    public void setRemoveId(int removeId) {
        this.removeId = removeId;
        CallSignsDAO delete = new CallSignsDAO();
        delete.deleteCall(removeId);
    }

    public String getCall_sign() {
        return call_sign;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCall_sign(String call_sign) {
        this.call_sign = call_sign;
    }

    public String getOperator_name() {
        return operator_name;
    }

    public void setOperator_name(String operator_name) {
        this.operator_name = operator_name;
    }

//    public String getContact_date() { return contact_date; }
//
//    public void setContact_date(String contact_date) {
//        this.contact_date = contact_date;
//    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public String getRaport_send() {
        return raport_send;
    }

    public void setRaport_send(String raport_send) {
        this.raport_send = raport_send;
    }

    public String getRaport_received() {
        return raport_received;
    }

    public void setRaport_received(String raport_received) {
        this.raport_received = raport_received;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

    public String getMode() { return mode; }

    public void setMode(String mode) { this.mode = mode; }

    public String getConfirmation() { return confirmation; }

    public void setConfirmation(String confirmation) { this.confirmation = confirmation; }

    public String getNote() { return note; }

    public void setNote(String note) { this.note = note; }
}
