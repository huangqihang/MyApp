package guava.null_;

import java.util.Set;

import javax.swing.text.html.Option;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Strings;

public class Demo1 {
	public static void main(String[] args) {
		Integer i = 5;
		Optional<Integer> possible = Optional.of(i);
		System.out.println(possible.isPresent());
		System.out.println(possible.get());
		
		Optional t = Optional.absent();
		System.out.println(t.isPresent());
		
		String s = null;
		Optional<String> ss  = Optional.fromNullable(s);
		System.out.println(ss.or("?")+"a");
		System.out.println(ss.orNull()+"a");
		
		Set<String> set = ss.asSet();
		System.out.println(set.size());
		
		Object o = MoreObjects.firstNonNull(s, "xx");
		System.out.println(o);
		
		Integer iii = null;
		Optional<Integer> opt = Optional.fromNullable(iii);
		int k = opt.or(2);
		System.out.println(k);
		
		String sr = Strings.nullToEmpty(s);
		System.out.println(sr);
		
	}
}
