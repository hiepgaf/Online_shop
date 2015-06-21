package by.epam.shop.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import by.epam.shop.entity.User;

public class LoginTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(LoginTag.class);

	@Override
	public int doStartTag() {
		User user = (User) pageContext.getSession().getAttribute("user");
		JspWriter out = pageContext.getOut();
		try {
			if (user == null) {
				out.write("<div id=\"innerlogin\">");
				out.write("<form method=\"POST\" action=\"Controller\">");
				out.write("<div>");
				out.write("<input name=\"action\" type=\"hidden\" value=\"login\" />");
				out.write("</div>");
				out.write("<div>");
				out.write("<input class=\"input\" type=\"text\" name=\"login\" value=\"\" />");
				out.write("</div>");
				out.write("<div>");
				out.write("<input class=\"input\" type=\"password\" name=\"password\"");
				out.write("value=\"********\" />");
				out.write("</div>");
				out.write("<div>");
				out.write("<input class=\"button\" type=\"submit\" value=\"Войти\" />");
				out.write("</div>");
				out.write("</form>");
				out.write("<div id=\"register\">");
				out.write("<a href=\"#\">Зарегестрироваться</a>");
				out.write("</div>");
				out.write("</div>");
			} else {
				out.write("<div id=\"innerlogin\">");
				out.write("<div>Вы вошли как ${message}</div>");
				out.write("<div>");
				out.write("<form method=\"POST\" action=\"Controller\">");
				out.write("<input name=\"action\" type=\"hidden\" value=\"show_shopping_cart\" /> <input");
				out.write(" class=\"button\" type=\"submit\" name=\"shopping_cart\"");
				out.write("value=\"Ваша корзина\">");
				out.write("</form>");
				out.write("</div>");
				out.write("<div>");
				out.write("<form method=\"POST\" action=\"Controller\">");
				out.write("<input name=\"action\" type=\"hidden\" value=\"show_orders\" /> <input");
				out.write(" class=\"button\" type=\"submit\" name=\"orders\" value=\"Ваши заказы\">");
				out.write("</form>");
				out.write("</div>");
				out.write("<div>");
				out.write("<form method=\"POST\" action=\"Controller\">");
				out.write("<input name=\"action\" type=\"hidden\" value=\"log_out\" /> <input");
				out.write(" class=\"button\" type=\"submit\" name=\"log_out\" value=\"Выйти\">");
				out.write("</form>");
				out.write("</div>");
				out.write("</div>");
			}
		} catch (IOException e) {
			log.error(e);
		}
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}
