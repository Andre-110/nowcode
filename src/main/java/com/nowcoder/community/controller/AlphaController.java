package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Printer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

//controller
@Controller
//浏览器访问类的地址名
@RequestMapping("/alpha")
public class AlphaController {
    //浏览器访问类方法的地址名
    @RequestMapping("/hello")
    //注解表名返回的是字符串而不是网页
    @ResponseBody
    public String sayHello(){
        return "Hello Spring Boot.";
    }

    @Autowired
    private AlphaService alphaService;
    @RequestMapping("/data")
    @ResponseBody
    public String getData(){
        return alphaService.find();
    }

    @RequestMapping("/http")
    //直接输出给网页，不需要返回，也不需要Responsebody
    public void http(HttpServletRequest request, HttpServletResponse response){
        //读取请求中包含的数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> enumeration = request.getHeaderNames();
        while(enumeration.hasMoreElements()){
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name+":"+value);
        }
        //输出浏览器地址同名参数
        System.out.println(request.getParameter("code"));

        //返回响应数据
        response.setContentType("text/html;charset=utf-8");
        //写在括号里，省去写finally
        try(PrintWriter writer = response.getWriter();){
            writer.write("<h1>牛客网</h1>");
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    //处理Get请求，用于向服务器获取某些数据，默认为Get
    // /student?current=1&limit=10
    //指定请求方式
    @RequestMapping(path="/student",method= RequestMethod.GET)
    @ResponseBody
    //获取与处理参数
    public String getStudent(@RequestParam(name="current",required = false,defaultValue = "1") int current,
                             @RequestParam(name="limit",required=false,defaultValue = "10") int limit){
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }

    //将参数编排为路径的一部分
    // /student/123
    @RequestMapping(path="/student/{id}",method = RequestMethod.GET)
    @ResponseBody
    // 获取参数变量
    public String getStudent(@PathVariable("id")int id){
        System.out.println(id);
        return "a student";
    }

    //为什么不用get传
    //路径长度有限，且是在url上传参
    //post请求
    //浏览器将带有表单的网页提交给服务器
    // path可以与get路径一致
    @RequestMapping(path="/student",method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name,int age){
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    //返回动态html数据,不用加注解，默认为网页，类型为ModelAndView
    @RequestMapping(path="/teacher",method=RequestMethod.GET)
    public ModelAndView getTeacher(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("name","詹大三");
        mav.addObject("age",30);
        //设置一个模板的路径和名字,templates这一级目录不用写
        mav.setViewName("/demo/view");
        return mav;
    }

    @RequestMapping(path="/school",method = RequestMethod.GET)
    public String getSchool(Model model){
        model.addAttribute("name","詹大三");
        model.addAttribute("age",30);
        return "/demo/view";
    }

    //在异步请求中响应json数据(当前网页未刷新)
    //java对象-Json字符串-JS对象
    //返回类型和注解自动决定返回json
    @RequestMapping(path="/emp",method=RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getEmp(){
        Map<String,Object> emp = new HashMap<>();
        emp.put("name","张三");
        emp.put("age",23);
        emp.put("salary",8000.00);
        return emp;
    }

    @RequestMapping(path="/emps",method=RequestMethod.GET)
    @ResponseBody
    public List<Map<String,Object>> getEmps(){
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> emp = new HashMap<>();
        emp.put("name","张三");
        emp.put("age",23);
        emp.put("salary",8000.00);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name","李四");
        emp.put("age",24);
        emp.put("salary",9000.00);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name","王五");
        emp.put("age",22);
        emp.put("salary",10000.00);
        list.add(emp);
        return list;
    }
}
