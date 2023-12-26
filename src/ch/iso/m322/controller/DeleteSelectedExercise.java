package ch.iso.m322.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ch.iso.m322.model.Exercise;
import ch.iso.m322.util.ReferenceFinder;
import ch.iso.m322.view.MyFrame;

public class DeleteSelectedExercise implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        final Component item = (Component) e.getSource();
        final MyFrame frame = (MyFrame) ReferenceFinder.findFrame(item);

        final JList<Exercise> listExercise = frame.getExerciseJList();
        final DefaultListModel<Exercise> modelExercise = (DefaultListModel<Exercise>) listExercise.getModel();

        final JTable tableTracker = frame.getGetTrackerTable();
        final DefaultTableModel modelTracker = (DefaultTableModel) tableTracker.getModel();

        int[] selectedRows = listExercise.getSelectedIndices();
        if (selectedRows.length > 0) {
            for (int i = selectedRows.length - 1; i >= 0; i--) {
                Exercise deletedExercise = modelExercise.getElementAt(selectedRows[i]);
                modelExercise.remove(selectedRows[i]);

                removeTrackersForExercise(modelTracker, deletedExercise);
            }
        }
    }

    private void removeTrackersForExercise(DefaultTableModel modelTracker, Exercise deletedExercise) {
        ArrayList<Integer> rowsToRemove = new ArrayList<>();

        for (int row = 0; row < modelTracker.getRowCount(); row++) {
            String exerciseName = (String) modelTracker.getValueAt(row, 1);

            if (exerciseName.equals(deletedExercise.getName())) {
                rowsToRemove.add(row);
            }
        }

        for (int i = rowsToRemove.size() - 1; i >= 0; i--) {
            modelTracker.removeRow(rowsToRemove.get(i));
        }
    }
}
