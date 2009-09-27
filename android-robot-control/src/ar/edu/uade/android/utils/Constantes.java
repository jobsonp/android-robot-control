package ar.edu.uade.android.utils;

public class Constantes
{
    /**
     * Intents
     */

    public static final String INTENT_PANTALLA_CONEXION = "pantalla.conectar";

    public static final String INTENT_PANTALLA_MAPA = "pantalla.mapa";

    public static final String INTENT_PANTALLA_WEBCAM_PRINCIPAL = "pantalla.webcam.principal";

    public static final String INTENT_PANTALLA_WEBCAM_BRAZO = "pantalla.webcam.brazo";
    
    public static final String INTENT_PANTALLA_CONFIGURACION = "pantalla.configuracion";

    public static final String INTENT_ACCIONES_DESCONECTAR = "acciones.desconectar";

    public static final String INTENT_SERVICIO_BRAZO = "servicio.brazo";

    public static final String INTENT_SERVICIO_GPS = "servicio.gps";

    public static final String INTENT_SERVICIO_SERVOS = "servicio.servos";

    public static final String INTENT_SERVICIO_WIFI = "servicio.wifi";

    /**
     * Nombre del archivo de Preferencias
     */
    
    public static final String NOMBRE_ARCHIVO_PREFERENCIAS = "ar.edu.uade.android.preferences"; 
    
    /**
     * Debug Messages
     */

    public static final String CONEXION_OK = "CONEXION (  OK  ) -";

    public static final String CONEXION_FAILED = "CONEXION (FAILED) - ";

    public static final String SERVIDOR_OFFLINE = "SERVIDOR EN MODO (OFFLINE) - ";

    public static final String INTERFACE_GPS_OK = "INTERFACE GPS (OK) - ";

    public static final String INTERFACE_GPS_FAILED = "INTERFACE GPS (FAILED) - ";

    public static final String INTERFACE_CAMARA_OK = "INTERFACE CAMERA (OK) - ";

    public static final String INTERFACE_CAMARA_FAILED = "INTERFACE CAMERA (FAILED) - ";

    public static final String INTERFACE_WIRELESS_OK = "INTERFACE WIRELESS (OK) - ";

    public static final String INTERFACE_WIRELESS_FAILED = "INTERFACE WIRELESS (FAILED) - ";

    public static final String INTERFACE_SERVORTAI_OK = "INTERFACE SERVORTAI (OK) - ";

    public static final String INTERFACE_SERVORTAI_FAILED = "INTERFACE SERVORTAI (FAILED) - ";

    public static final String INTERFACE_MOTOR_OK = "INTERFACE MOTOR (OK) - ";

    public static final String INTERFACE_MOTOR_FAILED = "INTERFACE MOTOR (FAILED) - ";

    public static final String MODULO_GPS = "GPS - ";

    public static final String MODULO_MANO = "BRAZO-MANO - ";

    public static final String MODULO_CAMARA = "CAMARA - ";

    public static final String MODULO_WIFI = "WIFI - ";

    public static final String MODULO_RUEDAS = "RUEDAS - ";

    public static final String MODULO_CONECTOR = "CONECTOR - ";

    /**
     * Campos de Configuracion
     */

    public static final String IP_PLAYER = "IP_PLAYER";

    public static final String PORT_PLAYER = "PORT_PLAYER";

    public static final String ONLINE_PLAYER = "ONLINE_PLAYER";

    public static final String BRAZO_HORIZ_ID = "BRAZO_HORIZ_ID";

    public static final String BRAZO_VERT_ID = "BRAZO_VERT_ID";

    public static final String BRAZO_MANO_ID = "BRAZO_MANO_ID";

    public static final String BRAZO_HORIZ_MIN = "BRAZO_HORIZ_MIN";

    public static final String BRAZO_HORIZ_MAX = "BRAZO_HORIZ_MAX";

    public static final String BRAZO_VERT_MIN = "BRAZO_VERT_MIN";

    public static final String BRAZO_VERT_MAX = "BRAZO_VERT_MAX";

    public static final String BRAZO_MANO_MIN = "BRAZO_MANO_MIN";

    public static final String BRAZO_MANO_MAX = "BRAZO_MANO_MAX";

    public static final String BRAZO_STEP = "BRAZO_STEP";

    /**
     * Constantes del brazo, configuraciones por defecto.
     */

    public static final int SERVO_HORIZONTAL_ARRAY = 0;

    public static final int SERVO_VERTICAL_ARRAY = 1;

    public static final int SERVO_MANO_ARRAY = 2;

    /**
     * Constantes de las camaras
     */

    public static final int CAMARA_PRINCIPAL = 1;

    public static final int CAMARA_BRAZO = 0;
}
