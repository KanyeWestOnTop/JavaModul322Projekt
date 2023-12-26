package ch.iso.m322.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;


import ch.iso.m322.model.Exercise;
import ch.iso.m322.util.ReferenceFinder;
import ch.iso.m322.view.MyFrame;

public class AddExerciseListener implements ActionListener{

	private JTextField exercise;

	public AddExerciseListener(JTextField exercise) {
		super();
		this.exercise = exercise;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		final Component item = (Component) e.getSource();
		final JFrame frame = ReferenceFinder.findFrame(item);		
		
		final JList<Exercise> list = ((MyFrame) frame).getExerciseJList();
		final DefaultListModel<Exercise> model = (DefaultListModel<Exercise>) list.getModel();
		
		 if (!exercise.getText().isEmpty()) {
			 	Exercise addExercise = new Exercise(exercise.getText());
	            model.addElement(addExercise);
	            exercise.setText("");
	        }
	}


	
}
