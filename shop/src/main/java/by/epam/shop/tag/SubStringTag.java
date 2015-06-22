package by.epam.shop.tag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

public class SubStringTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(SubStringTag.class);
	private String text;

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public int doStartTag() {
		int maxTextLength = 140;
		try {
			if (text.length() > maxTextLength) {
				pageContext.getOut().write(text.substring(0, 140) + "...");
			} else {
				pageContext.getOut().write(text + "...");
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
