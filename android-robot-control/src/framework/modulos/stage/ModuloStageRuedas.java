package framework.modulos.stage;

import framework.interfaces.IModuloRuedas;

public class ModuloStageRuedas implements IModuloRuedas {
	
	private static ModuloStageRuedas instance = null;
	
	private ModuloStageRuedas() {

	}
	
	public static ModuloStageRuedas getInstance () {
		if (instance==null)
			instance = new ModuloStageRuedas();
		return instance;
	}

	@Override
	public void Adelante() {
		// TODO Auto-generated method stub
	}

	@Override
	public void Atras() {
		// TODO Auto-generated method stub
	}

	@Override
	public void GirarDer() {
		// TODO Auto-generated method stub
	}

	@Override
	public void GirarIzq() {
		// TODO Auto-generated method stub
	}

}
