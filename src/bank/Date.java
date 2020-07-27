package bank;

public class Date {	//日期类

	private int year;		//年
	private int month;		//月
	private int day;		//日
	private	int totalDays;	//该日期是从公元元年1月1日开始的第几天
	public int getYear()  { return year; }
	public int getMonth() { return month; }
	public int getDay() { return day; }

	public boolean isLeapYear()  {	//判断当年是否为闰年
		return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
	}

	//计算两个日期之间差多少天	
	public int distance(Date date){
		return totalDays - date.totalDays;
	}
	//存储平年中某个月1日之前有多少天，为便于getMaxDay函数的实现，该数组多出一项
	public int  DAYS_BEFORE_MONTH[] = { 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365 };

//用年、月、日构造日期
	
    public Date(int year, int month, int day){
    	this.year = year;
    	this.month = month;
    	this.day = day;
	if (day <= 0 || day > getMaxDay()) {
		System.out.print("Invalid date: ");
		show();
		System.out.println();
		System.exit(1);
	}
	int years = year - 1;
	totalDays = years * 365 + years / 4 - years / 100 + years / 400
		+ DAYS_BEFORE_MONTH[month - 1] + day;
	if (isLeapYear() && month > 2) totalDays++;
}
	//获得当月有多少天
    public int getMaxDay(){
	if (isLeapYear() && month == 2)
		return 29;
	else
		return DAYS_BEFORE_MONTH[month]- DAYS_BEFORE_MONTH[month - 1];
    }
    //输出当前日期
    public void show(){
    	System.out.print(getYear() + "-" + getMonth() + "-" + getDay());
    }

}