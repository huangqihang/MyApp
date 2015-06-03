package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegTest {
	public static void main(String[] args) {
		test1();
		//test2();
		//test3();

	}

	private static void test1() {
		String address = "Address [province=四川,city=a洪雅b]";
		Pattern p=Pattern.compile("(\\w+)=([\\w\u4e00-\u9fa5]*)");
		Matcher m=p.matcher(address); 
		while(m.find()) {
			System.out.println(m.group(1)+"=="+m.group(2));
		}
	}

	private static void test2() {
		Pattern p=Pattern.compile("([a-z]+)(\\d+)"); 
		Matcher m=p.matcher("aaa2223bb"); 
		if(m.find()) {
			System.out.println(m.group()); // m.group() 输出整个regex所匹配到的字符串
			for(int i=1; i<=m.groupCount(); i++) {
				System.out.println(m.group(i));
			}
		}
		
		System.out.println("\n");
	}
	private static void test3() {
		Pattern p=Pattern.compile("\\d+"); 
		Matcher m=p.matcher("我的QQ是:456456 我的电话是:0532214 我的邮箱是:aaa123@aaa.com"); 
		while(m.find()) { 
		     System.out.println(m.group()); 
		} 
	}
}
