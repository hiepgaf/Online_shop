//package by.epam.shop.filter;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import by.epam.shop.entity.User;
//
//@WebFilter(urlPatterns = { "/Controller" }, servletNames = { "Controller" })
//public class ServletSecurityFilter implements Filter {
//
//	@Override
//	public void init(FilterConfig filterConfig) throws ServletException {
//	}
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response,
//			FilterChain chain) throws IOException, ServletException {
//		HttpServletRequest req = (HttpServletRequest) request;
//		HttpServletResponse resp = (HttpServletResponse) response;
//		HttpSession session = req.getSession();
//		User user = (User) session.getAttribute("user");
//		if (user == null || user.getAccessLevel() == 0) {
//			RequestDispatcher dispatcher = request.getServletContext()
//					.getRequestDispatcher("/pages/main.jsp");
//			dispatcher.forward(req, resp);
//			return;
//		}
//		chain.doFilter(request, response);
//	}
//
//	@Override
//	public void destroy() {
//	}
//
//}
