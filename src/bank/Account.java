package bank;
public abstract class  Account { //�˻���
	String id; // �˺�
	private double balance; // ���
	private static double total;  //�����˻����ܽ��
	protected Account(Date date, String id) 
	{			
		this.id = id;
		date.show();
		System.out.println("\t#" + id +" created" );
	}
	public final double getBalance() {
		return balance;
	}

	public static double getTotal() {
		return total;
	}
	
	// ��¼һ���ʣ�dateΪ���ڣ�amountΪ��descΪ˵��
	protected void record(Date date, double amount,String desc) {
		amount = Math.floor(amount * 100 + 0.5)/100;     // ����С�������λ
		balance += amount;
		balance = Math.floor(balance * 100 + 0.5)/100;     // ����С�������λ	
		total += amount;
		date.show();
		System.out.println("\t#" + id + "\t" + amount + "\t" + balance+ "\t" + desc);
	}
	
	// ��ʾ�˻���Ϣ
	abstract public void show();
	protected final void error(String msg){
		System.out.println("Error(#" + id + "):" + msg);
	}
	// ��õ�ָ������Ϊֹ�Ĵ������ۻ�ֵ
	abstract public void deposit(Date date, double amount, String desc);
	abstract public void withdraw(Date date, double amount, String desc);
	abstract public void settle(Date date);
	
}

