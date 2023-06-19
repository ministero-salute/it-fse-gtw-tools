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

import it.finanze.sanita.gva.validator.CVACValidator;
import it.finanze.sanita.gva.validator.DocumentTypeValidator;
import it.finanze.sanita.gva.validator.LABValidator;
import it.finanze.sanita.gva.validator.LDOValidator;
import it.finanze.sanita.gva.validator.PssValidator;
import it.finanze.sanita.gva.validator.RADValidator;
import it.finanze.sanita.gva.validator.RSAValidator;
import it.finanze.sanita.gva.validator.SVACValidator;
import it.finanze.sanita.gva.validator.TRASFValidator;
import it.finanze.sanita.gva.validator.VPSValidator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum EsameEnum {
	
	//Lab OK e KO
	TEST1LAB(1, TipoDocEnum.REFERTO_LABORATORIO, LABValidator.class,"validateUC1"),
	TEST2LAB(2, TipoDocEnum.REFERTO_LABORATORIO, LABValidator.class,"validateUC2"),
	TEST3LAB(3, TipoDocEnum.REFERTO_LABORATORIO, LABValidator.class,"validateUC3"),
	TEST4LAB(4, TipoDocEnum.REFERTO_LABORATORIO, LABValidator.class,"validateUC4"),
	TEST5LAB(5, TipoDocEnum.REFERTO_LABORATORIO, LABValidator.class,"validateUC5"), 

	//LDO
	TEST6LDO(6, TipoDocEnum.LETTERA_DIMISSIONE_OSPEDALIERA, LDOValidator.class,"validateUC1"),
	TEST7LDO(7, TipoDocEnum.LETTERA_DIMISSIONE_OSPEDALIERA, LDOValidator.class,"validateUC2"),
	TEST8LDO(8, TipoDocEnum.LETTERA_DIMISSIONE_OSPEDALIERA, LDOValidator.class,"validateUC3"),
	TEST9LDO(9, TipoDocEnum.LETTERA_DIMISSIONE_OSPEDALIERA, LDOValidator.class,"validateUC4"),
	
	//RAD
	TEST11RAD(11, TipoDocEnum.REFERTO_RADIOLOGIA,  RADValidator.class,"validateUC1"),
	TEST12RAD(12, TipoDocEnum.REFERTO_RADIOLOGIA,  RADValidator.class,"validateUC2"),
	TEST13RAD(13, TipoDocEnum.REFERTO_RADIOLOGIA,  RADValidator.class,"validateUC3"),
	TEST14RAD(14, TipoDocEnum.REFERTO_RADIOLOGIA,  RADValidator.class,"validateUC4"),
	
	//CERT VACC
	TEST16VACC(16, TipoDocEnum.CERTIFICATO_VACCINALE,  CVACValidator.class,"validateUC1"),
	TEST17VACC(17, TipoDocEnum.CERTIFICATO_VACCINALE,  CVACValidator.class,"validateUC2"),
	TEST18VACC(18, TipoDocEnum.CERTIFICATO_VACCINALE,  CVACValidator.class,"validateUC3"),
	TEST19VACC(19, TipoDocEnum.CERTIFICATO_VACCINALE,  CVACValidator.class,"validateUC4"),
	
	//SIN VACC
	TEST20SIN_VACC(20, TipoDocEnum.SCHEDA_SINGOLA_VACCINAZIONE,  SVACValidator.class,"validateUC1"),
	TEST21SIN_VACC(21, TipoDocEnum.SCHEDA_SINGOLA_VACCINAZIONE,  SVACValidator.class,"validateUC2"),
	TEST22SIN_VACC(22, TipoDocEnum.SCHEDA_SINGOLA_VACCINAZIONE,  SVACValidator.class,"validateUC3"),
	TEST23SIN_VACC(23, TipoDocEnum.SCHEDA_SINGOLA_VACCINAZIONE,  SVACValidator.class,"validateUC4"),
	
	//VPS
	TEST24VPS(24, TipoDocEnum.VERBALE_PRONTO_SOCCORSO,  VPSValidator.class,"validateUC1"),
	TEST25VPS(25, TipoDocEnum.VERBALE_PRONTO_SOCCORSO,  VPSValidator.class,"validateUC2"),
	TEST26VPS(26, TipoDocEnum.VERBALE_PRONTO_SOCCORSO,  VPSValidator.class,"validateUC3"),
	TEST27VPS(27, TipoDocEnum.VERBALE_PRONTO_SOCCORSO,  VPSValidator.class,"validateUC4"),
	
	//RSA
	TEST147RSA(147, TipoDocEnum.REFERTO_SPECIALISTICA_AMBULATORIALE,  RSAValidator.class,"validateUC1"),
	TEST148RSA(148, TipoDocEnum.REFERTO_SPECIALISTICA_AMBULATORIALE,  RSAValidator.class,"validateUC2"),
	TEST149RSA(149, TipoDocEnum.REFERTO_SPECIALISTICA_AMBULATORIALE,  RSAValidator.class,"validateUC3"),
	TEST150RSA(150, TipoDocEnum.REFERTO_SPECIALISTICA_AMBULATORIALE,  RSAValidator.class,"validateUC4"),
	
	//PSS
	TEST170PSS(170, TipoDocEnum.PROFILO_SANITARIO_SINTETICO,  PssValidator.class,"validateUC1"),
	TEST171PSS(171, TipoDocEnum.PROFILO_SANITARIO_SINTETICO,  PssValidator.class,"validateUC2"),
	TEST172PSS(172, TipoDocEnum.PROFILO_SANITARIO_SINTETICO,  PssValidator.class,"validateUC3"),
	TEST173PSS(173, TipoDocEnum.PROFILO_SANITARIO_SINTETICO,  PssValidator.class,"validateUC4"),

	//TRASFUSIONALE
	TEST191TRASF(191, TipoDocEnum.REFERTO_LABORATORIO, TRASFValidator.class,"validateUC1"),
	TEST376TRASF(376, TipoDocEnum.REFERTO_LABORATORIO, TRASFValidator.class,"validateUC2");
	

	
	private Integer idTest;
	private TipoDocEnum tipoDocumento;
	private Class<? extends DocumentTypeValidator> validator;
	private String methodName;
	
	public static EsameEnum getCode(final Integer inIdTest) {
		EsameEnum out = null;
		for (EsameEnum v: EsameEnum.values()) {
			if (v.getIdTest().equals(inIdTest)) {
				out = v;
				break;
			}
		}
		return out;
	}
}
