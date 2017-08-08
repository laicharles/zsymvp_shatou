import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {
	public static void main(String[] args) {
		
		Date dNow = new Date();   //当前时间
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(dNow);//把当前时间赋给日历
		calendar.add(Calendar.DAY_OF_MONTH, -25);  //设置为前一天
		dBefore = calendar.getTime();   //得到前一天的时间

		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
		String defaultStartDate = sdf.format(dBefore);    //格式化前一天
		String defaultEndDate = sdf.format(dNow); //格式化当前时间

		System.out.println("前一天的时间是：" + defaultStartDate);
		System.out.println("生成的时间是：" + defaultEndDate);  

	}
}
