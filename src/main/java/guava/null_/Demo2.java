package guava.null_;

import static com.google.common.base.Preconditions.*;

import java.util.Objects;

import com.google.common.base.Throwables;


public class Demo2 {
	public static void main(String[] args) {
		try {
			boolean success = false;
			
			checkNotNull(success);
			checkArgument(success);
		} catch(Exception e) {
			String error = Throwables.getStackTraceAsString(e);
			System.out.println("================="+error);
		}
		
	
	}
}
