package gr.aueb.cf.myrestbackendapi.Security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
// similar to CustomAuthenticationEntryPoint, this handles the case of logged in user that has no clearance
// in this project this refers to users with role USER.
// SUPER_ADMIN role should never get this message
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException,
            ServletException {
        // Set the response status and content type
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json;charset=UTF-8");

        // Write a custom JSON response with the collected information
        String json = "{\"code\": \"userNotAuthorized\", \"description\": \"User is not allowed to access this endpoint.\"}";
        response.getWriter().write(json);
    }
}
