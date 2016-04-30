package ubezpieczalnia.utils;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author layfl
 */
public class SessionManager {

    private static ExternalContext context = null;

    public static ExternalContext getContext() {
        return context;
    }

    public static void initSession() {
        context = FacesContext.getCurrentInstance().getExternalContext();
    }

    public static void addToSession(String name, Object object) {
        initSession();
        if (context == null || context.getSessionMap() == null) {
            return;
        }
        context.getSessionMap().put(name, object);

    }

    public static Object getObjectFromSession(String name) {
        initSession();
        if (context == null) {
            return null;
        }
        Object object = null;
        for (Map.Entry<String, Object> entry : context.getSessionMap().entrySet()) {
            if (entry.getKey().equals(name)) {
                object = entry.getValue();
                break;
            }
        }
        return object;
    }

    public static Object removeObjectFromSession(String name) {
        initSession();
        if (context == null) {
            return null;
        }
        Iterator<String> it = context.getSessionMap().keySet().iterator();
        Object object = getObjectFromSession(name);
        while (it.hasNext()) {
            String val = it.next();
            if (val.equals(name)) {
                it.remove();
            }
        }
        return object;
    }

    public static void destroySession() {
        initSession();
        if(context == null) return;
        context.invalidateSession();
    }

    public static void redirect(String url) {
        initSession();
        if(context == null) return;
        try {
            context.redirect(url);
        } catch (IOException ex) {
            Logger.getLogger(SessionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
