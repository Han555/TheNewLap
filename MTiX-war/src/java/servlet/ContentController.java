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
import manager.RegisterManager;
import session.stateless.commoninfrastucture.RegisterSessionLocal;
import session.stateless.contentmanagement.WebsiteManagementBeanLocal;

/**
 *
 * @author JingYing
 */
@WebServlet(name = "ContentController", urlPatterns = {"/ContentController", "/ContentController/*"})
public class ContentController extends HttpServlet {

    @EJB
    private WebsiteManagementBeanLocal webManagementBean;
    @EJB
    private RegisterSessionLocal registerSession;

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

//        System.err.println("******** ContentController: " + request.getServletPath());
//        System.err.println("******** ContentController: " + request.getContextPath());
//        System.err.println("******** ContentController: " + request.getPathInfo());
        System.err.println("******** ContentController: " + request.getRequestURI());

        String requestURL = request.getRequestURI();
        String url[] = requestURL.split("/");
        System.err.println(url[4]);
        String action = url[4];
        String company = url[3];
        RegisterManager registerManager = new RegisterManager(registerSession);

        //String action = request.getParameter("action");
        if (action.equals("Home")) {
            List<ArrayList> data = webManagementBean.getWebpageList(company);
            String companyLogo = webManagementBean.getCompanyLogo(company);
            List<ArrayList> propertyData = webManagementBean.getAllPropertyName(company);
            request.setAttribute("companyname", company);
            request.setAttribute("propertyData", propertyData);
            request.setAttribute("CompanyLogo", companyLogo);
            request.setAttribute("data", data);
            request.getRequestDispatcher("/home.jsp").forward(request, response);
        } else if (action.equals("viewEventWebpage")) {
            String id = url[5];
            ArrayList data = webManagementBean.getEventWebpageInfo(id);
            List<ArrayList> sessions = webManagementBean.getEventSessionInfo(id);
            List<ArrayList> promotions = webManagementBean.getEventPromotionInfo(id);

            String companyLogo = webManagementBean.getCompanyLogo(company);
            List<ArrayList> propertyData = webManagementBean.getAllPropertyName(company);
            request.setAttribute("propertyData", propertyData);
            request.setAttribute("CompanyLogo", companyLogo);
            request.setAttribute("companyname", company);
            request.setAttribute("data", data);
            request.setAttribute("promotions", promotions);
            request.setAttribute("sessions", sessions);
            request.getRequestDispatcher("/viewEventWebpage.jsp").forward(request, response);
        } else if (action.equals("creditCardPromotion")) {
            List<ArrayList> data = webManagementBean.getCreditCardEvents(company);
            String companyLogo = webManagementBean.getCompanyLogo(company);
            List<ArrayList> propertyData = webManagementBean.getAllPropertyName(company);
            request.setAttribute("companyname", company);
            request.setAttribute("propertyData", propertyData);
            request.setAttribute("CompanyLogo", companyLogo);
            request.setAttribute("data", data);
            request.getRequestDispatcher("/creditCardPromotion.jsp").forward(request, response);
        } else if (action.equals("volumeDiscountPromotion")) {
            List<ArrayList> data = webManagementBean.getVolumeDiscountEvents(company);
            String companyLogo = webManagementBean.getCompanyLogo(company);
            List<ArrayList> propertyData = webManagementBean.getAllPropertyName(company);
            request.setAttribute("companyname", company);
            request.setAttribute("propertyData", propertyData);
            request.setAttribute("CompanyLogo", companyLogo);
            request.setAttribute("data", data);
            request.getRequestDispatcher("/volumeDiscountPromotion.jsp").forward(request, response);
        } else if (action.equals("companyStory")) {
            ArrayList data = webManagementBean.getCompanyInfo(company);
            String companyLogo = webManagementBean.getCompanyLogo(company);
            List<ArrayList> propertyData = webManagementBean.getAllPropertyName(company);
            request.setAttribute("companyname", company);
            request.setAttribute("propertyData", propertyData);
            request.setAttribute("CompanyLogo", companyLogo);
            request.setAttribute("data", data);
            request.getRequestDispatcher("/companyStory.jsp").forward(request, response);
        } else if (action.equals("displayConcertEvents")) {
            List<ArrayList> data = webManagementBean.getEventConcert("concert", company);
            String companyLogo = webManagementBean.getCompanyLogo(company);
            List<ArrayList> propertyData = webManagementBean.getAllPropertyName(company);
            request.setAttribute("companyname", company);
            request.setAttribute("propertyData", propertyData);
            request.setAttribute("CompanyLogo", companyLogo);
            request.setAttribute("data", data);
            request.getRequestDispatcher("/displayConcertEvents.jsp").forward(request, response);
        } else if (action.equals("displayDanceEvents")) {
            List<ArrayList> data = webManagementBean.getEventConcert("dance", company);
            String companyLogo = webManagementBean.getCompanyLogo(company);
            List<ArrayList> propertyData = webManagementBean.getAllPropertyName(company);
            request.setAttribute("companyname", company);
            request.setAttribute("propertyData", propertyData);
            request.setAttribute("CompanyLogo", companyLogo);
            request.setAttribute("data", data);
            request.getRequestDispatcher("/displayDanceEvents.jsp").forward(request, response);
        } else if (action.equals("displaySportsEvents")) {
            List<ArrayList> data = webManagementBean.getEventConcert("sports", company);
            String companyLogo = webManagementBean.getCompanyLogo(company);
            List<ArrayList> propertyData = webManagementBean.getAllPropertyName(company);
            request.setAttribute("companyname", company);
            request.setAttribute("propertyData", propertyData);
            request.setAttribute("CompanyLogo", companyLogo);
            request.setAttribute("data", data);
            request.getRequestDispatcher("/displaySportsEvents.jsp").forward(request, response);
        } else if (action.equals("displayVenueEvents")) {
            String id = url[5];
            List<ArrayList> data = webManagementBean.getPropertyEvents(id);
            String companyLogo = webManagementBean.getCompanyLogo(company);
            List<ArrayList> propertyData = webManagementBean.getAllPropertyName(company);
            String propertyName = webManagementBean.getPropertyName(Long.valueOf(id));
            request.setAttribute("companyname", company);
            request.setAttribute("propertyName", propertyName);
            request.setAttribute("propertyData", propertyData);
            request.setAttribute("CompanyLogo", companyLogo);
            request.setAttribute("data", data);
            request.getRequestDispatcher("/displayVenueEvents.jsp").forward(request, response);  
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
