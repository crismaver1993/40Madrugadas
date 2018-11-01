package dot7.madrugadas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import dot7.madrugadas.auxiliar.ResaltarEstructuraBD;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by cmarin on 05/03/2016.
 */
public class ContenidoActivity extends Activity {

    private NestedScrollView scrol;


    private FloatingActionsMenu multiple_actions;
    TextView txtTitulo, txtTexto, txtOrandoPor, txtPersonas;
    RelativeLayout relativeL;
    SharedPreferences prefe;
    Intent i;
    private Realm mRealm;
    Spannable spannableString;

    int startScroll, endScroll;
    String orando;
    int numLeccion, numLeccion2;
    int start, end;
    int nl, ini, fi;
    int noche, tamanoL;
    String etComentario;
    int colorMarcar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ContenidoActivity.this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ContenidoActivity.this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.contenido);


        //REALM
        Realm.init(ContenidoActivity.this);
        mRealm = Realm.getDefaultInstance();

        txtTitulo = findViewById(R.id.txtTitulo);
        txtTexto = findViewById(R.id.txtTexto);
        txtOrandoPor = findViewById(R.id.txtOrandoPor);
        txtPersonas = findViewById(R.id.txtPersonas);
        scrol = findViewById(R.id.scrol);

        FloatingActionButton fabRemoveMark = findViewById(R.id.fabRemoveMark);
        FloatingActionButton fabMark = findViewById(R.id.fabMark);
        multiple_actions = findViewById(R.id.multiple_actions);
        Bundle num = ContenidoActivity.this.getIntent().getExtras();
        if (num != null) {
            numLeccion = num.getInt("numeroleccion");
        }
        numLeccion2 = numLeccion;
        relativeL = findViewById(R.id.relativeL);

        prefe = getSharedPreferences("datos", Context.MODE_PRIVATE);
        noche = prefe.getInt("noche", 0);
        orando = prefe.getString("orando", "");

        multiple_actions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                multiple_actions.expand();
            }
        });


        if (noche == 1) {
            colorMarcar = 0xCCCCCCCC;
            relativeL.setBackgroundColor(Color.rgb(0, 0, 0));
            txtTitulo.setTextColor(Color.rgb(237, 154, 29));
            txtTexto.setTextColor(Color.rgb(206, 203, 203));
            txtOrandoPor.setTextColor(Color.rgb(237, 154, 29));
            txtPersonas.setTextColor(Color.rgb(200, 203, 203));

        } else {
            colorMarcar = 0xFFFFFF00;
            relativeL.setBackgroundColor(Color.rgb(255, 255, 255));
            txtTitulo.setTextColor(Color.rgb(237, 154, 29));
            txtTexto.setTextColor(Color.rgb(0, 0, 0));
            txtOrandoPor.setTextColor(Color.rgb(237, 154, 29));
            txtPersonas.setTextColor(Color.rgb(0, 0, 0));
        }

        prefe = getSharedPreferences("datos", Context.MODE_PRIVATE);
        tamanoL = prefe.getInt("tamano", 16);
        //tgen.setTextSize(tamanoL);
        txtTexto.setTextSize(tamanoL);
        txtPersonas.setText(orando);

        switch (numLeccion) {
            //1-10
            case 1:
                txtTitulo.setText(R.string.titulo1);
                txtTexto.setText(R.string.dia1);
                spannableString = new SpannableString(getString(R.string.dia1));
                break;

            case 2:
                txtTitulo.setText(R.string.titulo2);
                txtTexto.setText(R.string.dia2);
                spannableString = new SpannableString(getString(R.string.dia2));
                break;

            case 3:
                txtTitulo.setText(R.string.titulo3);
                txtTexto.setText(R.string.dia3);
                spannableString = new SpannableString(getString(R.string.dia3));
                break;

            case 4:
                txtTitulo.setText(R.string.titulo4);
                txtTexto.setText(R.string.dia4);
                spannableString = new SpannableString(getString(R.string.dia4));
                break;

            case 5:
                txtTitulo.setText(R.string.titulo5);
                txtTexto.setText(R.string.dia5);
                spannableString = new SpannableString(getString(R.string.dia5));
                break;

            case 6:
                txtTitulo.setText(R.string.titulo6);
                txtTexto.setText(R.string.dia6);
                spannableString = new SpannableString(getString(R.string.dia6));
                break;

            case 7:
                txtTitulo.setText(R.string.titulo7);
                txtTexto.setText(R.string.dia7);
                spannableString = new SpannableString(getString(R.string.dia7));
                break;

            case 8:
                txtTitulo.setText(R.string.titulo8);
                txtTexto.setText(R.string.dia8);
                spannableString = new SpannableString(getString(R.string.dia8));
                break;

            case 9:
                txtTitulo.setText(R.string.titulo9);
                txtTexto.setText(R.string.dia9);
                spannableString = new SpannableString(getString(R.string.dia9));
                break;

            case 10:
                txtTitulo.setText(R.string.titulo10);
                txtTexto.setText(R.string.dia10);
                spannableString = new SpannableString(getString(R.string.dia10));
                break;
            //11-20
            case 11:
                txtTitulo.setText(R.string.titulo11);
                txtTexto.setText(R.string.dia11);
                spannableString = new SpannableString(getString(R.string.dia11));
                break;

            case 12:
                txtTitulo.setText(R.string.titulo12);
                txtTexto.setText(R.string.dia12);
                spannableString = new SpannableString(getString(R.string.dia12));
                break;

            case 13:
                txtTitulo.setText(R.string.titulo13);
                txtTexto.setText(R.string.dia13);
                spannableString = new SpannableString(getString(R.string.dia13));
                break;

            case 14:
                txtTitulo.setText(R.string.titulo14);
                txtTexto.setText(R.string.dia14);
                spannableString = new SpannableString(getString(R.string.dia14));
                break;

            case 15:
                txtTitulo.setText(R.string.titulo15);
                txtTexto.setText(R.string.dia15);
                spannableString = new SpannableString(getString(R.string.dia15));
                break;

            case 16:
                txtTitulo.setText(R.string.titulo16);
                txtTexto.setText(R.string.dia16);
                spannableString = new SpannableString(getString(R.string.dia16));
                break;

            case 17:
                txtTitulo.setText(R.string.titulo17);
                txtTexto.setText(R.string.dia17);
                spannableString = new SpannableString(getString(R.string.dia17));
                break;

            case 18:
                txtTitulo.setText(R.string.titulo18);
                txtTexto.setText(R.string.dia18);
                spannableString = new SpannableString(getString(R.string.dia18));
                break;

            case 19:
                txtTitulo.setText(R.string.titulo19);
                txtTexto.setText(R.string.dia19);
                spannableString = new SpannableString(getString(R.string.dia19));
                break;

            case 20:
                txtTitulo.setText(R.string.titulo20);
                txtTexto.setText(R.string.dia20);
                spannableString = new SpannableString(getString(R.string.dia20));
                break;

            //21-30
            case 21:
                txtTitulo.setText(R.string.titulo21);
                txtTexto.setText(R.string.dia21);
                spannableString = new SpannableString(getString(R.string.dia21));
                break;

            case 22:
                txtTitulo.setText(R.string.titulo22);
                txtTexto.setText(R.string.dia22);
                spannableString = new SpannableString(getString(R.string.dia22));
                break;

            case 23:
                txtTitulo.setText(R.string.titulo23);
                txtTexto.setText(R.string.dia23);
                spannableString = new SpannableString(getString(R.string.dia23));
                break;

            case 24:
                txtTitulo.setText(R.string.titulo24);
                txtTexto.setText(R.string.dia24);
                spannableString = new SpannableString(getString(R.string.dia24));
                break;

            case 25:
                txtTitulo.setText(R.string.titulo25);
                txtTexto.setText(R.string.dia25);
                spannableString = new SpannableString(getString(R.string.dia25));
                break;

            case 26:
                txtTitulo.setText(R.string.titulo26);
                txtTexto.setText(R.string.dia26);
                spannableString = new SpannableString(getString(R.string.dia26));
                break;

            case 27:
                txtTitulo.setText(R.string.titulo27);
                txtTexto.setText(R.string.dia27);
                spannableString = new SpannableString(getString(R.string.dia27));
                break;

            case 28:
                txtTitulo.setText(R.string.titulo28);
                txtTexto.setText(R.string.dia28);
                spannableString = new SpannableString(getString(R.string.dia28));
                break;

            case 29:
                txtTitulo.setText(R.string.titulo29);
                txtTexto.setText(R.string.dia29);
                spannableString = new SpannableString(getString(R.string.dia29));
                break;

            case 30:
                txtTitulo.setText(R.string.titulo30);
                txtTexto.setText(R.string.dia30);
                spannableString = new SpannableString(getString(R.string.dia30));
                break;
            //31-40
            case 31:
                txtTitulo.setText(R.string.titulo31);
                txtTexto.setText(R.string.dia31);
                spannableString = new SpannableString(getString(R.string.dia31));
                break;

            case 32:
                txtTitulo.setText(R.string.titulo32);
                txtTexto.setText(R.string.dia32);
                spannableString = new SpannableString(getString(R.string.dia32));
                break;

            case 33:
                txtTitulo.setText(R.string.titulo33);
                txtTexto.setText(R.string.dia33);
                spannableString = new SpannableString(getString(R.string.dia33));
                break;

            case 34:
                txtTitulo.setText(R.string.titulo34);
                txtTexto.setText(R.string.dia34);
                spannableString = new SpannableString(getString(R.string.dia34));
                break;

            case 35:
                txtTitulo.setText(R.string.titulo35);
                txtTexto.setText(R.string.dia35);
                spannableString = new SpannableString(getString(R.string.dia35));
                break;

            case 36:
                txtTitulo.setText(R.string.titulo36);
                txtTexto.setText(R.string.dia36);
                spannableString = new SpannableString(getString(R.string.dia36));
                break;

            case 37:
                txtTitulo.setText(R.string.titulo37);
                txtTexto.setText(R.string.dia37);
                spannableString = new SpannableString(getString(R.string.dia37));
                break;

            case 38:
                txtTitulo.setText(R.string.titulo38);
                txtTexto.setText(R.string.dia38);
                spannableString = new SpannableString(getString(R.string.dia38));
                break;

            case 39:
                txtTitulo.setText(R.string.titulo39);
                txtTexto.setText(R.string.dia39);
                spannableString = new SpannableString(getString(R.string.dia39));
                break;

            case 40:
                txtTitulo.setText(R.string.titulo40);
                txtTexto.setText(R.string.dia40);
                spannableString = new SpannableString(getString(R.string.dia40));
                break;

            case 41:
                txtTitulo.setText(R.string.titulo41);
                txtTexto.setText(R.string.dia41);
                spannableString = new SpannableString(getString(R.string.dia41));
                break;
        }//switch

        findViewById(R.id.pink_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                i = new Intent(ContenidoActivity.this, ConfiguracionActivity.class);
                i.putExtra("numeroleccion", numLeccion2);
                startActivity(i);
                finish();
            }
        });


        final RealmResults<ResaltarEstructuraBD> results1 = mRealm.where(ResaltarEstructuraBD.class)
                .equalTo("numLeccion", numLeccion).findAll();
        for (ResaltarEstructuraBD w : results1) {
            nl = w.getNumLeccion();
            ini = w.getInicio();
            fi = w.getFin();


            if (ini >= 0 && fi >= 0) {
                spannableString.setSpan(new BackgroundColorSpan(colorMarcar), ini, fi, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                txtTexto.setText(spannableString);
            }
        }


        //REALM Fab-Mark
        fabMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start = txtTexto.getSelectionStart();
                end = txtTexto.getSelectionEnd();

                if (start > 0 && end > 0) {

                    spannableString = (Spannable) txtTexto.getText();
                    spannableString.setSpan(new BackgroundColorSpan(colorMarcar), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                    txtTexto.setFocusable(false);
                    txtTexto.setText(spannableString);
                    scrol.scrollTo(startScroll, endScroll);

                    mRealm.beginTransaction();
                    ResaltarEstructuraBD estructura = mRealm.createObject(ResaltarEstructuraBD.class);
                    estructura.setNumLeccion(numLeccion);
                    estructura.setInicio(start);
                    estructura.setFin(end);
                    mRealm.commitTransaction();
                }
            }
        });


        //REALM FabRemoveMark
        fabRemoveMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RealmResults<ResaltarEstructuraBD> results1 = mRealm.where(ResaltarEstructuraBD.class)
                        .equalTo("numLeccion", numLeccion).findAll();


                for (ResaltarEstructuraBD w : results1) {
                    nl = w.getNumLeccion();
                    ini = w.getInicio();
                    fi = w.getFin();
                }
                if (ini > 0 && fi > 0) {
                    spannableString.setSpan(new BackgroundColorSpan(Color.TRANSPARENT), ini, fi, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    txtTexto.setText(spannableString);
                }

                if (results1.size() > 0) {
                    mRealm.beginTransaction();
                    results1.deleteLastFromRealm();
                    mRealm.commitTransaction();

                }
            }
        });


        //Scroll
        scrol.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                multiple_actions.collapse();
                startScroll = scrollX;
                endScroll = scrollY;
                txtTexto.setFocusable(true);
                txtTexto.setFocusableInTouchMode(true);

            }
        });

    }


}
