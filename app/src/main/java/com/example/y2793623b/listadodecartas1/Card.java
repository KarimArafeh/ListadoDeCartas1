package com.example.y2793623b.listadodecartas1;

import java.io.Serializable;

/**
 * Created by y2793623b on 18/10/16.
 */

public class Card implements Serializable{

    String name;
    String type;
    String rarity;
    String mySet;
    String colors;
    String imageUrl;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMySet() {
        return mySet;
    }

    public void setMySet(String mySet) {
        this.mySet = mySet;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String text;

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", rarity='" + rarity + '\'' +
                ", mySet='" + mySet + '\'' +
                ", colors='" + colors + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}

