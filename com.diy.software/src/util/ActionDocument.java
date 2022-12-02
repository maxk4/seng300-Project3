package util;


import javax.swing.SwingUtilities;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class ActionDocument extends PlainDocument {
	private static final long serialVersionUID = 5229352517172993851L;
	
	private final Runnable action;
	
	public ActionDocument(Runnable action) {
		this.action = action;
	}
	
	@Override
	public void insertString(int offs, String str, AttributeSet attrs) throws BadLocationException {
		super.insertString(offs, str, attrs);
		action.run();
	}
	
	@Override
	public void removeUpdate(AbstractDocument.DefaultDocumentEvent chng) {
		super.removeUpdate(chng);
		System.out.println("removed");
		SwingUtilities.invokeLater(() -> {
			action.run();
		});
	}
}
