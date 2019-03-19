package pattern2_singleton;

public class Lazy {
	private Lazy() {}
	private static Lazy lazy = null;
	public static synchronized Lazy getInstance() {
		if (lazy == null) {
			lazy = new Lazy();
		}
		return lazy;
	}
}