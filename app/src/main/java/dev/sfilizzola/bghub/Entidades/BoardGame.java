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
    private List<BoardGameLink> links;

    public BoardGame() {
       links = new ArrayList<BoardGameLink>();
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

    public List<BoardGameLink> getLinks() {
        return links;
    }

    public void setLinks(List<BoardGameLink> links) {
        this.links = links;
    }

    public void addBoardgameLink (BoardGameLink item){
        links.add(item);
    }


}
