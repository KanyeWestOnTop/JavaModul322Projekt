package ch.iso.m322.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ch.iso.m322.util.ReferenceFinder;
import ch.iso.m322.view.MyFrame;

public class ExitListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Component item = (Component) e.getSource();
		MyFrame frame = (MyFrame) ReferenceFinder.findFrame(item);
		
		frame.setVisible(true);
		frame.dispose();
		
		// force exit
		System.exit(0);
	}

}
