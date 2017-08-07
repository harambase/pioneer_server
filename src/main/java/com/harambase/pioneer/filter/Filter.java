package com.harambase.pioneer.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by sky on 2017/7/5.
 */
@WebFilter(filterName = "Filter")
public class Filter implements javax.servlet.Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();

        String uri = request.getRequestURI();
        if (uri.indexOf("Login_action")!=-1){
            chain.doFilter(req,resp);
            return;
        }
        if (uri.indexOf("welcomeStudent")!=-1){
            chain.doFilter(req,resp);
            return;
        }

        if(session.getAttribute("admin") !=null)
            chain.doFilter(request,resp);
        else if(session.getAttribute("member") !=null)
            chain.doFilter(request,resp);
        else {
            request.getRequestDispatcher("/WEB-INF/page/index.jsp").forward(req,resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
