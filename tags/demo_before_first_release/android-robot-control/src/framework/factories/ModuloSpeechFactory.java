package framework.factories;

import framework.enums.ModuloType;
import framework.interfaces.IModulo;
import framework.modulos.player.ModuloPlayerSpeech;
import framework.modulos.stage.ModuloStageSpeech;

public class ModuloSpeechFactory extends ModuloFactory {
	
	@Override
	public IModulo factoryMethod(ModuloType moduloType) {
		
		switch (moduloType) {
		case PLAYER:
			return ModuloPlayerSpeech.getInstance();
		case STAGE:
			return ModuloStageSpeech.getInstance();           
		}

		throw new IllegalArgumentException("moduloType " + moduloType + " is not valid.");
		
	}

}
