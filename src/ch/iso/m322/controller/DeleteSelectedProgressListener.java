package ch.iso.m322.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ch.iso.m322.util.ReferenceFinder;
import ch.iso.m322.view.MyFrame;

public class DeleteSelectedProgressListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {

		final Component item = (Component) e.getSource();
		final MyFrame frame = (MyFrame) ReferenceFinder.findFrame(item);

		final JTable table = frame.getGetTrackerTable();
		final DefaultTableModel model = (DefaultTableModel) table.getModel();

		int[] selectedRows = table.getSelectedRows();
		if (selectedRows.length > 0) {
			// Remove selected rows from the table model in reverse order
			for (int i = selectedRows.length - 1; i >= 0; i--) {
				model.removeRow(selectedRows[i]);
			}

		}
	}
}
