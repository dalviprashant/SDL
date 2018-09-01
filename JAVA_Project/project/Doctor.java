package project;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;



class Doctor extends login
{
	int id;
	String doc_name;
	String speciality;
	String address;
	int age;
	int experience;
	Appointment app=new Appointment();
	LinkedList<Appoint>loa=new LinkedList<Appoint>();
	Scanner s=new Scanner(System.in);
	public Doctor()
	{
		id=0;
		doc_name=null;
		speciality=null;
		address=null;
		age=0;
		experience=0;
		
	}
	public Doctor(String a,String b,String c,String d,String e,String f,String g,String h)
	{
		this.user=a;
		this.id=Integer.parseInt(b);
		this.doc_name=c;
		this.speciality=d;
		this.address=e;
		this.age=Integer.parseInt(f);
		this.experience=Integer.parseInt(g);
		this.pass=h;
	}
	
	public void get_doctor(String user)
	{
		
		this.user=user;
		System.out.println("Enter Details of Doctor");
		System.out.println("Doctor ID:");
		id=s.nextInt();
		s.nextLine();
		System.out.println("Doctor name:");
		doc_name=s.nextLine();
		System.out.println("Speciality:");
		speciality=s.nextLine();
		System.out.println("Address:");
		address=s.nextLine();
		System.out.println("Age:");
		age=s.nextInt();
		System.out.println("Experience:");
		experience=s.nextInt();
		s.nextLine();
		System.out.println("Create password:");
		pass=s.nextLine();
	
		
		
	}
	public void show_doctor()
	{
		System.out.println("Details of Doctor");
		System.out.println("Doctor ID:"+id);
		System.out.println("Doctor Name:"+doc_name);
		System.out.println("Speciality:"+speciality);
		System.out.println("Address:"+address);
		System.out.println("Age:"+age);
		System.out.println("Experience:"+experience);
		System.out.println("----------------------------------------");
	}
	public void display_to_patient()
	{
		System.out.println("Doctor Name:"+doc_name);
		System.out.println("Speciality:"+speciality);
		System.out.println("Appointments available:"+app.get_noa());

	}
	public void update(int ii)
	{
		
		System.out.println("Enter New Details of Doctor");
		id=ii;
		s.nextLine();
		System.out.println("Doctor name:");
		doc_name=s.nextLine();
		System.out.println("Speciality:");
		speciality=s.nextLine();
		System.out.println("Address:");
		address=s.nextLine();
		System.out.println("Age:");
		age=s.nextInt();
		System.out.println("Experience:");
		experience=s.nextInt();
		
	}
	public void add_appoint(int time,patient p)
	{
		Appoint set_appoint=new Appoint();
		set_appoint.time=time;
		set_appoint.p=p;
		loa.add(set_appoint);
	}
	public void cancel_appoint(int time)
	{
		
		app.cancel_appoint(time);
		ListIterator itr=loa.listIterator();
		while(itr.hasNext())
		{
			Appoint a=(Appoint) itr.next();
			if(a.time==time)
			{
				loa.remove(a);
			}
			
		}
	}
	public void show_all_appointments()
	{
		System.out.println("Available appointments");
		ListIterator itr=loa.listIterator();
		while(itr.hasNext())
		{
			Appoint a=(Appoint) itr.next();
			System.out.println(a.time+" 00 hrs"+" - "+a.p.name+" ("+a.p.phone+") ");
			
		}
	}
	String get_user()
	{
		return user;
	}
	String get_pass()
	{
		return pass;
	}
	public int get_ID()
	{
		return id;
	}
	public String get_spec()
	{
		return speciality;
		
	}
	void show_app()
	{
		app.show_appoint();
	}
}