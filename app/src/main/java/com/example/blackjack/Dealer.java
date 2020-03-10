package com.example.blackjack;

import java.util.ArrayList;
import java.util.List;

public class Dealer {
    private List<Integer> handList = new ArrayList<>();

    public Dealer() {
    }

    public List<Integer> getHandList() {
        return handList;
    }
}
