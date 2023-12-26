package ch.iso.m322.model;

import java.util.ArrayList;

/**
 * data store
 * 
 * @author Kevin Marrer
 * @version 23.10.2023
 * 
 */

public interface IDataStoreExercise {


	
	public static final int EXERCISE_LIST = 1;
	
	/**
	 * load list
	 * 
	 * @param type EXERCISE_LIST
	 * @return ArrayList<Exercise>
	 */
	
	public ArrayList<Exercise> load(int type);
	
	/**
	 * 
	 * @param ArrayList<Exercise>
	 * @param type type EXERCISE_LIST
	 */
	
	public void save(ArrayList<Exercise> data, int type); 
	
	
}
