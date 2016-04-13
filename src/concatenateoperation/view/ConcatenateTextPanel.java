package concatenateoperation.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import concatenateoperation.action.AddTextFileAction;
import concatenateoperation.action.ClearTextFilesAction;
import concatenateoperation.action.ConcatenateTextFilesAction;
import concatenateoperation.model.FileListModel;

public class ConcatenateTextPanel extends JPanel{
	
	private FileListModel fileListModel;

	private static final long serialVersionUID = 4711157982600097937L;
	

	public ConcatenateTextPanel()  {
		Box vertical = Box.createVerticalBox();
		
		vertical.add(createConcatenatedFiles());
		vertical.add(createConcatenateActionButton());
		
		this.add(vertical);
	}


	private Component createConcatenateActionButton() {
		JPanel actionPanel = new JPanel(new GridLayout(1, 2));
		
		JButton clearButton = new JButton("Temizle");
		clearButton.addActionListener(new ClearTextFilesAction(this));
		
		JButton addFileButton = new JButton("Dosya Ekle");
		addFileButton.addActionListener(new AddTextFileAction(this));
		
		JButton concatenateButton = new JButton("Birleþtir");
		concatenateButton.addActionListener(new ConcatenateTextFilesAction(this));
		
		actionPanel.add(clearButton);
		actionPanel.add(addFileButton);
		actionPanel.add(concatenateButton);
		
		return actionPanel;
	}


	private Component createConcatenatedFiles() {
		fileListModel = new FileListModel();
		JList<File> fileList = new JList<File>(fileListModel);
		fileList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		fileList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		JScrollPane listScroller = new JScrollPane(fileList);
		listScroller.setPreferredSize(new Dimension(600, 600));
		return listScroller;
	}


	public FileListModel getFileListModel() {
		return fileListModel;
	}
}
