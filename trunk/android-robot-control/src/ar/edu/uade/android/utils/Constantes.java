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

    public static final String NOMBRE_ARCHIVO_PREFERENCIAS = "ar.edu.uade.android_preferences";

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
    
    public static final String INTERFACE_SPEECH_OK = "INTERFACE SPEECH (OK) - ";

    public static final String INTERFACE_SPEECH_FAILED = "INTERFACE SPEECH (FAILED) - ";
    
    public static final String INTERFACE_POSITION_2D_OK = "INTERFACE POSITION 2D (OK) - ";

    public static final String INTERFACE_POSITION_2D_FAILED = "INTERFACE POSITION 2D (FAILED) - ";

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
    
    public static final String MAIN_WEBCAM_PORT = "MAIN_WEBCAM_PORT";
    
    public static final String ARM_WEBCAM_PORT = "ARM_WEBCAM_PORT";

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
     * Stage configurations
     */
    
    /* Constantes de las ruedas, configuraciones por defecto. */
    
    public static final float STAGE_MOVEMENT_SPEED = 0.5F;
    
    public static final float STAGE_TURN_MOVEMENT_SPEED = 0.5F;
    
    public static final long STAGE_SECONDS_MOVEMENT = 1;
    
    /* Constantes del brazo y mano. */
    
    public static int STAGE_HAND_OPEN = 1;
	
    public static int STAGE_HAND_CLOSE = 0;
    
    public static int STAGE_HAND_DEFAULT_POSITION = 0;
	
    public static int STAGE_ARM_HORIZONTAL_MIN_ANGLE = 0;
	
    public static int STAGE_ARM_HORIZONTAL_MAX_ANGLE = 180;
	
    public static int STAGE_ARM_HORIZONTAL_DEFAULT_ANGLE = 90;
	
    public static int STAGE_ARM_VERTICAL_MIN_ANGLE = 0;
	
    public static int STAGE_ARM_VERTICAL_MAX_ANGLE = 180;
	
    public static int STAGE_ARM_VERTICAL_DEFAULT_ANGLE = 90;
    
    public static int STAGE_ARM_VERTICAL_DEFAULT_MOVEMENT_ANGLE = 5;
    
    public static int STAGE_ARM_HORIZONTAL_DEFAULT_MOVEMENT_ANGLE = 5;
    
    public static String STAGE_ARM_CURRENT_VERTICAL_ANGLE_PREFERENCES_KEY = "STAGE_ARM_CURRENT_VERTICAL_ANGLE_PREFERENCES_KEY";
    
    public static String STAGE_ARM_CURRENT_HORIZONTAL_ANGLE_PREFERENCES_KEY = "STAGE_ARM_CURRENT_HORIZONTAL_ANGLE_PREFERENCES_KEY";
    
    public static String STAGE_ARM_CURRENT_HAND_VALUE_PREFERENCES_KEY = "STAGE_ARM_CURRENT_HAND_VALUE_PREFERENCES_KEY";

    /**
     * Constantes de las camaras
     */

    public static final int CAMARA_PRINCIPAL = 1;

    public static final int CAMARA_BRAZO = 0;
}
