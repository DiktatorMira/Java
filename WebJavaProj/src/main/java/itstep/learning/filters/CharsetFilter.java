package itstep.learning.filters;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CharsetFilter implements Filter {
    private FilterConfig filterConfig;
    @Override public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        request.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        System.out.println("CharsetFilter worked for: " + request.getRequestURI());
        filterChain.doFilter(servletRequest, servletResponse);
    }
    @Override public void destroy() { this.filterConfig = null; }
    @Override public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }
}