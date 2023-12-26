package ch.iso.m322.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DataStoreExercise implements IDataStoreExercise {

	private static String connectionString = "jdbc:mysql://localhost:3307/JTraining?useSSL=false";

	private static String connectionUser = "root";

	private static String connectionPassword = "test05";

	public ArrayList<Exercise> load(int type) {

		ArrayList<Exercise> data = new ArrayList<Exercise>();

		try {

			// Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);

			Statement stmt = conn.createStatement();

			ResultSet rs = null;

			if (type == IDataStoreExercise.EXERCISE_LIST) {

				rs = stmt.executeQuery("select name " + "from tExercise");
			}

			while (rs.next()) {
				String name = rs.getString("name");

				data.add(new Exercise(name));
			}

			conn.close();
			stmt.close();
			rs.close();

		} catch (Exception e) {
			data.add(new Exercise("data not loaded help"));
			e.printStackTrace();
		}

		return data;
	}

	@Override
	public void save(ArrayList<Exercise> data, int type) {

		try {

			Connection conn = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);
			conn.setAutoCommit(false);

			Statement stmt = conn.createStatement();

			if (type == IDataStoreExercise.EXERCISE_LIST)
				stmt.executeUpdate("DELETE FROM tExercise;");

			for (Exercise exercise : data) {

				stmt.executeUpdate("INSERT INTO tExercise (name) " + "VALUES (" + "'" + exercise.getName() + "');",
						Statement.RETURN_GENERATED_KEYS);

			}

			stmt.close();
			conn.commit();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
