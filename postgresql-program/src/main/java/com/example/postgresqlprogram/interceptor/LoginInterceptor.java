package com.example.postgresqlprogram.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    private boolean isAuthorized(HttpServletRequest request, String expectedRole) {
        HttpSession session = request.getSession(false);
        if (session == null) return false;
        String userRole = (String) session.getAttribute("userRole");
        return expectedRole.equals(userRole);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();

        if (uri.startsWith("/managerdashboard") && !isAuthorized(request, "manager")) {
            response.sendRedirect("/login");
            return false;
        }

        if (uri.startsWith("/spectatordashboard") && !isAuthorized(request, "spectator")) {
            response.sendRedirect("/login");
            return false;
        }

        if (uri.startsWith("/teamdashboard") && !isAuthorized(request, "team")) {
            response.sendRedirect("/login");
            return false;
        }

        return true;
    }
}
