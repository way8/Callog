package sp9gi.callog.beans;


import sp9gi.callog.ejb.CallSignsDAO;
import sp9gi.callog.jpa.CallSignsDB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class NewCallBean {

    private int id;
    private String call_sign;
    private String operator_name;
    private String contact_date;
    private String band;
    private String raport_send;
    private String raport_received;
    private String mail;
    private String password;




    //przesyła dane do DAO i otwiera stronę startową
    public String addData() {

        CallSignsDAO add = new CallSignsDAO();

        add.addData2(this.call_sign, this.operator_name, this.contact_date, this.band, this.raport_send, this.raport_received, this.mail, this.password);
        return "default";
    }

    //method to fill in the from in updateCall.xhtml
    public void updateCall(){
        CallSignsDAO update = new CallSignsDAO();

        //we get only one position form DB, so we always want to get index 0 from the list
        call_sign = update.getSingleCallSign(id).get(0).getCall_sign();
        operator_name = update.getSingleCallSign(id).get(0).getOperator_name();
        contact_date = update.getSingleCallSign(id).get(0).getContact_date();
        band = update.getSingleCallSign(id).get(0).getBand();
        raport_send = update.getSingleCallSign(id).get(0).getRaport_send();
        raport_received = update.getSingleCallSign(id).get(0).getRaport_received();
        mail = update.getSingleCallSign(id).get(0).getMail();

    }


    //Getters and setters

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

    public String getContact_date() {
        return contact_date;
    }

    public void setContact_date(String contact_date) {
        this.contact_date = contact_date;
    }

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
}
