package dot7.madrugadas;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import dot7.madrugadas.auxiliar.AlarmReceiver;


public class ConfiguracionActivity extends AppCompatActivity {

    //Gráficos
    SeekBar seekbar;
    CheckBox checkN;
    Intent i;
    Button btnGuardar;
    private TextView guardada;
    TextView tam, Personas, textomod;
    AlertDialog  alertDialog;
    AlarmManager alarmManager;
    CheckBox alarmCheck;
    private TimePicker alarmTimePicker;
    //Variables
     int tamanoL, numeroleccion, vengoL, numeroleccion2, noche = 0;
    SharedPreferences prefe;
    SharedPreferences prefAlarma;
    SharedPreferences.Editor editor;
    String checado, orando, personasConfig;
    String progreso;
    private PendingIntent pendingIntent;
    private static ConfiguracionActivity inst;
    int checkbox, valorCheck, checkAlarma;


    public static ConfiguracionActivity instance() {
        return inst;
    }

    @Override
    public void onStart() {
        super.onStart();
        inst = this;
    }


    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.configuracion);


        checkN = (CheckBox) findViewById(R.id.checkNoche);
        prefe = getSharedPreferences("datos", Context.MODE_PRIVATE);
        checado = prefe.getString("checado", "");
        orando = prefe.getString("orando", "");

        btnGuardar = (Button) findViewById(R.id.btnguardarConfig);
        tam = (TextView) findViewById(R.id.tamano);
        Personas = (TextView) findViewById(R.id.txtPersonasConfig);
        seekbar = (SeekBar) findViewById(R.id.seekbar);
        textomod = (TextView) findViewById(R.id.textmod);

        Personas.setText(orando);

        if (checado.equals("true")) {
            checkN.setChecked(true);
            textomod.setBackgroundColor(Color.rgb(0, 0, 0));
            textomod.setTextColor(Color.rgb(206, 203, 203));
        } else {
            checkN.setChecked(false);
            textomod.setBackgroundColor(Color.rgb(255, 255, 255));
            textomod.setTextColor(Color.rgb(0, 0, 0));
        }

        Bundle num = this.getIntent().getExtras();
        if (num != null) {
            vengoL = num.getInt("vengolecciones");
            numeroleccion = num.getInt("numeroleccion");
            numeroleccion2 = numeroleccion;

        }

        tamanoL = prefe.getInt("tamano", 16);
        seekbar.setProgress(prefe.getInt("tamano", tamanoL));
        textomod.setTextSize(tamanoL);
        tam.setText("Tama\u0148o de la letra " + tamanoL);
        seekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                tam.setText("Tama\u0148o de la letra " + seekbar.getProgress());
                textomod.setTextSize(progress);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });


    }

    public void guardarConfig(View v) {

        personasConfig = Personas.getText().toString();

        if (personasConfig.equals("")) {
            personasConfig = "";
        }

        prefe = getSharedPreferences("datos", Context.MODE_PRIVATE);
        editor = prefe.edit();
        editor.putInt("tamano", seekbar.getProgress());
        editor.commit();
        i = new Intent(ConfiguracionActivity.this, ContenidoActivity.class);

        if (checkN.isChecked()) {
            noche = 1;
            editor.putString("checado", "true");
            editor.putInt("noche", noche);
        } else {
            noche = 0;
            editor.putString("checado", "false");
            editor.putInt("noche", noche);
        }
        editor.putString("orando", personasConfig);
        editor.commit();

        i.putExtra("numeroleccion", numeroleccion2);
        startActivity(i);
        finish();
        Toast.makeText(getApplicationContext(),
                "Guardando configuraciones...",
                Toast.LENGTH_SHORT).show();
    }


    public void check(View v) {
        if (checkN.isChecked()) {
            textomod.setBackgroundColor(Color.rgb(0, 0, 0));
            textomod.setTextColor(Color.rgb(206, 203, 203));


        } else {
            textomod.setBackgroundColor(Color.rgb(255, 255, 255));
            textomod.setTextColor(Color.rgb(0, 0, 0));

        }
    }

    ///alarma

    public void btnClickNotificaciones(View view) {
        LayoutInflater inflater = getLayoutInflater();
        View layoutOrando = inflater.inflate(R.layout.notificaciones, null);
        // etPersona1 = (EditText) layoutOrando.findViewById(R.id.et_Persona1);

        alarmTimePicker = (TimePicker) layoutOrando.findViewById(R.id.alarmTimePicker);

        alarmCheck = (CheckBox) layoutOrando.findViewById(R.id.alarmCheck);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        guardada = (TextView) layoutOrando.findViewById(R.id.txtGuardada);


        alarmTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            public void onTimeChanged(TimePicker arg0, int arg1, int arg2) {
                guardada.setText(alarmTimePicker.getCurrentHour() + ":" + alarmTimePicker.getCurrentMinute());
            }
        });

       prefAlarma = getSharedPreferences("datos", Context.MODE_PRIVATE);

        if (prefAlarma != null)

        {
            checkAlarma = prefAlarma.getInt("check", 0);
            if (checkAlarma == 1) {
                alarmCheck.setChecked(true);
                checkAlarma = 1;
            } else {
                checkAlarma = 0;
            }
            String hora = (String.valueOf(prefAlarma.getInt("hour", 0)));
            String minutos = (String.valueOf(prefAlarma.getInt("minute", 00)));
            guardada.setText(hora + ":" + minutos);
        }



        alarmCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkAlarma == 0) {
                    checkAlarma = 1;
                    guardada.setText(alarmTimePicker.getCurrentHour() + ":" + alarmTimePicker.getCurrentMinute());
                    alarmCheck.setChecked(true);
                    Toast toast = Toast.makeText(ConfiguracionActivity.this, "ON..", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();

                } else {
                    checkAlarma = 0;
                    guardada.setText("");
                    guardada.setHint("Active el alarma");
                    Toast toast = Toast.makeText(ConfiguracionActivity.this, "OFF..", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();
                }
            }
        });


        alertDialog = new AlertDialog.Builder(ConfiguracionActivity.this)
                .setView(layoutOrando).create();
        alertDialog.setCancelable(false);
        alertDialog.show();

    }

    public void btnClickNotificacionGuardar(View v) {

        SharedPreferences preferencias=getSharedPreferences("datos",Context.MODE_PRIVATE);
        editor=preferencias.edit();

        if (alarmCheck.isChecked()) {
            valorCheck=1;
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY,alarmTimePicker.getCurrentHour());
            calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());
            guardada.setText(alarmTimePicker.getCurrentHour()+":"+alarmTimePicker.getCurrentMinute());
            Intent myIntent = new Intent(ConfiguracionActivity.this, AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(ConfiguracionActivity.this, 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
            editor.putInt("hour", alarmTimePicker.getCurrentHour());
            editor.putInt("minute", alarmTimePicker.getCurrentMinute());
        } else {
            valorCheck=0;
            guardada.setText("");
            guardada.setHint("Active el alarma");

            Intent intent = new Intent(ConfiguracionActivity.this, AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(ConfiguracionActivity.this, 0, intent, 0);
            alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            alarmManager.cancel(pendingIntent);
            editor.putInt("hour", 0);
            editor.putInt("minute", 00);
        }

        editor.putInt("check",valorCheck);
        editor.commit();

        if(alertDialog!=null){
            alertDialog.dismiss();
        }

        Intent i = new Intent(ConfiguracionActivity.this, ConfiguracionActivity.class);
        i.putExtra("numeroleccion", numeroleccion2);
        startActivity(i);
        finish();

    }



    @Override
    public void onBackPressed()
    {

        new AlertDialog.Builder(ConfiguracionActivity.this)
                .setMessage("¿Estás seguro de salir SIN GUARDAR tus cambios?")
                .setPositiveButton("GUARDAR", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        personasConfig = Personas.getText().toString();

                        if (personasConfig.equals("")) {
                            personasConfig = "";
                        }

                        prefe = getSharedPreferences("datos", Context.MODE_PRIVATE);
                        editor = prefe.edit();
                        editor.putInt("tamano", seekbar.getProgress());
                        editor.commit();
                        i = new Intent(ConfiguracionActivity.this, ContenidoActivity.class);

                        if (checkN.isChecked()) {
                            noche = 1;
                            editor.putString("checado", "true");
                            editor.putInt("noche", noche);
                        } else {
                            noche = 0;
                            editor.putString("checado", "false");
                            editor.putInt("noche", noche);
                        }
                        editor.putString("orando", personasConfig);
                        editor.commit();
                        i.putExtra("numeroleccion", numeroleccion2);
                        if(alertDialog!=null){
                            alertDialog.dismiss();
                        }

                        startActivity(i);
                        finish();
                        Toast.makeText(getApplicationContext(),
                                "Guardando configuraciones...",
                                Toast.LENGTH_SHORT).show();



                    }
                })
                .setNegativeButton("SALIR SIN GUARDAR", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        i = new Intent(ConfiguracionActivity.this, ContenidoActivity.class);
                        i.putExtra("numeroleccion", numeroleccion2);
                        startActivity(i);
                        finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}//class