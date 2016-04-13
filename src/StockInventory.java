import java.awt.Dimension;
import java.awt.Toolkit;

import mainoperation.view.MainScreen;


public class StockInventory {
	public static void main(String[] args) {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		MainScreen mainScreen = new MainScreen();
		mainScreen.setSize(new Dimension(650, 700));
		mainScreen.setLocation(dim.width/2-mainScreen.getSize().width/2, dim.height/2-mainScreen.getSize().height/2);
		
		mainScreen.setVisible(true);
	}
}
