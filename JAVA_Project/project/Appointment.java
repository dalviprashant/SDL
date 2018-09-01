package project;

import java.util.BitSet;
import java.util.Scanner;

 class Appointment {
	BitSet avl=new BitSet(12);
	int noa;
	Appointment()
	{
		noa=12;
	}
	int get_noa()
	{
		return noa;
	}
	void show_appoint()
	{
		int i=9;
		System.out.println("Appointments available");
		for(int j=0;j<12;j++,i++)
		{
			if(avl.get(j)!=true)
			{
				System.out.print(i+" hrs \t\t");
			}
			if((i-8)%4==0)
				System.out.println("\n");
		}
	}
	void cancel_appoint(int time)
	{
		avl.clear(time-9);
		noa++;
	}
	void book_appoint(int time)
	{
		Scanner s=new Scanner(System.in);
		
		if(avl.get(time-9)!=true)
		{
			System.out.println("Appointment Booked");
			avl.set(time-9);
			noa--;
			
		}
		else
		{
			System.out.println("Not available");
		}
	}
}