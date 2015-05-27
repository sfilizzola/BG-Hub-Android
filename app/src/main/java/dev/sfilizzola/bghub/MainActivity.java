package dev.sfilizzola.bghub;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.Parse;
import com.parse.ParseInstallation;

import java.util.ArrayList;
import java.util.List;

import dev.sfilizzola.bghub.BLL.BoardGames;
import dev.sfilizzola.bghub.Entidades.BoardGame;
import dev.sfilizzola.bghub.Entidades.HotItem;
import dev.sfilizzola.bghub.Entidades.SearchResult;


public class MainActivity extends ActionBarActivity {

    private List<SearchResult> Resultados;
    private List<HotItem> Hots;
    private BoardGame Jogo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Resultados = new ArrayList<SearchResult>();
        Jogo = new BoardGame();
        Hots = new ArrayList<HotItem>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Parse.initialize(this, "OOjZ2IorAoNKn0MF5sxlH7vVXQhvX4E9rTcWOx2I", "WrjsMoDWEX66tu6QY85BRcCitVW3SYqkkTTsSCbw");
        ParseInstallation.getCurrentInstallation().saveInBackground();

        TestTask mTask = new TestTask();
        mTask.execute();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public class TestTask  extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... params) {
            BoardGames BLL = new BoardGames();
            //Resultados = BLL.Busca("pandemic");
            //Jogo = BLL.CarregaJogo("120677");
            Hots = BLL.Top50();
            BLL.Dispose();
            return null;
        }
    }

}
