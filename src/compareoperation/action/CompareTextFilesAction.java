package compareoperation.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import common.util.BarcodeFileReaeder;
import compareoperation.dto.Comparison;
import compareoperation.view.CompareTextPanel;

public class CompareTextFilesAction implements ActionListener {

	private CompareTextPanel compareTextPanel;

	public CompareTextFilesAction(CompareTextPanel compareTextPanel) {
		this.compareTextPanel = compareTextPanel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Map<String, Long> actualFileBarcodes = readActualFileBarcodes();
		Map<String, Long> expectedFileBarcodes = readExpectedFileBarcodes();
		
		Set<String> barcodes = new HashSet<String>();
		barcodes.addAll(actualFileBarcodes.keySet());
		barcodes.addAll(expectedFileBarcodes.keySet());
 	
		List<Comparison> comparisons = new ArrayList<Comparison>();
		for(String barcode : barcodes) {
			Comparison result = new Comparison();
			result.setBarcode(barcode);
			result.setActual(getValue(actualFileBarcodes, barcode));
			result.setExpected(getValue(expectedFileBarcodes, barcode));
			
			if(compareTextPanel.onlyShowDifferentRows() && result.getActual().equals(result.getExpected())) {
				continue;
			}
			
			comparisons.add(result);
		}
		compareTextPanel.setComparisons(comparisons);
	}

	private Long getValue(Map<String, Long> fileBarcodes, String barcode) {
		if(fileBarcodes.containsKey(barcode)) {
			return fileBarcodes.get(barcode);
		}
		return 0L;
	}

	private Map<String, Long> readActualFileBarcodes() {
		File actualFile = compareTextPanel.getActualFile();
		return new BarcodeFileReaeder(actualFile).read();
	}
	
	private Map<String, Long> readExpectedFileBarcodes() {
		File expectedFile = compareTextPanel.getExpectedFile();
		return new BarcodeFileReaeder(expectedFile).read();
	}

}
