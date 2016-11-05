/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.CompanyEntity;
import entity.Event;
import entity.PropertyEntity;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import manager.EquipmentManager;
import manager.FoodOutletManager;

import manager.ManpowerManager;
import manager.RegisterManager;

import manager.ReservationManager;

import manager.SeatingPlanManager;
import session.stateless.commoninfrastucture.RegisterSessionLocal;

import session.stateless.propertymanagement.EquipmentBeanLocal;
import session.stateless.propertymanagement.FoodOutletBeanLocal;
import session.stateless.propertymanagement.ManpowerBeanLocal;
import session.stateless.propertymanagement.ReservePropertyBeanLocal;
import session.stateless.propertymanagement.SeatingPlanManagementBeanLocal;

/**
 *
 * @author catherinexiong
 */
@WebServlet(name = "BackPropertyController", urlPatterns = {"/BackPropertyController"})
@MultipartConfig
public class BackPropertyController extends HttpServlet {

    @EJB
    private RegisterSessionLocal registerSessionLocal;
    @EJB
    private SeatingPlanManagementBeanLocal seatingPlanManagementBeanLocal;

    @EJB
    private ReservePropertyBeanLocal reservePropertyBeanLocal;

    @EJB
    private EquipmentBeanLocal equipmentBeanLocal;
    @EJB
    private ManpowerBeanLocal manpowerBeanLocal;
    @EJB
    private FoodOutletBeanLocal foodoutletBeanLocal;

    String currentUser;
    String role;

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
        try {
            String action;
            SeatingPlanManager spm = new SeatingPlanManager(seatingPlanManagementBeanLocal);
            ReservationManager rm = new ReservationManager(reservePropertyBeanLocal);
            EquipmentManager em = new EquipmentManager(equipmentBeanLocal);
            ManpowerManager mm = new ManpowerManager(manpowerBeanLocal);
            FoodOutletManager fom = new FoodOutletManager(foodoutletBeanLocal);
            RegisterManager rem = new RegisterManager(registerSessionLocal);
            int page = 1;
            int recordsPerPage = 8;

            action = request.getParameter("action");

            System.out.println("Action = " + action);

            if (action.equals("propertyMain")) {

                request.getRequestDispatcher("/propertyMain.jsp").forward(request, response);
            } else if (action.equals("seatingMain")) {
                request.getRequestDispatcher("/seatingMain.jsp").forward(request, response);
            } else if (action.equals("createProperty")) {
//                currentUser = request.getParameter("username");
//                role = request.getParameter("role");
//                request.setAttribute("username", currentUser);
//                request.setAttribute("role", role);
                HttpSession session = request.getSession();
                session.setAttribute("company", Long.valueOf("1"));
                session.setMaxInactiveInterval(30 * 60);
                Cookie company = new Cookie("company", "1");
                company.setMaxAge(30 * 60);
                response.addCookie(company);
                request.getRequestDispatcher("/createNewProperty.jsp").forward(request, response);
            } else if (action.equals("propertyCreated")) {
                Part propertyMain = request.getPart("filePhoto1");
                Part propertyLayout = request.getPart("filePhoto2");
                Part data = request.getPart("fileData");

                String pName = request.getParameter("title");
                String capacity = request.getParameter("capacity");
                String rental = request.getParameter("rental");
                String[] types = request.getParameterValues("types");
                String recommend = request.getParameter("recommend");
//                Integer categories = Integer.valueOf(request.getParameter("categories"));
//                Integer sections = Integer.valueOf(request.getParameter("numOfSections"));
                String ext1 = request.getParameter("ext1");
                String ext2 = request.getParameter("ext2");
                String ext3 = request.getParameter("ext3");
                Long companyId = (Long) request.getSession(false).getAttribute("company");
                CompanyEntity company = rem.getCompanyEntityById(companyId);
                Long propertyId = spm.createNewProperty(company, propertyMain, propertyLayout, data, pName, Integer.valueOf(capacity), Integer.valueOf(rental), types, recommend, ext1, ext2, ext3);
                request.setAttribute("username", currentUser);
                request.setAttribute("role", role);
//                request.setAttribute("categories", categories);
//                request.setAttribute("sections", sections);
                request.setAttribute("propertyId", propertyId);
                request.setAttribute("propertyName", pName);
                request.getRequestDispatcher("/seatingMain.jsp").forward(request, response);
            } else if (action.equals("viewProperty")) {
                Long companyId = (Long) request.getSession(false).getAttribute("company");
                CompanyEntity company = rem.getCompanyEntityById(companyId);
                List<PropertyEntity> properties = spm.getAllPropertiesByCompany(company);
                request.setAttribute("properties", properties);
                request.setAttribute("companyName", company.getCompanyName());
                request.setAttribute("username", currentUser);
                request.setAttribute("role", role);
                request.getRequestDispatcher("/viewCompanyProperties.jsp").forward(request, response);

            } else if (action.equals("eventReservationSearch")) {
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                Long companyId = (Long) request.getSession(false).getAttribute("company");
                CompanyEntity company = rem.getCompanyEntityById(companyId);
                request.setAttribute("companyName", company.getCompanyName());
                request.getRequestDispatcher("/reservationSearch.jsp").forward(request, response);

            } else if (action.equals("reservationSearchResult")) {
                try {

                    String type = request.getParameter("eventcate");
                    Long companyId = (Long) request.getSession(false).getAttribute("company");
                    CompanyEntity company = rem.getCompanyEntityById(companyId);
                    List<PropertyEntity> aProperties = rm.getAvailableProperties(company, request);
                    if (aProperties.isEmpty()) {
                        request.setAttribute("errormsg", " No venues are available ");
                        request.setAttribute("role", role);
                        request.setAttribute("username", currentUser);
                        request.getRequestDispatcher("/reservationSearch.jsp").forward(request, response);
                    } else {
                        List<PropertyEntity> properties = rm.getReservationSearchResult(aProperties, request);
                        List<PropertyEntity> pRList = rm.checkRecommendation(properties, request);
                        String daterange = request.getParameter("daterange");
                        if (properties.isEmpty()) {
                            request.setAttribute("errormsg", " Please note: There are no suitable venues matching the number of your expected auidence and type of event  ");
                            request.setAttribute("role", role);
                            request.setAttribute("username", currentUser);
                            request.getRequestDispatcher("/reservationSearch.jsp").forward(request, response);
                        } else {
                            request.setAttribute("pList", properties);
                            request.setAttribute("pRList", pRList);
                            request.setAttribute("daterange", daterange);
                            request.setAttribute("type", type);
                            request.setAttribute("role", role);
                            request.setAttribute("username", currentUser);
                            request.getRequestDispatcher("/reservationSearchResult.jsp").forward(request, response);
                        }
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(BackController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (action.equals("venueSelected")) {
                HttpSession session = request.getSession();
                String daterange = (String) session.getAttribute("daterange");
                String type = (String) session.getAttribute("type");
                String idStr = request.getParameter("id");
                PropertyEntity property = spm.getPropertyById(Long.valueOf(idStr));
                request.setAttribute("type", type);
                request.setAttribute("daterange", daterange);
                request.setAttribute("property", property);
                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                request.getRequestDispatcher("/venueSelected.jsp").forward(request, response);
            } else if (action.equals("saveNewEvent")) {
                Long companyId = (Long) request.getSession(false).getAttribute("company");
                CompanyEntity company = rem.getCompanyEntityById(companyId);
                String daterange = request.getParameter("daterange");
                String pidStr = request.getParameter("pid");
                Long pid = Long.valueOf(pidStr);
                //String idStr = request.getParameter("propertyId");
                String ename = request.getParameter("eventname");
                String eDes = request.getParameter("eventdes");
                String email = request.getParameter("eoemail");
                String type = request.getParameter("type");
                System.out.println("Entered save new event 1.");
                System.out.println("email save new event: " + email);
                boolean checkUser = rm.checkUser(company,email);

                request.setAttribute("role", role);
                request.setAttribute("username", currentUser);
                if (checkUser) {
                    System.out.println("Entered save new event 2.");
                    Event event = rm.addNewEvent(company, ename, eDes, daterange, pid, email, type);
                    if (event != null) {
                        System.out.println("Entered save new event 3.");
                        request.setAttribute("event", event);
                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        request.setAttribute("start", format.format(event.getStart()));
                        request.setAttribute("end", format.format(event.getEnd()));
                        request.getRequestDispatcher("/saveNewEvent.jsp").forward(request, response);
                    } else {
                        System.out.println("Entered save new event 4.");
                        PropertyEntity property = spm.getPropertyById(pid);
                        request.setAttribute("msg", "error when creating the reservation");
                        request.setAttribute("property", property);

                        request.getRequestDispatcher("/venueSelected.jsp").forward(request, response);

                    }
                } else {
                    request.setAttribute("userResult", "Please note: The email you entered is not a valid user. Please enter again.");
                   
                        PropertyEntity property = spm.getPropertyById(pid);
                        request.setAttribute("property", property);
                        request.setAttribute("eventname", ename);
                        request.setAttribute("eventdes", eDes);

                        request.setAttribute("eventdes", eDes);
                        request.getRequestDispatcher("/venueSelected.jsp").forward(request, response);
                   
                }

                // 
            } else if (action.equals("subReservationSearchResult")) {

                try {
                    HttpSession session = request.getSession();
                    Long eventid = (Long) session.getAttribute("eventid");
                    Long companyId = (Long) request.getSession(false).getAttribute("company");
                    CompanyEntity company = rem.getCompanyEntityById(companyId);
                    String type = request.getParameter("eventcate");
                    System.out.println("=======session get eventid" + eventid);
                    request.setAttribute("eventid", eventid);
                    List<PropertyEntity> aProperties = rm.getAvailableProperties(company, request);
                    if (aProperties.isEmpty()) {
                        request.setAttribute("errormsg", " Please note: The date range you entered conflicts with an exsiting reservation or a maintenance shedule  ");
                        request.setAttribute("role", role);
                        request.setAttribute("username", currentUser);
                        request.getRequestDispatcher("/subReservationSearch.jsp").forward(request, response);
                    } else {
                        List<PropertyEntity> properties = rm.getReservationSearchResult(aProperties, request);
                        List<PropertyEntity> pRList = rm.checkRecommendation(properties, request);
                        String daterange = request.getParameter("daterange");
                        if (properties.isEmpty()) {
                            request.setAttribute("errormsg", " Please note: There are no suitable venues matching the number of your expected auidence and type of event  ");
                            request.setAttribute("role", role);
                            request.setAttribute("username", currentUser);
                            request.getRequestDispatcher("/subReservationSearch.jsp").forward(request, response);
                        } else {
                            request.setAttribute("pList", properties);
                            request.setAttribute("pRList", pRList);
                            request.setAttribute("daterange", daterange);
                            request.setAttribute("type", type);
                            request.setAttribute("role", role);
                            request.setAttribute("username", currentUser);
                            request.getRequestDispatcher("/subReservationSearchResult.jsp").forward(request, response);
                        }
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(BackController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
//            } else if (action.equals("sectionCreated")) {
//                Boolean success = spm.createSectionsUnderProperty(request);
//                request.setAttribute("username", currentUser);
//                request.setAttribute("role", role);
//                
//
//                request.getRequestDispatcher("/createFees.jsp").forward(request, response);
//            }
        } catch (Exception ex) {
            ex.printStackTrace();
            //request.getRequestDispatcher("/error.jsp").forward(request, response);
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
