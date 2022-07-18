package it.finanze.sanita.fjm;

/**
 * Copyright (c) 2022, Ministero della Salute
 */
enum ArgumentEnum {

	/**
	 * Show help page (optional mode).
	 */
	HELP_MODE("-h", false, "show this help page"),

	/**
	 * Json data input (mandatory parameter).
	 */
	JSON_DATA("-d", true, "specify json data file path (mandatory)"),

	/**
	 * File to publish (optional parameter).
	 */
	FILE_TO_PUBLISH("-f", true, "specify PDF file path to publish (optional, if given the tool will calculate hash)"),

	/**
	 * P12 alias (mandatory parameter).
	 */
	P12_ALIAS("-a", true, "specify alias of p12 file (mandatory)"),

	/**
	 * P12 password (mandatory parameter).
	 */
	P12_PWD("-p", true, "specify password of p12 file (mandatory)"),

	/**
	 * Token duration (from now till n hours, optional parameter).
	 */
	DURATION_JWT("-t", true, "specify token duration (optional, default is 24h)"),

	/**
	 * Show verbose message (optional mode).
	 */
	VERBOSE_MODE("-x", false, "enable verbose mode (optional, default is false)"),

	/**
	 * Show validation info (optional mode).
	 */
	VALIDATION_MODE("-v", false, "enable validation mode (optional, default is false)");
	
	/**
	 * Key argument.
	 */
	private String key;
	
	/**
	 * Flag mode or argument.
	 */
	private boolean flagHasValue;
	
	/**
	 * Key description (for help page).
	 */
	private String description;
	
	
	/**
	 * Constructor.
	 * 
	 * @param inKey				key
	 * @param inFlagHasValue	flag mode or argument
	 * @param inDescription		description
	 */
	private ArgumentEnum(String inKey, boolean inFlagHasValue, String inDescription) {
		key = inKey;
		flagHasValue = inFlagHasValue;
		description = inDescription;
	}

	/**
	 * Getter key.
	 * 
	 * @return	key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Getter flag attribute or mode.
	 * 
	 * @return	flag attribute or mode
	 */
	public boolean getFlagHasValue() {
		return flagHasValue;
	}

	/**
	 * Getter description.
	 * 
	 * @return	description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Get argument by key.
	 * 	
	 * @param key	key	
	 * @return		argument
	 */
	public static ArgumentEnum getByKey(String key) {
		ArgumentEnum out = null;
		for (ArgumentEnum a:ArgumentEnum.values()) {
			if (a.getKey().equals(key)) {
				out = a;
				break;
			}
		}
		return out;
	}
	
}
