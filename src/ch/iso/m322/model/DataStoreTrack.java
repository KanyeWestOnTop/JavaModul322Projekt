package ch.iso.m322.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DataStoreTrack implements IDataStoreTracker {

	private static String connectionString = "jdbc:mysql://localhost:3307/JTraining?useSSL=false";

	private static String connectionUser = "root";

	private static String connectionPassword = "test05";

	public ArrayList<Tracker> load(int type) {

		ArrayList<Tracker> data = new ArrayList<Tracker>();

		try {

			Connection conn = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);

			Statement stmt = conn.createStatement();

			ResultSet rs = null;

			if (type == IDataStoreTracker.TRACK_LIST) {

				rs = stmt.executeQuery(
						"SELECT tTrack.name AS trackName, tExercise.name AS exerciseName, tTrack.date, tTrack.weight, tTrack.RPE "
								+ "FROM tTrack " + "JOIN tExercise ON tTrack.exercise_id = tExercise.id");

			}

			while (rs.next()) {
			    String name = rs.getString("trackName");
			    String exerciseName = rs.getString("exerciseName");
			    Date date = rs.getDate("date");
			    int weight = rs.getInt("weight");
			    int RPE = rs.getInt("RPE");

			    Exercise exercise = findExerciseByName(exerciseName);

			    data.add(new Tracker(name, exercise, date, weight, RPE));
			}


			conn.close();
			stmt.close();
			rs.close();

		} catch (Exception e) {
			data.add(new Tracker("data not loaded", null, null, 0, 0));
			e.printStackTrace();
		}

		return data;
	}

	@Override
	public void save(ArrayList<Tracker> data, int type) {
		try {

			Connection conn = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);
			conn.setAutoCommit(false);

			Statement stmt = conn.createStatement();

			if (type == IDataStoreTracker.TRACK_LIST)
				stmt.executeUpdate("delete from tTrack;");

			for (Tracker tracker : data) {
			    String exerciseName = tracker.getExercise().getName();

			    int exerciseId = findExerciseIdByName(exerciseName);
			    
			    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			    stmt.executeUpdate(
			        "INSERT INTO tTrack (name, exercise_id, date, weight, RPE) VALUES ('"
			            + tracker.getName() + "', '" + exerciseId + "', '" + dateFormat.format(tracker.getDate()) + "', "
			            + tracker.getWeight() + ", " + tracker.getRPE() + ");",
			        Statement.RETURN_GENERATED_KEYS);
			}


			stmt.close();
			conn.commit();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private int findExerciseIdByName(String exerciseName) {
	    int exerciseId = -1; 

	    try {
	        Connection conn = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);
	        Statement stmt = conn.createStatement();

	        ResultSet rs = stmt.executeQuery("SELECT id FROM tExercise WHERE name = '" + exerciseName + "'");

	        if (rs.next()) {
	            exerciseId = rs.getInt("id");
	        }

	        conn.close();
	        stmt.close();
	        rs.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return exerciseId;
	}

	
	private Exercise findExerciseByName(String exerciseName) {
	    Exercise exercise = null;

	    try {
	        Connection conn = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);
	        Statement stmt = conn.createStatement();

	        ResultSet rs = stmt.executeQuery("SELECT name FROM tExercise WHERE name = '" + exerciseName + "'");

	        if (rs.next()) {
	            String name = rs.getString("name");
	            exercise = new Exercise(name);
	        }

	        conn.close();
	        stmt.close();
	        rs.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return exercise;
	}


}
