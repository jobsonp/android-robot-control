package framework.modulos.stage;

import java.util.Vector;

import javaclient2.PlayerClient;
import javaclient2.SpeechInterface;
import android.os.Handler;
import android.util.Log;
import ar.edu.uade.android.utils.Constantes;
import framework.conector.Conector;
import framework.interfaces.IModuloSpeech;
import framework.modulos.player.ModuloPlayerSpeech;

public class ModuloStageSpeech implements IModuloSpeech {
	
	private static ModuloStageSpeech instance = null;
	
	public ModuloStageSpeech ( ) {
		
	}
	
	public static ModuloStageSpeech getInstance()
    {
        if ( instance == null )
            instance = new ModuloStageSpeech();
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
			
			closePlayerClientConnection((PlayerClient) vv.elementAt(0));
			
		}else
			Log.e( ModuloPlayerSpeech.class.getName(), Constantes.MODULO_SPEECH + Constantes.SERVIDOR_OFFLINE );
	}
	
	private void closePlayerClientConnection (final PlayerClient playerClient) {
		
		Handler handler = new Handler(); 
		handler.postDelayed(new Runnable() { 
			public void run() {
				playerClient.close();
			} 
		}, Constantes.STAGE_SHOW_SPEECH_MESSAGE_DEFAULT_TIME_IN_SECONDS*1000);
		
	}

}
