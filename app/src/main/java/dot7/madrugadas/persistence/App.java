package dot7.madrugadas.persistence;

import android.app.Application;

import io.realm.Realm;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }

}
