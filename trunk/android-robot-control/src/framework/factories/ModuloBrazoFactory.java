package framework.factories;

import framework.enums.ModuloType;
import framework.interfaces.IModulo;
import framework.modulos.player.ModuloPlayerBrazo;
import framework.modulos.stage.ModuloStageBrazo;

public class ModuloBrazoFactory extends ModuloFactory  {

	@Override
	public IModulo factoryMethod(ModuloType moduloType) {
		switch (moduloType) {
		case PLAYER:
			return ModuloPlayerBrazo.getInstance();
		case STAGE:
			return ModuloStageBrazo.getInstance();           
		}
		
		throw new IllegalArgumentException("moduloType " + moduloType + " is not valid.");
	}

}
