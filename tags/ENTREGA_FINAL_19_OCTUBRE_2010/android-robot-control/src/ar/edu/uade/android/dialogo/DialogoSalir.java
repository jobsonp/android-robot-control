package ar.edu.uade.android.dialogo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import ar.edu.uade.android.R;

public class DialogoSalir
    extends AlertDialog.Builder
{

    public DialogoSalir( Context context, final Activity activity, boolean solicitarConfirmacion )
    {
        super( context );
        if ( solicitarConfirmacion == false )
        {
            desconectar( activity );
            return;
        }

        this.setTitle( R.string.menu_desconectar_dialogo_titulo );
        this.setMessage( R.string.menu_desconectar_dialogo_descripcion );
        this.setIcon( R.drawable.dialogo_pregunta );
        this.setPositiveButton( R.string.general_aceptar, new DialogInterface.OnClickListener()
        {
            public void onClick( DialogInterface dialog, int which )
            {
                desconectar( activity );
            }
        } );
        this.setNegativeButton( R.string.general_cancelar, new DialogInterface.OnClickListener()
        {
            public void onClick( DialogInterface dialog, int which )
            {
                dialog.dismiss();
            }
        } );
        this.show();
    }

    /**
     * EN ESTE METODO TIENE QUE IR TODA LA LOGICA DE DESCONECCION o POR LO MENOS LLAMARSE
     */
    public void desconectar( Activity activity )
    {
        activity.finish();
    }

}
