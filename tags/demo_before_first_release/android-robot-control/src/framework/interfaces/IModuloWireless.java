package framework.interfaces;

public interface IModuloWireless extends IModulo {
	
	public int[] getWifiData();

	public int getNoise();

	public int getLevel();

	public int getLink();

}
