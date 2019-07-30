package sp9gi.callog.beans;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;


@ManagedBean
@SessionScoped
public class QslBean implements Serializable {

    private static Logger LOGGER = Logger.getLogger("QslBean");
    private static final long serialVersionUID = 1254145489;
    private BufferedImage image;

    public BufferedImage getTempImage() {
        return tempImage;
    }

    public void setTempImage(BufferedImage tempImage) {
        this.tempImage = tempImage;
    }

    public BufferedImage tempImage;


    public void createQsl() {
        LOGGER.info("odpalono metodę create qsl");
                try {
                    InputStream eqsl = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("resources/qsl/sp9gi_eqsl.png");
       //     String path = externalContext.getRealPath()("resources/qsl/sp9gi_eqsl.png");
                    LOGGER.info("input stream");
//            image = ImageIO.read(new File(path));
                    image = ImageIO.read(eqsl);
        } catch (IOException e) {
            e.printStackTrace();
        }
      //  process(image);
    }

    public byte[] process() {

        InputStream eqsl = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("resources/qsl/sp9gi_eqsl.png");

        LOGGER.info("input stream");

        BufferedImage old = null;
        try {
            old = ImageIO.read(eqsl);
        } catch (IOException e) {
            e.printStackTrace();
        }

        LOGGER.info("odpalono metodę buffered image");
        int w = old.getWidth();
        int h = old.getHeight();
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2d.drawImage(old, 0, 0, w, h, null);
        g2d.setPaint(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 70));
        String cs = "SP9GI/p";
        String da = "12/12/2019";
        String ba = "20m";
        String re = "59";
        String mo = "SSB";

        g2d.drawString(cs, 120, 1000);
        g2d.drawString(da, 500, 1000);
        g2d.drawString(ba, 920, 1000);
        g2d.drawString(re, 1120, 1000);
        g2d.drawString(mo, 1270, 1000);
        g2d.dispose();

//        File outputfile = new File("resources/qsl/sp9gi_eqsl1.png");
//        try {
//            ImageIO.write(img, "png", outputfile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(img, "png", baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] imageToPassAsValueAttr = baos.toByteArray();

        return imageToPassAsValueAttr;
    }

    public void sendMail() {
        LOGGER.info("odpalono metodę sendMail");
        Email email = new SimpleEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("sp9gi.qsl@gmail.com", "adminsp9gi"));
        email.setSSLOnConnect(true);
        try {
            email.setFrom("sp9gi.qsl@gmail.com");
            email.setSubject("TestMail");
            email.setMsg("This is a test mail ... :-)");
            email.addTo("sylwester.sp9gi@gmail.com");
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

}
