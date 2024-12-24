/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enotes.controller;

import Enotes.dao.EnotesDAO;
import Enotes.dto.EnotesAddingNotedto;
import Enotes.dto.EnotesShowAllNotesdto;
import Enotes.dto.EnotesUserdto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author acer
 */
public class EnotesDeleteDataServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String title=request.getParameter("title");
            String subject=request.getParameter("subject");
            String content=request.getParameter("content");
            String userid=request.getParameter("name");
            String password=request.getParameter("password");
            
            
            EnotesUserdto temp=new EnotesUserdto();
            temp.setName(userid);
            temp.setPassword(password);
            List<EnotesShowAllNotesdto> list=  EnotesDAO.getAllNOtes(temp);
            System.out.println("list size is "+list.size());
                EnotesAddingNotedto obj=new EnotesAddingNotedto();
                obj.setTitle(title);
                obj.setSubject(subject);
                obj.setContent(content) ;
            
            int result1=EnotesDAO.getRowId(list, obj);
            System.out.println("Row is is "+result1);
            if(result1>0){
                
                
                 boolean result=EnotesDAO.deletingNotes(result1);
                 System.out.println("Row deleted result "+result);
                 if(result==true)
                    out.print("success");
                else
                   out.print("error");
                }
            else {
              out.print("error");
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
