package sp9gi.callog.beans;

import org.apache.commons.mail.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;


/**
 *
 */

@ManagedBean
@SessionScoped
public class QslBean implements Serializable {

    //dependency injection
    @ManagedProperty(value = "#{newCallBean}")
    private NewCallBean newCallBean;

    private static Logger LOGGER = Logger.getLogger("QslBean");
    private static final long serialVersionUID = 1254145489;
    ByteArrayOutputStream baos;

    public NewCallBean getNewCallBean() {
        return newCallBean;
    }

    public void setNewCallBean(NewCallBean newCallBean) {
        this.newCallBean = newCallBean;
    }

    /**
     *
     * @return
     */
    public byte[] process() {
        //image to InputStream
        InputStream eqsl = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("resources/qsl/sp9gi_eqsl.png");

        LOGGER.info("input stream");

        BufferedImage old = null;
        try {
            old = ImageIO.read(eqsl);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //drawing on the image
        LOGGER.info("odpalono  buffered image");
        int w = old.getWidth();
        int h = old.getHeight();
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2d.drawImage(old, 0, 0, w, h, null);
        g2d.setPaint(Color.BLUE);
        g2d.setFont(new Font("Rockness", Font.BOLD, 47));
        String cs = newCallBean.getCall_sign();
        //Date to String and subtract one day because of some failure
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//        Calendar c = Calendar.getInstance();
//        c.setTime(newCallBean.getDate());
//        c.add(Calendar.DAY_OF_MONTH, -1);
//        Date DateMinusOne = c.getTime();

        String da = formatter.format(newCallBean.getDate());
        String ba = newCallBean.getBand();
        String re = newCallBean.getRaport_received();
        String mo = newCallBean.getMode();

        g2d.drawString(cs, 70, 667);
        g2d.drawString(da, 325, 667);
        g2d.drawString(ba, 610, 667);
        g2d.drawString(re, 750, 667);
        g2d.drawString(mo, 850, 667);
        g2d.dispose();

        //saving image on the server in standalone/data folder
        File dataDir = new File(System.getProperty("jboss.server.data.dir"));
        File outputfile = new File(dataDir, "sp9gi_eqsl1.png");
        try {
            ImageIO.write(img, "png", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //generating ByteArrayOutputStream in which will by save image
        baos = new ByteArrayOutputStream();
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

        // Create the attachment
        EmailAttachment attachment = new EmailAttachment();
        attachment.setPath(System.getProperty("jboss.server.data.dir") + "/sp9gi_eqsl1.png");
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription("eQSL");
        attachment.setName("SP9GI");

        MultiPartEmail email = new MultiPartEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("sp9gi.qsl@gmail.com", "adminsp9gi"));
        email.setSSLOnConnect(true);
        try {
            email.setFrom("sp9gi.qsl@gmail.com");
            email.setSubject("eQSL");
            email.setMsg("This is your eQSL card - regards SP9GI");
            email.addTo(newCallBean.getMail());
            // add the attachment
            email.attach(attachment);
            // send the email
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }

    }

}
