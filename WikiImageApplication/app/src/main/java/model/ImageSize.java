package model;

/**
 * Created by thukralp on 20/05/16.
 */
public enum ImageSize {

    SMALL(300,300),
    BIG(600,600);

    int width;
    int height;

    ImageSize(int width,int height)
    {
        this.width=width;
        this.height=height;
    }



    static{



    }


}
