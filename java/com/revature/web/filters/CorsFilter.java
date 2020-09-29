package com.revature.web.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

//this will be the filter class to allow the browser to not get blocked
//implement the filter
public class CorsFilter implements Filter {
	/**
	 * The idea of a filter, before the request is delegated to a servlet, it must first pass through any and all
	 * filters. These filters can manipulate the request and response as needed, in this case, adding certain headers
	 * required to allow cross-origin requests.
	 * 
	 *Our response will be manipulated, we will add some headers to our response to allow this to work. However, we can add filter to 
	 *detect certain requests and perhaps deny them before they even reach a servlet.
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		//we are going to want our response, but this is not a specific enough response so:
		if (!(response instanceof HttpServletResponse)) {
			/**
			 * if the response is not of type httpservletresponse, then
			 * continue the chain and return.
			 */
			chain.doFilter(request, response);
			return;
		}
		/**
		 * if the response IS an instance of HttpServletResponse:
		 * We need to cast the response as an http servlet response because we are
		 * going to add headers, which is specific to Http. Other protocols do not have
		 * headers in the same way.
		 */
		HttpServletResponse res = (HttpServletResponse) response;
		/**
		 * We're going to set multiple different headers:
		 */
		res.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");//allow all origins
		//Allow Specific Http verbs
		res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
		//Allow specific HTTP Headers
		res.setHeader("Accesss-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type," + 
		"Access-Control-Request-Method, Access-Control-Request-Headers");
		
		//now we can allow credentials:
		res.setHeader("Access-Control-Allow-Credentials", "true" );
		//continues the flter chain
		chain.doFilter(request, response);
	}

}
