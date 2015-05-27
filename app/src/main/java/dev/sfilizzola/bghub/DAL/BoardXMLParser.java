package dev.sfilizzola.bghub.DAL;

import android.util.Xml;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import dev.sfilizzola.bghub.Entidades.BoardGame;
import dev.sfilizzola.bghub.Entidades.SearchResult;

/**
 * Created by samuel.filizzola on 10/04/2014.
 */
public class BoardXMLParser {

    private static final String ns = null;
    private String TAG = "XMLPARSER";

    public List<SearchResult> parseBusca(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readBusca(parser);
        } finally {
            in.close();
        }
    }

    private List<SearchResult> readBusca(XmlPullParser parser) throws XmlPullParserException, IOException {

        List<SearchResult> jogos = new ArrayList<SearchResult>();

        parser.require(XmlPullParser.START_TAG, ns, "itens");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("item")){
                jogos.add(readBuscaBoardGame(parser));
            } else {
                skip(parser);
            }
        }
        return jogos;
    }

    public BoardGame parseJogo(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readJogo(parser);
        } finally {
            in.close();
        }
    }

    private BoardGame readJogo(XmlPullParser parser) throws XmlPullParserException, IOException {

        BoardGame jogo = new BoardGame();

        parser.require(XmlPullParser.START_TAG, ns, "boardgames");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("boardgame")){
                jogo = readBoardGame(parser);
            } else {
                skip(parser);
            }
        }
        return jogo;



    }


    private SearchResult readBuscaBoardGame(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "item");
        SearchResult oRetGame = new SearchResult();
        oRetGame.setID(parser.getAttributeValue(1));
        oRetGame.setType(parser.getAttributeValue(0));
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            String name = parser.getName();
            if (name.equals("name")){
                oRetGame.setName(parser.getAttributeValue(1));
            } else if (name.equals("yearpublished")){
                oRetGame.setYearpublished(parser.getAttributeValue(0));
            } else {
                skip(parser);
            }
        }
        return oRetGame;
    }

    private BoardGame readBoardGame(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "boardgame");
        BoardGame oRetGame = new BoardGame();
        oRetGame.setID(parser.getAttributeValue(0));
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("name") && parser.getAttributeValue(0).equals("true")){
                oRetGame.setName(readItem(parser, "name"));
            } else if (name.equals("yearpublished")){
                oRetGame.setYearpublished(readItem(parser, "yearpublished"));
            } else if (name.equals("minplayers")){
                oRetGame.setMinplayers(readItem(parser, "minplayers"));
            } else if (name.equals("maxplayers")){
                oRetGame.setMaxplayers(readItem(parser, "maxplayers"));
            } else if (name.equals("playingtime")){
                oRetGame.setPlayingtime(readItem(parser, "playingtime"));
            } else if (name.equals("age")){
                oRetGame.setAge(readItem(parser, "age"));
            } else if (name.equals("description")){
                oRetGame.setDescription(readItem(parser, "description"));
            } else if (name.equals("thumbnail")){
                oRetGame.setThumbnail(readItem(parser, "thumbnail"));
            } else if (name.equals("image")){
                oRetGame.setImage(readItem(parser, "image"));
            } else if (name.equals("boardgamepublisher")){
                oRetGame.addBoardgamepublisher(readItem(parser, "boardgamepublisher"));
            } else if (name.equals("boardgamehonor")){
                oRetGame.addBoardgamehonor(readItem(parser, "boardgamehonor"));
            } else if (name.equals("boardgamefamily")){
                oRetGame.addBoardgamefamily(readItem(parser, "boardgamefamily"));
            } else if (name.equals("boardgamepodcastepisode")){
                oRetGame.addBoardgamepodcastepisode(readItem(parser, "boardgamepodcastepisode"));
            } else if (name.equals("boardgameversion")){
                oRetGame.addBoardgameversion(readItem(parser, "boardgameversion"));
            } else if (name.equals("boardgamecategory")){
                oRetGame.addBoardgamecategory(readItem(parser, "boardgamecategory"));
            } else if (name.equals("boardgamemechanic")){
                oRetGame.addBoardgamemechanic(readItem(parser, "boardgamemechanic"));
            } else if (name.equals("boardgameexpansion")){
                oRetGame.addBoardgameexpansion(readItem(parser, "boardgameexpansion"));
            } else if (name.equals("boardgameartist")){
                oRetGame.addBoardgameartist(readItem(parser, "boardgameartist"));
            } else {
                skip(parser);
            }
        }
        return oRetGame;
    }

    private String readItem(XmlPullParser parser, String pTagNAme) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, pTagNAme);
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, pTagNAme);
        return title;
    }

    // For the tags title and summary, extracts their text values.
    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }


}
