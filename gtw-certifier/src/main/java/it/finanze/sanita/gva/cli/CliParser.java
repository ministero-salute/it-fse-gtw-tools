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
