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
package it.finanze.sanita.gva.dto;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

@Getter
public class ProcessResDTO {
    private final Set<Integer> unverifiable;
    private final Set<Integer> success;
    private final Map<Integer, String> failures;

    public ProcessResDTO() {
        this.unverifiable = new TreeSet<>();
        this.success = new TreeSet<>();
        this.failures = new HashMap<>();
    }

    public void success(int id) {
        success.add(id);
    }

    public void unverifiable(int id) {
        unverifiable.add(id);
    }

    public void failure(int id, String issue) {
        failures.putIfAbsent(id, issue);
    }

    public void print(Logger logger) {
        logger.info("[TEST SUPERATI]");
        logger.info(String.format("Test case OK con ids: %s", success));
        logger.info("[TEST NON VERIFICABILI]");
        logger.info(String.format("Test case KO con ids: %s", unverifiable));
        logger.info("[TEST FALLITI]");
        logger.info(String.format("Test case KO con ids: %s", failures.keySet()));
        logger.info("");
        failures.forEach((id, stack) -> {
            logger.info(String.format("[ERRORI TEST %s]", id));
            logger.info(String.format("%s%s%s", System.lineSeparator(), stack, System.lineSeparator()));
        });
    }

}
