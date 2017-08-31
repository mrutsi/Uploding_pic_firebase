package com.example.codertribe_ruu.uploding_pic_firebase;

/**
 * Created by CoderTribe_Ruu on 2017/08/30.
 */

public class ImageUpload {

    public String name;
    public String uri;

    public ImageUpload(String name, String uri) {
        this.name = name;
        this.uri = uri;
    }

    public ImageUpload() {
        this.name = name;
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public String getUri() {
        return uri;
    }



    
}
