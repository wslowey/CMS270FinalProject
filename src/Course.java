
public class Course {
	public String Name;
	public String Type;
	public int CreditCount;
	public String StartTime;
	public String EndTime;
	public Professor professor;
	public Department department;
	public String Building;
	public int RoomNumber;
	public int CRN;//course refrence number
	
	public Course(String n, String type, int cc, String StarTime,String Endtime,Professor p,Department d,String b, int RN, int crn) {
		this.Name = n;
		this.Type = type;
		this.CreditCount = cc;
		this.StartTime = StarTime;
		this.EndTime = Endtime;
		this.professor = p;
		this.department = d;
		this.Building = b;
		this.RoomNumber = RN;
		this.CRN = crn;
	}

}
