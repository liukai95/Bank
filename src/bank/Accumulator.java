package bank;
public class Accumulator {	//��ĳ����ֵ�����ۼ�
	private Date lastDate;	//�ϴα����ֵ��ʱ��
 	private	double value;	//��ֵ�ĵ�ǰֵ
 	private	double sum;		//��ֵ�����ۼ�֮��
 	
 	//���캯����dateΪ��ʼ�ۼӵ����ڣ�valueΪ��ʼֵ
	public Accumulator(Date date, double value)
	{
		this.lastDate = date; 
		this.value = value;
	}
	//��õ�����date���ۼӽ��
	public final double getSum(Date date){
		return sum + value * date.distance(lastDate);
	}

	//��date����ֵ���Ϊvalue
	public void change(Date date, double value) {
		sum = getSum(date);
		lastDate = date;
		this.value = value;
	}

	//��ʼ���������ڱ�Ϊdate����ֵ��Ϊvalue���ۼ�������
	public void reset(Date date, double value) {
		lastDate = date;
		this.value = value;
		sum = 0;
	}
}