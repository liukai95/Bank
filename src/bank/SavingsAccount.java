package bank;
public class SavingsAccount extends Account{
	private Accumulator acc;
	private double rate; // 存款的年利率

	
	public SavingsAccount(Date date, String id, double rate) 
	{	
		super(date,id);
		this.rate = rate;
		acc = new Accumulator(date,0);

	}

	public final double getRate() {
		return rate;
	}
	// 存入现金
	public void deposit(Date date, double amount,String desc) {
		record(date, amount,desc);
		acc.change(date, getBalance());
	}

	// 取出现金
	public void withdraw(Date date, double amount,String desc) {
		if (amount > getBalance()){
			error("Error: not enough money");
		}else{
			record(date, -amount,desc);
			acc.change(date, getBalance());
		}
	}

	// 结算利息，每年1月1日调用一次该函数
	public void settle(Date date) {
		
		double interest = acc.getSum(date) * rate 
				/ date.distance(new Date(date.getYear()-1,1,1)); // 计算年息
		if (interest != 0)
			record(date, interest,"interest");
		acc.reset(date,getBalance());
	}
	public void show()
	{
		System.out.println(id + "\tBalance: " + getBalance());  
	}
}

