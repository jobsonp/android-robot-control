package framework.modulos.player;

import java.util.Vector;

import javaclient2.PlayerClient;
import javaclient2.SpeechInterface;
import android.util.Log;
import ar.edu.uade.android.utils.Constantes;
import framework.conector.Conector;
import framework.interfaces.IModuloSpeech;

public class ModuloPlayerSpeech implements IModuloSpeech {
	
	private static ModuloPlayerSpeech instance = null;
	
	public ModuloPlayerSpeech ( ) {
		
	}
	
	public static ModuloPlayerSpeech getInstance()
    {
        if ( instance == null )
            instance = new ModuloPlayerSpeech();
        return instance;
    }
	
	public void decirTexto(String texto)
	{
		Conector conector = Conector.getInstance();
		if (conector.isOnline())
		{
			Vector<Object> vv = conector.getInterfaceSPEECH();
			
			((PlayerClient) vv.elementAt(0)).setNotThreaded();
			
			((SpeechInterface)vv.elementAt(1)).speech(texto);
			
			Log.d( ModuloPlayerSpeech.class.getName(), Constantes.MODULO_SPEECH + "Texto Reproducido" );
			
			((PlayerClient) vv.elementAt(0)).stop();
			
		}else
			Log.e( ModuloPlayerSpeech.class.getName(), Constantes.MODULO_SPEECH + Constantes.SERVIDOR_OFFLINE );
	}

}
