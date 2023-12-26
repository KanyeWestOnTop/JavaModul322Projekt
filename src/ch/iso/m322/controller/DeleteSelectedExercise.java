package ch.iso.m322.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import ch.iso.m322.model.Exercise;
import ch.iso.m322.util.ReferenceFinder;
import ch.iso.m322.view.MyFrame;

public class DeleteSelectedExercise implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {

		final Component item = (Component) e.getSource();
		final MyFrame frame = (MyFrame) ReferenceFinder.findFrame(item);

		final JList<Exercise> table = frame.getExerciseJList();
		final DefaultListModel<Exercise> model = (DefaultListModel<Exercise>) table.getModel();

		int[] selectedRows = table.getSelectedIndices();
		if (selectedRows.length > 0) {
			// Remove selected rows from the table model in reverse order
			for (int i = selectedRows.length - 1; i >= 0; i--) {
				model.remove(selectedRows[i]);
			}

		}
	}

}
