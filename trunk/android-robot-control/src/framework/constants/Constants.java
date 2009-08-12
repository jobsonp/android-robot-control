package framework.constants;

public class Constants {
	// TIPOS DE MENSAJE DE LOG
	public static final int MSG_ERR = 1;	//MENSAJE ERROR
	public static final int MSG_STD = 2;	//MENSAJE STANDARD
	// CAMPOS DEL ARCHIVO DE CONFIGURACION
	public static final String LOG_PANTALLA 	= "LOG_PANTALLA:";
	public static final String IP_PLAYER 		= "IP_PLAYER:";
	public static final String PORT_PLAYER 		= "PORT_PLAYER:";
	public static final String ONLINE_PLAYER 	= "ONLINE_PLAYER:";
	public static final String BRAZO_HORIZ_ID 	= "BRAZO_HORIZ_ID:";
	public static final String BRAZO_VERT_ID 	= "BRAZO_VERT_ID:";
	public static final String BRAZO_MANO_ID 	= "BRAZO_MANO_ID:";
	public static final String BRAZO_HORIZ_MIN 	= "BRAZO_HORIZ_MIN:";
	public static final String BRAZO_HORIZ_MAX 	= "BRAZO_HORIZ_MAX:";
	public static final String BRAZO_VERT_MIN 	= "BRAZO_VERT_MIN:";
	public static final String BRAZO_VERT_MAX 	= "BRAZO_VERT_MAX:";
	public static final String BRAZO_MANO_MIN 	= "BRAZO_MANO_MIN:";
	public static final String BRAZO_MANO_MAX 	= "BRAZO_MANO_MAX:";
	public static final String BRAZO_STEP 		= "BRAZO_STEP:";
	
	// BOTONES DEL JOYSTICK, PRESIONADOS JUNTOS SE SUMAN
	public static final int BOTON_1 = 1;
	public static final int BOTON_2 = 2;
	public static final int BOTON_3 = 4;
	public static final int BOTON_4 = 8;
	public static final int BOTON_5 = 16;
	public static final int BOTON_6 = 32;
	public static final int BOTON_7 = 64;
	public static final int BOTON_8 = 128;
	public static final int BOTON_9 = 256;
	public static final int BOTON_10 = 512;	
	
	// CONSTANTES DEL BRAZO, configuraciones por defecto.
	public static final int SERVO_HORIZONTAL_ARRAY			= 0;
	public static final int SERVO_VERTICAL_ARRAY			= 1;
	public static final int SERVO_MANO_ARRAY				= 2;
	
	// CONSTANTES DE LA CAMARA
	public static final int CAMARA_PRINCIPAL =1;
	public static final int CAMARA_BRAZO     =0;
}
