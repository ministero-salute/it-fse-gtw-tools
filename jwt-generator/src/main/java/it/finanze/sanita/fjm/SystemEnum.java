package it.finanze.sanita.fjm;

/**
 * Copyright (c) 2022, Ministero della Salute
 */
enum SystemEnum {

	/**
	 * Show help page (optional mode).
	 */
	GATEWAY("gateway"),
	
	/**
	 * Show help page (optional mode).
	 */
	PROVISIONING("provisioning"),
	
	/**
	 * specify jwt application target.
	 */
	MONITORING("monitoring");
	
	/**
	 * Key argument.
	 */
	private String key;
	
	
	/**
	 * Constructor.
	 * 
	 * @param inKey				key
	 * @param inFlagHasValue	flag mode or argument
	 * @param inDescription		description
	 */
	private SystemEnum(String inKey) {
		key = inKey;
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
	 * Get argument by key.
	 * 	
	 * @param key	key	
	 * @return		argument
	 */
	public static SystemEnum getByKey(String key) {
		SystemEnum out = null;
		for (SystemEnum a:SystemEnum.values()) {
			if (a.getKey().equals(key)) {
				out = a;
				break;
			}
		}
		return out;
	}
	
}
