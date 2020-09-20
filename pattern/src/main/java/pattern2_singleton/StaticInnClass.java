package pattern2_singleton;

public class StaticInnClass {
	private static class IinnerClass {
		private static final StaticInnClass instance = new StaticInnClass();
	}
	public static StaticInnClass getInstance() {
		return IinnerClass.instance;
	}
	private StaticInnClass() {}
}