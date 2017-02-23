package serialization;

public class Overloadingwithvarags {
	
	void A(int... x){
		for(int i=0;i<x.length;i++){
		System.out.println(x[i]);
	}}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Overloadingwithvarags   a=new  Overloadingwithvarags();    
		a.A(1,2,3);
	}

}
