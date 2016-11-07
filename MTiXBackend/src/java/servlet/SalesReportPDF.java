/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Font;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.stateless.commoninfrastucture.GetAllProductDetailsLocal;
import session.stateless.commoninfrastucture.ProductSessionLocal;

/**
 *
 * @author JingYing
 */
@WebServlet(name = "SalesReportPDF", urlPatterns = {"/SalesReportPDF"})
public class SalesReportPDF extends HttpServlet {

    @EJB
    private ProductSessionLocal productSession;
    @EJB
    private GetAllProductDetailsLocal getAllProductDetailsLocal;

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
        try {
            Document document = new Document();
            List<ArrayList> data = productSession.getEventList();
            List<ArrayList> sessions = getAllProductDetailsLocal.getAllSessions();

            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            //get current date time with Date()
            Date date = new Date();
            //System.out.println(dateFormat.format(date));

            @SuppressWarnings("unused")
            PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream("C:/Users/Yong Jing Ying/Desktop/EventRecords.pdf"));
            document.open();
            document.add(new Paragraph("Management Report", FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.BLUE)));
            document.add(new Paragraph("PDF created on " + dateFormat.format(date).toString() + "\n", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLACK)));
            document.add(new Paragraph("------------------------------------------------------------------------------------", FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.BLACK)));

            for (int i = 0; i < data.size(); i++) {
                document.add(new Paragraph(data.get(i).get(1).toString() + " Event Details ", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLACK)));
                document.add(new Paragraph("Event Type : " + data.get(i).get(4).toString() + "\nStart Date : " + data.get(i).get(2).toString()
                        + "\nEnd Date : " + data.get(i).get(3).toString() + "\nProperty Name : " + data.get(i).get(5).toString()
                        + "\nNo of Category : " + data.get(i).get(6).toString() + "\nPromotions : " + data.get(i).get(7).toString() + "\n\n"));
                document.add(new Paragraph("Session", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLACK)));
                PdfPTable table = new PdfPTable(6);
                //table.addCell("item1");
                table.setSpacingBefore(5);
                table.setSpacingAfter(5);
                table.setWidths(new int[]{1, 2, 3, 3, 3, 3});
                table.setWidthPercentage(100);
                PdfPCell cell;
                cell = new PdfPCell(new Phrase(
                        "No:", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLACK)));
                cell.setBackgroundColor(BaseColor.CYAN);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(
                        "Name:", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLACK)));
                cell.setBackgroundColor(BaseColor.CYAN);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(
                        "Description:", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLACK)));
                cell.setBackgroundColor(BaseColor.CYAN);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(
                        "Start:", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLACK)));
                cell.setBackgroundColor(BaseColor.CYAN);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(
                        "End:", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLACK)));
                cell.setBackgroundColor(BaseColor.CYAN);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(
                        "TicketPrices:", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLACK)));
                cell.setBackgroundColor(BaseColor.CYAN);
                table.addCell(cell);

                for (int j = 0; j < sessions.size(); j++) {
                    if (sessions.get(j).get(1).toString().equals(data.get(i).get(4).toString())
                            && sessions.get(j).get(0).toString().equals(data.get(i).get(0).toString())) {
                        cell = new PdfPCell(new Phrase(sessions.get(j).get(8).toString()));
                        cell.setBackgroundColor(BaseColor.WHITE);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase(sessions.get(j).get(4).toString()));
                        cell.setBackgroundColor(BaseColor.WHITE);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase(sessions.get(j).get(5).toString()));
                        cell.setBackgroundColor(BaseColor.WHITE);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase(sessions.get(j).get(6).toString()));
                        cell.setBackgroundColor(BaseColor.WHITE);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase(sessions.get(j).get(7).toString()));
                        cell.setBackgroundColor(BaseColor.WHITE);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase(sessions.get(j).get(9).toString()));
                        cell.setBackgroundColor(BaseColor.WHITE);
                        table.addCell(cell);
                    }

                }
                document.add(table);
                document.add(new Paragraph("\n"));
                document.add(new Paragraph("Alert", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLACK)));
                table = new PdfPTable(6);
                //table.addCell("item1");
                table.setSpacingBefore(5);
                table.setSpacingAfter(5);
                table.setWidths(new int[]{1, 2, 2, 3, 3, 3});
                table.setWidthPercentage(100);
                cell = new PdfPCell(new Phrase(
                        "No:", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLACK)));
                cell.setBackgroundColor(BaseColor.GREEN);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(
                        "Alert Type:", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLACK)));
                cell.setBackgroundColor(BaseColor.GREEN);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(
                        "Below Sales:", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLACK)));
                cell.setBackgroundColor(BaseColor.GREEN);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(
                        "In-Charged Email:", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLACK)));
                cell.setBackgroundColor(BaseColor.GREEN);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(
                        "Start:", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLACK)));
                cell.setBackgroundColor(BaseColor.GREEN);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(
                        "End:", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLACK)));
                cell.setBackgroundColor(BaseColor.GREEN);
                table.addCell(cell);

                for (int j = 0; j < sessions.size(); j++) {
                    if (sessions.get(j).get(1).toString().equals(data.get(i).get(4).toString())
                            && sessions.get(j).get(0).toString().equals(data.get(i).get(0).toString())
                            && sessions.get(j).size() == 15) {

                        cell = new PdfPCell(new Phrase(sessions.get(j).get(8).toString()));
                        cell.setBackgroundColor(BaseColor.WHITE);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase(sessions.get(j).get(10).toString()));
                        cell.setBackgroundColor(BaseColor.WHITE);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase(sessions.get(j).get(11).toString()));
                        cell.setBackgroundColor(BaseColor.WHITE);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase(sessions.get(j).get(12).toString()));
                        cell.setBackgroundColor(BaseColor.WHITE);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase(sessions.get(j).get(13).toString()));
                        cell.setBackgroundColor(BaseColor.WHITE);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase(sessions.get(j).get(14).toString()));
                        cell.setBackgroundColor(BaseColor.WHITE);
                        table.addCell(cell);
                    }

                }
                document.add(table);
                document.add(new Paragraph("\n\n"));

            }
            document.close();

            Thread.sleep(1000);
            
            PrintWriter out = response.getWriter();
            String fileName = "EventRecords.pdf"; 
            String filePath = "C:/Users/Yong Jing Ying/Desktop/";
            response.setContentType("APPLICATION/OCTET-STREAM");
            response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
            
            FileInputStream fi = new FileInputStream("C:/Users/Yong Jing Ying/Desktop/EventRecords.pdf");
            int i;
            while((i=fi.read()) != -1){
                out.write(i);
            }
            
            out.close();
            fi.close();
        } catch (Exception ex) {
            Logger.getLogger(SalesReportPDF.class.getName()).log(Level.SEVERE, null, ex);
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
