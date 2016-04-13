package compareoperation.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import compareoperation.dto.Comparison;

public class ComparisonTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -4329832013774899695L;

	private String[] columnNames = new String[]{"Sýra", "Barkod", "Birinci Dosya Adet", "Ýkinci Dosya Adet", "Fark"};
	private Object[][] data;

	public String getColumnName(int col) {
        return columnNames[col].toString();
    }
	
	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		if(data == null) {
			return 0;
		}
		return data.length;
	}

	public Object getValueAt(int row, int col) {
		if(data != null) {
			return data[row][col];
		}
		return null;
	}

	public void setData(List<Comparison> comparisons) {
		data = new Object[comparisons.size()][5];
		for(int i = 0; i < comparisons.size(); i++) {
			Comparison comparison = comparisons.get(i);
			data[i][0] = i + 1;
			data[i][1] = comparison.getBarcode();
			data[i][2] = comparison.getActual();
			data[i][3] = comparison.getExpected();
			data[i][4] = comparison.getDifference();
		}
		fireTableDataChanged();
	}
}
