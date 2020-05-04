package github;

public class SingletonTest {
	public static void main(String[] args) {
		
		Bank bank1 = Bank.instance;
		Bank bank2 = Bank.instance;
		
		Bank bank3 = null;
		
		System.out.println(bank1 == bank2); 
	}
}
class Bank{
	
	private Bank(){
		
	}
	
	public static Bank instance = new Bank();
}