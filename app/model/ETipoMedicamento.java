package model;

import java.util.HashMap;
import java.util.Map;

public enum ETipoMedicamento {

	COMPRIMIDO, XAROPE, POMADA, COLIRIO;

	public static Map<String, String> options(){
		Map<String, String> opcoes = new HashMap<String, String>();
		for (ETipoMedicamento tipo: ETipoMedicamento.values()){
			opcoes.put(tipo.name(), tipo.name());
		}
		return opcoes;
	}
}
