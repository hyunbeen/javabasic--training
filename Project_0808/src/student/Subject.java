package student;

public class Subject {
	private int subnum;
	private String subname;
	private Student s;
	private Teacher t;
	
	
	public Subject(int subnum,String subname,Student s,Teacher t)
	{
		this.subnum = subnum;
		this.subname = subname;
		this.s = s;
		this.t = t;
	}
	public void output() {
		System.out.println(s.getStuname()+" �л��� "+t.getTname()+" ������ "+subname+" ������ ��û�մϴ�");
	}
}
