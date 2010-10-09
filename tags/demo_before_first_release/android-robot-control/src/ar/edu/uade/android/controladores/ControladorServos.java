package ar.edu.uade.android.controladores;

import framework.enums.ModuloType;
import framework.factories.ModuloRuedasFactory;
import framework.interfaces.IModuloRuedas;

public class ControladorServos {

    private IModuloRuedas ruedas;
    
    public ControladorServos() {
    	ModuloRuedasFactory factory = new ModuloRuedasFactory();
        ruedas = (IModuloRuedas)factory.createModulo(ModuloType.STAGE);
    }

    public void adelante()
    {
        ruedas.Adelante();
    }

    public void atras()
    {
        ruedas.Atras();
    }
    
    public void girarIzq()
    {
        ruedas.GirarIzq();
    }

    public void girarDer()
    {
        ruedas.GirarDer();
    }

}
