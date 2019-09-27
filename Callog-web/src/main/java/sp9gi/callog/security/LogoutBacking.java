package sp9gi.callog.security;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.util.logging.Logger;

@Named
@RequestScoped
public class LogoutBacking {
    private static Logger LOGGER = Logger.getLogger("LogoutBacking");
    public String submit() {
        LOGGER.info("Odpalono submit w LogoutBacking");
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/home.xhtml?faces-redirect=true";
    }
}