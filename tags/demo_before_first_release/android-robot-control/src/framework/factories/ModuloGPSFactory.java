package framework.factories;

import framework.enums.ModuloType;
import framework.interfaces.IModulo;
import framework.modulos.player.ModuloPlayerGPS;
import framework.modulos.stage.ModuloStageGPS;

public class ModuloGPSFactory extends ModuloFactory {
	
	@Override
	public IModulo factoryMethod(ModuloType moduloType) {
		
		switch (moduloType) {
			case PLAYER:
				return ModuloPlayerGPS.getInstance();
			case STAGE:
				return ModuloStageGPS.getInstance();           
		}
	
		throw new IllegalArgumentException("moduloType " + moduloType + " is not valid.");
		
	}

}
