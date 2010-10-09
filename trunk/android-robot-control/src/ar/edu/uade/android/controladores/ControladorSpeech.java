package ar.edu.uade.android.controladores;

import framework.enums.ModuloType;
import framework.factories.ModuloSpeechFactory;
import framework.interfaces.IModuloSpeech;

public class ControladorSpeech {
	
	private IModuloSpeech speech;
	
	public ControladorSpeech() {
    	ModuloSpeechFactory factory = new ModuloSpeechFactory();
    	speech = (IModuloSpeech)factory.createModulo(ModuloType.STAGE);
    }
	
	public void decirTexto(String texto) {
		speech.decirTexto(texto);
	}

}
