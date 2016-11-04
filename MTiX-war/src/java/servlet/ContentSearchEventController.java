/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.stateless.contentmanagement.WebsiteManagementBeanLocal;

/**
 *
 * @author JingYing
 */
@WebServlet(name = "ContentSearchEventController", urlPatterns = {"/ContentSearchEventController"})
public class ContentSearchEventController extends HttpServlet {
     @EJB
    private WebsiteManagementBeanLocal webManagementBean;

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
        
            String keyword = request.getParameter("search");;
            String companyLogo = webManagementBean.getCompanyLogo();
            List<ArrayList> propertyData = webManagementBean.getAllPropertyName();
            
            List<ArrayList> byTypes = webManagementBean.searchEngineBasedOnTypes(keyword);
            List<ArrayList> byEvents = webManagementBean.searchEvents(keyword);
            
            System.out.println("BY EVENT SIZE IS " + byEvents.size());
            request.setAttribute("byTypes", byTypes);
            request.setAttribute("byEvents", byEvents);
            request.setAttribute("propertyData", propertyData);
            request.setAttribute("CompanyLogo", companyLogo);
            request.getRequestDispatcher("/searchEngine.jsp").forward(request, response); 
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
