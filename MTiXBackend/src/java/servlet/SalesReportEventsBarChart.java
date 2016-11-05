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
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
import session.stateless.commoninfrastucture.ProductSessionLocal;

/**
 *
 * @author JingYing
 */
@WebServlet(name = "SalesReportEventsBarChart", urlPatterns = {"/SalesReportEventsBarChart", "/SalesReportEventsBarChart/*"})
public class SalesReportEventsBarChart extends HttpServlet {

    @EJB
    private ProductSessionLocal productSession;

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
        List<ArrayList> data = productSession.getEventSessionNo();

        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 0; i < data.size(); i++) {
            dataset.addValue(Integer.valueOf(data.get(i).get(1).toString()), "Sessions", data.get(i).get(0).toString());
        }

        final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());

        JFreeChart barChart = ChartFactory.createBarChart(
                "No of Sessions",
                "Event", "Sessions",
                dataset, PlotOrientation.VERTICAL,
                true, true, false);
        

        CategoryPlot cplot = (CategoryPlot) barChart.getPlot();
        cplot.setBackgroundPaint(Color.WHITE);//change background color
        
        //set  bar chart color
        ((BarRenderer) cplot.getRenderer()).setBarPainter(new StandardBarPainter());

        BarRenderer r = (BarRenderer) barChart.getCategoryPlot().getRenderer();
        r.setSeriesPaint(0, Color.GREEN);

        r.setBaseItemLabelGenerator(
                new StandardCategoryItemLabelGenerator(
                        "{2}", NumberFormat.getInstance()));
        r.setBaseItemLabelsVisible(true);
        
        CategoryAxis categoryAxis = cplot.getDomainAxis();
        categoryAxis.setUpperMargin(0.15);
        
        NumberAxis rangeAxis = (NumberAxis) cplot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setUpperMargin(0.15);
        
        
         
        int width = 550; /* Width of the image */

        int height = 450; /* Height of the image */

        response.setContentType(
                "image/png");
        OutputStream out = response.getOutputStream();

        ChartUtilities.writeChartAsPNG(out, barChart,
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
