package framework.factories;

import framework.enums.ModuloType;
import framework.interfaces.IModulo;
import framework.modulos.player.ModuloPlayerWireless;

public class ModuloWirelessFactory extends ModuloFactory {

	@Override
	public IModulo factoryMethod(ModuloType moduloType) {
		
		switch (moduloType) {
		case PLAYER:
			return ModuloPlayerWireless.getInstance();
		case STAGE:
			return ModuloPlayerWireless.getInstance();           
		}

		throw new IllegalArgumentException("moduloType " + moduloType + " is not valid.");
		
	}

}
