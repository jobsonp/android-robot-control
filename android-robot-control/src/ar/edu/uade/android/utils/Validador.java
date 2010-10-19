package ar.edu.uade.android.utils;

import java.util.regex.Pattern;

public class Validador
{
    /**
     * Regular expression que matechea de 1 a 3 numeros siempre entre 0 y 255
     */
    public static final String _255 = "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";

    /**
     * Pattern que matchea la regular expression de arriba 4 veces seguidas
     */
    public static final Pattern pattern = Pattern.compile( "^(?:" + _255 + "\\.){3}" + _255 + "$" );

    /**
     * Valida el formato de una ip.
     * 
     * @param ip a validar
     * @return true / false dependiendo si la ip es valida o no.
     */
    public static boolean validarIp( String ip )
    {
        return pattern.matcher( ip ).matches();
    }

    /**
     * Valida un numero de puerto.
     * 
     * @param puerto a validar
     * @return true / false dependiendo si el puerto es valido o no.
     */
    public static boolean validarPuerto( String puerto )
    {
        try
        {
            int puertoInt = Integer.parseInt( puerto );
            if ( puertoInt > 0 && puertoInt <= 65535 )
            {
                return true;
            }
        }
        catch ( NumberFormatException nfe )
        {
        }
        return false;
    }

    /**
     * Valida una ip mas un puerto. Ej: 192.168.1.110:8412
     * 
     * @param el ip:puerto a ser validado.
     * @return true / false si el string es valido.
     */
    public static boolean validarIpPuerto( String ipPuerto )
    {
        String[] ipMasPuerto = ipPuerto.split( ":" );
        if ( ipMasPuerto.length == 2 )
        {
            return Validador.validarIp( ipMasPuerto[0] ) && Validador.validarPuerto( ipMasPuerto[1] );
        }
        return false;
    }
}
