package com.tele2.digital;

import com.tele2.digital.pojo.Incident;
import com.tele2.digital.pojo.IncidentBuilder;
import com.tele2.digital.pojo.Redirect;
import com.tele2.digital.util.HibernateUtil;
import com.tele2.digital.util.ValidatorURL;
import org.apache.commons.codec.binary.Base64;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.PrintWriter;

public class RedirectServlet extends HttpServlet{

    public static boolean validateCredentials(String userCredentials) {
        String login = "admin";
        String password = "admin123";
        String validCredentials = "Basic " + Base64.encodeBase64String((login + ":" + password).getBytes());
        return userCredentials.equals(validCredentials);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        if (validateCredentials(req.getHeader("Authorization"))){
//            resp.sendError(401);
//            return;
//        }
//
//        PrintWriter out = resp.getWriter();
//        out.print("<h1>Hello Servlet</h1>");

        PrintWriter out = resp.getWriter();
        out.print("<h1>S</h1>");
        System.out.println("=================================================================");

        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<incident>\n" +
                "   <author>Смердов Александр Сергеевич</author>\n" +
                "   <number>SR00819068</number>\n" +
                "   <description>Редирект с раздела «Тарифы» в Карелиию. Задача срочная.</description>\n" +
                "   <redirect>karelia.tele2.ru/tariffs/ karelia.tele2.ru/tariff/my-choice-300-10 302\n" +
                "   orel.tele2.ru/tariffs/ orel.tele2.ru/tariff/my-choice-300-10 302</redirect>\n" +
                "   <link>https://bpm.tele2.ru/0/Nui/ViewModule.aspx#CardModuleV2/CasePage/edit/281bb33a-75a8-465a-806b-00ca3a138df2</link>\n" +
                "</incident>";
        try
        {
            Incident incident = new IncidentBuilder(xml).build();
            System.out.println("list size = " + incident.getListRedirects().size());
            ValidatorURL.validateURL(incident);
            System.out.println("Maven + Hibernate + MySQL");
            Session sessionIncident = HibernateUtil.getSessionFactory().openSession();
            sessionIncident.beginTransaction();
            sessionIncident.save(incident);
            sessionIncident.getTransaction().commit();
            sessionIncident.close();
            for(Redirect redirect: incident.getListRedirects()){
                Session session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                session.save(redirect);
                session.getTransaction().commit();
            }
        }
        catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        if (validateCredentials(req.getHeader("Authorization"))){
//            resp.sendError(401);
//            return;
//        }
    }
}
