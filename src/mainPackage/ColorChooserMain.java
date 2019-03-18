package mainPackage;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

/**
 * Starts the application and sets up the menu bar.
 * @author Alexander Libot
 *
 */
@SuppressWarnings("serial")
public class ColorChooserMain extends JFrame {
	
	private JMenuBar menuBar;
	private JMenu menu;
	private ArrayList<JMenuItem> menuItems;
	private int currentPanelIndex = 0;
	
	private Component currentPanel;
	
	public ColorChooserMain() {
		initMenu();
		
		currentPanel = new ColorChooser(); //Starts normal mode
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(currentPanel);
		setJMenuBar(menuBar);
		setTitle("Normal mode");
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	/**
	 * Initializes a menu with three items with corresponding key-commands
	 */
	private void initMenu() {
		menuBar = new JMenuBar();
		menu = new JMenu("Change mode");
		menuBar.add(menu);

		menuItems = new ArrayList<>();

		JMenuItem menuItemNormal = new JMenuItem("Normal mode", KeyEvent.VK_1);
		menuItemNormal.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.META_MASK));
		menuItemNormal.getAccessibleContext().setAccessibleDescription("Changes to normal mode");
		menuItemNormal.setEnabled(false);
		menu.add(menuItemNormal);
		menuItems.add(menuItemNormal);

		JMenuItem menuItemAlpha = new JMenuItem("Alpha mode", KeyEvent.VK_2);
		menuItemAlpha.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.META_MASK));
		menuItemAlpha.getAccessibleContext().setAccessibleDescription("Changes to alpha mode");
		menu.add(menuItemAlpha);
		menuItems.add(menuItemAlpha);

		JMenuItem menuItemCSS = new JMenuItem("CSS mode", KeyEvent.VK_3);
		menuItemCSS.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.META_MASK));
		menuItemCSS.getAccessibleContext().setAccessibleDescription("Changes to CSS mode");
		menu.add(menuItemCSS);
		menuItems.add(menuItemCSS);

		new MenuListener();
	}
	

	/**
	 * Listener for menu-items. Changes between the three implemented modes.
	 *
	 */
	private class MenuListener implements ActionListener {

	public MenuListener() {
		for (JMenuItem menuItem : menuItems)
			menuItem.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		remove(currentPanel);
		if (e.getSource().equals(menuItems.get(0))) { //If normal mode is chosen
			currentPanel = new ColorChooser();
			setTitle("Normal mode");
			menuItems.get(currentPanelIndex).setEnabled(true);
			currentPanelIndex = 0;
		}
		else if (e.getSource().equals(menuItems.get(1))) { //If alpha mode is chosen
			currentPanel = new ColorChooserAlpha();
			setTitle("Alpha mode");
			menuItems.get(currentPanelIndex).setEnabled(true);
			currentPanelIndex = 1;
		}
			
		else if (e.getSource().equals(menuItems.get(2))) { //If CSS mode is chosen
			currentPanel = new ColorChooserAlphaCSS();
			setTitle("CSS mode");
			menuItems.get(currentPanelIndex).setEnabled(true);
			currentPanelIndex = 2;
		}
		
		add(currentPanel);
		pack();
		menuItems.get(currentPanelIndex).setEnabled(false);
	}
	
}
	
	/**
	 * Main-method for starting the application
	 */
	public static void main(String[] args) {
		System.setProperty("apple.laf.useScreenMenuBar", "true"); //Used to show Mac menu-bar
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ColorChooserMain();
			}
		});
		
	}

}
