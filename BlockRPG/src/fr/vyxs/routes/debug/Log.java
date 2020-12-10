package fr.vyxs.routes.debug;


public final class Log {

	protected Log() {
		throw new UnsupportedOperationException();
	}
	
	public static final void d(Object...objects) {
		System.err.println(String.format("[D]\t%s", extracted(objects).toString()));
	}
	
	public static final void e(Object...objects) {
		System.err.println(String.format("[E]\t%s", extracted(objects).toString()));
	}
	
	public static final void i(Object...objects) {
		System.out.println(String.format("[I]\t%s", extracted(objects).toString()));
	}
	
	public static final void w(Object...objects) {
		System.out.println(String.format("[W]\t%s", extracted(objects).toString()));
	}
	
	private static final StringBuilder extracted(Object... objects) {
		StringBuilder stringBuilder = new StringBuilder();
		for (Object o : objects)
			stringBuilder.append(o.toString());
		return stringBuilder;
	}
	
	public static final void test() {
		
	}
	
}
