package framework.modulos.stage;

import framework.interfaces.IModuloWireless;

public class ModuloStageWireless implements IModuloWireless {
	
	private static ModuloStageWireless instance = null;
	
    private ModuloStageWireless() {
    
    }

    public static ModuloStageWireless getInstance()
    {
        if ( instance == null )
            instance = new ModuloStageWireless();
        return instance;
    }

	@Override
	public int getLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getLink() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNoise() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int[] getWifiData() {
		// TODO Auto-generated method stub
		return null;
	}

}
