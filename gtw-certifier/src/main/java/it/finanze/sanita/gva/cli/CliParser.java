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
package it.finanze.sanita.gva.cli;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import it.finanze.sanita.gva.cli.data.Args;
import it.finanze.sanita.gva.utility.LogUtility;

import java.util.logging.Logger;

import static com.beust.jcommander.JCommander.newBuilder;
import static java.lang.System.*;

public class CliParser {

    public static final Logger logger = LogUtility.getLogger(CliParser.class);

    private static final String EXECUTABLE_NAME = "gtw-certifier";
    private static final String PROGRAM_VERSION = "v1.0";

    private final Args args;
    private final JCommander commander;
    private boolean init;

    public CliParser() {
        this.args = new Args();
        this.commander = commander();
        this.init = false;
    }

    private JCommander commander() {
        return newBuilder().programName(EXECUTABLE_NAME).addObject(args).build();
    }

    public void parse(String ...args) {
        try {
            // Try to parse
            commander.parse(args);
            // Set flag
            init = true;
        } catch (ParameterException e) {
            // Log error
            logger.info(String.format("ERR: %s%s", e.getMessage(), lineSeparator()));
            // Show usage
            commander.usage();
        }
    }

    public Args getArgs() {
        return args;
    }

    public boolean isInit() {
        return init;
    }

    public void usage() {
        commander.usage();
    }

    public void version() {
        logger.info(PROGRAM_VERSION);
    }

    public void unknown() {
        logger.info(String.format("ERR: One or more unknown arguments were provided: %s%s", args.getUnknown(), lineSeparator()));
        usage();
    }

}
