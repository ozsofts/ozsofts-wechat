package net.ozsofts.wechat;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Error {
	private static Object lock = new Object();

	private static Properties properties = null;

	public static String getErrorMessage(String errorCode) {
		if (properties == null) {
			synchronized (lock) {
				if (properties == null) {
					try {
						String propertiesName = Error.class.getPackage().getName().replace('.', '/') + "/error.properties";
						InputStream in = Error.class.getClassLoader().getResourceAsStream(propertiesName);
						properties = new Properties();
						properties.load(new BufferedInputStream(in));
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		}

		return properties.getProperty(errorCode);
	}

	public static void main(String[] args) {
		System.out.println(Error.getErrorMessage("40033"));
	}
}
