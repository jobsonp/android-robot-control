package ar.edu.uade.android.controladores;

import framework.enums.ModuloType;
import framework.factories.ModuloBrazoFactory;
import framework.interfaces.IModuloBrazo;

public class ControladorBrazo {

	private IModuloBrazo brazo;

    public ControladorBrazo() {
    	ModuloBrazoFactory factory = new ModuloBrazoFactory();
    	brazo = (IModuloBrazo)factory.createModulo(ModuloType.STAGE);
    }

    // Movimientos por steps (presicion contiene la cantidad de pasos a mover)
    public void brazoSubir( int presicion )
    {
        brazo.brazoSubir( presicion );
    }

    public void brazoBajar( int presicion )
    {
        brazo.brazoBajar( presicion );
    }

    public void brazoDerecha( int presicion )
    {
        brazo.brazoDerecha( presicion );
    }

    public void brazoIzquierda( int presicion )
    {
        brazo.brazoIzquierda( presicion );
    }

    public void abrirMano( int presicion )
    {
        brazo.manoAbrir( presicion );
    }

    public void cerrarMano( int presicion )
    {
        brazo.manoCerrar( presicion );
    }

    // Abrir y cerrar maximo hasta el fin de escala
    public void abrirManoMaximo()
    {
        brazo.manoAbrirMaximo();
    }

    public void cerrarManoMaximo()
    {
        brazo.manoCerrarMaximo();
    }

    // Devolver y Setear Coordenadas
    public int[] getPosBrazo()
    {
        return brazo.getPosBrazo();
    }

    public void setPosBrazo( int[] data )
    {
        brazo.setPosBrazo( data );
    }

    // Posiciones actuales
    public int getValorVertical()
    {
        return brazo.getValorVertical();
    }

    public int getValorHorizontal()
    {
        return brazo.getValorHorizontal();
    }

    public int getValorMano()
    {
        return brazo.getValorMano();
    }

    // Mover hasta una determinada posicion de forma suave (en varios steps)
    public void moverVerticalSuave( int posicion )
    {
        brazo.moverVerticalSuave( posicion );
    }

    public void moverHorizontalSuave( int posicion )
    {
        brazo.moverHorizontalSuave( posicion );
    }

    public void moverManoSuave( int posicion )
    {
        brazo.moverManoSuave( posicion );
    }

    // Devolver los minimos o maximos valores de la escala
    public int[] getMinimos()
    {
        return brazo.getMinimos();
    }

    public int[] getMaximos()
    {
        return brazo.getMaximos();
    }
}
