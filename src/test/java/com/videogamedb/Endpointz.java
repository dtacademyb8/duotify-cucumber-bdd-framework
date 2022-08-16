package com.videogamedb;

public enum Endpointz {


    VIDEOGAME("/videogames"),
    VIDEOGAME_BY_ID("/videogames/{videoGameID}");


    final String str;
    Endpointz(String str) {
       this.str = str;

    }
}
