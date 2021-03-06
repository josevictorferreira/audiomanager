
package com.utfpr.audiomanager.controller;

import com.google.gson.Gson;
import com.utfpr.audiomanager.dao.UsuarioDao;
import com.utfpr.audiomanager.model.Usuario;
import com.utfpr.audiomanager.util.HashingUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "BasicAuthFilter",
        urlPatterns = {"/api/audio2/*"})
public class BasicAuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        String header = httpRequest.getHeader("Authorization");
        if(header.contains("Basic")) {
            String basicAuthEncoded = header.substring(6);
            String basicAuthAsString = new String(Base64.getDecoder()
                    .decode(basicAuthEncoded.getBytes()));
            
            Usuario resultUsuario = new UsuarioDao()
                .getUsuarioByEmail(basicAuthAsString.split(":")[0]);
            
            boolean isSenhaValid = HashingUtil
                    .validateHashedPassword(basicAuthAsString.split(":")[1],
                            resultUsuario.getSenha());
            
            if (isSenhaValid) {
                httpRequest.setAttribute("usuario", resultUsuario);
                chain.doFilter(httpRequest,httpResponse);
            }
        } else {
            response.setContentType("application/json;charset=UTF-8");
            httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }  
    }

    @Override
    public void destroy() { }
    
}
