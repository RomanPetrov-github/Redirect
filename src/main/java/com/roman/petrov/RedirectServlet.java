package com.roman.petrov;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RedirectServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.print("<h1>Hello Servlet</h1>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        System.out.println(req.getRequestURL());
        String author = req.getParameter("author");
        String number = req.getParameter("number");
        String description = req.getParameter("description");
        String link = req.getParameter("link");

        String data = req.getParameter("data");
        String[] split = data.split("/n");
        System.out.println(split[0]);

        ScriptBPM script = new ScriptBPM(author, number, description, link);
        for (String x: split) {
            script.addRedirect(x.trim().split(" "));
        }
        System.out.println("Вызов saveScript");

        int requestID = DBWorker.saveScript(script);
        DBWorker.saveRedirects(requestID, script.getRedirects());
    }
}
