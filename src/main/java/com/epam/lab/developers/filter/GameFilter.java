package com.epam.lab.developers.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.epam.lab.developers.data.DataHolder;
import com.epam.lab.developers.data.LoginData;
import com.epam.lab.developers.entity.User;

/**
 * Servlet Filter implementation class GameFilter
 */
@WebFilter({"/home", "/connect","/game"})
public class GameFilter implements Filter {

    /**
     * Default constructor. 
     */
    public GameFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		User user = LoginData.userLogined((HttpServletRequest) request);
		if (null != user) {
			if (DataHolder.getInstance().isUserPlaying(user)) {//якщо у грі то завжди переходимо на сторінку гри
				String url = "game";
				request.getRequestDispatcher(url).forward(request, response);
			} else {//переходимо на connect
				chain.doFilter(request, response);
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
