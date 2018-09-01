
package project;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client {
	
	public static void main(String [] args)
	{
		DataInputStream input=null;
		Socket socket=null;
		DataOutputStream out=null;
		DataInputStream in=null;
		try
		{
			input = new DataInputStream(System.in);
			socket=new Socket("10.10.13.230",8000);
			out=new DataOutputStream(socket.getOutputStream());
			in=new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		try
		{
			String ss="",rec="";
			while(!ss.equals("over"))
			{
				ss=input.readLine();
				out.writeUTF(ss);
				rec=in.readUTF();
				System.out.println("Server:"+rec);
			}
		}catch(Exception ee)
		{
			System.out.println(ee.getMessage());
		}
	}

}
