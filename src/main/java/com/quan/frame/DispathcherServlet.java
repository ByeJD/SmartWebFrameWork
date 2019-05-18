package com.quan.frame;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quan.frame.bean.Data;
import com.quan.frame.bean.Handler;
import com.quan.frame.bean.Param;
import com.quan.frame.bean.View;
import com.quan.frame.common.*;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.security.auth.login.Configuration;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class DispathcherServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        HelperLoader.init();

        ServletContext servletContext = config.getServletContext();

        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");

        jspServlet.addMapping(ConfigHelper.getAppBaseJspPath() + "*");

        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");

        defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.service(req, resp);

        String requestMethod = req.getMethod().toLowerCase();
        String requestPath = req.getPathInfo();

        Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);

        if (handler != null) {
            Class<?> controllerClass = handler.getControllerClass();
            Object controllerBean = BeanHelper.getBean(controllerClass);
            LinkedHashMap<String, Object> paramMap = new LinkedHashMap<>();
            Enumeration<String> paramNames = req.getParameterNames();

            while (paramNames.hasMoreElements()) {
                String paramName = paramNames.nextElement();
                String paramValue = req.getParameter(paramName);
                paramMap.put(paramName, paramValue);
            }

            String body = CodecUtil.decodeURL(StreamUtil.getString(req.getInputStream()));

            if (StringUtils.isNotEmpty(body)) {
                String[] params = StringUtils.split(body, "&");
                if (ArrayUtils.isNotEmpty(params)) {
                    for (String param : params) {
                        String[] array = StringUtils.split(param, "=");
                        if (ArrayUtils.isNotEmpty(array) && array.length == 2) {
                            String paramName = array[0];
                            String paramValue = array[1];
                            paramMap.put(paramName,paramValue);
                        }
                    }
                }
            }

            Param param = new Param(paramMap);
            Method actionMethod = handler.getActionMethod();
            Object result;
            if (param.isEmpty()){
                result = ReflectionUtil.invokeMethod(controllerBean,actionMethod);
            }else {
                result = ReflectionUtil.invokeMethod(controllerBean,actionMethod,param);
            }

            if(result instanceof View){
                View view = (View) result;
                String path = view.getPath();
                if(StringUtils.isNotEmpty(path)){
                    if(path.startsWith("/")){
                        resp.sendRedirect(req.getContextPath()+path);
                    }else {
                        Map<String,Object> model = view.getModel();
                        model.forEach((k,v)->req.setAttribute(k,v));
                    }
                    req.getRequestDispatcher(ConfigHelper.getAppBaseJspPath() + path).forward(req,resp);
                }
            }else if(result instanceof Data) {
                Data data = (Data) result;
                Object model = data.getModel();
                if(model != null){
                    resp.setContentType("application/json");
                    resp.setCharacterEncoding("UTF-8");
                    PrintWriter writer = resp.getWriter();
                    String json = JsonUtil.toJson(model);
                    writer.write(json);
                    writer.flush();
                    writer.close();
                }
            }
        }
    }

}
