package org.beanone.xlogger.configure;

import java.util.HashMap;
import java.util.Map;

import org.beanone.xlogger.AspectContext;
import org.beanone.xlogger.ConfigHandler;
import org.beanone.xlogger.LoggerLevel;
import org.springframework.stereotype.Component;

/**
 * Handles exception logging configuration.
 *
 * @author Hongyan Li
 *
 */
@Component(ConfigHandler.EXCEPTION_HANDLER)
public class ExceptionConfigHandler
        extends AbstractConfigHandler<AspectContext> {
	private final Map<String, LoggerLevel> configEntries = new HashMap<>();

	@Override
	public LoggerLevel getConfiguration(AspectContext context) {
		final LoggerLevel level = this.configEntries
		        .get(context.getException().getClass().getName());
		return level == null ? LoggerLevel.ERROR : level;
	}

	@Override
	public String getPrefix() {
		return "xlogger.exception.";
	}

	@Override
	protected void configDefault(String key, String value) {
		// do nothing;
	}

	@Override
	protected void configure(String key, String value) {
		this.configEntries.put(key, toLoggerLevel(value));
	}

}
