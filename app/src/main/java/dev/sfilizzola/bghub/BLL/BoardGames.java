package dev.sfilizzola.bghub.BLL;


import java.util.List;

import dev.sfilizzola.bghub.Entidades.BoardGame;
import dev.sfilizzola.bghub.Entidades.HotItem;
import dev.sfilizzola.bghub.Entidades.SearchResult;

/**
 * Created by samuel.filizzola on 10/04/2014.
 */
public class BoardGames implements IDisposable {

    private dev.sfilizzola.bghub.DAL.BoardGames oBoardGamesDAL;

    public BoardGames() {
        oBoardGamesDAL = new dev.sfilizzola.bghub.DAL.BoardGames();
    }

    public List<SearchResult> Busca(String pBusca) {
        return oBoardGamesDAL.Busca(pBusca);
    }

    public BoardGame CarregaJogo(String pIDJogo) {
        return oBoardGamesDAL.CarregaJogoXML(pIDJogo);
    }

    public List<HotItem> Top50 (){
        return oBoardGamesDAL.Top50();
    }

    public boolean  AdicionaJogoColecao(String IDBGGJogo){
          return true;
    }



    @Override
    public void Dispose() {
        if (oBoardGamesDAL != null)
            oBoardGamesDAL = null;
    }


}
