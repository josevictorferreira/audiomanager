/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utfpr.audiomanager.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author josevictor
 */
@WebServlet(name = "Index", urlPatterns = {"/index"})
public class Index extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String eTagFromBrowser = request.getHeader("If-None-Match");
        String eTagFromServer = getETag(request);

        if (eTagFromServer.equals(eTagFromBrowser)) {
            // retornar código 304
            response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
            return;
        }

        response.addHeader("ETag", getETag(request));
        getServletContext()
                .getRequestDispatcher("/index.jsp")
                .forward(request, response);        
    }
    
    private String getETag (HttpServletRequest request) {
        String uploadPath = request.getServletContext().getRealPath("")
                    + File.separator + "index.jsp";
        Path path = Paths.get(uploadPath);

        File file = new File(uploadPath);
        if (!file.exists()) {
            try {
                throw new ServletException("File doesn't exists on server.");
            } catch (ServletException ex) {
                Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            try {
                byte[] sha1 = messageDigest.digest(Files.readAllBytes(path));
                return DatatypeConverter.printHexBinary(sha1);
            } catch (IOException ex) {
                Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "errado" + path.toString();
  }
}
