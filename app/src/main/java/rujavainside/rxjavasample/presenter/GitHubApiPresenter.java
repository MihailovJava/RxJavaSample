package rujavainside.rxjavasample.presenter;

import javax.inject.Inject;

import rujavainside.rxjavasample.di.MyApp;
import rujavainside.rxjavasample.model.Model;
import rujavainside.rxjavasample.rest.User;
import rujavainside.rxjavasample.view.GitHubApiView;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Nixy on 26.03.2016.
 */
public class GitHubApiPresenter implements Presenter {

    @Inject
    Model model;

    @Inject
    CompositeSubscription subscription;

    GitHubApiView view;

    User cache;

    public GitHubApiPresenter() {
        MyApp.component().inject(this);
    }

    public void attach(GitHubApiView view) {
        this.view = view;
        if (cache != null) {
            view.showUser(cache);
        } else {
            loadUser("MihailovJava");
        }
    }

    public void loadUser(String userName) {

        Subscription subscribe = model.getUser(userName)
                .subscribe(user -> {
                    cache = user;
                    view.showUser(user);
                });

        subscription.add(subscribe);

    }

    @Override
    public void onStop() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
