package mainoperation.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import mainoperation.view.MainScreen;
import differentiateoperation.view.DifferentiateTextPanel;

public class ShowDifferentiateTextPanelAction implements ActionListener{

	private MainScreen mainScreen;

	public ShowDifferentiateTextPanelAction(MainScreen mainScreen) {
		this.mainScreen = mainScreen;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DifferentiateTextPanel differentiateTextPanel = new DifferentiateTextPanel();
		mainScreen.changeContent(differentiateTextPanel);
	}

}
