package github2;
/*
 * 请使用继承的思想，设计CommonEmployee类和Manager类，
 */
public class EmployeeTest {
	public static void main(String[] args) {
		
		Employee manager = new Manager("库克",1001,5000,50000);
		
		manager.work();
		
		CommonEmployee commonEmployee = new CommonEmployee();
		commonEmployee.work();
	}
}
