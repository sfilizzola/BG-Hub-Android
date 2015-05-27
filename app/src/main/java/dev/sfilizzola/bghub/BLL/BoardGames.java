package dev.sfilizzola.boardgamehub.BLL;

import dev.sfilizzola.boardgamehub.Entidades.BoardGame;

import java.util.List;

/**
 * Created by samuel.filizzola on 10/04/2014.
 */
public class BoardGames implements IDisposable {

    private dev.sfilizzola.boardgamehub.DAL.BoardGames oBoardGamesDAL;

    public BoardGames()
    {
        oBoardGamesDAL = new dev.sfilizzola.boardgamehub.DAL.BoardGames();
    }

    public List<BoardGame> Busca (String pBusca){
        return oBoardGamesDAL.Busca(pBusca);
    }

    public BoardGame CarregaJogo (String pIDJogo) { return oBoardGamesDAL.CarregaJogoXML(pIDJogo);}

    public void AdicionaJogo(BoardGame param) {
    }

    @Override
    public void Dispose() {
        if(oBoardGamesDAL != null)
            oBoardGamesDAL = null;
    }


}
