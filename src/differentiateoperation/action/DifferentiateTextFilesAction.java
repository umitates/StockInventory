package differentiateoperation.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import common.util.BarcodeFileReaeder;
import common.util.BarcodeFileWriter;
import differentiateoperation.view.DifferentiateTextPanel;

public class DifferentiateTextFilesAction implements ActionListener {

	private DifferentiateTextPanel differentiateTextPanel;

	public DifferentiateTextFilesAction(
			DifferentiateTextPanel differentiateTextPanel) {
		this.differentiateTextPanel = differentiateTextPanel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		Map<String, Long> firstFileBarcodes = readFirstFileBarcodes();
		Map<String, Long> secondFileBarcodes = readSecondFileBarcodes();
		Map<String, Long> difference = findDifference(firstFileBarcodes, secondFileBarcodes);
		
		showFileChooserToWriteOutput(difference);
	}

	private Map<String, Long> findDifference(Map<String, Long> firstFileBarcodes,	Map<String, Long> secondFileBarcodes) {
		Map<String, Long> differenceMap = new TreeMap<String, Long>();
		
		for(String key : firstFileBarcodes.keySet()) {
			Long firstFileAmount = firstFileBarcodes.get(key);
			Long secondFileAmount = secondFileBarcodes.get(key) == null ? 0L : secondFileBarcodes.get(key);
			
			Long differenceInAmount = firstFileAmount - secondFileAmount;
			if (differenceInAmount > 0) {
				differenceMap.put(key, differenceInAmount);
			}
		}
		
		return differenceMap;
	}

	private void showFileChooserToWriteOutput(Map<String, Long> difference) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Metin Dosyalarý", "txt"));
		int operation = fileChooser.showSaveDialog(differentiateTextPanel);

		if (operation == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();
			writeAllElementsToSelectedFile(fileToSave, difference);
			showOperationResult(fileToSave.getAbsolutePath());
		}
	}

	@SuppressWarnings("static-access")
	private void showOperationResult(String filePath) {
		JOptionPane resultPopup = new JOptionPane(JOptionPane.INFORMATION_MESSAGE);
		resultPopup.showMessageDialog(differentiateTextPanel, "Fark Oluþturma iþlemi tamamlanmýþtýr.\nDosya: " + filePath);
	}

	private Map<String, Long> readFirstFileBarcodes() {
		File firstFile = differentiateTextPanel.getFirstFile();
		return new BarcodeFileReaeder(firstFile).read();
	}

	private Map<String, Long> readSecondFileBarcodes() {
		File secondFile = differentiateTextPanel.getSecondFile();
		return new BarcodeFileReaeder(secondFile).read();
	}
	
	private void writeAllElementsToSelectedFile(File file, Map<String, Long> elements) {
		BarcodeFileWriter fileWriter = new BarcodeFileWriter(file);
		fileWriter.write(elements);
	}

}
