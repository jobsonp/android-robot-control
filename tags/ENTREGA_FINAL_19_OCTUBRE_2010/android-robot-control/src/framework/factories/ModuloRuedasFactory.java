package framework.factories;

import framework.enums.ModuloType;
import framework.interfaces.IModulo;
import framework.modulos.player.ModuloPlayerRuedas;
import framework.modulos.stage.ModuloStageRuedas;

public class ModuloRuedasFactory  extends ModuloFactory {

	@Override
	public IModulo factoryMethod(ModuloType moduloType) {
		switch (moduloType) {
		case PLAYER:
			return ModuloPlayerRuedas.getInstance();
		case STAGE:
			return ModuloStageRuedas.getInstance();           
		}

		throw new IllegalArgumentException("moduloType " + moduloType + " is not valid.");
	}

}
