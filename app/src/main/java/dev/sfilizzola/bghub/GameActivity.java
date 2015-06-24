package dev.sfilizzola.bghub;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import dev.sfilizzola.bghub.BLL.BoardGames;
import dev.sfilizzola.bghub.Entidades.BoardGame;

public class GameActivity extends BaseActivity {

    //variaveis do layout
    private View mProgressView;
    private View mGameFormView;
    private RelativeLayout mGameHeader;
    private TextView mGameDiscription;
    private ImageView mGameCover;
    private ImageView mGameThumb;

    //variaveis da classe
    private BoardGame JogoSelecionado;
    private String IDJogoSelecionado;
    private String LOG_TAG = GameActivity.class.getSimpleName();
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        toolbar = activateToolbarWithHomeEnabled();


        mGameFormView = findViewById(R.id.game_full_layout);
        mProgressView = findViewById(R.id.game_progress);
        mGameHeader = (RelativeLayout)findViewById(R.id.game_header);
        mGameDiscription = (TextView)findViewById(R.id.game_description);
        mGameCover = (ImageView) findViewById(R.id.game_cover);
        mGameThumb = (ImageView) findViewById(R.id.game_cover_thumb);

        IDJogoSelecionado = getIntent().getStringExtra("IDBGG");

        showProgress(true);
        GameLoaderTask mTask = new GameLoaderTask();
        mTask.execute(IDJogoSelecionado);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    private void preencheDados() {

        CarregaImagens(JogoSelecionado.getImage(), JogoSelecionado.getThumbnail());
        toolbar.setTitle(JogoSelecionado.getName());
        mGameDiscription.setText(JogoSelecionado.getDescription());

    }

    private void CarregaImagens(String image, String thumb) {

        Log.w(LOG_TAG, "Carregando fundo: " + image);
        Log.w(LOG_TAG, "Carregando thumb: " + thumb);

        Picasso.with(getApplicationContext())
                .load(image)
                .fit().centerCrop()
                .into(mGameCover);

        Picasso.with(getApplicationContext())
                .load(thumb)
                .error(R.drawable.placeh)
                .placeholder(R.drawable.placeh)
                .into(mGameThumb);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mGameFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mGameFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mGameFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mGameFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    public class GameLoaderTask extends AsyncTask<String, Void, Void>{

        @Override
        protected Void doInBackground(String... params) {
            BoardGames BLL = new BoardGames();
            JogoSelecionado = BLL.CarregaJogo(IDJogoSelecionado);
            BLL.Dispose();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            preencheDados();
            showProgress(false);
        }
    }


}
