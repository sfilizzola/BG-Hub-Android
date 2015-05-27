package dev.sfilizzola.bghub.Entidades;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samuel.filizzola on 10/04/2014.
 */
public class BoardGame {

    private String ID;
    private String name;
    private String yearpublished;
    private String minplayers;
    private String maxplayers;
    private String playingtime;
    private String age;
    private String description;
    private String thumbnail;
    private String image;
    private List<String> boardgamehonor;
    private List<String> boardgamepublisher;
    private List<String> boardgamemechanic;
    private List<String> boardgameversion;
    private List<String> boardgamepodcastepisode;
    private List<String> boardgamesubdomain;
    private List<String> boardgamefamily;
    private List<String> boardgamecategory;
    private List<String> boardgameexpansion;
    private List<String> boardgameartist;

    public BoardGame() {
        boardgamehonor = new ArrayList<String>();
        boardgamepublisher = new ArrayList<String>();
        boardgamemechanic = new ArrayList<String>();
        boardgameversion = new ArrayList<String>();
        boardgamepodcastepisode = new ArrayList<String>();
        boardgamesubdomain = new ArrayList<String>();
        boardgamefamily = new ArrayList<String>();
        boardgamecategory = new ArrayList<String>();
        boardgameartist = new ArrayList<String>();
        boardgameexpansion = new ArrayList<String>();
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYearpublished() {
        return yearpublished;
    }

    public void setYearpublished(String yearpublished) {
        this.yearpublished = yearpublished;
    }

    public String getMinplayers() {
        return minplayers;
    }

    public void setMinplayers(String minplayers) {
        this.minplayers = minplayers;
    }

    public String getMaxplayers() {
        return maxplayers;
    }

    public void setMaxplayers(String maxplayers) {
        this.maxplayers = maxplayers;
    }

    public String getPlayingtime() {
        return playingtime;
    }

    public void setPlayingtime(String playingtime) {
        this.playingtime = playingtime;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getBoardgamehonor() {
        return boardgamehonor;
    }

    public void setBoardgamehonor(List<String> boardgamehonor) {
        this.boardgamehonor = boardgamehonor;
    }

    public List<String> getBoardgamepublisher() {
        return boardgamepublisher;
    }

    public void setBoardgamepublisher(List<String> boardgamepublisher) {
        this.boardgamepublisher = boardgamepublisher;
    }

    public List<String> getBoardgamemechanic() {
        return boardgamemechanic;
    }

    public void setBoardgamemechanic(List<String> boardgamemechanic) {
        this.boardgamemechanic = boardgamemechanic;
    }

    public List<String> getBoardgameversion() {
        return boardgameversion;
    }

    public void setBoardgameversion(List<String> boardgameversion) {
        this.boardgameversion = boardgameversion;
    }

    public List<String> getBoardgamepodcastepisode() {
        return boardgamepodcastepisode;
    }

    public void setBoardgamepodcastepisode(List<String> boardgamepodcastepisode) {
        this.boardgamepodcastepisode = boardgamepodcastepisode;
    }

    public List<String> getBoardgamesubdomain() {
        return boardgamesubdomain;
    }

    public void setBoardgamesubdomain(List<String> boardgamesubdomain) {
        this.boardgamesubdomain = boardgamesubdomain;
    }

    public List<String> getBoardgamefamily() {
        return boardgamefamily;
    }

    public void setBoardgamefamily(List<String> boardgamefamily) {
        this.boardgamefamily = boardgamefamily;
    }

    public List<String> getBoardgamecategory() {
        return boardgamecategory;
    }

    public void setBoardgamecategory(List<String> boardgamecategory) {
        this.boardgamecategory = boardgamecategory;
    }

    public List<String> getBoardgameexpansion() {
        return boardgameexpansion;
    }

    public void setBoardgameexpansion(List<String> boardgameexpansion) {
        this.boardgameexpansion = boardgameexpansion;
    }

    public List<String> getBoardgameartist() {
        return boardgameartist;
    }

    public void setBoardgameartist(List<String> boardgameartist) {
        this.boardgameartist = boardgameartist;
    }

    public void addBoardgamehonor (String item){
        boardgamehonor.add(item);
    }
    public void addBoardgamepublisher (String item){
        boardgamepublisher.add(item);
    }
    public void addBoardgamemechanic (String item){
        boardgamemechanic.add(item);
    }
    public void addBoardgameversion (String item){
        boardgameversion.add(item);
    }
    public void addBoardgamepodcastepisode (String item){
        boardgamepodcastepisode.add(item);
    }
    public void addBoardgamesubdomain (String item){
        boardgamesubdomain.add(item);
    }
    public void addBoardgamefamily (String item){
        boardgamefamily.add(item);
    }
    public void addBoardgamecategory (String item){
        boardgamecategory.add(item);
    }
    public void addBoardgameexpansion (String item){
        boardgameexpansion.add(item);
    }
    public void addBoardgameartist (String item){
        boardgameartist.add(item);
    }


}
