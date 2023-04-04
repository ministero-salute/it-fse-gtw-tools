/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package it.finanze.sanita.gva.enums;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum TipoDocEnum {
	
	REFERTO_LABORATORIO("Referto di Laboratorio"),
	PROFILO_SANITARIO_SINTETICO("Profilo Sanitario Sintetico"),
	LETTERA_DIMISSIONE_OSPEDALIERA("Lettera di Dimissione Ospedaliera"),
	REFERTO_RADIOLOGIA("Referto di Radiologia"),
	SCHEDA_SINGOLA_VACCINAZIONE("Scheda singola vaccinazione"),
	CERTIFICATO_VACCINALE("Certificato Vaccinale"),
	VERBALE_PRONTO_SOCCORSO("Verbale di Pronto Soccorso"),
	REFERTO_SPECIALISTICA_AMBULATORIALE("Referto specialistico");

	private String description;

}
