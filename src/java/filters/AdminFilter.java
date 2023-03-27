package filters;

import dataaccess.UserDB;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;

/**
 *
 * @author Christian
 */
public class AdminFilter implements Filter {
    
@Override
public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
        throws IOException, ServletException {
    
    HttpServletRequest request = (HttpServletRequest) req;
    HttpSession session = request.getSession(false);
    
    if (session != null && session.getAttribute("email") != null) {
        UserDB userdb = new UserDB();
        User user = userdb.get((String) session.getAttribute("email"));
        
        if (user != null && user.getRole().getRoleId() == 2) {
            HttpServletResponse response = (HttpServletResponse) res;
            response.sendRedirect("notes");
            return;
        }
    }
        
    chain.doFilter(req, res); // execute the servlet
}

@Override
public void init(FilterConfig config) throws ServletException {}

@Override
public void destroy() {}

    
}
