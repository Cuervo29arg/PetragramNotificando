package com.christiancollado.petagram.resAPI;

/**
 * Created by colla on 29/11/2016.
 */

public class ConstantesRA {

    public static final String VERSION = "/v1/";
    public static final String ROOT_URL = "https://api.instagram.com"+ VERSION;
    public static final String KEY_ACCESS_TOKEN = "?access_token=";
    public static final String ACCESS_TOKEN = "332966123.e007a3a.152ed52d1ddb46c2b7477f45146935ef";
    public static final String TAG_ACCESS_TOKEN = KEY_ACCESS_TOKEN + ACCESS_TOKEN;


    public static final String KEY_GET_RECENT_MEDIA_USER = "users/self/media/recent/";
    public static final String URL_GET_RECENT_MEDIA_USER = KEY_GET_RECENT_MEDIA_USER + TAG_ACCESS_TOKEN;

    public static final String KEY_GET_RECENT_MEDIA_AMIGOS = "users/{usuario}/media/recent/";
    public static final String URL_GET_RECENT_MEDIA_AMIGOS = KEY_GET_RECENT_MEDIA_AMIGOS + TAG_ACCESS_TOKEN;
    public static final String KEY_SEARCH_USER = "users/search";


    public static final String ROOT_URL_FIREBASE = "https://quiet-escarpment-23797.herokuapp.com/";
    public static final String KEY_POST_ID_TOKEN = "token-device/";
}
