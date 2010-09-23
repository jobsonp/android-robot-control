/**
 * ModuloBrazo.
 */
package framework.modulos;

import java.util.Vector;

import ar.edu.uade.android.utils.Constantes;
import framework.conector.Conector;

public class ModuloBrazo
    extends ModuloServos
{

    private static ModuloBrazo instance = null;

    // Los siguientes array mantienen los valores minimos y maximos que se deben manejar
    // de los servos (LIMITES POR SOFT).
    private int[] id = { 1, 2, 3 };

    private int[] min = { 0, 0, 0 }; // orden: horiz, vert, mano

    private int[] max = { 127, 127, 127 };

    // Tope de escala de los servos (HARDWARE)
    private int maximoValor = 127;

    // Valores actuales actualizados momento a momento de la ubicacion de cada servo
    private int[] valorActualServos;

    /**
     * Constructor privado porque se aplica el patr�n de dise�o singleton
     */
    private ModuloBrazo()
    {
        super();
        InicializarBrazo();
    }

    /**
     * El getInstance devuelve la instancia, en caso de no existir, la crea. Singleton
     * @return ModuloBrazo
     */
    public static ModuloBrazo getInstance()
    {
        if ( instance == null )
            instance = new ModuloBrazo();
        return instance;
    }

    /**
     * @param data
     */
    private void setMaximoValor( int data )
    {
        maximoValor = data;
    }

    /**
     * InicializarBrazo es el primer m�todo que debe ser invocado, coloca el brazo en una posicion
     * media e inicializa el vector con los valores actuales.
     */
    private void InicializarBrazo()
    {

        // Cargar desde la configuraci�n el valor maximo de pasos para los servos.
        Vector vv = Conector.getInstance().getInfoBrazo();
        setMaximoValor( Integer.parseInt( vv.elementAt( 0 ).toString() ) );

        // Cargar desde la configuracion el id de cada servo.
        id = (int[]) vv.elementAt( 1 );
        // Cargar desde la configuracion el valor m�ximo los servos. Restriccion de Software
        min = (int[]) vv.elementAt( 2 );
        // Cargar desde la configuracion el valor m�nimo los servos. Restriccion de Software
        max = (int[]) vv.elementAt( 3 );

        // INIT array enteros
        valorActualServos = new int[8];

        /************************************************************************
         * Sincronismo del modelo-vista COLOCAR LOS SERVOS A LA MITAD
         ************************************************************************/
        // lo primero que hay que hacer es setear el brazo en una posicion inicial
        // para poder saber en que posici�n estar� (esto se debe a que no hay forma
        // de preguntarle al driver en que posici�n se encuentra)
        setPosicionAbsoluta( id[Constantes.SERVO_HORIZONTAL_ARRAY], 63 ); // num servo, grados
        setPosicionAbsoluta( id[Constantes.SERVO_VERTICAL_ARRAY], 63 ); // num servo, grados
        setPosicionAbsoluta( id[Constantes.SERVO_MANO_ARRAY], 63 ); // num servo, grados

    } // END Constructor.

    /****************************************************************************
     * setPosicionAbsoluta es un m�todo privado y es usado para setear el valor en el servo, en el
     * array de valores actuales
     * @param servo (0 a 7)
     * @param valor (INT 0 a +127) Devuelve true si se pudo realizar, false si no...
     ****************************************************************************/
    private void setPosicionAbsoluta( int servo, int valor )
    {
        valorActualServos[servo] = valor;
        setServo( servo, -valorActualServos[servo] + 127 );
    }

    /****************************************************************************
     * cambiarPosicionSuave es llamado por la vista, cuando se pide el cambio de un servo servo, es
     * el numero de servo (0 al 7) y modificacion es el valor en int de los pasos/grados a modificar
     * (negativo para un lado y positivo para el otro) La diferencia con cambiaValor es que este lo
     * hace en 10 pasos para que el movimiento sea m�s sueve
     * @param servo (0 a 7)
     * @param modificion (INT -127 a +127) Devuelve true si se pudo realizar, false si no...
     ****************************************************************************/
    private void setearPosicionSuave( int servo, int posicion )
    {
        // Verificar que no se exceda de la escala
        if ( servo == id[Constantes.SERVO_VERTICAL_ARRAY] )
        {
            if ( posicion > max[Constantes.SERVO_VERTICAL_ARRAY] )
                posicion = max[Constantes.SERVO_VERTICAL_ARRAY];
            if ( posicion < min[Constantes.SERVO_VERTICAL_ARRAY] )
                posicion = min[Constantes.SERVO_VERTICAL_ARRAY];
        }
        if ( servo == id[Constantes.SERVO_HORIZONTAL_ARRAY] )
        {
            if ( posicion > max[Constantes.SERVO_HORIZONTAL_ARRAY] )
                posicion = max[Constantes.SERVO_HORIZONTAL_ARRAY];
            if ( posicion < min[Constantes.SERVO_HORIZONTAL_ARRAY] )
                posicion = min[Constantes.SERVO_HORIZONTAL_ARRAY];
        }
        if ( servo == id[Constantes.SERVO_MANO_ARRAY] )
        {
            if ( posicion > max[Constantes.SERVO_MANO_ARRAY] )
                posicion = max[Constantes.SERVO_MANO_ARRAY];
            if ( posicion < min[Constantes.SERVO_MANO_ARRAY] )
                posicion = min[Constantes.SERVO_MANO_ARRAY];
        }

        if ( posicion == valorActualServos[servo] )
            return;
        int step = ( posicion - valorActualServos[servo] ) / 15;
        if ( step == 0 )
        {
            // Si step == 0 entonces el for es infinito
            // pero el paso es tan chico que no hace falta hacer varios pasos
            // para llegar a �l, entonces un paso solo basta.
            setPosicionAbsoluta( servo, posicion ); // Setea la posicion final
            return;
        }
        int posicionInicial = valorActualServos[servo];
        if ( posicion > valorActualServos[servo] )
        {
            for ( int i = posicionInicial; i <= posicion; i = i + step )
            {
                setPosicionAbsoluta( servo, i );
                try
                {
                    Thread.sleep( 20 );
                }
                catch ( Exception e )
                {
                }
            }
        }
        if ( posicion < valorActualServos[servo] )
        {
            for ( int i = posicionInicial; i >= posicion; i = i + step )
            {
                setPosicionAbsoluta( servo, i );
                try
                {
                    Thread.sleep( 30 );
                }
                catch ( Exception e )
                {
                }
            }
        }
        setPosicionAbsoluta( servo, posicion ); // Setea la posicion final
    }


    public void brazoSubir( int presicion )
    {
        setearPosicionSuave( id[Constantes.SERVO_VERTICAL_ARRAY], valorActualServos[id[Constantes.SERVO_VERTICAL_ARRAY]]
            + presicion );
    }

    public void brazoBajar( int presicion )
    {
        setearPosicionSuave( id[Constantes.SERVO_VERTICAL_ARRAY], valorActualServos[id[Constantes.SERVO_VERTICAL_ARRAY]]
            - presicion );
    }

    public void brazoDerecha( int presicion )
    {
        setearPosicionSuave( id[Constantes.SERVO_HORIZONTAL_ARRAY],
                             valorActualServos[id[Constantes.SERVO_HORIZONTAL_ARRAY]] + presicion );
    }

    public void brazoIzquierda( int presicion )
    {
        setearPosicionSuave( id[Constantes.SERVO_HORIZONTAL_ARRAY],
                             valorActualServos[id[Constantes.SERVO_HORIZONTAL_ARRAY]] - presicion );
    }

    public void manoAbrir( int presicion )
    {
        setearPosicionSuave( id[Constantes.SERVO_MANO_ARRAY], valorActualServos[id[Constantes.SERVO_MANO_ARRAY]]
            + presicion );
    }

    public void manoCerrar( int presicion )
    {
        setearPosicionSuave( id[Constantes.SERVO_MANO_ARRAY], valorActualServos[id[Constantes.SERVO_MANO_ARRAY]]
            - presicion );
    }

    /**
     * Devuelve el estado actual del brazo
     * @return
     */
    public int[] getPosBrazo()
    {
        int[] data = new int[3];
        data[0] = valorActualServos[id[Constantes.SERVO_HORIZONTAL_ARRAY]];
        data[1] = valorActualServos[id[Constantes.SERVO_VERTICAL_ARRAY]];
        data[2] = valorActualServos[id[Constantes.SERVO_MANO_ARRAY]];
        return data;
    }

    /**
     * getMinimos devuelve los valores de fin de escala (minimos) de los servos.
     * @return
     */
    public int[] getMinimos()
    {
        return min;
    }

    /**
     * getMaximos devuelve los valores de fin de escala (maximos) de los servos.
     * @return
     */
    public int[] getMaximos()
    {
        return max;
    }

    /**
     * Recibido un array de 3 posiciones con el valor de los 3 servos, se mueve el brazo a esa
     * posicion
     * @param pos
     */
    public void setPosBrazo( int[] pos )
    {
        setPosicionAbsoluta( id[Constantes.SERVO_HORIZONTAL_ARRAY], pos[Constantes.SERVO_HORIZONTAL_ARRAY] );
        setPosicionAbsoluta( id[Constantes.SERVO_VERTICAL_ARRAY], pos[Constantes.SERVO_VERTICAL_ARRAY] );
        setPosicionAbsoluta( id[Constantes.SERVO_MANO_ARRAY], pos[Constantes.SERVO_MANO_ARRAY] );
    }

    public void manoAbrirMaximo()
    {
        if ( valorActualServos[id[Constantes.SERVO_MANO_ARRAY]] != min[Constantes.SERVO_MANO_ARRAY] )
            setearPosicionSuave( id[Constantes.SERVO_MANO_ARRAY], min[Constantes.SERVO_MANO_ARRAY] );
    }

    public void manoCerrarMaximo()
    {
        if ( valorActualServos[id[Constantes.SERVO_MANO_ARRAY]] != max[Constantes.SERVO_MANO_ARRAY] )
            setearPosicionSuave( id[Constantes.SERVO_MANO_ARRAY], max[Constantes.SERVO_MANO_ARRAY] );
    }

    public int getValorVertical()
    {
        return valorActualServos[id[Constantes.SERVO_VERTICAL_ARRAY]];
    }

    public int getValorHorizontal()
    {
        return valorActualServos[id[Constantes.SERVO_HORIZONTAL_ARRAY]];
    }

    public int getValorMano()
    {
        return valorActualServos[id[Constantes.SERVO_MANO_ARRAY]];
    }

    public void moverVerticalSuave( int posicion )
    {
        setearPosicionSuave( id[Constantes.SERVO_VERTICAL_ARRAY], posicion );
    }

    public void moverHorizontalSuave( int posicion )
    {
        setearPosicionSuave( id[Constantes.SERVO_HORIZONTAL_ARRAY], posicion );
    }

    public void moverManoSuave( int posicion )
    {
        setearPosicionSuave( id[Constantes.SERVO_MANO_ARRAY], posicion );
    }
}
