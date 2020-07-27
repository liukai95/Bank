package bank;

public class CreditAccount extends Account { //信用账户类
	private Accumulator acc;	//辅助计算利息的累加器
	private double credit;		//信用额度
	private double rate;		//欠款的日利率
	private double fee;			//信用卡年费

	private final double getDebt() {	//获得欠款额
		double balance = getBalance();
		return (balance < 0 ? balance : 0);
	}
	//构造函数
	
	public CreditAccount(Date date,String id, double credit, double rate, double fee){
		super(date,id);  //调用父类的构造方法
		
		this.credit = credit;
		this.rate = rate;
		this.fee = fee;
		acc = new Accumulator(date,0);
		
	}
	public final double getCredit()  { return credit; }
	public final double getRate()  { return rate; }
	public final double getFee()  { return fee; }
	public final double getAvailableCredit() {	//获得可用信用
		if (getBalance() < 0) 
			return credit + getBalance();
		else
			return credit;
	}
	public void deposit(Date date, double amount,String desc) {
		record(date, amount, desc);
		acc.change(date, getDebt());
	}

	public void withdraw(Date date, double amount, String desc) {
		if (amount - getBalance() > credit) {
			error("not enough credit");
		} else {
			record(date, -amount, desc);
			acc.change(date, getDebt());
		}
	}

	public void settle(Date date) {
		double interest = acc.getSum(date) * rate;
		if (interest != 0)
			record(date, interest, "interest");
		if (date.getMonth() == 1)
			record(date, -fee, "annual fee");
		acc.reset(date, getDebt());
	}

	public void show() {
		
		System.out.println(id + "\tBalance: " + getBalance() + "\tAvailable credit:" + getAvailableCredit());
	}
	
}

