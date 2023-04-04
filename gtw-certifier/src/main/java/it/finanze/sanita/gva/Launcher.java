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