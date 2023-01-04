package tse.fise2.image3.cardmatcher.model;

import tse.fise2.image3.cardmatcher.sift.Descriptor;

public class Card {
    String name;
    Descriptor desc;

    public Card() {
    }

    public void setDesc(Descriptor desc) {
        this.desc = desc;
    }

    public Descriptor getDesc() {
        return desc;
    }

    public Card(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
