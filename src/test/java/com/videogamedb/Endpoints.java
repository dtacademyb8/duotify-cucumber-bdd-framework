package com.videogamedb;

public enum Endpoints {


    VIDEOGAMES("/videogames"),
    VIDEOGAME_BY_ID("/videogames/{videoGameId}");


    final String str;
    Endpoints(String str) {
       this.str = str;

    }

    @Override
    public String toString() {
        return this.str;
    }
}
