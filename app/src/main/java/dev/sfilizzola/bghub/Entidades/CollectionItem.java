package dev.sfilizzola.bghub.Entidades;

/**
 * Created by samuel.filizzola on 02/07/2015.
 */
public class CollectionItem {

    private String Name;
    private String Image;
    private String Thumbnail;
    private boolean HaveIt;
    private boolean WannaPlay;

    public CollectionItem() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        Thumbnail = thumbnail;
    }

    public boolean isHaveIt() {
        return HaveIt;
    }

    public void setHaveIt(boolean haveIt) {
        HaveIt = haveIt;
    }

    public boolean isWannaPlay() {
        return WannaPlay;
    }

    public void setWannaPlay(boolean wannaPlay) {
        WannaPlay = wannaPlay;
    }
}
