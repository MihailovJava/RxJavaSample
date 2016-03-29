package rujavainside.rxjavasample.model;

import javax.inject.Inject;
import javax.inject.Named;

import rujavainside.rxjavasample.di.MyApp;
import rujavainside.rxjavasample.rest.GitHubApi;
import rujavainside.rxjavasample.rest.User;
import rx.Observable;
import rx.Scheduler;

/**
 * Created by Nixy on 26.03.2016.
 */
public class Model {

    private Observable.Transformer schedulersTransformer;

    @Inject
    GitHubApi api;

    @Inject
    @Named("UI_THREAD")
    Scheduler uiThread;

    @Inject
    @Named("IO_THREAD")
    Scheduler ioThread;

    public Model() {
        MyApp.component().inject(this);
        schedulersTransformer = (Observable o) ->  o.subscribeOn(ioThread)
                .observeOn(uiThread)
                .unsubscribeOn(ioThread);
    }

    public Observable<User> getUser(String userName){
        return api.user(userName).compose(schedulersTransformer);
    }

    @SuppressWarnings("unchecked")
    private <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }
}
