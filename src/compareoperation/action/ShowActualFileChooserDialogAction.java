package compareoperation.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import compareoperation.view.CompareTextPanel;

public class ShowActualFileChooserDialogAction implements ActionListener{

	private CompareTextPanel compareTextPanel;

	public ShowActualFileChooserDialogAction(CompareTextPanel compareTextPanel) {
		this.compareTextPanel = compareTextPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Sayým Dosyalarý", "txt", "csv"));
		int operation = fileChooser.showOpenDialog(compareTextPanel);
		
		if (operation == JFileChooser.APPROVE_OPTION) {
            compareTextPanel.setActualFile(fileChooser.getSelectedFile());
        }
	}

}
