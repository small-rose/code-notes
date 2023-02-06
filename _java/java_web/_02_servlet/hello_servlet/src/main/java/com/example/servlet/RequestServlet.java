package com.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

/**
 * 请求
 *
 * @author luguosong
 * @date 2023/1/3
 */
@WebServlet(name = "request", value = "/request")
public class RequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取请求信息
        getRequestInfo(req, resp);

        //GET方法从请求行获取参数
        System.out.println("GET方式从请求行中获取请求参数：" + req.getQueryString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //POST方式通过流传输，设置编码解决中文乱码
        req.setCharacterEncoding("UTF-8");

        //获取请求信息
        getRequestInfo(req, resp);

        //POST方法从请求体获取参数
        System.out.println("POST方式从请求体中获取请求参数：" + req.getReader().readLine());
    }

    private void getRequestInfo(HttpServletRequest request, HttpServletResponse response) {
        //请求行信息获取
        System.out.println("=====请求行=====");
        System.out.println("请求方式：" + request.getMethod());
        System.out.println("项目路径：" + request.getContextPath());
        System.out.println("统一资源定位符:" + request.getRequestURL());
        System.out.println("统一资源标识符：" + request.getRequestURI());

        //获取请求头
        System.out.println("=====请求头=====");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String element = headerNames.nextElement();
            System.out.println(element + ":" + request.getHeader(element));
        }

        //通用方式获取请求参数
        System.out.println("=====获取请求参数=====");
        Map<String, String[]> map = request.getParameterMap();
        System.out.println(map);
        for (String key : map.keySet()) {
            System.out.println(key + ":" + request.getParameter(key));
        }

        //存储数据到request域中
        request.setAttribute("id", 12);

        //请求转发
        try {
            request.getRequestDispatcher("/response").forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
