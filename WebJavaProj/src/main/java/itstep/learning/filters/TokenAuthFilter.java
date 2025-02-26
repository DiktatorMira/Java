package itstep.learning.filters;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import itstep.learning.dal.dao.AuthDao;
import itstep.learning.dal.dto.User;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Singleton public class TokenAuthFilter implements Filter {
    private final AuthDao authDao;

    @Inject public TokenAuthFilter( AuthDao authDao ) {
        this.authDao = authDao;
    }
    @Override public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String authHeader = req.getHeader( "Authorization" );
        if (authHeader != null) {
            String authScheme = "Bearer ";
            if (authHeader.startsWith(authScheme)) {
                String token = authHeader.substring(authScheme.length());
                User user = authDao.getUserByToken(token);
                if (user != null) request.setAttribute("auth-token-user", user);
            }
        }
        chain.doFilter(request, response);
    }
    @Override public void init(FilterConfig filterConfig) throws ServletException {}
    @Override public void destroy() {}
}