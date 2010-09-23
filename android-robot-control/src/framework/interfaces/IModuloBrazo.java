package framework.interfaces;

public interface IModuloBrazo extends IModulo {
	
	// Movimientos por steps (presici�n contiene la cantidad de pasos a mover)
    public void brazoSubir( int presicion );

    public void brazoBajar( int presicion );

    public void brazoDerecha( int presicion );

    public void brazoIzquierda( int presicion );

    public void manoAbrir( int presicion );

    public void manoCerrar( int presicion );

    // Abrir y cerrar maximo hasta el fin de escala
    public void manoAbrirMaximo();

    public void manoCerrarMaximo();

    // Devolver y Setear Coordenadas
    public int[] getPosBrazo();

    public void setPosBrazo( int[] data );

    // Posiciones actuales
    public int getValorVertical();

    public int getValorHorizontal();

    public int getValorMano();

    // Mover hasta una determinada posicion de forma suave (en varios steps)
    public void moverVerticalSuave( int posicion );

    public void moverHorizontalSuave( int posicion );

    public void moverManoSuave( int posicion );

    // Devolver los minimos o maximos valores de la escala
    public int[] getMinimos();

    public int[] getMaximos();

}
