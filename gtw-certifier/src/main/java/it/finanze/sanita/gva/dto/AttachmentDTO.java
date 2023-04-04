/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package it.finanze.sanita.gva.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AttachmentDTO {
	
	private String fileName;
	
	private byte[] content;
	
	private String mimeType;
}
