package util.old.format;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 转换符
 * 
 *  %d	十进制
 *  
 *  %s	字符串
 *  
 *  %b	布尔
 *  
 *  %tX	日期(X代表不同的日期或时间转换符)
 *  
 *  %n  独立平台换行符
 */
public class MyStringFormatter {
	public static void main(String[] args) {
		numberFormat();
		
		floatFormat();
		
		stringFormat();
		
		dateFormat();
	}


	/**
	 * 基本格式：
	 * 	%[index$][标识][最小宽度]转换方式
	 * 
	 * 标识：
	 * 	' '	用空格填充
	 *  0	用0填充
	 *  ,	每3位数字之间用“，”分隔
	 *  
	 *  转换方式：
	 *  d	10进制整数
	 * 
	 */
	private static void numberFormat() {
		log(String.format("%n【整数格式化】"));
		
		//空格填充
		log(String.format("% 3d", 7)); 
		log(String.format("%3d", 7));
		
		//0填充
		log(String.format("%03d", 7)); 
		
		//分组：百分位，千分位...
		log(String.format("%,d", Integer.MAX_VALUE)); 
		log(String.format("%1$,d", Integer.MAX_VALUE));
	}
	

	/**
	 * 基本格式：
	 * 	%[index$][标识][最少宽度][.精度]转换方式
	 *  %[index$][flags][width][.precision]conversion 
	 * 
	 * 	使用范围：float、Float、double、Double 和 BigDecimal
	 * 
	 * 标识：
	 * 	' '	用空格填充
	 *  0	用0填充
	 *  ,	每3位数字之间用“，”分隔
	 * 
	 * 转换方式：
	 * 	f 浮点数
	 */
	private static void floatFormat() {
		log(String.format("%n【小数格式化】"));
		
		BigDecimal bd1 = new BigDecimal("1000.1248");
		BigDecimal bd2 = new BigDecimal("1000.1258");
		
		log(String.format("%1$ 10.2f", 1000.1248f)); //   1000.12 -空格填充满10位
		log(String.format("%1$010.2f", 1000.1248d)); //0001000.12 -0填充满10位
		log(String.format("%1$,.2f", 1000.1248)); //1,000.12 -有四舍五入功能
		log(String.format("%1$,.2f", 1000.1258)); //1,000.13 -有四舍五入功能
		log(String.format("%1$,.2f", bd1)); //1,000.12
		log(String.format("%1$,.2f", bd2)); //1,000.13
		
		
	}
	
	/**
	 * 基本格式：
	 *  %[index$][标识]转换方式 
	 * 
	 * 标识：
	 * 	 t或者T 
	 * 
	 * 转换方式：	
	 *  c	全部日期和时间	
	 *  F	年-月-日
	 *  D	月/日/年
	 *  r	HH:MM:SS PM (12时制)
	 * 	T	HH:MM:SS	(24时制)
	 *  R	HH:MM	(24时制)
	 *  
	 *  Y	年
	 *  m	月
	 *  d	日
	 *  
	 *  H	2位数字24时制的小时（不足2位前面补0）
	 *  M 	2位数字的分钟（不足2位前面补0）
	 *  S	2位数字的秒（不足2位前面补0）
	 *  L	3位数字的毫秒（不足3位前面补0）
	 * 
	 */
	private static void dateFormat() {
		log(String.format("%n【日期与时间格式化】"));
		
		Date now = new Date();
		log(String.format("%tc", now)); //星期二 五月 12 00:04:10 CST 2015
		log(String.format("%1$TF %1$TT", now)); //2015-05-12 00:22:51
		log(String.format("%1$tF %1$tT", now)); //2015-05-12 00:22:51
		log(String.format("%1$tD %1$tT", now)); //05/12/15 00:06:50
		log(String.format("%1$tF %1$tr", now)); //2015-05-12 12:06:50 上午
		log(String.format("%1$tF %1$tR", now)); //2015-05-12 00:06

		log(String.format("%1$tY年", now)); //2015年05月12日
		log(String.format("%1$tm月", now)); //2015年05月12日
		log(String.format("%1$td日", now)); //2015年05月12日
		log(String.format("%1$tY年%1$tm月%1$td日", now)); //2015年05月12日

		log(String.format("%1$tH时", now)); //00时
		log(String.format("%1$tM分", now)); //15分
		log(String.format("%1$tS秒", now)); //22秒
		log(String.format("%1$tL毫秒", now)); //22秒
		log(String.format("%1$tH时%1$tM分%1$tS秒%1$tL毫秒", now)); //00时14分46秒

		log(String.format("%1$tY年%1$tm月%1$td日 %1$tH时%1$tM分%1$tS秒%1$tL毫秒", now)); //2015年05月12日 00时21分19秒507毫秒
		log(String.format("%1$tY/%1$tm/%1$td %1$tH:%1$tM:%1$tS.%1$tL", now)); //2015/05/12 00:21:19.507
		log(String.format("%1$tF %1$tH:%1$tM:%1$tS.%1$tL", now)); //2015-05-12 00:21:19.507

		log(String.format("%1$ts", now)); //1970-1-1 00:00:00 到现在所经过的秒数
		log(String.format("%1$tQ", now)); //1970-1-1 00:00:00 到现在所经过的毫秒数
		log(System.currentTimeMillis()+"");
		
		
		
		
	}

	private static void stringFormat() {
		log(String.format("%n【字符串格式化】"));
		
		log(String.format("Hello %s", "John"));//Hello John
		log(String.format("Hello %s ABC", "John"));//Hello John ABC
		log(String.format("%s", "John","Mike")); //只取第1个参数-John
		log(String.format("%1$s%2$s", "123", "xyz")); //拼接字符串-123xyz
		log(String.format("%1$s%2$s", 123, 456)); //拼接字符串-123456
		log(String.format("%1$s%2$05d", 123, 456)); //拼接字符串-12300456
	}

	private static void log(String s) {
		System.out.println(s);
	}
}
