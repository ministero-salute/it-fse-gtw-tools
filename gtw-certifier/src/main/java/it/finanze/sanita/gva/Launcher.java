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
package it.finanze.sanita.gva;

import it.finanze.sanita.gva.cli.CliParser;
import it.finanze.sanita.gva.cli.data.Args;
import it.finanze.sanita.gva.core.DataProcessor;

import java.util.logging.Logger;

import static it.finanze.sanita.gva.utility.LogUtility.getLogger;
import static it.finanze.sanita.gva.utility.LogUtility.welcome;

public class Launcher {

    private static final Logger logger = getLogger(Launcher.class);

    private static final CliParser cli = new CliParser();

    public static void main(String[] args) {
        // Print ascii-art
        welcome(logger);
        // Parse args
        cli.parse(args);
        // Check
        if(cli.isInit()) {
            // Get args
            Args arg = cli.getArgs();
            // Process according to args
            if(arg.anyUnknownParam()) {
                // If an unknown param was parsed
                cli.unknown();
            } else if(arg.isVersion()) {
                // If asking for version
                cli.version();
            } else if (arg.isHelp()) {
                // If asking for help
                cli.usage();
            } else if(arg.getDir() != null) {
                // If it should process
                new DataProcessor(arg.getDir()).checkAndprocess();
            }
        }
    }
}