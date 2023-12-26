package ch.iso.m322.controller;

import java.util.ArrayList;
import java.util.Iterator;

import ch.iso.m322.model.Exercise;
import ch.iso.m322.model.DataStoreExercise;
import ch.iso.m322.model.IDataStoreExercise;

public class ExerciseLoaderListener implements Iterator<Exercise> {

	private ArrayList<Exercise> data;
	private int elm;
	
	public ExerciseLoaderListener() {
		this.data = new ArrayList<Exercise>();
		this.elm = 0;
		
		loadData();
	}
	
	private void loadData() {
		IDataStoreExercise store = new DataStoreExercise();
		this.data = store.load(IDataStoreExercise.EXERCISE_LIST);
	}
	
		
	@Override
	public boolean hasNext() {
		return (this.elm < data.size());
	}

	@Override
	public Exercise next() {
		return data.get(this.elm++);
	}
	
}