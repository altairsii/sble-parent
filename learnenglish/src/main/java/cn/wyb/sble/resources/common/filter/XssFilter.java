package cn.wyb.sble.resources.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import cn.wyb.sble.resources.common.filter.util.XssHttpServletRequestWrapper;

/**
 * <p>拦截防止sql注入、xss注入 </p>
 * @author wangyongbing
 *
 */
public class XssFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		 XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(
		            (HttpServletRequest) request);
		        filterChain.doFilter(xssRequest, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
