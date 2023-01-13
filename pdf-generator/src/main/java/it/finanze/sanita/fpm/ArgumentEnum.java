package it.finanze.sanita.fpm;

/**
 * Copyright (c) 2022, Ministero della Salute
 *
 * Arguments enum.
 */
enum ArgumentEnum {

	/**
	 * Show help page (optional mode).
	 */
	HELP_MODE("-h", false, "show this help page"),

	/**
	 * File pdf to inject (optional parameter).
	 */
	FILE_CDA("-c", true, "specify CDA file path to inject (mandatory)"),

	/**
	 * File pdf to inject (optional parameter).
	 */
	FILE_PDF("-p", true, "specify PDF file path (optional, if not given the tool will use a sample)"),

	/**
	 * Show verbose message (optional mode).
	 */
	VERBOSE_MODE("-x", false, "enable verbose mode (optional, default is false)"),

	/**
	 * Show validation info (optional mode).
	 */
	VALIDATION_MODE("-v", false, "enable validation mode (optional, default is false)"),
	
	/**
	 * File pdf to inject (optional parameter).
	 */
	FILE_OUTPUT("-o", true, "specify path output pdf file (optional , default is output.pdf)"),
	
	/**
	 * Pdf sign(optional parameter).
	 */
	SIGN_PDF("-s", false, "specify if pdf have to sign(optional , default is false)");
	
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
