package util.old.format;

import java.text.MessageFormat;

public class MyMessageFormat {
	public static void main(String[] args) {
		String text = "{0}您好，一共消费{1}元。";
		Object[] arguments = new Object[] {"张三", 1000.888888};
		
		System.out.println("before: "+text);
		
		text = fillData(text, arguments);
		
		System.out.println("after: "+text);
	}
	
	public static String fillData(String text, Object...args) {
		if(args.length > 0) {
			text = MessageFormat.format(text, args);
		}
		return text;
	}
}
