package app;
import java.util.Scanner;
public class Main {

	public static void main(String[] args) 
	{
		int ACCN,DAM,WAM,Ans1,Ans2;
		String Username; Username = "";
		Bank bank = new Bank("Default Bank");
		boolean TF = true;
		do {
			Scanner S1 = new Scanner(System.in);
			System.out.println("1. Excess My Account");
			System.out.println("2. Create New Account");
			Ans1 = S1.nextInt();
			if(Ans1==1)
			{
				System.out.println("Please Enter The Account Number");
				 ACCN = S1.nextInt();
				//System.out.println("Please Enter The Password");
				//int PS = S1.nextInt();
			}else
			{
				System.out.println("Please Enter The User Name");
				Scanner S2 = new Scanner(System.in);
				Username = S2.nextLine();
				ACCN = bank.createAccount(Username);
				System.out.println("The Account Number is "+ACCN);
				
			}
			do {
				System.out.println("1. Check Info ");
				System.out.println("2. Deposite ");
				System.out.println("3. Withdraw ");
				System.out.println("4. Close Account ");
				System.out.println("5. Transfer ");
				System.out.println("6. Exit");
				Ans2 = S1.nextInt();
				//System.out.println("
				switch(Ans2)
				{
				case 1:
					bank.checkBalance(ACCN);
					
					break;
						
				case 2:
					System.out.println("Please Enter Amount"); 
					DAM = S1.nextInt();
					bank.deposit(ACCN,DAM);
					
					break;

				case 3:
					System.out.println("Please Enter Amount"); 
					WAM = S1.nextInt();
					bank.withdraw(ACCN,WAM);
					
					
					break;

				case 4:
					bank.closeAccount(ACCN);
					
					break;

				case 5:
					
					break;

				case 6:
					
				}
			}while(Ans2!=6);
			System.out.println("Thanks");
			
		}while(TF);
		
		
		//example1();
	}
	
	public static void example1()
	{
		Bank bank = new Bank("Bank of CPHS");
		int ewbankAccountNo = bank.createAccount("Mr. Ewbank");
		int howardAccountNo = bank.createAccount("Mr. Howard");
		
		bank.deposit(ewbankAccountNo, 25);
		bank.deposit(howardAccountNo, 75);

		bank.checkBalance(ewbankAccountNo);
		bank.checkBalance(howardAccountNo);
		
		bank.withdraw(ewbankAccountNo, 10);
		bank.closeAccount(howardAccountNo);
		
		bank.checkBalance(ewbankAccountNo);
		bank.checkBalance(howardAccountNo);
		
	}
}
