package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerFactory {
	static Logger logger;

	public static Logger getLogger() {
		logger = LogManager.getLogger(); // Log4j
		return logger;
	}
}
