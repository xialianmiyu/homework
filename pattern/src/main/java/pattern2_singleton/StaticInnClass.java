package pattern2_singleton;

public class StaticInnClass {
	private static class innerClass {
		private static final StaticInnClass instance = new StaticInnClass();
	}
	public static StaticInnClass getInstance() {
		return innerClass.instance;
	}
	private StaticInnClass() {}
}