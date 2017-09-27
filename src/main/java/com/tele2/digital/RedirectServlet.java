package com.tele2.digital;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.PrintWriter;

public class RedirectServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.print("<h1>Hello Servlet</h1>");
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
//            for(Redirect redirect: incident.getListRedirects()){
//                DBWorker.saveRedirect(redirect);
//            }
        }
        catch (JAXBException e) {
            e.printStackTrace();
        }
    }

}