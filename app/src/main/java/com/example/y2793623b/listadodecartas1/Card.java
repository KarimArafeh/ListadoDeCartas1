package com.example.y2793623b.listadodecartas1;

/**
 * Created by y2793623b on 18/10/16.
 */

public class Card {

    String mame;
    String type;
    String rarity;
    String set;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
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

    public String getMame() {
        return mame;
    }

    public void setMame(String mame) {
        this.mame = mame;
    }

    String text;

    @Override
    public String toString() {
        return "Card{" +
                "mame='" + mame + '\'' +
                ", type='" + type + '\'' +
                ", rarity='" + rarity + '\'' +
                ", set='" + set + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}

