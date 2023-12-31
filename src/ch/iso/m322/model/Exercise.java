package ch.iso.m322.model;

public class Exercise {

	private int id;
	private String name;

	public Exercise(String name) {
		super();
		this.name = name;
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
	
	 @Override
	    public String toString() {
	        return name; 
	    }

}
