package mainoperation.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import mainoperation.view.MainScreen;
import concatenateoperation.view.ConcatenateTextPanel;

public class ShowConcatenateTextPanelAction implements ActionListener{

	private MainScreen mainScreen;

	public ShowConcatenateTextPanelAction(MainScreen mainScreen) {
		this.mainScreen = mainScreen;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ConcatenateTextPanel concatenateTextPanel = new ConcatenateTextPanel();
		mainScreen.changeContent(concatenateTextPanel);
	}

}
