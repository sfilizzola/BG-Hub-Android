package dev.sfilizzola.bghub.Entidades;

/**
 * Created by Samuel on 26/05/2015.
 */
public class SearchResult {
    private String type;
    private String ID;
    private String name;
    private String yearpublished;

    public SearchResult() {
    }

    public SearchResult(String type, String ID, String name, String yearpublished) {
        this.type = type;
        this.ID = ID;
        this.name = name;
        this.yearpublished = yearpublished;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
