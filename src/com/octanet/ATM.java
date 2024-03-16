package com.octanet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ATM {
	private String userId;
	private String userPin;
	private double balance;
	private List<String>transactionHistory;
	
	public ATM(String userId,String userPin,double balance) {
		this.userId=userId;
		this.userPin=userPin;
		this.balance=balance;
		this.transactionHistory=new ArrayList<>();
	}
	
	public void run() {
		Scanner scanner=new Scanner(System.in);
		int choice;
		do {
			System.out.println("\nATM Menu:");
			System.out.println("1.Transaction History");
			System.out.println("2.Withdraw");
			System.out.println("3.Deposit");
			System.out.println("4.Transfer");
			System.out.println("5.Quit");
			System.out.println("Enter your choice:");
			choice=scanner.nextInt();
			switch(choice) {
			case 1:
				displayTransactionHistory();
				break;
			case 2:
				withdraw(scanner);
				break;
			case 3:
				deposit(scanner);
				break;
			case 4:
				transfer(scanner);
				break;
			case 5:
				System.out.println("Thank you for using the ATM.Goodbye!");
				break;
				default:
					System.out.println("Invalid choice.Please try again.");
			}
		}
		while(choice!=5);
	}
	
	private void displayTransactionHistory() {
		System.out.println("Transaction History:");
		if(transactionHistory.isEmpty()) {
			System.out.println("[No transactions yet]");
		}
		else {
			for(String transaction:transactionHistory) {
				System.out.println(transaction);
			}
		}
	}
	
	private void withdraw(Scanner scanner) {
		System.out.print("Enter amount to withdraw:");
				double amount=scanner.nextDouble();
				if(amount<=balance) {
					balance-=amount;
					transactionHistory.add("Withdrawal:$"+amount);
					System.out.println("Successfully withdrew $"+amount);
				}
				else {
					System.out.println("Insufficient funds.Withdrawal failed.");
				}
	}
	
	private void deposit(Scanner scanner) {
		System.out.println("Enter amount to deposit:");
		double amount=scanner.nextDouble();
		balance+=amount;
		transactionHistory.add("Deposit:$"+amount);
		System.out.println("Successfully deposited $"+amount);
	}
	
	private void transfer(Scanner scanner) {
		System.out.println("Enter recipient's userId:");
		String recipientId=scanner.next();
		System.out.print("Enter amount to transfer:");
		double amount=scanner.nextDouble();
		if(amount<=balance) {
			balance-=amount;
			transactionHistory.add("Transfer to"+recipientId+":$"+amount);
			System.out.println("Successfully transferred $"+amount+" to "+recipientId);
		}
		else {
			System.out.println("Insufficient funds.Transfer failed.");
		}
	}

	public static void main(String[] args) {
		ATM atm=new ATM("user123","1234",1000.0);
		atm.run();
	}
}