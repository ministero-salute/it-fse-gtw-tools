/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 * 
 * Copyright (C) 2023 Ministero della Salute
 * 
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
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
