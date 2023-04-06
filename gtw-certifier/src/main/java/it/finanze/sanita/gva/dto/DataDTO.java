/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package it.finanze.sanita.gva.dto;

import java.io.File;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataDTO {

	private DataJSONDTO data;
	
	private List<File> files;

	private String hash;
	
	private List<File> signedFiles;

}
