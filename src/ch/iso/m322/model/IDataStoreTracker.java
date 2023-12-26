package ch.iso.m322.model;

import java.util.ArrayList;

public interface IDataStoreTracker {

	/**
	 * data store
	 * 
	 * @author Kevin Marrer
	 * @version 23.10.2023
	 * 
	 */

		public static final int TRACK_LIST = 1;

		/**
		 * load list
		 * 
		 * @param type TRACK_LIST
		 * @return ArrayList<Tracker>
		 */

		public ArrayList<Tracker> load(int type);

		/**
		 * 
		 * @param ArrayList<Tracker>
		 * @param type               type TRACK_LIST
		 */

		public void save(ArrayList<Tracker> data, int type);
	
}



