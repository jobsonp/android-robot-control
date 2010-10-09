package ar.edu.uade.android.dialogo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import ar.edu.uade.android.R;

public class DialogoAlerta
    extends AlertDialog.Builder
{
    public DialogoAlerta( Context context, String mensaje )
    {
        super( context );
        this.setTitle( R.string.salir );
        this.setMessage( mensaje );
        this.setIcon( R.drawable.info );
        this.show();
        this.setNeutralButton( R.string.general_aceptar, new DialogInterface.OnClickListener()
        {
            public void onClick( DialogInterface dialog, int whichButton )
            {
            }
        } );
    }
}
