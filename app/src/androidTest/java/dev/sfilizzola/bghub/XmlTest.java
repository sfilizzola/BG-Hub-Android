package dev.sfilizzola.bghub;

import junit.framework.TestCase;

import java.util.List;

import dev.sfilizzola.bghub.DAL.BoardGames;
import dev.sfilizzola.bghub.Entidades.SearchResult;

/**
 * Created by Samuel on 27/05/2015.
 */
public class XmlTest extends TestCase {

    protected String busca;

    @Override
    protected void setUp() throws Exception {
        busca = "pandemic";
    }

    public void searchTest(){
        BoardGames DAL = new BoardGames();
        List<SearchResult> result = DAL.Busca(busca);
        assertTrue(result != null && result.size() != 0);
    }

    @Override
    protected void runTest() throws Throwable {
        searchTest();
    }
}
