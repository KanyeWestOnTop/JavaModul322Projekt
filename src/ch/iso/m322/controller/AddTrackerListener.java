package ch.iso.m322.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import ch.iso.m322.model.Tracker;
import ch.iso.m322.util.ReferenceFinder;
import ch.iso.m322.view.MyFrame;


public class AddTrackerListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		final Component item = (Component) e.getSource();
		final JFrame frame = ReferenceFinder.findFrame(item);
		
		Tracker t = ((MyFrame) frame).getData();
		
		
		final JTable table = ((MyFrame) frame).getGetTrackerTable();
		final DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		model.addRow(t.toStringArray());
		
	}


	
}
