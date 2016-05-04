package ubezpieczalnia.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ubezpieczalnia.controllers.PageController;

/**
 *
 * @author layfl
 */
public class PageFilter implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String uri = ((HttpServletRequest) request).getRequestURI();
        String splitPattern = "\\?";
        String[] path = uri.split("faces");
        
        String[] clearPath;
        if(path.length > 1) clearPath = path[1].split(splitPattern);
        else clearPath = path;
        
        if (!PageController.isPageExist(clearPath[0])) {
            ((HttpServletResponse) response).sendRedirect("/Prodzekt-war/faces/index.xhtml?faces-redirect=true");
        }
        else chain.doFilter(request, response);
    }
    
    @Override
    public void destroy() {
    }
    
}
