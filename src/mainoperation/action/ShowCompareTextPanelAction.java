package mainoperation.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import mainoperation.view.MainScreen;
import compareoperation.view.CompareTextPanel;

public class ShowCompareTextPanelAction implements ActionListener{

	private MainScreen mainScreen;

	public ShowCompareTextPanelAction(MainScreen mainScreen) {
		this.mainScreen = mainScreen;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		CompareTextPanel compareTextPanel = new CompareTextPanel();
		mainScreen.changeContent(compareTextPanel);
	}

}
