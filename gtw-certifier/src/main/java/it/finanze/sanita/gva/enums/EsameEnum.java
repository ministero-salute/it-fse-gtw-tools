/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package it.finanze.sanita.gva.enums;

import java.util.ArrayList;
import java.util.List;

import it.finanze.sanita.gva.validator.CVACValidator;
import it.finanze.sanita.gva.validator.DocumentTypeValidator;
import it.finanze.sanita.gva.validator.LABValidator;
import it.finanze.sanita.gva.validator.LDOValidator;
import it.finanze.sanita.gva.validator.PssValidator;
import it.finanze.sanita.gva.validator.RADValidator;
import it.finanze.sanita.gva.validator.RSAValidator;
import it.finanze.sanita.gva.validator.RapValidator;
import it.finanze.sanita.gva.validator.SVACValidator;
import it.finanze.sanita.gva.validator.TRASFValidator;
import it.finanze.sanita.gva.validator.VPSValidator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum EsameEnum {
	

	//Ripristinare con vecchio validatore
	TEST1LAB(1, TipoDocEnum.REFERTO_LABORATORIO,  LABValidator.class,"validateUC1"),
	TEST2LAB(2, TipoDocEnum.REFERTO_LABORATORIO,  LABValidator.class,"validateUC2"),
	TEST3LAB(3, TipoDocEnum.REFERTO_LABORATORIO,  LABValidator.class,"validateUC3"),
	TEST5LAB(5, TipoDocEnum.REFERTO_LABORATORIO,  LABValidator.class,"validateUC5"),
	TEST6LDO(6, TipoDocEnum.LETTERA_DIMISSIONE_OSPEDALIERA, LDOValidator.class,"validateUC1"),
	TEST7LDO(7, TipoDocEnum.LETTERA_DIMISSIONE_OSPEDALIERA, LDOValidator.class,"validateUC2"),
	TEST8LDO(8, TipoDocEnum.LETTERA_DIMISSIONE_OSPEDALIERA, LDOValidator.class,"validateUC3"),
	TEST9LDO(9, TipoDocEnum.LETTERA_DIMISSIONE_OSPEDALIERA, LDOValidator.class,"validateUC4"),
	TEST16VACC(16, TipoDocEnum.CERTIFICATO_VACCINALE,  CVACValidator.class,"validateUC1"),
	TEST17VACC(17, TipoDocEnum.CERTIFICATO_VACCINALE,  CVACValidator.class,"validateUC2"),
	TEST18VACC(18, TipoDocEnum.CERTIFICATO_VACCINALE,  CVACValidator.class,"validateUC3"),
	TEST19VACC(19, TipoDocEnum.CERTIFICATO_VACCINALE,  CVACValidator.class,"validateUC4"),
	TEST20SIN_VACC(20, TipoDocEnum.SCHEDA_SINGOLA_VACCINAZIONE,  SVACValidator.class,"validateUC1"),
	TEST21SIN_VACC(21, TipoDocEnum.SCHEDA_SINGOLA_VACCINAZIONE,  SVACValidator.class,"validateUC2"),
	TEST22SIN_VACC(22, TipoDocEnum.SCHEDA_SINGOLA_VACCINAZIONE,  SVACValidator.class,"validateUC3"),
	TEST23SIN_VACC(23, TipoDocEnum.SCHEDA_SINGOLA_VACCINAZIONE,  SVACValidator.class,"validateUC4"),
	TEST24VPS(24, TipoDocEnum.VERBALE_PRONTO_SOCCORSO,  VPSValidator.class,"validateUC1"),
	TEST25VPS(25, TipoDocEnum.VERBALE_PRONTO_SOCCORSO,  VPSValidator.class,"validateUC2"),
	TEST27VPS(27, TipoDocEnum.VERBALE_PRONTO_SOCCORSO,  VPSValidator.class,"validateUC4"),
	TEST147RSA(147, TipoDocEnum.REFERTO_SPECIALISTICA_AMBULATORIALE,  RSAValidator.class,"validateUC1"),
	TEST148RSA(148, TipoDocEnum.REFERTO_SPECIALISTICA_AMBULATORIALE,  RSAValidator.class,"validateUC2"),
	TEST149RSA(149, TipoDocEnum.REFERTO_SPECIALISTICA_AMBULATORIALE,  RSAValidator.class,"validateUC3"),
	TEST150RSA(150, TipoDocEnum.REFERTO_SPECIALISTICA_AMBULATORIALE,  RSAValidator.class,"validateUC4"),
	TEST0LAB(368, TipoDocEnum.REFERTO_LABORATORIO,  LABValidator.class,null),
	TEST0LDO(369, TipoDocEnum.LETTERA_DIMISSIONE_OSPEDALIERA,  LDOValidator.class,null),
	TEST0VACC(371, TipoDocEnum.CERTIFICATO_VACCINALE,  CVACValidator.class,null),
	TEST0SIN_VACC(372, TipoDocEnum.SCHEDA_SINGOLA_VACCINAZIONE,  SVACValidator.class,null),
	TEST0VPS(373, TipoDocEnum.VERBALE_PRONTO_SOCCORSO,  VPSValidator.class,null),
	TEST0RSA(374, TipoDocEnum.REFERTO_SPECIALISTICA_AMBULATORIALE,  RSAValidator.class,null),
	//Lab OK e KO
	TEST452_17LAB(452, TipoDocEnum.REFERTO_LABORATORIO,  LABValidator.class,"validateUC17"),
	TEST4_4LAB(4, TipoDocEnum.REFERTO_LABORATORIO,  LABValidator.class,"validateUC4New"),
	 
	//LDO
	TEST450_17LDO(450, TipoDocEnum.LETTERA_DIMISSIONE_OSPEDALIERA, LDOValidator.class,"validateUC17"),
	TEST451_18LDO(451, TipoDocEnum.LETTERA_DIMISSIONE_OSPEDALIERA, LDOValidator.class,"validateUC18"),
	 
	//CERT VACC
	TEST458_18VACC(458, TipoDocEnum.CERTIFICATO_VACCINALE,  CVACValidator.class,"validateUC18"),
	TEST459_19VACC(459, TipoDocEnum.CERTIFICATO_VACCINALE,  CVACValidator.class,"validateUC19"),
	 
	//SIN VACC
	TEST456_4SIN_VACC(456, TipoDocEnum.SCHEDA_SINGOLA_VACCINAZIONE,  SVACValidator.class,"validateUC4New"),
	TEST457_20SIN_VACC(457, TipoDocEnum.SCHEDA_SINGOLA_VACCINAZIONE,  SVACValidator.class,"validateUC20"),
	 
	
	//VPS
	TEST454_30VPS(454, TipoDocEnum.VERBALE_PRONTO_SOCCORSO,  VPSValidator.class,"validateUC30"),
	TEST455_31VPS(455, TipoDocEnum.VERBALE_PRONTO_SOCCORSO,  VPSValidator.class,"validateUC31"),
	 
	//RSA
	TEST448_24RSA(448, TipoDocEnum.REFERTO_SPECIALISTICA_AMBULATORIALE,  RSAValidator.class,"validateUC24"),
	TEST449_25RSA(449, TipoDocEnum.REFERTO_SPECIALISTICA_AMBULATORIALE,  RSAValidator.class,"validateUC25"),
	 
	//RAD
	TEST446RAD(446, TipoDocEnum.REFERTO_RADIOLOGIA,  RADValidator.class,"validateUC1New"),
	TEST447RAD(447, TipoDocEnum.REFERTO_RADIOLOGIA,  RADValidator.class,"validateUC2New"),
 
	
	//PSS
	TEST444PSS(444, TipoDocEnum.PROFILO_SANITARIO_SINTETICO,  PssValidator.class,"validateUC1New"),
	TEST445PSS(445, TipoDocEnum.PROFILO_SANITARIO_SINTETICO,  PssValidator.class,"validateUC2New"),
	 	
	TEST191TRASF(191, TipoDocEnum.REFERTO_LABORATORIO,  TRASFValidator.class,"validateUC1New"),
	TEST376TRASF(376, TipoDocEnum.REFERTO_LABORATORIO,  TRASFValidator.class,"validateUC2New"),
 
	//Validazione Rap
	TEST1RAP(417, TipoDocEnum.REFERTO_ANATOMIA_PATOLOGICA,  RapValidator.class,"validateUC1New"),
	TEST2RAP(418, TipoDocEnum.REFERTO_ANATOMIA_PATOLOGICA,  RapValidator.class,"validateUC2New"),
	TEST3RAP(419, TipoDocEnum.REFERTO_ANATOMIA_PATOLOGICA,  RapValidator.class,"validateUC3New"),
	TEST460RAP(460, TipoDocEnum.REFERTO_ANATOMIA_PATOLOGICA,  RapValidator.class, "validateUC24New");
 
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
