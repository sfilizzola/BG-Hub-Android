package dev.sfilizzola.bghub.DAL;

import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import dev.sfilizzola.bghub.Entidades.BoardGame;
import dev.sfilizzola.bghub.Entidades.HotItem;
import dev.sfilizzola.bghub.Entidades.SearchResult;

/**
 * Created by samuel.filizzola on 10/04/2014.
 */
public class BoardGames extends WebResources{
    private String TAG = "DAL BOARDGAMES";
    private String URL_BASE = "http://www.boardgamegeek.com/xmlapi2/";

    public List<SearchResult> Busca (String pBusca){
        List<SearchResult> oBusca  =  new ArrayList<SearchResult>();
        BoardXMLParser xml = new BoardXMLParser();
        String URL = URL_BASE + "search?type=boardgame,boardgameexpansion&query=" + pBusca.replace(" ", "%20");

        try{
            InputStream vStream  = downloadUrl(URL);
            oBusca = xml.parseBusca(vStream);
        } catch (XmlPullParserException e) {
            Log.e(TAG, e.getMessage() + "Parser");
        } catch (IOException e) {
            Log.e(TAG, e.getMessage() + "IO");
        }
        return oBusca;
    }

    public BoardGame CarregaJogoXML (String pIDjogo){
        BoardGame oRetVal = new BoardGame();
        BoardXMLParser xml = new BoardXMLParser();
        String URL = URL_BASE + "thing?type=boardgame&id=" + pIDjogo;

        try {
            InputStream vStream = downloadUrl(URL);
            oRetVal = xml.parseJogo(vStream);
        }catch (XmlPullParserException e) {
            Log.e(TAG, e.getMessage() + "Parser");
        } catch (IOException e) {
            Log.e(TAG, e.getMessage() + "IO");
        }
        return oRetVal;
    }

    public List<HotItem> Top50() {
        List<HotItem> oRetVal = new ArrayList<HotItem>();
        BoardXMLParser xml = new BoardXMLParser();
        String URL = URL_BASE + "hot?type=boardgame";

        try {
            InputStream vStream = downloadUrl(URL);
            oRetVal = xml.parseHOT(vStream);
        }catch (XmlPullParserException e) {
            Log.e(TAG, e.getMessage() + "Parser");
        } catch (IOException e) {
            Log.e(TAG, e.getMessage() + "IO");
        }
        return oRetVal;
    }
}
