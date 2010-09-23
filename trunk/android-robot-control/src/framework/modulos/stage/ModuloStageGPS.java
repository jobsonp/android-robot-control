package framework.modulos.stage;

import framework.interfaces.IModuloGPS;

public class ModuloStageGPS implements IModuloGPS {
	
	private static ModuloStageGPS instance = null;

    private ModuloStageGPS() {
    
    }

    public static ModuloStageGPS getInstance() {
        if ( instance == null )
            instance = new ModuloStageGPS();
        return instance;
    }

	@Override
	public float getAltitude() {
		return 10F;
	}

	@Override
	public float getLatitude() {
		return 34.12345F;
	}

	@Override
	public float getLongitude() {
		return 58.12345F;
	}

}
