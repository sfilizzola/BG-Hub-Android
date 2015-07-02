package dev.sfilizzola.bghub.BLL;


import android.util.Log;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

import dev.sfilizzola.bghub.Entidades.BoardGame;
import dev.sfilizzola.bghub.Entidades.CollectionItem;
import dev.sfilizzola.bghub.Entidades.HotItem;
import dev.sfilizzola.bghub.Entidades.SearchResult;

/**
 * Created by samuel.filizzola on 10/04/2014.
 */
public class BoardGames implements IDisposable {

    private dev.sfilizzola.bghub.DAL.BoardGames oBoardGamesDAL;

    private ParseObject oCurrentGame;

    private ParseUser oCurrentUser;

    private Boolean RetValueForAddGame;

    private Boolean RetValueForWannaPlay;

    private String LOG_TAG = BoardGames.class.getSimpleName();

    public BoardGames() {
        oBoardGamesDAL = new dev.sfilizzola.bghub.DAL.BoardGames();
    }

    public List<SearchResult> Busca(String pBusca) {
        return oBoardGamesDAL.Busca(pBusca);
    }

    public BoardGame CarregaJogo(String pIDJogo) {
        BoardGame JogoCarregado = oBoardGamesDAL.CarregaJogoXML(pIDJogo);

        VerificaExistenciaDojogo(JogoCarregado);

        return JogoCarregado;
    }
    public List<HotItem> Top50 (){
        return oBoardGamesDAL.Top50();
    }

    public boolean  AdicionaJogoColecao(BoardGame Jogo){

        final ParseUser usuario = ParseUser.getCurrentUser();

        ParseQuery<ParseObject> queryGame = ParseQuery.getQuery("Games");
        queryGame.whereEqualTo("IdBGG", Jogo.getID());
        queryGame.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(final ParseObject oJogoParse, ParseException e) {
                //jogo vai sempre existir pois para chegar nessa tela ele foi adicionado necessáriamente

                ParseQuery<ParseObject> queryGU = ParseQuery.getQuery("Games_User");
                queryGU.whereEqualTo("thisUser", usuario);
                queryGU.whereEqualTo("thisGame", oJogoParse);
                queryGU.getFirstInBackground(new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject RelatedObject, ParseException e) {
                        if ( RelatedObject == null) //não existe nenhum relacionamento entre usuário e jogo em questao
                        {
                            ParseObject relationship = new ParseObject("Games_User");
                            relationship.put("thisUser", usuario);
                            relationship.put("thisGame", oJogoParse);
                            relationship.put("haveIt", true);
                            relationship.put("wannaPlay", false);
                            relationship.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    setRetValueForAddGame(true);
                                }
                            });
                        }
                        else // existe obejto na base do usuário
                        {
                            RelatedObject.put("haveIt", true);
                            RelatedObject.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    setRetValueForAddGame(true);
                                }
                            });
                        }
                    }
                });
            }
        });
        return true;
    }

    public boolean AdicionaQueroJogar(BoardGame Jogo) {

        final ParseUser usuario = ParseUser.getCurrentUser();

        ParseQuery<ParseObject> queryGame = ParseQuery.getQuery("Games");
        queryGame.whereEqualTo("IdBGG", Jogo.getID());
        queryGame.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(final ParseObject oJogoParse, ParseException e) {
                //jogo vai sempre existir pois para chegar nessa tela ele foi adicionado necessáriamente
                Log.d(LOG_TAG, "Jogo Recuperado: " +  oJogoParse.get("GameName"));

                ParseQuery<ParseObject> queryGU = ParseQuery.getQuery("Games_User");
                queryGU.whereEqualTo("thisUser", usuario);
                queryGU.whereEqualTo("thisGame", oJogoParse);
                queryGU.getFirstInBackground(new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject RelatedObject, ParseException e) {

                        Log.d(LOG_TAG, "Related: " +  RelatedObject);

                        if ( RelatedObject == null) //não existe nenhum relacionamento entre usuário e jogo em questao
                        {
                            ParseObject relationship = new ParseObject("Games_User");
                            relationship.put("thisUser", usuario);
                            relationship.put("thisGame", oJogoParse);
                            relationship.put("haveIt", false);
                            relationship.put("wannaPlay", true);
                            relationship.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {

                                    Log.d(LOG_TAG, "Relação Salva.");
                                    setRetValueForWannaPlay(true);

                                    if (e != null){
                                        Log.d(LOG_TAG, "Relação Erro: ." + e.getMessage());
                                    }
                                }
                            });
                        }
                        else // existe obejto na base do usuário
                        {
                            RelatedObject.put("wannaPlay", true);
                            RelatedObject.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    Log.d(LOG_TAG, "Relação Salva 2.");
                                    setRetValueForWannaPlay(true);
                                }
                            });
                        }
                    }
                });
            }
        });
        return true;
    }

    @Override
    public void Dispose() {
        if (oBoardGamesDAL != null)
            oBoardGamesDAL = null;
    }

    private void VerificaExistenciaDojogo(final BoardGame jogoCarregado) {

        ParseQuery<ParseObject> queryGame = ParseQuery.getQuery("Games");
        queryGame.whereEqualTo("IdBGG", jogoCarregado.getID());
        queryGame.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject oGameObject, ParseException e) {
                if (oGameObject == null) {
                    //Create new game on Parse Base
                    final ParseObject oGameObjectInsert = new ParseObject("Games");
                    oGameObjectInsert.put("IdBGG", jogoCarregado.getID());
                    oGameObjectInsert.put("GameName", jogoCarregado.getName());
                    oGameObjectInsert.put("PhotoUri", jogoCarregado.getImage());
                    oGameObjectInsert.put("ThumbUri", jogoCarregado.getThumbnail());
                    oGameObjectInsert.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            //worked like a charm
                            if (e == null){
                                Log.d(LOG_TAG, "Jogo inserido: " +  oGameObjectInsert.getObjectId());
                            } else {
                                //holy hell what happened ?
                                Log.d(LOG_TAG, "Problema a criar novo jogo na base: " + e.getMessage());
                            }
                        }
                    });
                    Log.d(LOG_TAG, "Teoricamente jogo não exisita, foi criado um novo na base.");
                } else {
                    Log.d(LOG_TAG, "Jogo já existia, não foi criado.");
                }
            }
        });
    }

    private List<CollectionItem> PreencheGameItens(List<ParseObject> list) {
        return null;
    }

    public void setRetValueForAddGame(Boolean retValueForAddGame) {
        RetValueForAddGame = retValueForAddGame;
    }

    public void setRetValueForWannaPlay(Boolean retValueForWannaPlay) {
        RetValueForWannaPlay = retValueForWannaPlay;
    }

}
