/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enotes.controller;

import Enotes.dao.EnotesDAO;
import Enotes.dto.EnotesAddingNotedto;
import Enotes.dto.EnotesShowAllNotesdto;
import Enotes.dto.EnotesUpdatingNotesdto;
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
public class EnotesUpdateDataServlet extends HttpServlet {

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
            String oldtitle=request.getParameter("oldtitle");
            String newtitle=request.getParameter("newtitle");
            String oldsubject=request.getParameter("oldsubject");
            String newsubject=request.getParameter("newsubject");
            String oldcontent=request.getParameter("oldcontent");
            String newcontent=request.getParameter("newcontent");
            String name=request.getParameter("name");
            String password=request.getParameter("password");
            EnotesUpdatingNotesdto obj=new EnotesUpdatingNotesdto();
            obj.setOldTitle(oldtitle);
            obj.setNewTitle(newtitle);
            obj.setOldSubject(oldsubject);
            obj.setNewSubject(newsubject);
            obj.setOldContent(oldcontent);
            obj.setNewContent(newcontent);
            obj.setName(name);
            obj.setPassword(password);
            
            
            EnotesUserdto temp=new EnotesUserdto();
            temp.setName(name);
            temp.setPassword(password);
            List<EnotesShowAllNotesdto> list=  EnotesDAO.getAllNOtes(temp);
            System.out.println("list size is "+list.size());
            EnotesAddingNotedto tempObj=new EnotesAddingNotedto();
                tempObj.setTitle(oldtitle);
                tempObj.setSubject(oldsubject);
                tempObj.setContent(oldcontent) ;
            
            int selfId=EnotesDAO.getRowId(list, tempObj);
            System.out.println("Row is is "+selfId);
            if(selfId>0){
                  boolean result=EnotesDAO.updatingNotes(selfId,obj);
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
