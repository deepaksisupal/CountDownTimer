package timer.tiaa.fun;

public class Main {
	public static void main(String[] args) {
		if(null != args && args.length == 2){
			new CountDownFrame(new Countdown(args[0], args[1]));
		}
	}
}