package compareoperation.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.File;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import compareoperation.action.CompareTextFilesAction;
import compareoperation.action.ShowActualFileChooserDialogAction;
import compareoperation.action.ShowExpectedFileChooserDialogAction;
import compareoperation.dto.Comparison;
import compareoperation.model.ComparisonTableModel;

public class CompareTextPanel extends JPanel{
	
	private JTextField actualFileTextField;
	private JTextField expectedFileTextField;

	private JButton actualFileTextChooserButton;
	private JButton expectedFileTextChooserButton;

	private JCheckBox onlyShowDifferentRowsCheckBox;
	
	private ComparisonTableModel tableModel;
	
	private File actualFile;
	private File expectedFile;

	private static final long serialVersionUID = 2329998894472333726L;
	
	public CompareTextPanel() {
		Box vertical = Box.createVerticalBox();
		
		JPanel criteriaPanel = new JPanel(new GridLayout(3, 1));
		criteriaPanel.add(createActualFileChooser());
		criteriaPanel.add(createExpectedFileChooser());
		criteriaPanel.add(createCompareFiled());
		
		vertical.add(criteriaPanel);
		vertical.add(createComparisonResultDataGrid());
		this.add(vertical);
	}

	private Component createComparisonResultDataGrid() {
		tableModel = new ComparisonTableModel();
		JTable table = new JTable(tableModel);
		table.setFillsViewportHeight(true);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(5);
		table.getColumnModel().getColumn(1).setPreferredWidth(35);
		table.getColumnModel().getColumn(2).setPreferredWidth(20);
		table.getColumnModel().getColumn(3).setPreferredWidth(20);
		table.getColumnModel().getColumn(4).setPreferredWidth(20);
		
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(600, 500));
		return scrollPane;
	}

	private Component createCompareFiled() {
		JPanel comparePanel = new JPanel();
		comparePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		onlyShowDifferentRowsCheckBox = new JCheckBox("Sadece farklý olanlarý göster");
		comparePanel.add(onlyShowDifferentRowsCheckBox);
		
		JButton compareButton = new JButton("Karþýlaþtýr");
		compareButton.addActionListener(new CompareTextFilesAction(this));
		comparePanel.add(compareButton);
		
		return comparePanel;
	}

	private Component createActualFileChooser() {
		JPanel actualFileChoosePanel = new JPanel();
		actualFileChoosePanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		actualFileChoosePanel.add(new JLabel("Birinci Dosya: "));

		actualFileTextField = new JTextField();
		actualFileTextField.setEditable(false);
		actualFileChoosePanel.add(actualFileTextField);
		
		actualFileTextChooserButton = new JButton("Dosya Seç");
		actualFileTextChooserButton.addActionListener(new ShowActualFileChooserDialogAction(this));
		actualFileChoosePanel.add(actualFileTextChooserButton);
		
		return actualFileChoosePanel;
	}

	private Component createExpectedFileChooser() {
		JPanel expectedFileChoosePanel = new JPanel();
		expectedFileChoosePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		expectedFileChoosePanel.add(new JLabel("Ýkinci Dosya: "));

		expectedFileTextField = new JTextField();
		expectedFileTextField.setEditable(false);
		expectedFileChoosePanel.add(expectedFileTextField);
		
		expectedFileTextChooserButton = new JButton("Dosya Seç");
		expectedFileTextChooserButton.addActionListener(new ShowExpectedFileChooserDialogAction(this));
		expectedFileChoosePanel.add(expectedFileTextChooserButton);
		
		return expectedFileChoosePanel;
	}

	public void setActualFile(File selectedFile) {
		this.actualFile = selectedFile;
		this.actualFileTextField.setText(actualFile.getPath());
		this.validate();
	}
	
	public File getActualFile() {
		return actualFile;
	}
	
	public void setExpectedFile(File selectedFile) {
		this.expectedFile = selectedFile;
		this.expectedFileTextField.setText(expectedFile.getPath());
		this.validate();
	}
	
	public File getExpectedFile() {
		return expectedFile;
	}

	public void setComparisons(List<Comparison> comparisons) {
		tableModel.setData(comparisons);
	}
	
	public boolean onlyShowDifferentRows() {
		return onlyShowDifferentRowsCheckBox.isSelected();
	}
}
