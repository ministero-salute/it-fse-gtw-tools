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
package it.finanze.sanita.gva.cli.data;

import com.beust.jcommander.Parameter;
import it.finanze.sanita.gva.cli.converter.FileConverter;
import it.finanze.sanita.gva.cli.validators.DirectoryValidator;
import lombok.Getter;

import java.io.File;
import java.util.List;

@Getter
public class Args {
    @Parameter(
        names = "-dir",
        description = "Directory to look-up for FILES dir and data.json (required)",
        required = true,
        converter = FileConverter.class,
        validateValueWith = DirectoryValidator.class
    )
    private File dir;

    @Parameter(
        names = "--version",
        description = "Display current version",
        help = true
    )
    private boolean version;

    @Parameter(
        names = "--help",
        description = "Display usage",
        help = true
    )
    private boolean help;

    // Pick-up unknown provided args
    @Parameter(hidden = true)
    private List<String> unknown;

    public boolean anyUnknownParam() {
        return unknown != null && !unknown.isEmpty();
    }

}
