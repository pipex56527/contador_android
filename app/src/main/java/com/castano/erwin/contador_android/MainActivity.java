package com.castano.erwin.contador_android;


        import android.app.Activity;
        import android.os.Bundle;
        import android.view.KeyEvent;
        import android.view.View;
        import android.view.inputmethod.EditorInfo;
        import android.view.inputmethod.InputMethodManager;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.EditText;
        import android.widget.TextView;


public class MainActivity extends Activity {

    public int contador;
    public TextView contadorTextView;
    public boolean acercaDe=false;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contadorTextView = (TextView) findViewById(R.id.contadorTextView);
        contador = 0;

        EventoTeclado teclado = new EventoTeclado();
        EditText valorReseteaEditText = (EditText) findViewById(R.id.valorReseteaEditText);

        //valorReseteaEditText.setOnEditorActionListener(teclado); //escuchar el teclado

    }

    //estos dos metodos permiten que la información del contador persista aunque la actividad se reinicie (por ejemplo al girar el dispositivo)
    public void onSaveInstanceState(Bundle estado){

        estado.putInt("cuenta", contador);
        super.onSaveInstanceState(estado);

    }

    public void onRestoreInstanceState(Bundle estado){

        super.onRestoreInstanceState(estado);
        contador=estado.getInt("cuenta");
        contadorTextView.setText(""+contador);


    }

    class EventoTeclado implements TextView.OnEditorActionListener{ //clase interna para que al presionar ok en el teclado, se resetee el contador

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

            if(actionId== EditorInfo.IME_ACTION_DONE){ //presionar ok en el teclado
                limpiarContador(v); //no se requiere pasar la vista como paramatro
            }

            return false;
        }
    }

    public void aumentarContador(View vista){
        contador++;
        //mostrarResultado();
        contadorTextView.setText(Integer.toString(contador));

    }

    public void disminuirContador(View vista){
        contador--;
        //mostrarResultado();

        if(contador<0){
            CheckBox permitenegCheckBox = (CheckBox) findViewById(R.id.permitenegCheckBox);
            if( ! permitenegCheckBox.isChecked()){
                contador=0;
            }

        }

        contadorTextView.setText(Integer.toString(contador));
    }

    public void limpiarContador(View vista){
        //contador=0;
        //mostrarResultado();
        EditText valorReseteaEditText = (EditText) findViewById(R.id.valorReseteaEditText);
        String textoIngresado = valorReseteaEditText.getText().toString();

        if(!textoIngresado.equals("") && !textoIngresado.equals(null)){
            contador=Integer.parseInt(textoIngresado);
            if(contador<0){
                CheckBox permitenegCheckBox = (CheckBox) findViewById(R.id.permitenegCheckBox);
                if( ! permitenegCheckBox.isChecked()) {
                    contador = 0;
                }
            }
        } else {
            contador=0;
        }

        valorReseteaEditText.setText("");
        contadorTextView.setText(Integer.toString(contador));

        InputMethodManager miteclado = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        miteclado.hideSoftInputFromWindow(vista.getWindowToken(),0); //ocultar teclado
    }

    public void acercaDe(View vista){
        Button botonAcercaDe = (Button) findViewById(R.id.acercadeButton);
        if(!acercaDe) {
            botonAcercaDe.setText("Esta es mi primera aplicación en Android");
            acercaDe=true;
        } else {
            botonAcercaDe.setText("Acerca de");
            acercaDe=false;
        }

    }

/*    public void mostrarResultado(){

        TextView cajaTexto = (TextView) findViewById(R.id.contadorTextView);
        cajaTexto.setText(Integer.toString(contador));

    }*/


}
