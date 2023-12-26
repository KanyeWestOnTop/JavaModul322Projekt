package ch.iso.m322.view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import ch.iso.m322.controller.DeleteSelectedExercise;
import ch.iso.m322.controller.DeleteSelectedProgressListener;
import ch.iso.m322.controller.ExitListener;
import ch.iso.m322.controller.SaveListener;
import ch.iso.m322.ressources.MenuConstants;

public class MyMenu extends JMenuBar {

	private static final long serialVersionUID = 1946531818542677119L;

	public MyMenu() {
		super();
		
		createToolsMenu();
		
	}

	private void createToolsMenu() {
		final JMenu menuTools = new JMenu(MenuConstants.MENU_TITLE);
		final JMenuItem itemExit = new JMenuItem(MenuConstants.MENU_EXIT);
		final JMenuItem itemDelSelProgress = new JMenuItem(MenuConstants.MENU_DELETE_SELECTED_PROGRESS);
		final JMenuItem itemDelSelExercise = new JMenuItem(MenuConstants.MENU_DELETE_SELECTED_EXERCISES);
		final JMenuItem itemSave = new JMenuItem(MenuConstants.MENU_SAVE);
		
		itemExit.addActionListener(new ExitListener());
		itemSave.addActionListener(new SaveListener());
		itemDelSelProgress.addActionListener(new DeleteSelectedProgressListener());
		itemDelSelExercise.addActionListener(new DeleteSelectedExercise());
	
		menuTools.add(itemExit);
		menuTools.add(itemSave);
		menuTools.add(itemDelSelProgress);
		menuTools.add(itemDelSelExercise);

		this.add(menuTools);
	}
	
	
	
	
}
