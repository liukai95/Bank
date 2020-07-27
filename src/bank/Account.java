package bank;
public abstract class  Account { //账户类
	String id; // 账号
	private double balance; // 余额
	private static double total;  //所有账户的总金额
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
	
	// 记录一笔帐，date为日期，amount为金额，desc为说明
	protected void record(Date date, double amount,String desc) {
		amount = Math.floor(amount * 100 + 0.5)/100;     // 保留小数点后两位
		balance += amount;
		balance = Math.floor(balance * 100 + 0.5)/100;     // 保留小数点后两位	
		total += amount;
		date.show();
		System.out.println("\t#" + id + "\t" + amount + "\t" + balance+ "\t" + desc);
	}
	
	// 显示账户信息
	abstract public void show();
	protected final void error(String msg){
		System.out.println("Error(#" + id + "):" + msg);
	}
	// 获得到指定日期为止的存款金额按日累积值
	abstract public void deposit(Date date, double amount, String desc);
	abstract public void withdraw(Date date, double amount, String desc);
	abstract public void settle(Date date);
	
}

