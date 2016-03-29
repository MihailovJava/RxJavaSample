package rujavainside.rxjavasample.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rujavainside.rxjavasample.model.Model;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Nixy on 26.03.2016.
 */
@Module
public class PresenterModule {

    @Provides
    @Singleton
    Model provideModel() {
        return new Model();
    }

    @Provides
    CompositeSubscription provideCompositeSubscription() {
        return new CompositeSubscription();
    }
}
