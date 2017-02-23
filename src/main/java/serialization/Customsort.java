package serialization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.rims.model.Address;

public class Customsort {
	
	      static List<Address> li=new ArrayList<Address>();
               
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		customcomparator();
          
	}
  static void user(){
	  Address ad=new Address();
	  Address ad1=new Address();
	  Address ad2=new Address();
	  ad.setDistt("fatehpur");
	  ad1.setDistt("hariyana");
	  ad2.setDistt("aucknow");
	  ad.setCountry("india");		  
	  ad.setOthers("gdsg");
	  ad.setPincode(212635);
	  ad.setState("utter");
      li.add(ad);
      li.add(ad1);
      li.add(ad2);
   // Collections.sort(li);
      Iterator<Address> itr=li.iterator();
      while(itr.hasNext()){
    	  Address a = itr.next();
    	  System.out.println(a.getDistt());
      }
    	  
  }
  
  static void customcomparator(){
	  Address ad=new Address();
	  Address ad1=new Address();
	  Address ad2=new Address();
	  Address ad3=new Address();
	  ad.setDistt("fatehpur");
	  ad1.setDistt("hariyana");
	  ad2.setDistt("aucknow");
	  ad3.setDistt("bndia");		  
	  ad.setOthers("gdsg");
	  ad.setPincode(21263);
	  ad1.setPincode(2126);
	  ad2.setPincode(212635);
	  ad3.setPincode(4325);
	  ad.setState("utter");
      li.add(ad);
      li.add(ad1);
      li.add(ad2);
      li.add(ad3);
     Collections.sort(li,new Comparator<Address>() {

		public int compare(Address o1, Address o2) {		   
			return o2.compare(o2, o1);
		}
	});
      Iterator<Address> itr=li.iterator();
      while(itr.hasNext()){
    	  Address a = itr.next();
    	  System.out.println(a.getDistt());
    	  
      }
      Iterator<Address> itr1=li.iterator();
      while(itr1.hasNext()){
    	  Address b = itr1.next();
       	  System.out.println(b.getPincode());
      }
    	  
}
}
