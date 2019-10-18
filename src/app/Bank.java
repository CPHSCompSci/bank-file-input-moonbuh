package app;

import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class Bank {
	// Variable for logging/not logging
	private static final boolean LOG = true;

	private static int accountCounter = 1;
	private String name;
	private ArrayList<Account> accounts;
	
	public Bank() {
		this("Bank Name");
	}

	public Bank(String name) {
		this.name = name;
		accounts = new ArrayList<>();
		log("Bank Created");
	}

	public int createAccount(String name) {
		Account newAccount = new Account(name);
		accounts.add(newAccount);

		log("Added account " + newAccount);
		return newAccount.accountNumber;
	}

	public boolean closeAccount(int accountNumber) {
		Account account = findAccount(accountNumber);
		if (account == null) {
			log("Could not close account " + accountNumber);
			return false;
		}
		saveAccounts(accountNumber);
		accounts.remove(account);
		log("Successfully closed " + account);
		return true;
	}
	public boolean closeAccountN(int accountNumber) {
		Account account = findAccount(accountNumber);
		if (account == null) {
			log("Could not close account " + accountNumber);
			return false;
		}
		saveAccounts(accountNumber);
		accounts.remove(account);
		return true;
	}

	public boolean deposit(int accountNumber, int amount) {
		Account account = findAccount(accountNumber);
		if (account == null) {
			log("Could not deposit to account " + accountNumber);
			return false;
		}
		account.balance += amount;
		log("Successfully deposited $" + amount + " to " + account);
		return true;
	}

	public boolean withdraw(int accountNumber, int amount) {
		Account account = findAccount(accountNumber);
		if (account == null) {
			log("Could not withdraw from account " + accountNumber);
			return false;
		}
		if (account.balance < amount) {
			log("Insufficient funds in " + account);
			return false;
		}
		account.balance -= amount;
		log("Successfully withdrew $" + amount + " from " + account);
		return true;
	}

	public int checkBalance(int accountNumber) {
		Account account = findAccount(accountNumber);
		if (account == null) {
			log("Could not check balance of account " + accountNumber);
			return -1;
		}
		log("Successfully checked balance of account " + account);
		return account.balance;
	}

	public void saveAccounts(int AccountNumber) {
		// TODO
		Account account = findAccount(AccountNumber);
		String RFI;boolean TF = true;
		try
		{
			FileWriter fw = new FileWriter("Info.txt");
			BufferedReader br = new BufferedReader(new FileReader("Info.txt"));
			while ((RFI = br.readLine()) != null) 
	        {
				fw.append(RFI);
	        }
			fw.append(account.toString()+"\n");
			fw.close();
			br.close();
			
		}catch(IOException e) {
			System.out.println("Unable to find file");
			e.printStackTrace();
		}
	}
	public String getAccountIF(int AN)
	{
		String AIF,AIF2; int AIFAN;
		 try(BufferedReader br = new BufferedReader(new FileReader("Info.txt")))
		    {
			 while ((AIF = br.readLine()) != null) 
		        {
		            AIFAN = Integer.parseInt(AIF.substring(1, 5));
		            if(AIFAN == AN)
		            {

			            return AIF;
		            }
		        }
		    }
		    catch (IOException e) 
		    {
				System.out.println("Unable to find file");
		        e.printStackTrace();
		    }
		 return null;
		
	}

	public boolean loadAccount(int AN) {
		String FS = getAccountIF(AN);
		if(FS == null)
		{
			return false;
		}else
		{

			FS = FS.substring(7);
			
			int index1 = FS.indexOf("::$");
			
			String name = FS.substring(0,index1);
			int balance = Integer.parseInt(FS.substring(index1+3, FS.length()-1));
			
			Account AFF = new Account(AN, name, balance);
			accounts.add(AFF);
			
			return true;
		}
	}
	public boolean tranfer(int WA, int DA, int amount)
	{
		boolean TF = loadAccount(DA);
		if(TF)
		{
			withdraw(WA, amount);
			deposit(DA, amount);
			closeAccountN(DA);
		}
		
		return true;
	}

	private Account findAccount(int accountNumber) {
		for (int i = accounts.size() - 1; i >= 0; i--) {
			if (accounts.get(i).accountNumber == accountNumber)
				return accounts.get(i);
		}
		return null;
	}

	private void log(String message) {
		if (LOG)
			System.out.println(name + " ::: " + message + ".");
	}

	/**
	 * Private Inner Class Account
	 * Deals with Account information
	 */
	private class Account {
		int accountNumber;
		String name; 
		int balance;
		private Account(String name) {
			this.name = name;
			balance = 0; 
			int C1 = 0;
			boolean TF; TF = true;
			while(TF)
			{
				C1++;
				String FS = getAccountIF(C1);
				if(FS == null)
				{
					TF = false;
				}
			}
			accountNumber = C1;
		}
		private Account(int AN, String name, int balance)
		{
			this.name = name;
			accountNumber = AN;
			this.balance = balance;
		}

		public String toString() {
			String ANS = String.format("%04d", accountNumber);
			return "{" + ANS + "::" + name + "::$" + balance + "}";
		}

	}
}
