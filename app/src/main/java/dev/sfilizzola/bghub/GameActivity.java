package dev.sfilizzola.bghub;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.List;

import dev.sfilizzola.bghub.BLL.BoardGames;
import dev.sfilizzola.bghub.Entidades.BoardGame;
import dev.sfilizzola.bghub.Entidades.BoardGameLink;

public class GameActivity extends BaseActivity {

    //variaveis do layout
    private View mProgressView;
    private View mGameFormView;
    private RelativeLayout mGameHeader;
    private TextView mGameDiscription;
    private TextView mGameNumPlayers;
    private TextView mGameArtists;
    private TextView mGamePublishers;
    private TextView mGameDesigners;
    private TextView mGamePlayingTime;
    private TextView mGameTitle;
    private ImageView mGameCover;
    private ImageView mGameThumb;

    private FloatingActionButton mAddGameCollection;
    private FloatingActionButton mAddGameWannaPlay;

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
        mGameTitle = (TextView)findViewById(R.id.game_title);
        mGameDiscription = (TextView)findViewById(R.id.game_description);
        mGameCover = (ImageView) findViewById(R.id.game_cover);
        mGameThumb = (ImageView) findViewById(R.id.game_cover_thumb);

        mGameNumPlayers = (TextView) findViewById(R.id.game_text_number_of_players);
        mGameArtists = (TextView) findViewById(R.id.game_text_artists);
        mGamePublishers = (TextView) findViewById(R.id.game_text_publishers);
        mGameDesigners = (TextView) findViewById(R.id.game_text_designers);
        mGamePlayingTime = (TextView) findViewById(R.id.game_text_playing_time);

        mAddGameCollection = (FloatingActionButton) findViewById(R.id.game_add_collection);
        mAddGameWannaPlay = (FloatingActionButton) findViewById(R.id.game_add_wannaPlay);

        IDJogoSelecionado = getIntent().getStringExtra("IDBGG");

        //açoes dos botoes
        mAddGameCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Adiciona coleção" + IDJogoSelecionado, Toast.LENGTH_SHORT).show();
            }
        });

        mAddGameWannaPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Adiciona quero Jogar" + IDJogoSelecionado, Toast.LENGTH_SHORT).show();
            }
        });

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
        mGameTitle.setText(JogoSelecionado.getName());
        mGameDiscription.setText(Html.fromHtml(JogoSelecionado.getDescription()));

        mGameNumPlayers.setText(getResources().getString(R.string.game_detail_number_of_players_string, JogoSelecionado.getMinplayers(), JogoSelecionado.getMaxplayers()));
        mGameArtists.setText(GenerateStringFromLinks(JogoSelecionado.getLinks(), "boardgameartist"));
        mGamePublishers.setText(GenerateStringFromLinks(JogoSelecionado.getLinks(), "boardgamepublisher"));
        mGameDesigners.setText(GenerateStringFromLinks(JogoSelecionado.getLinks(), "boardgamedesigner"));
        mGamePlayingTime.setText(getResources().getString(R.string.game_detail_playing_time_string, JogoSelecionado.getPlayingtime()));
        /*
        CATEGORIAS EXISTENTES
        type="boardgamecategory"
        type="boardgamemechanic"
        type="boardgamefamily"
        type="boardgameexpansion"
        type="boardgamedesigner"
        type="boardgameartist"
        type="boardgamepublisher"
         */

    }

    private String GenerateStringFromLinks(List<BoardGameLink> links, String category)
    {
        String retorno = "";
        for (BoardGameLink link : links){
            if (link.getType().equals(category)){
                if (retorno.isEmpty())
                    retorno = link.getName();
                else
                    retorno = retorno + ", " + link.getName();
            }
        }
        if (retorno.isEmpty())
            retorno = getResources().getString(R.string.game_detail_not_available);
        return retorno;
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
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
               finish();
                return true;
            case R.id.menu_search:
                Intent intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                return true;
        }
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
