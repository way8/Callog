package sp9gi.callog.beans;

import sp9gi.callog.ejb.CallSignsDAO;
import sp9gi.callog.jpa.CallSignsDB;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="order")
@SessionScoped
public class OrderBean implements Serializable{

    private Logger logger = Logger.getLogger("order");

   private static final long serialVersionUID = 1L;

    private List<CallSignsDB> newsList;


    private static final Order[] orderList = new Order[] {

            new Order("A0001", "Intel CPU", new BigDecimal("700.00"), 1),
            new Order("A0002", "Harddisk 10TB", new BigDecimal("500.00"), 2),
            new Order("A0003", "Dell Laptop", new BigDecimal("11600.00"), 8),
            new Order("A0004", "Samsung LCD", new BigDecimal("5200.00"), 3),
            new Order("A0005", "A4Tech Mouse", new BigDecimal("100.00"), 10)
    };

    public Order[] getOrderList() {

        return orderList;

    }

    public void addData(){
        logger.warning("rozpoczęto dodawanie obiektu");
        CallSignsDAO add = new CallSignsDAO();
        logger.info("utworzono obiekt");
        add.addData1();
        logger.info("odpalono metodę");
    }



    public void loggCheck(){
        logger.warning("kliknięto button warning");
        logger.info("klinieto przycik info");
        System.out.println("kliknieto przyciks sout");

    }

    public List<CallSignsDB> getCallList() {
        logger.warning("rozpoczęto dodawanie obiektu");
        CallSignsDAO gett = new CallSignsDAO();
        logger.info("utworzono obiekt");
        gett.getCallSigns();
        logger.info("odpalono metodę");

        return newsList;
    }

    public void setNewsList(List<CallSignsDB> newsList) {
        this.newsList = newsList;
    }

    public static class Order{

        private Logger logger = Logger.getLogger("Próba::Oran");

        String orderNo;
        String productName;
        BigDecimal price;
        int qty;

        public Order(String orderNo, String productName,
                     BigDecimal price, int qty) {

            this.orderNo = orderNo;
            this.productName = productName;
            this.price = price;
            this.qty = qty;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }


    }
}