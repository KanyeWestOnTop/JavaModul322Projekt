package ch.iso.m322.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ch.iso.m322.model.DataStoreExercise;
import ch.iso.m322.model.DataStoreTrack;
import ch.iso.m322.model.Exercise;
import ch.iso.m322.model.IDataStoreExercise;
import ch.iso.m322.model.IDataStoreTracker;
import ch.iso.m322.model.Tracker;
import ch.iso.m322.util.ReferenceFinder;
import ch.iso.m322.view.MyFrame;

public class SaveListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        Component item = (Component) e.getSource();
        MyFrame frame = (MyFrame) ReferenceFinder.findFrame(item);

        // Save Exercise data
        final JList<Exercise> tableExercise = frame.getExerciseJList();
        final DefaultListModel<Exercise> modelExercise = (DefaultListModel<Exercise>) tableExercise.getModel();
        ArrayList<Exercise> exerciseData = new ArrayList<>();
        for (int i = 0; i < modelExercise.getSize(); i++) {
            Exercise exercise = modelExercise.getElementAt(i);
            exerciseData.add(exercise);
        }

        IDataStoreExercise storeExercise = new DataStoreExercise();
        storeExercise.save(exerciseData, IDataStoreExercise.EXERCISE_LIST);

        // Save Tracker data with Exercise references
        final JTable tableTracker = frame.getGetTrackerTable();
        final DefaultTableModel modelTracker = (DefaultTableModel) tableTracker.getModel();
        ArrayList<Tracker> data = new ArrayList<>();
        final Vector<?> left_vector = modelTracker.getDataVector();
        for (Object o : left_vector) {
            String trackName = (String) ((Vector<?>) o).elementAt(0);
            Exercise exercise = (Exercise) ((Vector<?>) o).elementAt(1);
            String date = (String) ((Vector<?>) o).elementAt(2);
            int weight = Integer.parseInt((String) ((Vector<?>) o).elementAt(3));
            int rpe = Integer.parseInt((String) ((Vector<?>) o).elementAt(4));

            // Assuming you have a constructor in Tracker that takes Exercise as a parameter
            Tracker tracker = new Tracker(trackName, exercise, date, weight, rpe);
            data.add(tracker);
        }

        IDataStoreTracker storeTracker = new DataStoreTrack();
        storeTracker.save(data, IDataStoreTracker.TRACK_LIST);
    }
}