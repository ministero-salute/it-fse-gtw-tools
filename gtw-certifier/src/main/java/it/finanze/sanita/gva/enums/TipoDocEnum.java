/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 * 
 * Copyright (C) 2023 Ministero della Salute
 * 
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.
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
