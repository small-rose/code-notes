package com.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 响应
 *
 * @author luguosong
 * @date 2023/1/3
 */
@WebServlet(name = "response",value = "/response")
public class ResponseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //设置响应内容格式
        resp.setHeader("content-type","text/html;charset=utf-8");
        //响应字符数据
        //resp.getWriter().write("<h1>响应测试</h1>");
        //响应字节数据
        resp.getOutputStream().write("JavaWeb编程".getBytes());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //重定向
        //resp.setStatus(302);
        //resp.setHeader("location","http://www.baidu.com");

        //重定向简化写法
        resp.sendRedirect("http://www.baidu.com");
    }
}
