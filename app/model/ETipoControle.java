package model;

import java.util.HashMap;
import java.util.Map;

public enum ETipoControle {

	SEM_CONTROLE, TARJA_VERMELHA, TARJA_PRETA, ANTIBIOTICO;

	public static Map<String, String> options(){
		Map<String, String> opcoes = new HashMap<String, String>();
		for (ETipoControle tipo: ETipoControle.values()){
			opcoes.put(tipo.name(), tipo.name());
		}
		return opcoes;
	}

}
