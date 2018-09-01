package project;

import java.util.Scanner;
import java.util.Stack;

 class patient extends login {
	String name;
	int age;
	long phone;
	Stack<String> recent=new Stack<String>();
	Appoint_doc app=new Appoint_doc();
	public patient()
	{
		
	}
	public patient(String a,String b,String c,String d,String e)
	{
		this.user=a;
		this.name=b;
		this.age=Integer.parseInt(c);
		this.phone=Long.parseLong(d);
		this.pass=e;
	}
	public void get_patient(String user)
	{
		Scanner scan=new Scanner(System.in);
		this.user=user;
		System.out.println("Name:");
		name=scan.nextLine();
		System.out.println("Age:");
		age=scan.nextInt();
		System.out.println("Phone no.:");
		phone=scan.nextLong();
		scan.nextLine();
		System.out.println("Create password:");
		pass=scan.nextLine();
	
		System.out.println("Created successfully!!");
		
	}

	void add_appoint(int time,Doctor dd)
	{
		app.time=time;
		app.doc_name=dd.doc_name;
		app.address=dd.address;
		app.doc_id=dd.id;
		app.status='y';
		
	}
	void cancel_appoint()
	{
		app.status='n';
	}
	void my_appoint()
	{
		if(app.status=='y')
		{
			System.out.println("Appointment with Dr."+app.doc_name+" at "+app.time);
		}
		else
		{
			System.out.println("No Appointments");
		}
	}
	String get_pass()
	{
		return pass;
	}
	 void printStack()
	{
		if(recent.isEmpty())
		{
			System.out.println("No recent Search");
		}
		else
		{
			System.out.printf("%s\n",recent);
		}
	}

}