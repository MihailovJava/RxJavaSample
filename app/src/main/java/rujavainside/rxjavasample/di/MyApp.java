package rujavainside.rxjavasample.di;

import android.app.Application;

import rujavainside.rxjavasample.di.components.AppComponent;
import rujavainside.rxjavasample.di.components.DaggerAppComponent;
import rujavainside.rxjavasample.di.modules.MainActivityModule;
import rujavainside.rxjavasample.di.modules.ModelModule;
import rujavainside.rxjavasample.di.modules.PresenterModule;

/**
 * Created by Nixy on 26.03.2016.
 */
public class MyApp extends Application {
   static AppComponent appComponent;


    public static AppComponent component(){
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent
                .builder()
                .mainActivityModule(new MainActivityModule())
                .modelModule(new ModelModule())
                .presenterModule(new PresenterModule())
                .build();
    }
}
