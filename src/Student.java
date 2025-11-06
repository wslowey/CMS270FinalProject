
public class Student {
	private String Name;
	public int ID;
	public int year;
	
	public Student(String n, int id, int y) {
		this.Name = n;
		this.ID = id;
		this.year = y;
	}
	
	public String getName(int id) {
		return this.Name;
	}
}
