package ch.iso.m322.model;


public class Tracker {

	private int id;
	private String name;
	private Exercise exercise;
	private String date;
	private int weight;
	private int RPE;
	
	public Tracker(String name, Exercise exercise, String date, int weight, int rPE) {
		super();
		this.name = name;
		this.exercise = exercise;
		this.date = date;
		this.weight = weight;
		this.RPE = rPE;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Exercise getExercise() {
		return exercise;
	}

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getRPE() {
		return RPE;
	}

	public void setRPE(int rPE) {
		RPE = rPE;
	}

	public String[] toStringArray() {
		String[] s = new String[5];
		s[0] = this.getName();
		s[1] = this.getExercise().getName();
		s[2] = this.getDate();
		s[3] = Integer.toString(this.getWeight());
		s[4] = Integer.toString(this.getRPE());
		return s;
	}
	
	
	
	
}
