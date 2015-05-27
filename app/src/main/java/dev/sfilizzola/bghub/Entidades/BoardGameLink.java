package dev.sfilizzola.bghub.Entidades;

/**
 * Created by samuel.filizzola on 27/05/2015.
 */
public class BoardGameLink {

    private String type;
    private String id;
    private String name;

    public BoardGameLink(String type, String id, String name) {
        this.type = type;
        this.id = id;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
