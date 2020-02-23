package de.uniba.dsg.dsam.client;

import de.uniba.dsg.dsam.model.Incentive;
import de.uniba.dsg.dsam.model.PromotionalGift;
import de.uniba.dsg.dsam.model.TrialPackage;
import de.uniba.dsg.dsam.persistence.IncentiveManagement;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;



/*

                <b>Servlet</b>

                - Servlet is a web component that is created in a compliance with the Servlet specification
                - used for WEB APPLICATIONS or WEB SERVICES
                - receives HTTP-request and after process it will return back the HTTP-response

                 Client-side                        Server-Side
                |-----------|                       |------------------|---------------> Database(s)
                |           |    HTTP-request       |                  |
                |   Client  | --------------------->|  Servlet-Engine  |---------------> File system(s)
                |           |                       |                  |
                |-----------|                       |------------------|---------------> Third Party
                     ↑             HTTP-response            |
                     |--------------------------------------|
                            (HTML, CSS, JS,...)


               We have several types of containers in JEE
                    - Web Container
                    - Application client Container
                    - EJB Container
                    -....

             - Servlets are hosted in Web Containers (Tomcat, Jetty, ....)
             - Full application servers offering a we container (Glassfish, Wildfly, IBM WAS, WebLogic)


             How Servlets are deployed?
             - Full-Featured applications which has certain footprint
             - Web Profile (lightweight and enough to all parts of containers)


             - Packaged as WAR (web achieve)



            Servlet Lifecycle
            - There is exactly one instance of servlet per web app per JVM
            - HttpServlet's method can be overriden
            - Asynchronous processing via HttpRequest.startAsync, AsyncContext.dispatch, AsynchContext.complete



            Servlet Filter
            - Modify or Adapt the request for a resource, and modify or adapt response from a resource
                         -----------                        ----------
    doFilter(req, res)  |          |  doFilter(req, res)   |          |  doFilter(req, res)
            --------->  |  Filter  | --------------------> |  Filter  |------------------->   MORE FILTERS
                        |          |                       |          |
                        ------------                       ------------







                JSP

                - Predefined variables
                    - out              JspWriter
                    - request          HttpServletRequest              ---->   tied to lifecycle of client request
                    - response         HttpServletResponse
                    - config           ServletConfig
                    - application      ServletContext                 -----> lifecycle of application
                    - session          HttpSession                     -----> lifestyle of client session
                    - pageContext      PageContext
                    - page             Object
                    - exception        Throwable






 */

@WebServlet(urlPatterns = "/addNewIncentive")
public class AddNewIncentiveServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AddNewIncentiveServlet.class.getName());

    @EJB
    private IncentiveManagement incentiveManagement;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

//        final AsyncContext asyncContext = req.startAsync(req, res);
//        asyncContext.dispatch();
//        asyncContext.complete();

        try{
            String name = req.getParameter("incentiveName");
            String incentiveType = req.getParameter("incentiveType");


            Incentive incentive;
            if(incentiveType.equals("trial")){
                incentive = new TrialPackage(name);
            }else{
                incentive = new PromotionalGift(name);
            }
            incentiveManagement.insertIncentive(incentive);
            req.getRequestDispatcher("/main.jsp").forward(req, res);
        }catch (Exception ex){
            logger.severe("Error saving incentive");
        }

    }
}

//
//@WebFilter("/securityFilter")
//class AddNewIncentiveFilter implements Filter{
//
//    private FilterConfig filterConfig;
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        this.filterConfig = filterConfig;
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//            // Do some filtering
//        chain.doFilter(request, response);
//    }
//}