package model;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * Created by thukralp on 01/03/16.
 */
public class Pages implements Serializable{

    private static final long serialVersionUID = 2L;
    Map<Integer,Page> pageMap;

    public Map<Integer, Page> getPageMap() {
        return pageMap;
    }
}
