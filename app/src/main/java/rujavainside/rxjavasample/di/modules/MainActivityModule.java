package rujavainside.rxjavasample.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rujavainside.rxjavasample.presenter.GitHubApiPresenter;

/**
 * Created by Nixy on 26.03.2016.
 */
@Module
public class MainActivityModule {

    @Provides
    @Singleton
    GitHubApiPresenter providePresenter(){
        return new GitHubApiPresenter();
    }
}
