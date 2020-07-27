package bank;

public class CreditAccount extends Account { //�����˻���
	private Accumulator acc;	//����������Ϣ���ۼ���
	private double credit;		//���ö��
	private double rate;		//Ƿ���������
	private double fee;			//���ÿ����

	private final double getDebt() {	//���Ƿ���
		double balance = getBalance();
		return (balance < 0 ? balance : 0);
	}
	//���캯��
	
	public CreditAccount(Date date,String id, double credit, double rate, double fee){
		super(date,id);  //���ø���Ĺ��췽��
		
		this.credit = credit;
		this.rate = rate;
		this.fee = fee;
		acc = new Accumulator(date,0);
		
	}
	public final double getCredit()  { return credit; }
	public final double getRate()  { return rate; }
	public final double getFee()  { return fee; }
	public final double getAvailableCredit() {	//��ÿ�������
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

