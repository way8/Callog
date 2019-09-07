package sp9gi.callog.beans;

import sp9gi.callog.ejb.CallSignsDAO;
import sp9gi.callog.jpa.CallSignsDB;
import sp9gi.callog.security.LoginBacking;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean(name= "calls")
@SessionScoped
public class CallBean implements Serializable{


    private static final long serialVersionUID = 1L;
    private Logger logger = Logger.getLogger("calls");

//    private List<CallSignsDB> List;


    public void addData(){
        logger.warning("rozpoczęto dodawanie obiektu");
        CallSignsDAO add = new CallSignsDAO();
        logger.info("utworzono obiekt");
        add.addData1();
        logger.info("odpalono metodę");
    }


    public List<CallSignsDB> getCallList() {
        logger.warning("rozpoczęto dodawanie obiektu");
        CallSignsDAO gett = new CallSignsDAO();
        logger.info("utworzono obiekt");
        return gett.getCallSigns();
    }

    public List<CallSignsDB> getUserCallList() {
        logger.warning("rozpoczęto dodawanie obiektu");
        CallSignsDAO gett = new CallSignsDAO();
        logger.info("utworzono obiekt");
        return gett.getUserCallSigns("SP9OZI");
    }



//    public void setNewsList(List<CallSignsDB> newsList) {
//        this.newsList = newsList;
//    }

 }