package ar.edu.uade.android.servicios;

import framework.enums.ModuloType;
import framework.factories.ModuloRuedasFactory;
import framework.interfaces.IModuloRuedas;

public class ServicioServos {

    private IModuloRuedas ruedas;
    
    public ServicioServos() {
    	ModuloRuedasFactory factory = new ModuloRuedasFactory();
        ruedas = (IModuloRuedas)factory.createModulo(ModuloType.STAGE);
    }

    public void Adelante()
    {
        ruedas.Adelante();
    }

    public void Atras()
    {
        ruedas.Atras();
    }
    
    public void GirarIzq()
    {
        ruedas.GirarIzq();
    }

    public void GirarDer()
    {
        ruedas.GirarDer();
    }

}
