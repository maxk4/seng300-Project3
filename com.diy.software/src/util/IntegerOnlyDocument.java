package util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class IntegerOnlyDocument extends ActionDocument {
	public IntegerOnlyDocument(Runnable action) {
		super(action);
	}

	private static final long serialVersionUID = -5571386913183482459L;

	@Override
	public void insertString(int offset, String s, AttributeSet attrs) throws BadLocationException {
		if (s.length() == 0) return;
		try {
			Integer.parseInt(s);
			super.insertString(offset, s, attrs);
		} catch(NumberFormatException e) {}
	}
}
