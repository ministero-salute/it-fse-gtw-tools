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
