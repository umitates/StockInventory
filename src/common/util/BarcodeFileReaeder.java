package common.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class BarcodeFileReaeder {

	private File barcodeFile;

	public BarcodeFileReaeder(File barcodeFile) {
		this.barcodeFile = barcodeFile;
	}

	public Map<String, Integer> read() {
		Map<String, Integer> barcodes = new TreeMap<String, Integer>();
		try {
			Scanner scanner = new Scanner(barcodeFile);
			while(scanner.hasNext()) {
				String barcode = scanner.next();
				String countAsString = scanner.next();
				Integer count = Integer.parseInt(countAsString.replaceAll("[\\D]", ""));
				if(barcodes.containsKey(barcode)) {
					int existingCount = barcodes.get(barcode);
					barcodes.put(barcode, existingCount + count);
				}else {
					barcodes.put(barcode, count);
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return barcodes;
	}
	
}