package com.wheelymammoth.config;

import com.wheelymammoth.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    
    // Pages that don't require authentication
    private static final String[] PUBLIC_PAGES = {
        "/login", "/register", "/static", "/h2-console", "/api"
    };
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getRequestURI();
        
        // Allow home page (index) without authentication
        if (path.equals("/") || path.equals("/index") || path.equals("/index.html")) {
            return true;
        }
        
        // Allow public pages
        for (String publicPage : PUBLIC_PAGES) {
            if (path.startsWith(publicPage)) {
                return true;
            }
        }
        
        // Check if user is logged in
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            // Store the intended destination
            String queryString = request.getQueryString();
            String redirectUrl = path + (queryString != null ? "?" + queryString : "");
            session = request.getSession(true);
            session.setAttribute("redirectAfterLogin", redirectUrl);
            
            response.sendRedirect("/login");
            return false;
        }
        
        return true;
    }
}

