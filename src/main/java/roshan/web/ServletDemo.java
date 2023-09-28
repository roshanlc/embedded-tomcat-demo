package roshan.web;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

public class ServletDemo {

    public static void main(String[] args) {
        try {
            Tomcat tomcat = new Tomcat();
            tomcat.setPort(7000);

            Context ctx = tomcat.addContext("/", new File(".").getAbsolutePath());

            // create a context
            Tomcat.addServlet(ctx, "Embedded", new HttpServlet() {
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
                    Writer w = resp.getWriter();
                    w.write("Embedded tomcat server.\nHave a good day.\n");
                    w.flush();
                    w.close();
                }

            });

            // map the context to a url path
            ctx.addServletMapping("/*", "Embedded");

            tomcat.start();
            tomcat.getServer().await();
            System.out.println("Started server...");

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        System.out.println("Program has ended");
    }
}