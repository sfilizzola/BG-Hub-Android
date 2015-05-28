package dev.sfilizzola.bghub.Entidades;

/**
 * Created by samuel.filizzola on 27/05/2015.
 */
public class HotItem {

    public String id;
    public int rank;
    public String thumbnail;
    public String name;
    public String yearpublished;

    public HotItem() {
    }

    public HotItem(String id, int rank, String thumbnail, String name, String yearpublished) {
        this.id = id;
        this.rank = rank;
        this.thumbnail = thumbnail;
        this.name = name;
        this.yearpublished = yearpublished;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getThumbnail() {
        return "http:"+thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
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
}
