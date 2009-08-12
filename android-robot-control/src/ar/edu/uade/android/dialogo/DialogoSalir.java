package ar.edu.uade.android.dialogo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import ar.edu.uade.android.R;

public class DialogoSalir
    extends AlertDialog.Builder
{

    public DialogoSalir( Context context, final Activity activity )
    {
        super( context );
        this.setTitle( R.string.salir );
        this.setMessage( R.string.salirMensaje );
        this.setIcon( R.drawable.ask );
        this.setPositiveButton( R.string.general_aceptar, new DialogInterface.OnClickListener()
        {
            public void onClick( DialogInterface dialog, int which )
            {
                activity.finish();
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
}
