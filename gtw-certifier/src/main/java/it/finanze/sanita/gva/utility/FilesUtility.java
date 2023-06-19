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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FilesUtility {

	public static List<Path> walkAndMatch(Predicate<Path> matcher, Path root) throws IOException {
		try (Stream<Path> stream = Files.walk(root)) {
			return stream.filter(matcher).collect(Collectors.toList());
		}
	}

	public static Predicate<Path> matchDir(String dirname) {
		return (f) -> Files.isDirectory(f) && f.getFileName().toFile().getName().equals(dirname);
	}

	public static Predicate<Path> matchFile(String filename) {
		return (f) -> Files.isRegularFile(f) && f.getFileName().toFile().getName().equals(filename);
	}

	public static boolean isUniqueMatch(List<Path> matches, String name, Logger logger) {
		boolean unique = false;
		// Check emptiness
		if(matches.isEmpty()) {
			logger.info(String.format("ERR: Missing %s", name));
		} // Check duplicates
		else if(matches.size() > 1) {
			logger.info(String.format("ERR: Multiple %s found", name));
		} else {
			// Set flag
			unique = true;
		}
		return unique;
	}


	/**
	 * Returns {@code true} if the string passed as parameter is null or empty.
	 * 
	 * @param str	String to validate.
	 * @return		{@code true} if the string passed as parameter is null or empty.
	 */
	public static boolean isNullOrEmpty(final String str) {
		return str == null || str.isEmpty();
	}


	public static <T> T readJsonFromFile(File f, Class<T> type) throws IOException {
		return new ObjectMapper().readValue(f, type);
	}

}
