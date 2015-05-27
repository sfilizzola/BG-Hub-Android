package dev.sfilizzola.bghub.DAL;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import dev.sfilizzola.bghub.Entidades.BoardGame;
import dev.sfilizzola.bghub.Entidades.BoardGameLink;
import dev.sfilizzola.bghub.Entidades.HotItem;
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

    public List<HotItem> parseHOT(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readHOTs(parser);
        } finally {
            in.close();
        }
    }

    private List<HotItem> readHOTs(XmlPullParser parser) throws XmlPullParserException, IOException {

        List<HotItem> jogos = new ArrayList<HotItem>();

        parser.require(XmlPullParser.START_TAG, ns, "items");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("item")) {
                jogos.add(readHOTItem(parser));
            } else {
                skip(parser);
            }
        }
        return jogos;
    }

    private HotItem readHOTItem(XmlPullParser parser) throws XmlPullParserException, IOException {

        parser.require(XmlPullParser.START_TAG, ns, "item");
        HotItem oRetGame = new HotItem();
        oRetGame.setId(parser.getAttributeValue(0));
        oRetGame.setRank(Integer.parseInt(parser.getAttributeValue(1)));
        while (parser.next() != XmlPullParser.END_TAG) {

            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            String name = parser.getName();

            if (name.equals("name")) {
                oRetGame.setName(parser.getAttributeValue(0));
                parser.nextTag();
            } else if (name.equals("thumbnail")) {
                oRetGame.setThumbnail(parser.getAttributeValue(0));
                parser.nextTag();
            } else if (name.equals("yearpublished")) {
                oRetGame.setYearpublished(parser.getAttributeValue(0));
                parser.nextTag();
            } else {
                skip(parser);
            }
        }
        return oRetGame;

    }

    private List<SearchResult> readBusca(XmlPullParser parser) throws XmlPullParserException, IOException {

        List<SearchResult> jogos = new ArrayList<SearchResult>();

        parser.require(XmlPullParser.START_TAG, ns, "items");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("item")) {
                jogos.add(readBuscaBoardGame(parser));
            } else {
                skip(parser);
            }
        }
        return jogos;
    }

    private BoardGame readJogo(XmlPullParser parser) throws XmlPullParserException, IOException {

        BoardGame jogo = new BoardGame();

        parser.require(XmlPullParser.START_TAG, ns, "items");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("item")) {
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

            if (name.equals("name")) {
                oRetGame.setName(parser.getAttributeValue(1));
                parser.nextTag();
            } else if (name.equals("yearpublished")) {
                oRetGame.setYearpublished(parser.getAttributeValue(0));
                parser.nextTag();
            } else {
                skip(parser);
            }
        }
        return oRetGame;
    }

    private BoardGame readBoardGame(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "item");
        BoardGame oRetGame = new BoardGame();
        oRetGame.setID(parser.getAttributeValue(0));
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("name") && parser.getAttributeValue(0).equals("primary")) {
                oRetGame.setName(parser.getAttributeValue(2));
                parser.nextTag();
            } else if (name.equals("yearpublished")) {
                oRetGame.setYearpublished(parser.getAttributeValue(0));
                parser.nextTag();
            } else if (name.equals("minplayers")) {
                oRetGame.setMinplayers(parser.getAttributeValue(0));
                parser.nextTag();
            } else if (name.equals("maxplayers")) {
                oRetGame.setMaxplayers(parser.getAttributeValue(0));
                parser.nextTag();
            } else if (name.equals("playingtime")) {
                oRetGame.setPlayingtime(parser.getAttributeValue(0));
                parser.nextTag();
            } else if (name.equals("minage")) {
                oRetGame.setAge(parser.getAttributeValue(0));
                parser.nextTag();
            } else if (name.equals("description")) {
                oRetGame.setDescription(readItem(parser, "description"));
            } else if (name.equals("thumbnail")) {
                oRetGame.setThumbnail(readItem(parser, "thumbnail"));
            } else if (name.equals("image")) {
                oRetGame.setImage(readItem(parser, "image"));
            } else if (name.equals("link")) {
                oRetGame.addBoardgameLink(new BoardGameLink(parser.getAttributeValue(0), parser.getAttributeValue(1), parser.getAttributeValue(2)));
                parser.nextTag();
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
