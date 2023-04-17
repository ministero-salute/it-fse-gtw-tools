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
