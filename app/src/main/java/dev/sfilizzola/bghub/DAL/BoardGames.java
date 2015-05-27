package dev.sfilizzola.bghub.DAL;

import android.util.Log;

import dev.sfilizzola.bghub.Entidades.BoardGame;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by samuel.filizzola on 10/04/2014.
 */
public class BoardGames extends WebResources{
    private String TAG = "DAL BOARDGAMES";

    public List<BoardGame> Busca (String pBusca){
        List<BoardGame> oBusca  =  new ArrayList<BoardGame>();
        BoardXMLParser xml = new BoardXMLParser();
        String URL = "http://www.boardgamegeek.com/xmlapi/search?search=" + pBusca.replace(" ", "%20");

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
        String URL = "http://www.boardgamegeek.com/xmlapi/boardgame/" + pIDjogo;

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
}
