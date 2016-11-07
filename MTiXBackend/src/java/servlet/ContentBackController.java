/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.CompanyEntity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import manager.RegisterManager;
import session.stateless.commoninfrastucture.FindUserCompanyLocal;
import session.stateless.commoninfrastucture.ProductSessionLocal;
import session.stateless.commoninfrastucture.RegisterSessionLocal;
import session.stateless.contentmanagement.WebContentBeanLocal;

/**
 *
 * @author JingYing
 */
@WebServlet(name = "ContentBackController", urlPatterns = {"/ContentBackController"})
@MultipartConfig
public class ContentBackController extends HttpServlet {

    @EJB
    private WebContentBeanLocal webContentBean;
    @EJB
    private ProductSessionLocal productSession;
    @EJB
    private FindUserCompanyLocal findUserCompany;
    @EJB
    private RegisterSessionLocal registerSession;
    RegisterManager registerManager = new RegisterManager(registerSession);

    public String currentUser;
    public String role = "";
    public String companyID = "";

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

        String action;
        action = request.getParameter("action");
        System.out.println("Enter contentBackcontroller");
        System.out.println("action: " + action);
        
        //If still cannot for session just get parameter. do it in content and contentreviewmain side only. then store it outside processRequest.
        
            
            
        
        
        
        //Need to change 
       /*if (action.equals("enterEventOrganizer")) {
            ArrayList email = productSession.getEventOrganizersEmail();
            System.out.println("current user: " +request.getParameter("username"));
            System.out.println("role: " + request.getParameter("role"));
            currentUser = request.getParameter("username");
            role = request.getParameter("role");
            request.setAttribute("data", email);
            request.setAttribute("role", role);
            request.setAttribute("username", currentUser);
            request.getRequestDispatcher("/enterEventOrganizer.jsp").forward(request, response);
        } else */if (action.equals("contentMain")) {
            currentUser = request.getParameter("username");
            role = request.getParameter("role");
            boolean signin = webContentBean.signIn(currentUser);
            request.setAttribute("role", role);
            request.setAttribute("username", currentUser);
            request.getRequestDispatcher("/contentMain.jsp").forward(request, response);
        } else if (action.equals("createWebpageMain")) {
            List<ArrayList> data = webContentBean.getEventList();
            request.setAttribute("data", data);
            request.setAttribute("role", role);
            request.setAttribute("username", currentUser);
            request.getRequestDispatcher("/createWebpageMain.jsp").forward(request, response);
        } else if (action.equals("createWebpage")) {
            String info = request.getParameter("id");
            String[] idType = info.split(" ");
            Long i = Long.valueOf(idType[0]); //EventId
            ArrayList data = webContentBean.createWebpageInfo(i, idType[1]);
            request.setAttribute("data", data);
            request.setAttribute("role", role);
            request.setAttribute("username", currentUser);
            request.getRequestDispatcher("/createWebpage.jsp").forward(request, response);
        } else if (action.equals("webpageCreated")) {
            Part filePart = request.getPart("filePhoto");
            String eventTitle = request.getParameter("title");
            String synopsis = request.getParameter("synopsis");
            String programDetails = request.getParameter("programDetails");
            String rules = request.getParameter("rules");
            String details = request.getParameter("details");
            String start = request.getParameter("date");
            String end = request.getParameter("endDate");

            String id = request.getParameter("id");
            String type = request.getParameter("type");
            String ext = request.getParameter("ext");

            webContentBean.createEventWebpage(filePart, eventTitle, synopsis, programDetails, rules, details, start, end, id, type, ext);

            List<ArrayList> data = webContentBean.getEventList();
            request.setAttribute("data", data);
            request.setAttribute("success", "true");
            request.setAttribute("role", role);
            request.setAttribute("username", currentUser);
            request.getRequestDispatcher("/createWebpageMain.jsp").forward(request, response);
        } else if (action.equals("editWebpageMain")) {
            List<ArrayList> data = webContentBean.getEditedWebEventList();
            request.setAttribute("data", data);
            request.setAttribute("role", role);
            request.setAttribute("username", currentUser);
            request.getRequestDispatcher("/editWebpageMain.jsp").forward(request, response);
        } else if (action.equals("editWebpage")) {
            String info = request.getParameter("id");
            String[] idType = info.split(" ");
            Long i = Long.valueOf(idType[0]); //EventId
            ArrayList data = webContentBean.editWebpageInfo(i, idType[1]);
            request.setAttribute("data", data);
            request.setAttribute("role", role);
            request.setAttribute("username", currentUser);
            request.getRequestDispatcher("/editWebpage.jsp").forward(request, response);
        } else if (action.equals("webpageEdited")) {
            Part filePart = request.getPart("filePhoto");
            String eventTitle = request.getParameter("title");
            String synopsis = request.getParameter("synopsis");
            String programDetails = request.getParameter("programDetails");
            String rules = request.getParameter("rules");
            String details = request.getParameter("details");
            String start = request.getParameter("date");
            String end = request.getParameter("endDate");

            String id = request.getParameter("id");
            String type = request.getParameter("type");
            String ext = request.getParameter("ext");

            webContentBean.editEventWebpage(filePart, eventTitle, synopsis, programDetails, rules, details, start, end, id, type, ext);

            List<ArrayList> data = webContentBean.getEditedWebEventList();
            request.setAttribute("data", data);
            request.setAttribute("success", "true");
            request.setAttribute("role", role);
            request.setAttribute("username", currentUser);
            request.getRequestDispatcher("/editWebpageMain.jsp").forward(request, response);

        } else if (action.equals("deleteWebpageMain")) {
            List<ArrayList> data = webContentBean.getEditedWebEventList(); //Get those that has record
            request.setAttribute("data", data);
            request.setAttribute("role", role);
            request.setAttribute("username", currentUser);
            request.getRequestDispatcher("/deleteWebpageMain.jsp").forward(request, response);
        } else if (action.equals("deleteWebpage")) {
            String info = request.getParameter("id");
            String[] idType = info.split(" ");
            Long i = Long.valueOf(idType[0]); //EventId
            ArrayList data = webContentBean.editWebpageInfo(i, idType[1]); //Get the details only
            request.setAttribute("data", data);
            request.setAttribute("role", role);
            request.setAttribute("username", currentUser);
            request.getRequestDispatcher("/deleteWebpage.jsp").forward(request, response);
        } else if (action.equals("webpageDeleted")) {
            String id = request.getParameter("id");
            String type = request.getParameter("type");

            webContentBean.deleteEventWebpage(id, type);

            List<ArrayList> data = webContentBean.getEditedWebEventList();
            request.setAttribute("data", data);
            request.setAttribute("success", "true");
            request.setAttribute("role", role);
            request.setAttribute("username", currentUser);
            request.getRequestDispatcher("/deleteWebpageMain.jsp").forward(request, response);

        } else if (action.equals("contentReviewMain")) {
            currentUser = request.getParameter("username");
            role = request.getParameter("role");
            response.reset(); 
             
            request.setAttribute("hascompanycontent", String.valueOf(webContentBean.hasCompanyContent(Long.valueOf(findUserCompany.findcompany(currentUser)))));
            request.setAttribute("role", role);
            request.setAttribute("username", currentUser);
            request.getRequestDispatcher("/contentReviewMain.jsp").forward(request, response);
        } else if (action.equals("contentReviewSelect")) {    
            List<ArrayList> data = webContentBean.geWebpageList( Long.valueOf(findUserCompany.findcompany(currentUser)));
            request.setAttribute("data", data);
            request.setAttribute("role", role);
            request.setAttribute("username", currentUser);
            request.getRequestDispatcher("/contentReviewSelect.jsp").forward(request, response);
        } else if (action.equals("contentReview")) {
            String id = request.getParameter("id");
            ArrayList data = webContentBean.reviewWebpageInfo(id);
            
            request.setAttribute("data", data);
            System.out.println("servlet ArrayList = " + data.size());
            request.setAttribute("role", role);
            request.setAttribute("username", currentUser);
            request.getRequestDispatcher("/contentReview.jsp").forward(request, response);
        } else if (action.equals("contentReviewed")){
            String id = request.getParameter("id");
            String review = request.getParameter("review");
            String apply = request.getParameter("apply");
            
            webContentBean.webpageReviewed(id, review, apply);
          
            List<ArrayList> data = webContentBean.geWebpageList(Long.valueOf(findUserCompany.findcompany(currentUser)));
            request.setAttribute("data", data);
            request.setAttribute("role", role);
            request.setAttribute("username", currentUser);
            request.setAttribute("success", "true");
            request.getRequestDispatcher("/contentReviewSelect.jsp").forward(request, response);
        } else if (action.equals("createCompanyContent")){
            request.setAttribute("role", role);
            request.setAttribute("username", currentUser);
            request.getRequestDispatcher("/createCompanyContent.jsp").forward(request, response);
        } else if (action.equals("companyInfoCreated")){
            Part filePart = request.getPart("filePhoto");
            String mission = request.getParameter("mission");
            String vision = request.getParameter("vision");
            String aboutUs = request.getParameter("aboutUs");
            String contactDetails = request.getParameter("contactDetails");
            String career = request.getParameter("career");
            String otherDetails = request.getParameter("others");
            String ext = request.getParameter("ext");         
               
            
            webContentBean.createCompanyWebpage(filePart, mission, vision, aboutUs, contactDetails, career, otherDetails, ext, Long.valueOf(findUserCompany.findcompany(currentUser)));
            request.setAttribute("hascompanycontent", String.valueOf(webContentBean.hasCompanyContent(Long.valueOf(findUserCompany.findcompany(currentUser)))));            
            request.setAttribute("role", role);
            request.setAttribute("username", currentUser);
            request.getRequestDispatcher("/contentReviewMain.jsp").forward(request, response);
        } else if (action.equals("editCompanyContent")){            
            ArrayList data = webContentBean.getCompanyInfo(Long.valueOf(findUserCompany.findcompany(currentUser)));
            request.setAttribute("data", data);
            request.setAttribute("role", role);
            request.setAttribute("username", currentUser);
            request.getRequestDispatcher("/editCompanyContent.jsp").forward(request, response);
        } else if (action.equals("companyInfoEdited")){
            Part filePart = request.getPart("filePhoto");
            String mission = request.getParameter("mission");
            String vision = request.getParameter("vision");
            String aboutUs = request.getParameter("aboutUs");
            String contactDetails = request.getParameter("contactDetails");
            String career = request.getParameter("career");
            String otherDetails = request.getParameter("others");
            String ext = request.getParameter("ext"); 
            long id = Long.valueOf(request.getParameter("id"));
            
            webContentBean.editCompanyWebpage(id, filePart, mission, vision, aboutUs, contactDetails, career, otherDetails, ext);
            request.setAttribute("role", role);
            request.setAttribute("username", currentUser);
             request.setAttribute("hascompanycontent", String.valueOf(webContentBean.hasCompanyContent(Long.valueOf(findUserCompany.findcompany(currentUser)))));
            request.getRequestDispatcher("/contentReviewMain.jsp").forward(request, response);
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
