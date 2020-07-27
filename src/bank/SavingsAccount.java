package bank;
public class SavingsAccount extends Account{
	private Accumulator acc;
	private double rate; // ����������

	
	public SavingsAccount(Date date, String id, double rate) 
	{	
		super(date,id);
		this.rate = rate;
		acc = new Accumulator(date,0);

	}

	public final double getRate() {
		return rate;
	}
	// �����ֽ�
	public void deposit(Date date, double amount,String desc) {
		record(date, amount,desc);
		acc.change(date, getBalance());
	}

	// ȡ���ֽ�
	public void withdraw(Date date, double amount,String desc) {
		if (amount > getBalance()){
			error("Error: not enough money");
		}else{
			record(date, -amount,desc);
			acc.change(date, getBalance());
		}
	}

	// ������Ϣ��ÿ��1��1�յ���һ�θú���
	public void settle(Date date) {
		
		double interest = acc.getSum(date) * rate 
				/ date.distance(new Date(date.getYear()-1,1,1)); // ������Ϣ
		if (interest != 0)
			record(date, interest,"interest");
		acc.reset(date,getBalance());
	}
	public void show()
	{
		System.out.println(id + "\tBalance: " + getBalance());  
	}
}

