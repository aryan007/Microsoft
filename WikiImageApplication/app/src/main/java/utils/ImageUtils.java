package utils;

import model.Page;

/**
 * Created by thukralp on 20/05/16.
 */
public class ImageUtils {

    public static String generateImageUrl(Page wikiPage)
    {
        String imageUrl=null;
        if(wikiPage!=null && wikiPage.getThumbnail()!=null && wikiPage.getThumbnail().getSource()!=null)
        imageUrl = wikiPage.getThumbnail().getSource();
        return  imageUrl;
    }

}
