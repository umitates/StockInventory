package concatenateoperation.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import concatenateoperation.view.ConcatenateTextPanel;

public class ClearTextFilesAction implements ActionListener{

	private ConcatenateTextPanel concatenateTextPanel;

	public ClearTextFilesAction(ConcatenateTextPanel concatenateTextPanel) {
		this.concatenateTextPanel = concatenateTextPanel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		concatenateTextPanel.getFileListModel().removeAllElements();
	}

}
