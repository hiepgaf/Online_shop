package by.epam.shop.tag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

/**
 * The Class SubStringTag. If the text from attribute is greater than a certain
 * length, cuts it.
 */
public class SubStringTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(SubStringTag.class);
	private String text;

	/**
	 * Sets the text.
	 *
	 * @param text
	 *            the new text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
	 */
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}
