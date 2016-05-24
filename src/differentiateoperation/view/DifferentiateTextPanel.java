package differentiateoperation.view;

import java.awt.Component;
import java.awt.FlowLayout;
import java.io.File;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import differentiateoperation.action.DifferentiateTextFilesAction;
import differentiateoperation.action.ShowFirstFileChooserDialogAction;
import differentiateoperation.action.ShowSecondFileChooserDialogAction;

public class DifferentiateTextPanel  extends JPanel{
	
	private JTextField firstFileTextField;
	private JTextField secondFileTextField;

	private JButton firstFileTextChooserButton;
	private JButton secondFileTextChooserButton;

	private File firstFile;
	private File secondFile;

	private static final long serialVersionUID = 2329998894472333726L;
	
	public DifferentiateTextPanel() {
		Box vertical = Box.createVerticalBox();
		
		vertical.add(createFirstFileChooser());
		vertical.add(createSecondFileChooser());
		vertical.add(createDifferentiateFiled());
		
		this.add(vertical);
	}



	private Component createDifferentiateFiled() {
		JPanel differentiatePanel = new JPanel();
		differentiatePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		JButton differentiateButton = new JButton("Fark Oluþtur");
		differentiateButton.addActionListener(new DifferentiateTextFilesAction(this));
		differentiatePanel.add(differentiateButton);
		
		return differentiatePanel;
	}

	private Component createFirstFileChooser() {
		JPanel firstFileChoosePanel = new JPanel();
		firstFileChoosePanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		firstFileChoosePanel.add(new JLabel("Birinci Dosya: "));

		firstFileTextField = new JTextField();
		firstFileTextField.setEditable(false);
		firstFileChoosePanel.add(firstFileTextField);
		
		firstFileTextChooserButton = new JButton("Dosya Seç");
		firstFileTextChooserButton.addActionListener(new ShowFirstFileChooserDialogAction(this));
		firstFileChoosePanel.add(firstFileTextChooserButton);
		
		return firstFileChoosePanel;
	}

	private Component createSecondFileChooser() {
		JPanel secondFileChoosePanel = new JPanel();
		secondFileChoosePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		secondFileChoosePanel.add(new JLabel("Ýkinci Dosya: "));

		secondFileTextField = new JTextField();
		secondFileTextField.setEditable(false);
		secondFileChoosePanel.add(secondFileTextField);
		
		secondFileTextChooserButton = new JButton("Dosya Seç");
		secondFileTextChooserButton.addActionListener(new ShowSecondFileChooserDialogAction(this));
		secondFileChoosePanel.add(secondFileTextChooserButton);
		
		return secondFileChoosePanel;
	}

	public void setFirstFile(File selectedFile) {
		this.firstFile = selectedFile;
		this.firstFileTextField.setText(firstFile.getPath());
		this.validate();
	}
	
	public File getFirstFile() {
		return firstFile;
	}
	
	public void setSecondFile(File selectedFile) {
		this.secondFile = selectedFile;
		this.secondFileTextField.setText(secondFile.getPath());
		this.validate();
	}
	
	public File getSecondFile() {
		return secondFile;
	}
}
