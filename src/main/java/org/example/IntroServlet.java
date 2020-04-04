package org.example;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.example.frontend.templateClasses.Intro;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;

@SuppressWarnings("serial")
public class IntroServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {

        System.out.println(request.getPathInfo());
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache m = mf.compile("templates/intro.mustache");
        StringWriter writer = new StringWriter();
        m.execute(writer, new Intro("Welcome to Food Suggest!!!"+ request.getPathTranslated())).flush();
        String html = writer.toString();
        response.getWriter().println(html);
    }
}