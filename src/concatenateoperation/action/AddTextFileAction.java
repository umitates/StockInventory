package concatenateoperation.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import concatenateoperation.view.ConcatenateTextPanel;

public class AddTextFileAction implements ActionListener {

	private ConcatenateTextPanel concatenateTextPanel;

	public AddTextFileAction(ConcatenateTextPanel concatenateTextPanel) {
		this.concatenateTextPanel = concatenateTextPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setMultiSelectionEnabled(true);
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Sayým Dosyalarý", "txt", "csv"));
		int operation = fileChooser.showOpenDialog(concatenateTextPanel);
		
		if (operation == JFileChooser.APPROVE_OPTION) {
			File[] selectedFiles = fileChooser.getSelectedFiles();
			for(File selectedFile : selectedFiles) {
				concatenateTextPanel.getFileListModel().addElement(selectedFile);
			}
        }
	}

}
