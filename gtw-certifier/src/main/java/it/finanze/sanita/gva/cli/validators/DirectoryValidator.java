/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
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
