/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author JingYing
 */
@WebServlet(name = "SalesReportPieChart", urlPatterns = {"/SalesReportPieChart"})
public class SalesReportPieChart extends HttpServlet {

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
        DefaultPieDataset dataset = new DefaultPieDataset();
        int totalTickets = Integer.valueOf(request.getParameter("totalTickets"));
        int totalSoldTickets = Integer.valueOf(request.getParameter("totalSoldTickets"));

        dataset.setValue(
                "Unsold Tickets", new Double(totalTickets - totalSoldTickets));
        dataset.setValue(
                "Sold Tickets", new Double(totalSoldTickets));

        JFreeChart chart = ChartFactory.createPieChart(
                "Ticket Sales", // chart title
                dataset, // data
                true, // include legend
                true,
                false);

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionPaint("Unsold Tickets", Color.DARK_GRAY);
        plot.setSectionPaint("Sold Tickets", Color.CYAN);
        plot.setExplodePercent("Unsold Tickets", 0.10);
        plot.setSimpleLabels(true);
        plot.setBackgroundPaint(Color.WHITE);
        
        PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
            "{0}: {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
        plot.setLabelGenerator(gen);

        final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());

        int width = 500; /* Width of the image */

        int height = 400; /* Height of the image */


        response.setContentType(
                "image/png");
        OutputStream out = response.getOutputStream();

        ChartUtilities.writeChartAsPNG(out, chart,
                400, 300, info);
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
