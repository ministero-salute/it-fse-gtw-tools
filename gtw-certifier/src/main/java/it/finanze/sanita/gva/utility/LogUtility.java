package it.finanze.sanita.gva.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public final class LogUtility {

    private static final InputStream SPLASH_SCREEN = LogUtility.class.getResourceAsStream("/splash-screen.txt");

    public static Logger getLogger(Class<?> clazz) {
        final Logger logger = Logger.getLogger(clazz.getName());
        logger.addHandler(handler());
        logger.setUseParentHandlers(false);
        return logger;
    }

    private static ConsoleHandler handler() {
        ConsoleHandler hnd = new ConsoleHandler();
        hnd.setFormatter(formatter());
        return hnd;
    }

    private static Formatter formatter() {
        return new Formatter() {
            @Override
            public String format(LogRecord record) {
                return record.getMessage() + System.lineSeparator();
            }
        };
    }


    public static void welcome(Logger logger) {
        if(SPLASH_SCREEN != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(SPLASH_SCREEN))) {
                reader.lines().forEach(logger::info);
            } catch (IOException e) {
                logger.info(String.format("Unable to load splash-screen: %s", e.getMessage()));
            }
        }
    }

}
