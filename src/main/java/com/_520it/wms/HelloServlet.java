package com._520it.wms;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAA");
        sayHello();
    }

    private void sayHello() {
        System.out.println("CCCCAAAAAAAAAAAAAAAAAAAACCCCCC");
    }

}
