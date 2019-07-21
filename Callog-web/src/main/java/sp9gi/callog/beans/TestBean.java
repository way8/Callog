package sp9gi.callog.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.logging.Logger;

@ManagedBean
@SessionScoped
public class TestBean {
  // private static final long serialVersionUID = 123212334;
    private static Logger LOGGER = Logger.getLogger("TestBean");

    int a;
    int c;

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public void loggCheck() {
        LOGGER.warning("kliknięto button warning");
        LOGGER.info("klinieto przycik info");
        System.out.println("kliknieto przyciks sout");
    }

    public String number() {
      //  c = a + 2;
       // return c;
        return "page1";
    }

    @Override
    public String toString() {
        return "TestBean{}";
    }

    public static Logger getLOGGER() {
        return LOGGER;
    }

    public static void setLOGGER(Logger LOGGER) {
        TestBean.LOGGER = LOGGER;
    }
}
