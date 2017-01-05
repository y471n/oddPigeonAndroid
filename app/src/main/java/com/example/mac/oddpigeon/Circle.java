package com.example.mac.oddpigeon;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import org.json.JSONArray;

/**
 * Created by mac on 01/09/14.
 */
@ParseClassName("Circle")
public class Circle extends ParseObject{

    public Circle(){
        // Default Constructor
    }

    public String getCircleType(){
        return getString("circleType");
    }

    public void setCircleType(String circleType) {
        put("circleType", circleType);
    }

    public String getCircleName() {
        return getString("circleName");
    }

    public void setCircleName(String circleName) {
        put("circleName", circleName);
    }

    public ParseUser getAuthor() {
        return getParseUser("author");
    }

    public void setAuthor(ParseUser user) {
        put("author", user);
    }

    public JSONArray getCircleRules() {
        return getJSONArray("circleRules");
    }

    public void setCircleRules(JSONArray circleRules) {
        put("circleRules", circleRules);
    }

    public ParseFile getPhotoFile() {
        return getParseFile("circlePhoto");
    }

    public void setPhotoFile(ParseFile file) {
        put("circlePhoto", file);
    }

}
