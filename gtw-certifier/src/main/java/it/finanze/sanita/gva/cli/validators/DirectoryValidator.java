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
package it.finanze.sanita.gva.cli.validators;

import com.beust.jcommander.IValueValidator;
import com.beust.jcommander.ParameterException;

import java.io.File;

public class DirectoryValidator implements IValueValidator<File> {
    @Override
    public void validate(String name, File value) throws ParameterException {
        if(value.isFile()) {
            throw new ParameterException(String.format("Parameter %s should be a directory", name));
        }
    }
}
