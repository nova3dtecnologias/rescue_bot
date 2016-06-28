package br.com.rescue_bots_android.sqlite.entity;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;

public abstract class Entity implements Serializable{//,Comparable{
	Object codigo;
	public Entity(Object codigo){
		super();
		this.codigo = codigo;
	}
	public Entity(){
		super();
	}
	public abstract void setId(Object id);
	public abstract Object getId();
	/**
	 * Gerar id randomico para a aplica��o
	 * @return Long - chave randomica
	 * @throws NoSuchAlgorithmException 
	 */
	public static Long getIdRandomico() {
		long random;
		try {
			
			random = SecureRandom.getInstance("SHA1PRNG","SUN").nextLong();//xtInt(99999999);
			//random = SecureRandom.getInstance("SHA1PRNG").nextLong();
			//if(random<0){
			//	random = (-random);
		//	}
			return random;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static StringBuilder appendParams(List items){
		StringBuilder inValues = new StringBuilder();
		boolean firstValue = true;
		for (int i=0; i < items.size(); i++) {
		  inValues.append("?");
		  if ( firstValue ) {
		    firstValue = false;
		  } else {
		    inValues.append(',');
		  }
		}
		return inValues;
	}

	
}
