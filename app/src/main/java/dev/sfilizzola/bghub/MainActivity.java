package dev.sfilizzola.bghub;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseInstallation;

import java.io.InputStream;
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


    public void inicializa(){
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this, Hots));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(MainActivity.this, "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });
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

        @Override
        protected void onPostExecute(Void aVoid) {
            inicializa();
        }
    }

    public class ImageAdapter extends BaseAdapter {
        private Context mContext;
        private List<HotItem> itens;

        public ImageAdapter(Context c, List<HotItem> hots) {
            mContext = c;
            itens = hots;
        }

        public int getCount() {
            return itens.size();
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                imageView = new ImageView(mContext);
               // imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
               // imageView.setPadding(8, 8, 8, 8);
            } else {
                imageView = (ImageView) convertView;
            }
            new DownloadImageTask(imageView)
                    .execute(itens.get(position).getThumbnail());

            return imageView;
        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
