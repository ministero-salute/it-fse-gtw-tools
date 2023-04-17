/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
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
