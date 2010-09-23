package framework.modulos.stage;

import framework.interfaces.IModuloBrazo;

public class ModuloStageBrazo implements IModuloBrazo {
	
	private static ModuloStageBrazo instance = null;
	
    private ModuloStageBrazo() {
    
    }

    public static ModuloStageBrazo getInstance()
    {
        if ( instance == null )
            instance = new ModuloStageBrazo();
        return instance;
    }

	@Override
	public void brazoBajar(int presicion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void brazoDerecha(int presicion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void brazoIzquierda(int presicion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void brazoSubir(int presicion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int[] getMaximos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getMinimos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getPosBrazo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getValorHorizontal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getValorMano() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getValorVertical() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void manoAbrir(int presicion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void manoAbrirMaximo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void manoCerrar(int presicion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void manoCerrarMaximo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moverHorizontalSuave(int posicion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moverManoSuave(int posicion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moverVerticalSuave(int posicion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPosBrazo(int[] data) {
		// TODO Auto-generated method stub
		
	}

}
