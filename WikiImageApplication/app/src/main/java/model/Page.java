package model;

/**
 * Created by thukralp on 09/06/16.
 */
public class Page {

    int pageid;
    String title;
    Thumbnail thumbnail;

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public int getPageid() {
        return pageid;
    }

    public String getTitle() {
        return title;
    }
}
