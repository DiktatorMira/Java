package itstep.learning.filters;
import com.google.inject.Singleton;
import javax.servlet.*;
import java.io.IOException;

@Singleton
public class SecurityFilter implements Filter {
    @Override public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setAttribute( "signature", true ) ;
        chain.doFilter( request, response ) ;
    }
    @Override public void init(FilterConfig filterConfig) throws ServletException {}
    @Override public void destroy() {}
}