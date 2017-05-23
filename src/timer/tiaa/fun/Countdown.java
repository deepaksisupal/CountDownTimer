package timer.tiaa.fun;

public class Countdown {
	private String message;
	private int minutes; // no need for an array, keep it simple and readable
	private int seconds;

	public Countdown(String time, String message) {
		this.message = message;
		String[] parts = time.split(":");
		this.minutes = Integer.valueOf(parts[0]);
		this.seconds = Integer.valueOf(parts[1]);
	}

	public boolean isCloseToEnd() // move timer logic to countdown
	{
		return minutes < 1 && seconds <= 5; // opposite condition
	}

	public boolean isFinished() {
		return minutes < 1 && seconds < 1;
	}

	public Countdown decrement() {
		if (seconds > 0)
			seconds--;
		else {
			seconds = 59;
			minutes--;
		}
		return this; // just for chaining in test :)
	}

	public String toReadableString() {
		return String.format("%02d:%02d", minutes, seconds); // unify to one
																// format
	}

	public String getMessage() {
		return message;
	}
}