package pattern2_singleton;

public class DoubleCheck {
	private volatile static DoubleCheck uniqueInstance;
	public DoubleCheck() {}
	public static DoubleCheck getInstance() {
		if (uniqueInstance == null) {
			synchronized (DoubleCheck.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new DoubleCheck();
				}
			}
		}
		return uniqueInstance;
	}
}