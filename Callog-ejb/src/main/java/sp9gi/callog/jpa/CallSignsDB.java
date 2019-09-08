package sp9gi.callog.jpa;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;


//id; callsign; op. name; data; band; mode; rap send; rap rcv, mail, password
@Entity
@Table(name = "call_table")
public class CallSignsDB {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementator", strategy = "increment")
    private int id;

    @Column(name = "call_sign")
    private String call_sign;

    @Column(name = "operator_name")
    private String operator_name;

    @Column(name = "band")
    private String band;

    @Column(name = "raport_send")
    private String raport_send;

    @Column(name = "raport_received")
    private String raport_received;

    @Column(name = "mail")
    private String mail;

    @Column(name = "password")
    private String password;

    @Column(name = "date")
    private Date date;

    @Column(name = "mode")
    private String mode;

    @Column(name = "confirmation")
    private String confirmation;

    @Column(name = "note")
    private String note;

    public CallSignsDB() {
    }

    public CallSignsDB(String call_sign, String operator_name, String band, String raport_send, String raport_received, String mail, String password, Date date, String mode, String confirmation, String note) {
        this.call_sign = call_sign;
        this.operator_name = operator_name;
        this.band = band;
        this.raport_send = raport_send;
        this.raport_received = raport_received;
        this.mail = mail;
        this.password = password;
        this.date = date;
        this.mode = mode;
        this.confirmation = confirmation;
        this. note = note;
    }

    //Getters and Setters
    public String getCall_sign() {
        return call_sign;
    }

    public void setCall_sign(String call_sign) {
        this.call_sign = call_sign;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOperator_name() {
        return operator_name;
    }

    public void setOperator_name(String operator_name) {
        this.operator_name = operator_name;
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

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

    public String getMode() { return mode; }

    public void setMode(String mode) { this.mode = mode; }

    public String getConfirmation() { return confirmation; }

    public void setConfirmation(String confirmation) { this.confirmation = confirmation; }

    public String getNote() { return note; }

    public void setNote(String note) { this.note = note; }
}

