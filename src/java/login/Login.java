/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import bean.AdmbSerranaBean;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import renderizacao.Renderizacao;

@WebFilter(urlPatterns = {"/faces/logado/*"})
public class Login implements Filter {
    @Inject
    private AdmbSerranaBean asb;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if (asb.getSetores().getLogin() == null) {            
            res.sendRedirect(req.getServletContext().getContextPath() + "/");
        } else {            
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }

 
    

}
