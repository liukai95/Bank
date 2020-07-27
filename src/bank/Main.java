package bank;

import java.util.Scanner;

public class Main {
	public static void main(String[] args)
	{	
		Date date = new Date(2008, 11, 1);	//��ʼ����
		//���������˻�
		SavingsAccount sa1 = new SavingsAccount(date, "S3755217", 0.015);
		SavingsAccount sa2 = new SavingsAccount(date, "02342342", 0.015);
		CreditAccount ca = new CreditAccount(date, "C5392394", 10000, 0.0005, 50);
		Account accounts[] = new Account[] { sa1, sa2, ca };
		final int n = accounts.length;	//�˻�����
		Scanner scanner = new Scanner(System.in);
		System.out.println("(d)deposit (w)withdraw (s)show (c)change day (n)next month (e)exit");
		char cmd;
		do {
			//��ʾ���ں��ܽ��
			date.show();
			System.out.print("\tTotal: " + Account.getTotal() + "\tcommand> ");
			int index, day;
			double amount;
			String desc;
			String masg;
			masg = scanner.nextLine();
			String[] input = masg.split(" ");//��split()����ֱ�ӷָ�
	        cmd = input[0].charAt(0);
			switch (cmd) {
			case 'd':	//�����ֽ�
				index = Integer.parseInt(input[1]);
				amount = Double.parseDouble(input[2]);
				desc = masg.substring(input[1].length()+input[2].length()+3,masg.length());
				accounts[index].deposit(date, amount, desc);
				break;
			case 'w':	//ȡ���ֽ�
				index = Integer.parseInt(input[1]);
				amount = Double.parseDouble(input[2]);
				desc = masg.substring(input[1].length()+input[2].length()+3,masg.length());
				accounts[index].withdraw(date, amount, desc);
				break;
			case 's':	//��ѯ���˻���Ϣ
				for (int i = 0; i < n; i++) {
					System.out.print("[" + i + "] ");
					accounts[i].show();
					System.out.println();
				}
				break;
			case 'c':	//�ı�����
				day = Integer.parseInt(input[1]);
				if (day < date.getDay())
					System.out.print("You cannot specify a previous day");
				else if (day > date.getMaxDay())
						System.out.println("Invalid day");
				else
					date = new Date(date.getYear(), date.getMonth(), day);
				break;
			case 'n':	//�����¸���
				if (date.getMonth() == 12)
					date = new Date(date.getYear() + 1, 1, 1);
				else
					date = new Date(date.getYear(), date.getMonth() + 1, 1);
				for (int i = 0; i < n; i++)
					accounts[i].settle(date);
				break;
			}
		} while (cmd != 'e');
	}
}


