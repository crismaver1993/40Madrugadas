package dot7.madrugadas;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Calendar;

import at.markushi.ui.CircleButton;
import dot7.madrugadas.auxiliar.AlarmReceiver;
import dot7.madrugadas.auxiliar.AlarmService;
import info.hoang8f.widget.FButton;


public class MainActivity extends Activity implements View.OnClickListener {

    private CircleButton fab, fab1, fab2, fab3, fab4;
    Dialog alertDialog;
    SharedPreferences prefe;
    SharedPreferences.Editor editor;
    FButton btnGuardarOrando;

    RelativeLayout rl,rl2, rl3, rl4;//rl,
    RadioButton radio1, radio2, radio3, radio4, radio5, radio6, radio7, radio8, radio9, radio10;
    RadioButton radio11, radio12, radio13, radio14, radio15, radio16, radio17, radio18, radio19, radio20;
    RadioButton radio21, radio22, radio23, radio24, radio25, radio26, radio27, radio28, radio29, radio30;
    RadioButton radio31, radio32, radio33, radio34, radio35, radio36, radio37, radio38, radio39, radio40,radio41;
    String personas, orando;
    String checado1, checado2, checado3, checado4, checado5, checado6, checado7, checado8, checado9, checado10;
    String checado11, checado12, checado13, checado14, checado15, checado16, checado17, checado18, checado19, checado20;
    String checado21, checado22, checado23, checado24, checado25, checado26, checado27, checado28, checado29, checado30;
    String checado31, checado32, checado33, checado34, checado35, checado36, checado37, checado38, checado39, checado40,checado41;
    EditText etPersona1;

    int checkAlarma;
    private PendingIntent pendingIntent;
    AlarmManager alarmManager;
    int numleccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        AlarmService.cancelNotification(getApplicationContext(),1);


        SharedPreferences prefAlarma = getSharedPreferences("datos", Context.MODE_PRIVATE);

        if (prefAlarma != null)

        {
            checkAlarma = prefAlarma.getInt("check", 0);
            int hora = prefAlarma.getInt("hour", 0);
            int minutos = prefAlarma.getInt("minute", 0);

            if (checkAlarma == 1) {
                if (hora > 0 && minutos > 0) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.HOUR_OF_DAY, hora);
                    calendar.set(Calendar.MINUTE, minutos);
                    Intent myIntent = new Intent(MainActivity.this, AlarmReceiver.class);
                    alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                    pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
                }
            }else{
                Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
                pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);
                alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                alarmManager.cancel(pendingIntent);
            }

        }


        prefe = getSharedPreferences("datos", Context.MODE_PRIVATE);
        editor = prefe.edit();

        orando = prefe.getString("orando", "");
        //Quitamos barra de titulo de la aplicacion
        prefe = getSharedPreferences("datos", Context.MODE_PRIVATE);
        checado1 = prefe.getString("checado1", "");
        checado2 = prefe.getString("checado2", "");
        checado3 = prefe.getString("checado3", "");
        checado4 = prefe.getString("checado4", "");
        checado5 = prefe.getString("checado5", "");
        checado6 = prefe.getString("checado6", "");
        checado7 = prefe.getString("checado7", "");
        checado8 = prefe.getString("checado8", "");
        checado9 = prefe.getString("checado9", "");
        checado10 = prefe.getString("checado10", "");
        //11-20
        checado11 = prefe.getString("checado11", "");
        checado12 = prefe.getString("checado12", "");
        checado13 = prefe.getString("checado13", "");
        checado14 = prefe.getString("checado14", "");
        checado15 = prefe.getString("checado15", "");
        checado16 = prefe.getString("checado16", "");
        checado17 = prefe.getString("checado17", "");
        checado18 = prefe.getString("checado18", "");
        checado19 = prefe.getString("checado19", "");
        checado20 = prefe.getString("checado20", "");
        //21-30
        checado21 = prefe.getString("checado21", "");
        checado22 = prefe.getString("checado22", "");
        checado23 = prefe.getString("checado23", "");
        checado24 = prefe.getString("checado24", "");
        checado25 = prefe.getString("checado25", "");
        checado26 = prefe.getString("checado26", "");
        checado27 = prefe.getString("checado27", "");
        checado28 = prefe.getString("checado28", "");
        checado29 = prefe.getString("checado29", "");
        checado30 = prefe.getString("checado30", "");
        //31-41
        checado31 = prefe.getString("checado31", "");
        checado32 = prefe.getString("checado32", "");
        checado33 = prefe.getString("checado33", "");
        checado34 = prefe.getString("checado34", "");
        checado35 = prefe.getString("checado35", "");
        checado36 = prefe.getString("checado36", "");
        checado37 = prefe.getString("checado37", "");
        checado38 = prefe.getString("checado38", "");
        checado39 = prefe.getString("checado39", "");
        checado40 = prefe.getString("checado40", "");
        checado41 = prefe.getString("checado41", "");

        fab1 = (CircleButton) findViewById(R.id.fab1);
        fab2 = (CircleButton) findViewById(R.id.fab2);
        fab3 = (CircleButton) findViewById(R.id.fab3);
        fab4 = (CircleButton) findViewById(R.id.fab4);

        rl = (RelativeLayout) findViewById(R.id.grupo1);
        rl2 = (RelativeLayout) findViewById(R.id.grupo2);
        rl3 = (RelativeLayout) findViewById(R.id.grupo3);
        rl4 = (RelativeLayout) findViewById(R.id.grupo4);

        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
        fab3.setOnClickListener(this);
        fab4.setOnClickListener(this);

        //1-10
        radio1 = (RadioButton) findViewById(R.id.dia1);
        radio2 = (RadioButton) findViewById(R.id.dia2);
        radio3 = (RadioButton) findViewById(R.id.dia3);
        radio4 = (RadioButton) findViewById(R.id.dia4);
        radio5 = (RadioButton) findViewById(R.id.dia5);
        radio6 = (RadioButton) findViewById(R.id.dia6);
        radio7 = (RadioButton) findViewById(R.id.dia7);
        radio8 = (RadioButton) findViewById(R.id.dia8);
        radio9 = (RadioButton) findViewById(R.id.dia9);
        radio10 = (RadioButton) findViewById(R.id.dia10);
//11-20
        radio11 = (RadioButton) findViewById(R.id.dia11);
        radio12 = (RadioButton) findViewById(R.id.dia12);
        radio13 = (RadioButton) findViewById(R.id.dia13);
        radio14 = (RadioButton) findViewById(R.id.dia14);
        radio15 = (RadioButton) findViewById(R.id.dia15);
        radio16 = (RadioButton) findViewById(R.id.dia16);
        radio17 = (RadioButton) findViewById(R.id.dia17);
        radio18 = (RadioButton) findViewById(R.id.dia18);
        radio19 = (RadioButton) findViewById(R.id.dia19);
        radio20 = (RadioButton) findViewById(R.id.dia20);
//21-30
        radio21 = (RadioButton) findViewById(R.id.dia21);
        radio22 = (RadioButton) findViewById(R.id.dia22);
        radio23 = (RadioButton) findViewById(R.id.dia23);
        radio24 = (RadioButton) findViewById(R.id.dia24);
        radio25 = (RadioButton) findViewById(R.id.dia25);
        radio26 = (RadioButton) findViewById(R.id.dia26);
        radio27 = (RadioButton) findViewById(R.id.dia27);
        radio28 = (RadioButton) findViewById(R.id.dia28);
        radio29 = (RadioButton) findViewById(R.id.dia29);
        radio30 = (RadioButton) findViewById(R.id.dia30);
//31-40
        radio31 = (RadioButton) findViewById(R.id.dia31);
        radio32 = (RadioButton) findViewById(R.id.dia32);
        radio33 = (RadioButton) findViewById(R.id.dia33);
        radio34 = (RadioButton) findViewById(R.id.dia34);
        radio35 = (RadioButton) findViewById(R.id.dia35);
        radio36 = (RadioButton) findViewById(R.id.dia36);
        radio37 = (RadioButton) findViewById(R.id.dia37);
        radio38 = (RadioButton) findViewById(R.id.dia38);
        radio39 = (RadioButton) findViewById(R.id.dia39);
        radio40 = (RadioButton) findViewById(R.id.dia40);
        radio41 = (RadioButton) findViewById(R.id.dia41);

    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.fab1:

                if (orando.equals("")) {
                    LayoutInflater inflater = getLayoutInflater();
                    View layoutOrando = inflater.inflate(R.layout.orando, null);
                    etPersona1 = (EditText) layoutOrando.findViewById(R.id.et_Persona1);
                    btnGuardarOrando=(FButton)layoutOrando.findViewById(R.id.btnGuardarOrando);

                    alertDialog = new AlertDialog.Builder(MainActivity.this)
                            .setView(layoutOrando).create();
                    alertDialog.show();

                    etPersona1.requestFocus();
                    alertDialog.setCancelable(false);
                    final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);



                } else {

                    rl.setVisibility(View.VISIBLE);
                    rl2.setVisibility(View.GONE);
                    rl3.setVisibility(View.GONE);
                    rl4.setVisibility(View.GONE);


                    rl.setBackgroundResource(R.drawable.fondo1);
                    rl.setBackgroundResource(R.drawable.fondo1);

                    fab1.setVisibility(View.INVISIBLE);
                    fab2.setVisibility(View.VISIBLE);
                    fab3.setVisibility(View.VISIBLE);
                    fab4.setVisibility(View.VISIBLE);


                    if (checado1.equals("true")) {
                        radio1.setChecked(true);
                    }

                    if (checado2.equals("true")) {
                        radio2.setChecked(true);
                    }

                    if (checado3.equals("true")) {
                        radio3.setChecked(true);
                    }
                    if (checado4.equals("true")) {
                        radio4.setChecked(true);
                    }

                    if (checado5.equals("true")) {
                        radio5.setChecked(true);
                    }

                    if (checado6.equals("true")) {
                        radio6.setChecked(true);
                    }

                    if (checado7.equals("true")) {
                        radio7.setChecked(true);
                    }

                    if (checado8.equals("true")) {
                        radio8.setChecked(true);
                    }

                    if (checado9.equals("true")) {
                        radio9.setChecked(true);
                    }

                    if (checado10.equals("true")) {
                        radio10.setChecked(true);
                    }
                }


                break;

            case R.id.fab2:

                if (orando.equals("")) {

                    LayoutInflater inflater = getLayoutInflater();
                    View layoutOrando = inflater.inflate(R.layout.orando, null);
                    etPersona1 = (EditText) layoutOrando.findViewById(R.id.et_Persona1);
                    btnGuardarOrando=(FButton)layoutOrando.findViewById(R.id.btnGuardarOrando);

                    alertDialog = new AlertDialog.Builder(MainActivity.this)
                            .setView(layoutOrando).create();
                    alertDialog.show();

                    etPersona1.requestFocus();
                    alertDialog.setCancelable(false);
                    final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);



                } else {
                    rl.setVisibility(View.GONE);
                    rl2.setVisibility(View.VISIBLE);
                    rl3.setVisibility(View.GONE);
                    rl4.setVisibility(View.GONE);

                    rl2.setBackgroundResource(R.drawable.fondo2);

                    fab1.setVisibility(View.VISIBLE);
                    fab2.setVisibility(View.INVISIBLE);
                    fab3.setVisibility(View.VISIBLE);
                    fab4.setVisibility(View.VISIBLE);


                    if (checado11.equals("true")) {
                        radio11.setChecked(true);
                    }

                    if (checado12.equals("true")) {
                        radio12.setChecked(true);
                    }

                    if (checado13.equals("true")) {
                        radio13.setChecked(true);
                    }
                    if (checado14.equals("true")) {
                        radio14.setChecked(true);
                    }

                    if (checado15.equals("true")) {
                        radio15.setChecked(true);
                    }

                    if (checado16.equals("true")) {
                        radio16.setChecked(true);
                    }

                    if (checado17.equals("true")) {
                        radio17.setChecked(true);
                    }

                    if (checado18.equals("true")) {
                        radio18.setChecked(true);
                    }

                    if (checado19.equals("true")) {
                        radio19.setChecked(true);
                    }

                    if (checado20.equals("true")) {
                        radio20.setChecked(true);
                    }

                }

                break;
            case R.id.fab3:

                if (orando.equals("")) {

                    LayoutInflater inflater = getLayoutInflater();
                    View layoutOrando = inflater.inflate(R.layout.orando, null);
                    etPersona1 = (EditText) layoutOrando.findViewById(R.id.et_Persona1);
                    btnGuardarOrando=(FButton)layoutOrando.findViewById(R.id.btnGuardarOrando);

                    alertDialog = new AlertDialog.Builder(MainActivity.this)
                            .setView(layoutOrando).create();
                    alertDialog.show();

                    etPersona1.requestFocus();
                    alertDialog.setCancelable(false);
                    final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);

                } else {
                    rl.setVisibility(View.GONE);
                    rl2.setVisibility(View.GONE);
                    rl3.setVisibility(View.VISIBLE);
                    rl4.setVisibility(View.GONE);

                    rl3.setBackgroundResource(R.drawable.fondo3);

                    fab1.setVisibility(View.VISIBLE);
                    fab2.setVisibility(View.VISIBLE);
                    fab3.setVisibility(View.INVISIBLE);
                    fab4.setVisibility(View.VISIBLE);


                    if (checado21.equals("true")) {
                        radio21.setChecked(true);
                    }

                    if (checado22.equals("true")) {
                        radio22.setChecked(true);
                    }

                    if (checado23.equals("true")) {
                        radio23.setChecked(true);
                    }
                    if (checado24.equals("true")) {
                        radio24.setChecked(true);
                    }

                    if (checado25.equals("true")) {
                        radio25.setChecked(true);
                    }

                    if (checado26.equals("true")) {
                        radio26.setChecked(true);
                    }

                    if (checado27.equals("true")) {
                        radio27.setChecked(true);
                    }

                    if (checado28.equals("true")) {
                        radio28.setChecked(true);
                    }

                    if (checado29.equals("true")) {
                        radio29.setChecked(true);
                    }

                    if (checado30.equals("true")) {
                        radio30.setChecked(true);
                    }
                }
                break;


            case R.id.fab4:
                if (orando.equals("")) {

                    LayoutInflater inflater = getLayoutInflater();
                    View layoutOrando = inflater.inflate(R.layout.orando, null);
                    etPersona1 = (EditText) layoutOrando.findViewById(R.id.et_Persona1);
                    btnGuardarOrando=(FButton)layoutOrando.findViewById(R.id.btnGuardarOrando);

                   alertDialog = new AlertDialog.Builder(MainActivity.this)
                            .setView(layoutOrando).create();
                    alertDialog.show();

                    etPersona1.requestFocus();
                    alertDialog.setCancelable(false);
                    final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);

                } else {
                    rl.setVisibility(View.GONE);
                    rl2.setVisibility(View.GONE);
                    rl3.setVisibility(View.GONE);
                    rl4.setVisibility(View.VISIBLE);

                    rl4.setBackgroundResource(R.drawable.fondo4);

                    fab1.setVisibility(View.VISIBLE);
                    fab2.setVisibility(View.VISIBLE);
                    fab3.setVisibility(View.VISIBLE);
                    fab4.setVisibility(View.INVISIBLE);


                    if (checado31.equals("true")) {
                        radio31.setChecked(true);
                    }

                    if (checado32.equals("true")) {
                        radio32.setChecked(true);
                    }

                    if (checado33.equals("true")) {
                        radio33.setChecked(true);
                    }
                    if (checado34.equals("true")) {
                        radio34.setChecked(true);
                    }

                    if (checado35.equals("true")) {
                        radio35.setChecked(true);
                    }

                    if (checado36.equals("true")) {
                        radio36.setChecked(true);
                    }

                    if (checado37.equals("true")) {
                        radio37.setChecked(true);
                    }

                    if (checado38.equals("true")) {
                        radio38.setChecked(true);
                    }

                    if (checado39.equals("true")) {
                        radio39.setChecked(true);
                    }

                    if (checado40.equals("true")) {
                        radio40.setChecked(true);
                    }

                    if (checado41.equals("true")) {
                        radio41.setChecked(true);
                    }
                }
                break;
        }
    }

    //1-10
    public void click1(View v) {
        Intent i = new Intent(MainActivity.this, ContenidoActivity.class);
        i.putExtra("numeroleccion", 1);
        editor.putString("checado1", "true");
        editor.commit();
        startActivity(i);
    }

    public void click2(View v) {
        Intent i = new Intent(MainActivity.this, ContenidoActivity.class);
        i.putExtra("numeroleccion", 2);
        editor.putString("checado2", "true");
        editor.commit();
        startActivity(i);
    }

    public void click3(View v) {
        Intent i = new Intent(MainActivity.this, ContenidoActivity.class);
        i.putExtra("numeroleccion", 3);
        editor.putString("checado3", "true");
        editor.commit();
        startActivity(i);
    }

    public void click4(View v) {
        Intent i = new Intent(MainActivity.this, ContenidoActivity.class);
        i.putExtra("numeroleccion", 4);
        editor.putString("checado4", "true");
        editor.commit();
        startActivity(i);
    }

    public void click5(View v) {
        Intent i = new Intent(MainActivity.this, ContenidoActivity.class);
        i.putExtra("numeroleccion", 5);
        editor.putString("checado5", "true");
        editor.commit();
        startActivity(i);
    }

    public void click6(View v) {
        Intent i = new Intent(MainActivity.this, ContenidoActivity.class);
        i.putExtra("numeroleccion", 6);
        editor.putString("checado6", "true");
        editor.commit();
        startActivity(i);
    }

    public void click7(View v) {
        Intent i = new Intent(MainActivity.this, ContenidoActivity.class);
        i.putExtra("numeroleccion", 7);
        editor.putString("checado7", "true");
        editor.commit();
        startActivity(i);
    }

    public void click8(View v) {
        Intent i = new Intent(MainActivity.this, ContenidoActivity.class);
        i.putExtra("numeroleccion", 8);
        editor.putString("checado8", "true");
        editor.commit();
        startActivity(i);
    }

    public void click9(View v) {
        Intent i = new Intent(MainActivity.this, ContenidoActivity.class);
        i.putExtra("numeroleccion", 9);
        editor.putString("checado9", "true");
        editor.commit();
        startActivity(i);
    }


    public void click10(View v) {
        Intent i = new Intent(MainActivity.this, ContenidoActivity.class);
        i.putExtra("numeroleccion", 10);
        editor.putString("checado10", "true");
        editor.commit();
        startActivity(i);
    }

    //11-20
    public void click11(View v) {
        Intent i = new Intent(MainActivity.this, ContenidoActivity.class);
        i.putExtra("numeroleccion", 11);
        editor.putString("checado11", "true");
        editor.commit();
        startActivity(i);
    }

    public void click12(View v) {
        Intent i = new Intent(MainActivity.this, ContenidoActivity.class);
        i.putExtra("numeroleccion", 12);
        editor.putString("checado12", "true");
        editor.commit();
        startActivity(i);
    }

    public void click13(View v) {
        Intent i = new Intent(MainActivity.this, ContenidoActivity.class);
        i.putExtra("numeroleccion", 13);
        editor.putString("checado13", "true");
        editor.commit();
        startActivity(i);
    }

    public void click14(View v) {
        Intent i = new Intent(MainActivity.this, ContenidoActivity.class);
        i.putExtra("numeroleccion", 14);
        editor.putString("checado14", "true");
        editor.commit();
        startActivity(i);
    }

    public void click15(View v) {
        Intent i = new Intent(MainActivity.this, ContenidoActivity.class);
        i.putExtra("numeroleccion", 15);
        editor.putString("checado15", "true");
        editor.commit();
        startActivity(i);
    }

    public void click16(View v) {
        Intent i = new Intent(MainActivity.this, ContenidoActivity.class);
        i.putExtra("numeroleccion", 16);
        editor.putString("checado16", "true");
        editor.commit();
        startActivity(i);
    }

    public void click17(View v) {
        Intent i = new Intent(MainActivity.this, ContenidoActivity.class);
        i.putExtra("numeroleccion", 17);
        editor.putString("checado17", "true");
        editor.commit();
        startActivity(i);
    }

    public void click18(View v) {
        Intent i = new Intent(MainActivity.this, ContenidoActivity.class);
        i.putExtra("numeroleccion", 18);
        editor.putString("checado18", "true");
        editor.commit();
        startActivity(i);
    }

    public void click19(View v) {
        Intent i = new Intent(MainActivity.this, ContenidoActivity.class);
        i.putExtra("numeroleccion", 19);
        editor.putString("checado19", "true");
        editor.commit();
        startActivity(i);
    }


    public void click20(View v) {
        Intent i = new Intent(MainActivity.this, ContenidoActivity.class);
        i.putExtra("numeroleccion", 20);
        editor.putString("checado20", "true");
        editor.commit();
        startActivity(i);
    }

    //21-30
    public void click21(View v) {
        Intent i = new Intent(MainActivity.this, ContenidoActivity.class);
        i.putExtra("numeroleccion", 21);
        editor.putString("checado21", "true");
        editor.commit();
        startActivity(i);
    }

    public void click22(View v) {
        Intent i = new Intent(MainActivity.this, ContenidoActivity.class);
        i.putExtra("numeroleccion", 22);
        editor.putString("checado22", "true");
        editor.commit();
        startActivity(i);
    }

    public void click23(View v) {
        Intent i = new Intent(MainActivity.this, ContenidoActivity.class);
        i.putExtra("numeroleccion", 23);
        editor.putString("checado23", "true");
        editor.commit();
        startActivity(i);
    }

    public void click24(View v) {
        Intent i = new Intent(MainActivity.this, ContenidoActivity.class);
        i.putExtra("numeroleccion", 24);
        editor.putString("checado24", "true");
        editor.commit();
        startActivity(i);
    }

    public void click25(View v) {
        Intent i = new Intent(MainActivity.this, ContenidoActivity.class);
        i.putExtra("numeroleccion", 25);
        editor.putString("checado25", "true");
        editor.commit();
        startActivity(i);
    }

    public void click26(View v) {
        Intent i = new Intent(MainActivity.this, ContenidoActivity.class);
        i.putExtra("numeroleccion", 26);
        editor.putString("checado26", "true");
        editor.commit();
        startActivity(i);
    }

    public void click27(View v) {
        Intent i = new Intent(MainActivity.this, ContenidoActivity.class);
        i.putExtra("numeroleccion", 27);
        editor.putString("checado27", "true");
        editor.commit();
        startActivity(i);
    }

    public void click28(View v) {
        Intent i = new Intent(MainActivity.this, ContenidoActivity.class);
        i.putExtra("numeroleccion", 28);
        editor.putString("checado28", "true");
        editor.commit();
        startActivity(i);
    }

    public void click29(View v) {
        Intent i = new Intent(MainActivity.this, ContenidoActivity.class);
        i.putExtra("numeroleccion", 29);
        editor.putString("checado29", "true");
        editor.commit();
        startActivity(i);
    }


    public void click30(View v) {
        Intent i = new Intent(MainActivity.this, ContenidoActivity.class);
        i.putExtra("numeroleccion", 30);
        editor.putString("checado30", "true");
        editor.commit();
        startActivity(i);
    }


    //31-40
    public void click31(View v) {
        Intent i = new Intent(MainActivity.this, ContenidoActivity.class);
        i.putExtra("numeroleccion", 31);
        editor.putString("checado31", "true");
        editor.commit();
        startActivity(i);
    }

    public void click32(View v) {
        Intent i = new Intent(MainActivity.this, ContenidoActivity.class);
        i.putExtra("numeroleccion", 32);
        editor.putString("checado32", "true");
        editor.commit();
        startActivity(i);
    }

    public void click33(View v) {
        Intent i = new Intent(MainActivity.this, ContenidoActivity.class);
        i.putExtra("numeroleccion", 33);
        editor.putString("checado33", "true");
        editor.commit();
        startActivity(i);
    }

    public void click34(View v) {
        Intent i = new Intent(MainActivity.this, ContenidoActivity.class);
        i.putExtra("numeroleccion", 34);
        editor.putString("checado34", "true");
        editor.commit();
        startActivity(i);
    }

    public void click35(View v) {
        Intent i = new Intent(MainActivity.this, ContenidoActivity.class);
        i.putExtra("numeroleccion", 35);
        editor.putString("checado35", "true");
        editor.commit();
        startActivity(i);
    }

    public void click36(View v) {
        Intent i = new Intent(MainActivity.this, ContenidoActivity.class);
        i.putExtra("numeroleccion", 36);
        editor.putString("checado36", "true");
        editor.commit();
        startActivity(i);
    }

    public void click37(View v) {
        Intent i = new Intent(MainActivity.this, ContenidoActivity.class);
        i.putExtra("numeroleccion", 37);
        editor.putString("checado37", "true");
        editor.commit();
        startActivity(i);
    }

    public void click38(View v) {
        Intent i = new Intent(MainActivity.this, ContenidoActivity.class);
        i.putExtra("numeroleccion", 38);
        editor.putString("checado38", "true");
        editor.commit();
        startActivity(i);
    }

    public void click39(View v) {
        Intent i = new Intent(MainActivity.this, ContenidoActivity.class);
        i.putExtra("numeroleccion", 39);
        editor.putString("checado39", "true");
        editor.commit();
        startActivity(i);
    }


    public void click40(View v) {
        Intent i = new Intent(MainActivity.this, ContenidoActivity.class);
        i.putExtra("numeroleccion", 40);
        editor.putString("checado40", "true");
        editor.commit();
        startActivity(i);
    }

    public void click41(View v) {
        Intent i = new Intent(MainActivity.this, ContenidoActivity.class);
        i.putExtra("numeroleccion", 41);
        editor.putString("checado41", "true");
        editor.commit();
        startActivity(i);
    }

    public void btnClickOrandoGuardar(View v) {
        prefe = getSharedPreferences("datos", Context.MODE_PRIVATE);
        editor = prefe.edit();
        personas = String.valueOf(etPersona1.getText().toString());
        if (personas.equals("")) {
            Toast toast = Toast.makeText(this, "Agregue por lo menos a una persona", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
        } else {
            editor.putString("orando", personas);
            editor.commit();

            Intent i = new Intent(MainActivity.this, MainActivity.class);
            startActivity(i);
            finish();//agregue este
            Toast toast = Toast.makeText(this, "Puede editar el nombre de las personas en Configuraciones", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();

            final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(etPersona1.getWindowToken(),0);
            alertDialog.dismiss();
        }
    }//click

    @Override
    public void onBackPressed() {

    }


}
