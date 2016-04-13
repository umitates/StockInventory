package mainoperation.view;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import mainoperation.action.ShowCompareTextPanelAction;
import mainoperation.action.ShowConcatenateTextPanelAction;

public class MainScreen extends JFrame {

	private static final long serialVersionUID = 1L;

	public MainScreen() {
		setJMenuBar(createMenuBar());
		this.getContentPane().add(new JPanel());
	}

	public void changeContent(Component component) {
		this.getContentPane().removeAll();
		this.getContentPane().add(component);
		this.getContentPane().validate();
	}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu operations = new JMenu("Ýþlemler");
		operations.add(getCompareMenuItem());
		operations.addSeparator();
		operations.add(getConcatenateMenuItem());
		menuBar.add(operations);

		return menuBar;
	}

	private JMenuItem getConcatenateMenuItem() {
		ShowConcatenateTextPanelAction showConcatenateTextPanelAction = new ShowConcatenateTextPanelAction(this);

		JMenuItem concatenateMenuItem = new JMenuItem("TxT Birleþtir");
		concatenateMenuItem.addActionListener(showConcatenateTextPanelAction);
		
		return concatenateMenuItem;
	}

	private JMenuItem getCompareMenuItem() {
		ShowCompareTextPanelAction showCompareTextPanelAction = new ShowCompareTextPanelAction(this);
		
		JMenuItem compareMenuItem = new JMenuItem("TxT Karþýlaþtýr");
		compareMenuItem.addActionListener(showCompareTextPanelAction);
		
		return compareMenuItem;
	}
}
