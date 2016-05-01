package ubezpieczalnia.controllers;

import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;
import ubezpieczalnia.utils.SessionManager;

/**
 *
 * @author layfl
 */
@ManagedBean
@RequestScoped
public class ErrorController {

    private String errorDefined = null;

    public String getError() {
        return this.errorDefined == null ? "" : (String) SessionManager.removeObjectFromSession(this.errorDefined);
    }

    public String getDefinedError() {
        return this.errorDefined;
    }

    public boolean hasError(String errorName) {
        this.errorDefined = errorName;
        Object error = SessionManager.getObjectFromSession(errorName);
        return error != null && ((String) error).length() > 0;
    }

}
