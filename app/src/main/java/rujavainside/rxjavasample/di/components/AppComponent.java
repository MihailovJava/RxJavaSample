package rujavainside.rxjavasample.di.components;

import javax.inject.Singleton;

import dagger.Component;
import rujavainside.rxjavasample.di.modules.MainActivityModule;
import rujavainside.rxjavasample.di.modules.ModelModule;
import rujavainside.rxjavasample.di.modules.PresenterModule;
import rujavainside.rxjavasample.model.Model;
import rujavainside.rxjavasample.presenter.GitHubApiPresenter;
import rujavainside.rxjavasample.view.MainActivity;

/**
 * Created by Nixy on 26.03.2016.
 */
@Singleton
@Component(modules = {
        PresenterModule.class,
        ModelModule.class,
        MainActivityModule.class
})
public interface AppComponent {
    void inject(Model model);
    void inject(GitHubApiPresenter presenter);
    void inject(MainActivity mainActivity);

}
