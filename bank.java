import java.io.*;
import java.util.*;
import java.lang.*;

class work
{
	long ac_no;
	String name, pw, n;
	long bal;
	Scanner sc = new Scanner(System.in);
	FileReader fr;
	FileWriter fw;
	
	

	void create() throws IOException
	{
		String last = "";
		try
		{	
			//Read to get the last ac_no and add 1 to it(Initailly ac_no is 100)
			FileReader fr = new FileReader("doc1.txt");
			Scanner r = new Scanner(fr);

			while(r.hasNextLine())
			{
				last = r.nextLine();
				Scanner a = new Scanner(last);
				ac_no = Long.valueOf(a.next());
				if(ac_no < 100)
				{
					break;
				}
				else
				{
					ac_no = ac_no + 1;
				}
			}
		}
		catch(Exception fe)
		{
			System.out.println("File not found");
		}
		
		System.out.print("Enter name: ");
		name = sc.next();
		System.out.print("Enter amount to be deposited: ");
		bal = sc.nextLong();
		System.out.println("Please note your account number: "+ac_no);
		System.out.print("Set your password now\nEnter a password: ");
		pw = sc.next();
		//append the entered name and amount with ac_no and password to the file
		
		last = String.format("%-20d %-20s %-20s %d", ac_no, pw, name, bal);
		fw = new FileWriter("doc1.txt", true);
		fw.write(last);
		fw.write(System.lineSeparator());
		fw.close(); 
	}

	int login() throws IOException
	{
		try
		{
			fr = new FileReader("doc1.txt");
			Scanner r = new Scanner(fr);
			String ac, pass;
			System.out.print("Enter your Ac.no: ");
			n = sc.next();
			System.out.print("Enter your password: ");
			String p = sc.next();
			while(r.hasNextLine())
			{
				ac = r.next();
				if(ac.equals(n))
				{
					pass = r.next();
					if(pass.equals(p))
					{
						System.out.println("You have succesfully logged in");
						return (1);
					}
					else
					{
						System.out.println("Incorrect password!");
						return (0);
					}
				}
			}
			System.out.println("Account not found! Create one to login.");
			return (0);
		}
		catch(Exception fe)
		{
			System.out.println("Account not found! Create one to login.");
			return (0);
		}
	}

	void display() throws IOException
	{
		try
		{
			
			FileReader fr = new FileReader("doc1.txt");
			Scanner r = new Scanner(fr);
			String ac;
			while(r.hasNextLine())
			{
				ac = r.next();
				if(ac.equals(n))
				{
					String re = r.next();
					System.out.println("Ac.No: "+ac+"  Name: "+r.next()+"  Balance: "+r.next());
					return;
				}
			}
		}
		catch(Exception fe)
		{
			System.out.println("File not found");
		}
	}


	void deposit() throws IOException
	{
		try
		{
			
			FileReader fr = new FileReader("doc1.txt");
			StringBuffer inputBuffer = new StringBuffer();
			Scanner r = new Scanner(fr);
			String ac, line;
			Long add;
			while(r.hasNextLine())
			{
				line = r.nextLine();	
				Scanner b = new Scanner(line);	
				ac = b.next();
				if(ac.equals(n))
				{
					String re1 = b.next();
					name = b.next();
					bal = Long.valueOf(b.next());
					System.out.print(name+", your current balance is "+bal+"\nPlease enter the amount you want to add to account: ");
					add = sc.nextLong();
					bal = bal + add;
					line = String.format("%-20s %-20s %-20s %d", ac, re1, name, bal); 
				}
				inputBuffer.append(line);
            			inputBuffer.append(System.lineSeparator());
			}
			String inputStr = inputBuffer.toString();
			fr.close();
			FileWriter fw = new FileWriter("doc1.txt", false);
			fw.write(inputStr);
			fw.close();
		}
		catch(Exception fe)
		{
			System.out.println("File not found");
		}
	}

	void withdraw() throws IOException
	{
		try
		{
			
			FileReader fr = new FileReader("doc1.txt");
			StringBuffer inputBuffer = new StringBuffer();
			Scanner r = new Scanner(fr);
			String ac, line;
			Long rem, update;
			while(r.hasNextLine())
			{
				line = r.nextLine();	
				Scanner c = new Scanner(line);	
				ac = c.next();
				if(ac.equals(n))
				{
					String re2 = c.next();
					name = c.next();
					bal = Long.valueOf(c.next());
					System.out.print(name+", your current balance is "+bal+"\nPlease enter the amount you want to withdraw from account: ");
					rem = sc.nextLong();
					update = bal - rem;
					if(update < 0)
					{
						System.out.println("Sorry you don't have enough balance to remove that amount");
					}
					else
					{
						line = String.format("%-20s %-20s %-20s %d", ac, re2, name, update); 
					}
				}
				inputBuffer.append(line);
            			inputBuffer.append(System.lineSeparator());
			}
			String inputStr = inputBuffer.toString();
			fr.close();
			FileWriter fw = new FileWriter("doc1.txt", false);
			fw.write(inputStr);
			fw.close();
		}
		catch(Exception fe)
		{
			System.out.println("File not found");
		}
	}			
	void transaction()
	{
		try
		{
			
			FileReader fr = new FileReader("doc1.txt");
			StringBuffer inputBuffer = new StringBuffer();
			Scanner r = new Scanner(fr);
			String n1, re3, line, ac;
			Long amt, update;
			System.out.print("Enter ac.no of the receiver: ");
			n1 = sc.next();
			System.out.print("Enter amount to be sent: ");
			amt = sc.nextLong();
			while(r.hasNextLine())
			{
				line = r.nextLine();	
				Scanner b = new Scanner(line);	
				ac = b.next();
				if(ac.equals(n1))
				{
					re3 = b.next();
					name = b.next();
					bal = Long.valueOf(b.next());
					bal = bal + amt;
					line = String.format("%-20s %-20s %-20s %d", ac, re3, name, bal); 
				}
				else if(ac.equals(n))
				{
					re3 = b.next();
					name = b.next();
					bal = Long.valueOf(b.next());
					update = bal - amt;
					if(update >= 0)
					{
						line = String.format("%-20s %-20s %-20s %d", ac, re3, name, update);
					}
					else
					{
						System.out.println("Sorry, you don't have enough balance to complete the transaction");
					}
				}
				inputBuffer.append(line);
            			inputBuffer.append(System.lineSeparator());
			}
			String inputStr = inputBuffer.toString();
			fr.close();
			FileWriter fw = new FileWriter("doc1.txt", false);
			fw.write(inputStr);
			fw.close();
		}
		catch(Exception e)
		{
			System.out.println("File not found");
		}
	}
					
}
	 
class bank
{
	public static void main(String args[]) throws IOException
	{
		work w = new work();
		Scanner c = new Scanner(System.in);
		int ch, ch1, val;
		do
		{
			System.out.print("1. CREATE AC\n2. LOGIN TO YOUR AC\n3. EXIT\nEnter your choice: "); 
			ch = c.nextInt();
			switch(ch)
			{
				case 1: //Ask for name and amt, give an ac.no, store these in the file
					w.create();
					break;
				case 2: val = w.login();
					if(val == 1)
					{
					do
					{
					System.out.print("1. VIEW AC\n2. DEPOSIT\n3. WITHDRAW\n4. MAKE A TRANSACTION\n5. LOGOUT\nEnter your choice: ");
					ch1 = c.nextInt();
					switch(ch1)
					{
						case 1:	//Ask for ac.no, display ac.no, name and amt
							w.display();
							break;
						case 2: //Ask for ac.no, ask amt to be added, add and store
							w.deposit();
							break;
						case 3: //Ask for ac.no, ask amt to be withdrawn, withdraw and store
							w.withdraw();
							break;
						case 4: //Ask for ac.no from and to, ask for amt
							w.transaction();
							break;
						case 5: //Logout
							System.out.println("You've successfully logged out");
							break;
					}
					}while(ch1 != 5);
					}
					break;
				case 3: //Exit
					break;
				default: System.out.println("ENTER A VALID CHOICE");
			}
		}
		while(ch != 3);
	}
}