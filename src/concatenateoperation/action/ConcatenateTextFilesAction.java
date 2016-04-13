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
	
		Map<String, Integer> allElements = readAllElements();
		showFileChooserToWriteOutput(allElements);
	}

	private void showFileChooserToWriteOutput(Map<String, Integer> allElements) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Metin Dosyalar�", "txt"));
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
		resultPopup.showMessageDialog(concatenateTextPanel, "Birle�tirme i�lemi tamamlanm��t�r.\nDosya: " + filePath);
	}

	private Map<String, Integer> readAllElements() {
		Map<String, Integer> allElements = new TreeMap<String, Integer>();
		
		Enumeration<File> files = concatenateTextPanel.getFileListModel().elements();
		
		while(files.hasMoreElements()) {
			BarcodeFileReaeder fileReader = new BarcodeFileReaeder(files.nextElement());
			Map<String, Integer> newBarcodeElements = fileReader.read();
			
			for(String barcode : newBarcodeElements.keySet()) {
				if(allElements.containsKey(barcode)) {
					int oldCount = allElements.get(barcode);
					int newCount = newBarcodeElements.get(barcode);
					allElements.put(barcode, oldCount + newCount);
				}else {
					allElements.put(barcode, newBarcodeElements.get(barcode));
				}
			}
		}
		
		return allElements;
	}

	private void writeAllElementsToSelectedFile(File file, Map<String, Integer> elements) {
		BarcodeFileWriter fileWriter = new BarcodeFileWriter(file);
		fileWriter.write(elements);
	}

}