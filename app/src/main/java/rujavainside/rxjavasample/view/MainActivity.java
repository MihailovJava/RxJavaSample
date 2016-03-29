package rujavainside.rxjavasample.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import rujavainside.rxjavasample.R;
import rujavainside.rxjavasample.di.MyApp;
import rujavainside.rxjavasample.presenter.GitHubApiPresenter;
import rujavainside.rxjavasample.rest.User;


public class MainActivity extends AppCompatActivity implements GitHubApiView {

    @Inject
    GitHubApiPresenter presenter;

    @Bind(R.id.name)
    TextView name;

    @Bind(R.id.email)
    TextView email;

    @Bind(R.id.avatar)
    ImageView avatarImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyApp.component().inject(this);
        ButterKnife.bind(this);
        presenter.attach(this);
    }



    @Override
    public void showUser(User user) {
        name.setText("Name: " + user.getName());
        email.setText("E-mail : " + user.getEmail());
        Picasso.with(this)
                .load(user.getAvatarUrl())
                .into(avatarImageView);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }
}
