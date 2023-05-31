package Shared;

import Shared.Response;

import java.util.ArrayList;
import java.io.Serializable;

public class ResponseBrowse extends Response implements Serializable {

    private static final long serialVersionUID = 1L;
    String games;

    public void addToGames(String newGame) {
        games += newGame;
    }

    public String getGames() {
        return games;
    }
}
