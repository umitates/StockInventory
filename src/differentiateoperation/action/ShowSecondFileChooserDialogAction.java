package differentiateoperation.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import differentiateoperation.view.DifferentiateTextPanel;

public class ShowSecondFileChooserDialogAction implements ActionListener{

	private DifferentiateTextPanel differentiateTextPanel;

	public ShowSecondFileChooserDialogAction(DifferentiateTextPanel differentiateTextPanel) {
		this.differentiateTextPanel = differentiateTextPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Say�m Dosyalar�", "txt", "csv"));
		int operation = fileChooser.showOpenDialog(differentiateTextPanel);
		
		if (operation == JFileChooser.APPROVE_OPTION) {
            differentiateTextPanel.setSecondFile(fileChooser.getSelectedFile());
        }
	}

}
