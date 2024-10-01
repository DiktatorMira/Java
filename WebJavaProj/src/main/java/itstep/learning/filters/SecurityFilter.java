package itstep.learning.filters;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityFilter implements Filter {
    private FilterConfig filterConfig;

    @Override public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }
    @Override public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Object signature = request.getAttribute("signature");

        if (signature == null || !(signature instanceof Boolean) || !(Boolean) signature) request.getRequestDispatcher("/WEB-INF/views/insecure.jsp").forward(request, response);
        else filterChain.doFilter(servletRequest, servletResponse);
    }
    @Override public void destroy() { this.filterConfig = null; }
}