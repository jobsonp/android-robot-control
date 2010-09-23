package ar.edu.uade.android.servicios;

import framework.enums.ModuloType;
import framework.factories.ModuloWirelessFactory;
import framework.interfaces.IModuloWireless;

public class ServicioWIFI {

    private IModuloWireless wireless;

    public ServicioWIFI() {
    	ModuloWirelessFactory factory = new ModuloWirelessFactory();
    	wireless = (IModuloWireless)factory.createModulo(ModuloType.STAGE);
    }

    public int[] getWifiData()
    {
        return wireless.getWifiData();
    }

    public int getNoise()
    {
        return wireless.getNoise();
    }

    public int getLevel()
    {
        return wireless.getLevel();
    }

    public int getLink()
    {
        return wireless.getLink();
    }
}