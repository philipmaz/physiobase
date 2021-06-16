package pl.physiobase.admin;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/patient/*", "/visit/*"})
public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session= httpRequest.getSession();

        String id=(String)session.getAttribute("id");
        if(id==null){
            HttpServletResponse httpResponse=(HttpServletResponse)response;
            ((HttpServletResponse) response).sendRedirect("/admin/login");
            return;
        }


        filterChain.doFilter(request, response);


    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }

    @Override
    public void destroy() {}


}
