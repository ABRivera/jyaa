package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.*;

/**
 * Servlet Filter implementation class FiltroCors
 */
//@WebFilter("/FiltroCors")
public class FiltroCors implements Filter {

    /**
     * Default constructor. 
     */
    public FiltroCors() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

		HttpServletResponse response = (HttpServletResponse) resp;
		response.setHeader("Access-Control-Allow-Origin","*"); response.setHeader("Access-Control-Allow-Methods","POST,GET,PUT,OPTIONS,DELETE");
		response.setHeader("Access-Control-Allow-Headers","Content-Type, Access-Control-Allow-Headers, x-requested-with, Authorization");
		chain.doFilter(req, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
