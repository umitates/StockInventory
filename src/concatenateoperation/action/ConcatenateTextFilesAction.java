package concatenateoperation.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import common.util.BarcodeFileReaeder;
import common.util.BarcodeFileWriter;
import concatenateoperation.view.ConcatenateTextPanel;

public class ConcatenateTextFilesAction implements ActionListener {

	private ConcatenateTextPanel concatenateTextPanel;

	public ConcatenateTextFilesAction(ConcatenateTextPanel concatenateTextPanel) {
		this.concatenateTextPanel = concatenateTextPanel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
	
		Map<String, Long> allElements = readAllElements();
		showFileChooserToWriteOutput(allElements);
	}

	private void showFileChooserToWriteOutput(Map<String, Long> allElements) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Metin Dosyalarý", "txt"));
		int operation = fileChooser.showSaveDialog(concatenateTextPanel);
		
		if (operation == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();
			writeAllElementsToSelectedFile(fileToSave, allElements);
			showOperationResult(fileToSave.getAbsolutePath());
        }
	}

	@SuppressWarnings("static-access")
	private void showOperationResult(String filePath) {
		JOptionPane resultPopup = new JOptionPane(JOptionPane.INFORMATION_MESSAGE);
		resultPopup.showMessageDialog(concatenateTextPanel, "Birleþtirme iþlemi tamamlanmýþtýr.\nDosya: " + filePath);
	}

	private Map<String, Long> readAllElements() {
		Map<String, Long> allElements = new TreeMap<String, Long>();
		
		Enumeration<File> files = concatenateTextPanel.getFileListModel().elements();
		
		while(files.hasMoreElements()) {
			BarcodeFileReaeder fileReader = new BarcodeFileReaeder(files.nextElement());
			Map<String, Long> newBarcodeElements = fileReader.read();
			
			for(String barcode : newBarcodeElements.keySet()) {
				if(allElements.containsKey(barcode)) {
					Long oldCount = allElements.get(barcode);
					Long newCount = newBarcodeElements.get(barcode);
					allElements.put(barcode, oldCount + newCount);
				}else {
					allElements.put(barcode, newBarcodeElements.get(barcode));
				}
			}
		}
		
		return allElements;
	}

	private void writeAllElementsToSelectedFile(File file, Map<String, Long> elements) {
		BarcodeFileWriter fileWriter = new BarcodeFileWriter(file);
		fileWriter.write(elements);
	}

}
