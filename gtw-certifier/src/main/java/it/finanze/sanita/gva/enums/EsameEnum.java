/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package it.finanze.sanita.gva.enums;

import java.util.ArrayList;
import java.util.List;

import it.finanze.sanita.gva.validator.*;
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
	//TEST11RAD(11, TipoDocEnum.REFERTO_RADIOLOGIA,  RADValidator.class,"validateUC1"),
	//TEST12RAD(12, TipoDocEnum.REFERTO_RADIOLOGIA,  RADValidator.class,"validateUC2"),
	//TEST13RAD(13, TipoDocEnum.REFERTO_RADIOLOGIA,  RADValidator.class,"validateUC3"),
	//TEST14RAD(14, TipoDocEnum.REFERTO_RADIOLOGIA,  RADValidator.class,"validateUC4"),
	TEST446RAD(446, TipoDocEnum.REFERTO_RADIOLOGIA, RADValidator.class,"validateUC1"),
	TEST447RAD(447, TipoDocEnum.REFERTO_RADIOLOGIA, RADValidator.class,"validateUC2"),
	
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
	//TEST170PSS(170, TipoDocEnum.PROFILO_SANITARIO_SINTETICO,  PssValidator.class,"validateUC1"),
	//TEST171PSS(171, TipoDocEnum.PROFILO_SANITARIO_SINTETICO,  PssValidator.class,"validateUC2"),
	//TEST172PSS(172, TipoDocEnum.PROFILO_SANITARIO_SINTETICO,  PssValidator.class,"validateUC3"),
	//TEST173PSS(173, TipoDocEnum.PROFILO_SANITARIO_SINTETICO,  PssValidator.class,"validateUC4"),
	TEST444PSS(444, TipoDocEnum.PROFILO_SANITARIO_SINTETICO, PssValidator.class,"validateUC1"),
	TEST445PSS(445, TipoDocEnum.PROFILO_SANITARIO_SINTETICO, PssValidator.class,"validateUC2"),

	//TRASFUSIONALE
	TEST191TRASF(191, TipoDocEnum.REFERTO_LABORATORIO, TRASFValidator.class,"validateUC1"),
	TEST376TRASF(376, TipoDocEnum.REFERTO_LABORATORIO, TRASFValidator.class,"validateUC2"),
	
	//TEST 0
	TEST0LAB(368, TipoDocEnum.REFERTO_LABORATORIO, LABValidator.class, null),
	TEST0LDO(369, TipoDocEnum.LETTERA_DIMISSIONE_OSPEDALIERA, LDOValidator.class, null),
	TEST0VACC(371, TipoDocEnum.CERTIFICATO_VACCINALE, CVACValidator.class,null),
	TEST0SIN_VACC(372, TipoDocEnum.SCHEDA_SINGOLA_VACCINAZIONE, SVACValidator.class,null),
	TEST0VPS(373, TipoDocEnum.VERBALE_PRONTO_SOCCORSO, VPSValidator.class,null),
	TEST0RSA(374, TipoDocEnum.REFERTO_SPECIALISTICA_AMBULATORIALE, RSAValidator.class,null),
	//PRS Missing
	//PRF Missing
	//RAP
	TEST0RAP(417,TipoDocEnum.REFERTO_ANATOMIA_PATOLOGICA, RapValidator.class, "validateUC1"),
	TEST1RAP(418, TipoDocEnum.REFERTO_ANATOMIA_PATOLOGICA, RapValidator.class,"validateUC2"),
	TEST2RAP(419, TipoDocEnum.REFERTO_ANATOMIA_PATOLOGICA, RapValidator.class,"validateUC3");
	
	private Integer idTest;
	private TipoDocEnum tipoDocumento;
	private Class<? extends DocumentTypeValidator> validator;
	private String methodName;

	private static final List<Integer> TEST0_LIST;

    static {
        TEST0_LIST = new ArrayList<>();
        TEST0_LIST.add(TEST0LAB.getIdTest());
        TEST0_LIST.add(TEST0LDO.getIdTest());
        TEST0_LIST.add(TEST0RSA.getIdTest());
        TEST0_LIST.add(TEST0SIN_VACC.getIdTest());
        TEST0_LIST.add(TEST0VPS.getIdTest());
        TEST0_LIST.add(TEST0VACC.getIdTest());
    }
	
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

	public boolean isTest0(Integer idTest){
		if(TEST0_LIST.contains(idTest))
			return true;
		
		return false;
	}
}
