package com.rest.util;

public final class Context {
	
	private static final ThreadLocal<Context> threadLocal=ThreadLocal.withInitial(Context::new);
	
	private String transactionId;
	
	private Context() {
		
	}

	/**
	 * @return the transactionId
	 */
	public static String transactionId() {
		return threadLocal.get().transactionId;
	}

	/**
	 * @param transactionId the transactionId to set
	 */
	public static void transactionId(String transactionId) {
		threadLocal.get().transactionId = transactionId;
	}
	
	//clear context
	public static void purgeContext() {
		threadLocal.remove();
	}
	

}
