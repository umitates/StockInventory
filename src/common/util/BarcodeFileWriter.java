package common.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class BarcodeFileWriter {

	private File file;
	
	private final String NEW_LINE = System.getProperty("line.separator");

	public BarcodeFileWriter(File file) {
		this.file = file;
	}

	public void write(Map<String, Long> elements) {
		try {
			
			FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			for (String barcode : elements.keySet()) {
				bufferedWriter.write(barcode + "   " + elements.get(barcode) + NEW_LINE);
			}
			
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
