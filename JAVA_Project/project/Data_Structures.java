package project;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.mysql.jdbc.Statement;


public class Data_Structures {
	static Scanner scan=new Scanner(System.in);
	static Map<String,Doctor> list=new HashMap<String,Doctor>();

	static Map<String,patient>list_p=new HashMap<String,patient>();
	
	
	static Connection conn;
	static java.sql.Statement stmt;
	
	public static void main(String []args) throws ClassNotFoundException, SQLException 
	{
		   Class.forName("com.mysql.jdbc.Driver");
		   
		    conn = DriverManager.getConnection("jdbc:mysql://localhost/doc","root","");
		
		   stmt=null;
		   char answer,ans;
		String user;
		int choice;
		do
		{
		
			
		System.out.println("1.Patient\n2.Doctor\n");
		System.out.println("Choice:");
		choice=scan.nextInt();
		
		
		switch(choice)
		{
		case 1:
			System.out.println("1.Login\n2.New User\n");
			int ch_p=scan.nextInt();
			switch(ch_p)
			{
			case 1:
				System.out.println("UserName:");
				 user=scan.next();
				 char a='y';
					scan.nextLine();
					System.out.println("PassWord:");
					String pass=scan.nextLine();
					String sql="SELECT * FROM login where user like '"+user+"'";
					stmt=conn.createStatement();
					ResultSet rs=stmt.executeQuery(sql);
					if(rs.next())
					{
						if(pass.equals(rs.getString("pass")))
						{
							patient p=new patient(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
					
							do	
							{
								System.out.println("1.Doctor List\n2.Search\n3.Recent Search\n4.Book Appointment\n5.Cancel Appointment\n6.Exit");
								choice=scan.nextInt();
								switch(choice)
								{
							
								case 1:
									display_Doc();
									break;
								case 2:
									System.out.println("1.By ID\n2.By Speciality\n");
									int cc=scan.nextInt();
									switch(cc)
									{
									case 1:
										search_Doc_ID(p);
										break;
									case 2:
										search_Doc(p);
									}
									
									break;
								case 3:
									p.printStack();
									break;
								case 4:
									book(p);
									break;
								case 5:
									cancel(p);
								case 6:
									a='n';
								}
								/*System.out.println("Continue?");
								answer=scan.next().charAt(0);*/
							}while(a=='y');
						}
						else
						{
							System.out.println("Password Incorect");
						}
					}
					else 
					{
						System.out.println("Username Does not exist");
					}
			
				//else
				{
					//System.out.println("Username Does not exist");
				}
				
				break;
				
			case 2:
				while(true)
				{
					scan.nextLine();
					System.out.println("UserName:");
					 user=scan.nextLine();
					 
					/*if(list_p.containsKey(user))
					{
						System.out.println("UserName Already exists");
					}*/
					//else
					{
						patient pp=new patient();
						pp.get_patient(user);
						
						sql="INSERT INTO login VALUES('"+user+"', '"+pp.name+"',  "+pp.age+"," +pp.phone+",'"+pp.pass+"')";
						
						stmt=conn.createStatement();
						stmt.executeUpdate(sql);
						//list_p.put(user, pp);
						break;
					}
					
				}
				break;
					
				
			}
			break;
			
		case 2:
			System.out.println("1.Login\n2.New User\n");
			int ch_d=scan.nextInt();
			switch(ch_d)
			{
			case 1:
				System.out.println("UserName:");
				 user=scan.next();
				
					scan.nextLine();
					System.out.println("PassWord:");
					String pass=scan.nextLine();
					String sql="SELECT * FROM login_d where user like '"+user+"'";
					stmt=conn.createStatement();
					ResultSet rs=stmt.executeQuery(sql);
					int flag=0;
					if(rs.next())
					{
						flag=1;
						if(pass.equals(rs.getString("pass")))
						{
							Doctor dd=new Doctor(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8));
							System.out.println("1.Update your Profile\n2.List all Appointments");
							System.out.println("Enter choice:");
							choice=scan.nextInt();
							switch(choice)
							{
							case 1:
								update_Doc(dd);
								break;
							case 2:
								dd.show_all_appointments();
							}
						}
						else
							System.out.println("PassWord Incorrect");
					}
			if(flag==0)
				{
					System.out.println("UserName does not exist");
				}
				break;
			case 2:
				while(true)
				{
					scan.nextLine();
					System.out.println("UserName:");
					 user=scan.nextLine();
					/*if(list.containsKey(user))
					{
						System.out.println("UserName Already exists");
					}*/
					
						Doctor dd=new Doctor();
						dd.get_doctor(user);
						try
						{
						 sql="Insert into login_d values('"+user+"','"+dd.id+"','"+dd.doc_name+"','"+dd.speciality+"','"+dd.address+"',"+dd.age+","+dd.experience+",'"+dd.pass+"')";
						stmt=conn.createStatement();
						stmt.executeUpdate(sql);
						for(int i=10;i<=18;i++)
						{
							if(i==12)
							{
								String sql1="INSERT INTO list_appoint values('"+dd.id+"',"+"'12:00:00')";
							}
							String sql1="INSERT INTO list_appoint values('"+dd.id+"',"+"'"+(i%12)+":00:00')";
							stmt.executeUpdate(sql1);
						}
						}catch(Exception e)
						{
							System.out.println(e.getMessage());
						//list.put(user,dd );
						break;
					}
					
				}
			}
			break;
			
		}
		System.out.println("Back to Main page?");
		ans=scan.next().charAt(0);
		}while(ans=='y');
		scan.close();
	}

	
	
	
	public static void display_Doc() throws SQLException
	{
		/*System.out.println("Doctor List");
		Set<Map.Entry<String,Doctor>>entries=list.entrySet();
		for(Map.Entry<String, Doctor> ee:entries)
		{
			Doctor dd=ee.getValue();
			dd.show_doctor();
		}*/
		stmt=conn.createStatement();
		String sql="Select * from login_d";
		ResultSet rs=stmt.executeQuery(sql);
		while(rs.next())
		{
			//System.out.println(rs.getString(1)+"  |  "+rs.getString(2)+"  |  "+rs.getString(3)+"  |  "+rs.getString(4)+"  |  "+rs.getString(5)+"  |  "+rs.getString(6)+"  |  "+rs.getString(7)+"  |  "+rs.getString(8));
			System.out.println("\nID:"+rs.getString(2)+"\nDoctor_name:"+rs.getString(3)+"\nSpecialization:"+rs.getString(4)+"\nExperience:"+rs.getInt(7));
		}
		
		
	}
	
	
	
	
	
	public static void search_Doc(patient pp) throws SQLException
	{
		System.out.println("Speciality:");
		String sp=scan.next();
		pp.recent.push(sp);
		int flag=0;
	
		/*Set<Map.Entry<String,Doctor>> entries1=list.entrySet();
		Doctor dd=new Doctor();
		for(Map.Entry<String, Doctor> ee:entries1)
		{
			 dd=ee.getValue();
			if(sp.equals(dd.get_spec()))
			{
				dd.display_to_patient();
				flag=1;
				
			}
		}*/
		String sql="SELECT * FROM login_d where spec='"+sp+"';";
		ResultSet rs=stmt.executeQuery(sql);
		while(rs.next())
		{
			flag=1;
			//System.out.println(rs.getString(1)+"  |  "+rs.getString(2)+"  |  "+rs.getString(3)+"  |  "+rs.getString(4)+"  |  "+rs.getString(5)+"  |  "+rs.getString(6)+"  |  "+rs.getString(7)+"  |  "+rs.getString(8));
			System.out.println("\n\n\nID:"+rs.getString(2)+"\nDoctor_name:"+rs.getString(3)+"\nSpecialization:"+rs.getString(4)+"\nExperience:"+rs.getInt(7));

		}
		if(flag==0)
		{
			System.out.println("No Doctors of the given Speciality");
		}
		else
		{
			System.out.println("Check For Appointment?");
			char aa=scan.next().charAt(0);
			if(aa=='y')
			{
				System.out.println("Enter ID:");
				int id=scan.nextInt();
				String sql1="SELECT t FROM list_appoint where ID="+id;
				ResultSet rs1=stmt.executeQuery(sql1);
				int i=0;
				while(rs1.next())
				{	
					if(i%3==0)
					{
						System.out.println("\n");
					}
					System.out.print(rs1.getTime("t")+"\t");
					i++;
				}
				//dd.show_app();
				try
				{
				System.out.println("\n\nEnter time(24hrs Format):");
				int time=scan.nextInt();
				sql1="INSERT INTO Appoint_book VALUES('"+id+"','"+time+":00:00','"+pp.name+"',"+pp.phone+")";
				/*dd.app.book_appoint(time);
				dd.add_appoint(time,pp);*/
				stmt.executeUpdate(sql1);
				System.out.println("Appointment booked");
				sql1="DELETE FROM list_appoint where ID="+id+" AND t='"+time+":00:00'";
				stmt.executeUpdate(sql1);
				
				}catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
				
			}
		}
	}
	
	
	
	public static void search_Doc_ID(patient pp) throws SQLException
	{
		System.out.println("Doctor_ID:");
		int id=scan.nextInt();
		int flag=0;
		/*Set<Map.Entry<String,Doctor>> entries1=list.entrySet();
		Doctor dd=new Doctor();
		int flag=0;
		for(Map.Entry<String, Doctor> ee:entries1)
		{
			 dd=ee.getValue();
			if(id==dd.get_ID())
			{
				dd.display_to_patient();
				flag=1;
				
			}
		}*/
		String sql="SELECT * FROM login_d where ID="+id;
		ResultSet rs=stmt.executeQuery(sql);
		while(rs.next())
		{
			flag=1;
			System.out.println(rs.getString(1)+"  |  "+rs.getString(2)+"  |  "+rs.getString(3)+"  |  "+rs.getString(4)+"  |  "+rs.getString(5)+"  |  "+rs.getString(6)+"  |  "+rs.getString(7)+"  |  "+rs.getString(8));
		}
	
		if(flag==0)
		{
			System.out.println("No Doctors of the given ID");
		}
	
		else
		{
			System.out.println("Check For Appointment?");
			char aa=scan.next().charAt(0);
			if(aa=='y')
			{
				
				String sql1="SELECT t FROM list_appoint where ID="+id;
				ResultSet rs1=stmt.executeQuery(sql1);
				int i=0;
				while(rs1.next())
				{	
					if(i%3==0)
					{
						System.out.println("\n");
					}
					System.out.print(rs1.getTime("t")+"\t");
					i++;
				}
				//dd.show_app();
				try
				{
				System.out.println("\n\nEnter time(24hrs Format):");
				int time=scan.nextInt();
				sql1="INSERT INTO Appoint_book VALUES('"+id+"','"+time+":00:00','"+pp.name+"',"+pp.phone+")";
				/*dd.app.book_appoint(time);
				dd.add_appoint(time,pp);*/
				stmt.executeUpdate(sql1);
				System.out.println("Appointment booked");
				sql1="DELETE FROM list_appoint where ID="+id+" AND t='"+time+":00:00'";
				stmt.executeUpdate(sql1);

				}catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
				
			}
		}
		/*else
		{
			System.out.println("Check For Appointment?");
			char aa=scan.next().charAt(0);
			if(aa=='y')
			{
				dd.show_app();
				System.out.println("Enter time(24hrs Format):");
				int time=scan.nextInt();
				dd.app.book_appoint(time);
				dd.add_appoint(time,pp);
				pp.add_appoint(time,dd);
			}
		}*/
	}
	public static void book(patient pp) throws SQLException
	{
		System.out.println("1.Search Doctor by ID\n2.Search Doctors by speciality");
		int ch=scan.nextInt();
		switch(ch)
		{
		case 1:
			search_Doc_ID(pp);
			break;
		case 2:
			search_Doc(pp);
			break;
		}
	}
	
	
	public static void update_Doc(Doctor d)
	{
		
			d.show_doctor();
			d.update(d.get_ID());
			list.remove(d.get_user());
			list.put(d.get_user(), d);
	
	}
	public static void cancel(patient p) throws SQLException
	{
		/*if(p.app.status=='n')
		{
			System.out.println("You Don't have any appointment to cancel");
			return ;
		}*/
		//else
		//{
		String sql="SELECT * FROM Appoint_book where p_phone="+p.phone;
		ResultSet rs=stmt.executeQuery(sql);
		Time time = null;
		String id="",sql1;
		int i=0;
	
		while(rs.next())
		{
			
			i++;
			time=rs.getTime(2);
			id=rs.getString(1);
			System.out.println("Appointment At "+time+"(Doc_ID-"+id+")");
		}
			//p.my_appoint();
		if(i>=1)
		{
			
			System.out.println("Cancel this appointment?(y/n)");
			char ans=scan.next().charAt(0);
			//p.cancel_appoint();
			//int id=p.app.doc_id;
			//Set<Map.Entry<String,Doctor>> entries1=list.entrySet();
			//Doctor dd=new Doctor();
			//int flag=0;
			if(ans=='y')
			{
				sql1="DELETE FROM Appoint_book where p_phone="+p.phone +" AND "+"doc_id='"+id+"'";
				stmt.executeUpdate(sql1);
				sql1="INSERT INTO list_appoint values('"+id+"',"+"'"+time+"')";
				stmt.executeUpdate(sql1);
				System.out.println("Appointment Cancelled");
			}
			/*for(Map.Entry<String, Doctor> ee:entries1)
			{
				 dd=ee.getValue();
				if(id==dd.get_ID())
				{
					dd.cancel_appoint(p.app.time);
					flag=1;
					
				}
			}*/
			
		}
	}
}
