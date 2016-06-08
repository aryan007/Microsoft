package model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by thukralp on 01/03/16.
 */
public class QueryResults implements Serializable {

    private static final long serialVersionUID = 1L;
    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }

    private List<Page> pages=null;


}
