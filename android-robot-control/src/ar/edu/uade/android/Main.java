package ar.edu.uade.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main
    extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   
        setContentView(R.layout.main);
        Button button = (Button)this.findViewById(R.id.ButtonConectar);

        button.setOnClickListener(new Button.OnClickListener() {
            public void onClick (View v){
                System.out.println( "blaa le pegue al boton de conectar" );
            }

        });
        
        Button button2 = (Button)this.findViewById(R.id.ButtonCancelar);

        button2.setOnClickListener(new Button.OnClickListener() {
            public void onClick (View v){
                System.out.println( "blaa le pegue al boton de cancelar" );
            }

        });
    }
    
    
    
}