package cn.dlpu.oa.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static cn.dlpu.oa.utils.PubFun.LOGIN_USER;

public class SessionTimeoutFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 过滤器初始化
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 获取请求的路径
        String requestURI = httpRequest.getRequestURI();
        if (!"/user/loginPage".equals(requestURI) && !"/smartLab/".equals(requestURI)) {
            // 检查session中是否有用户信息
            if (httpRequest.getSession().getAttribute(LOGIN_USER) == null) {
                // 如果没有用户信息，可以判断为会话超时
                httpResponse.sendRedirect("/page/user/loginPage.jsp"); // 重定向到登录页面
//                httpResponse.sendRedirect("user/loginPage.jsp"); // 重定向到登录页面
            } else {
                // 如果有用户信息，继续请求处理
                chain.doFilter(request, response);
            }
        } else {
            httpResponse.sendRedirect("smartLab/page/user/loginPage.jsp"); // 重定向到登录页面
        }

    }

    @Override
    public void destroy() {
        // 过滤器销毁
    }
}
