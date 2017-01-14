package serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


public class Employee {
      
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        ArrayList<Participants> al=new ArrayList<Participants>();
            al.add(new Participants("pradeep", "kumar", 25));
            al.add(new Participants("brajendra", "singh", 45));
            al.add(new Participants("priyanshy", "chaubey", 60));
           System.out.println(al);
           Employee emp=new Employee();
           try{
           emp.serialize1(al,
        		   "src/main/java/serialization/pradeep1.ser");
           System.out.println("serialization done");
           }
           catch(Exception e)
           {
        	   	e.printStackTrace();
           }
           
           
           try {
        	   List<Participants> klist= emp.deserialize("src/main/java/serialization/pradeep1.ser");
        	   System.out.println("After deserialize : "+klist);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
         
   
           
	}
	public void serialize1(List<Participants> plist, String fileName) throws Exception
	{
		ObjectOutputStream obj=new ObjectOutputStream(new FileOutputStream(fileName));
		obj.writeObject(fileName);
		obj.flush();  
		
	}

	@SuppressWarnings("unchecked")
	public List<Participants> deserialize(String filename) throws Exception

 {

		List<Participants> plist=null;
		try{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
			System.out.println("hello "+in);
			plist = (List<Participants>) in.readObject();
			System.out.println("da "+plist);
			in.close();
		}
		catch(Exception e)
		{
             System.out.println(e);			
		}

		return  plist;
	}

}
