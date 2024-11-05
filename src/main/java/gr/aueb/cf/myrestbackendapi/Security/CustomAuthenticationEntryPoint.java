package gr.aueb.cf.myrestbackendapi.Security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

//set response to 401 unauthorized instead of the baseline error page
// doing so by overriding the baseline commence method of AuthenticationEntryPoint
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        //set response to 401 unauthorized instead of the baseline error page
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        String json = "{\"code\": \"userNotAuthenticated\", \"description\": \"User must authenticate in order to access this endpoint\"}";
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
