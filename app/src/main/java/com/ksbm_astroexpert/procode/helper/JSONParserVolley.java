package com.ksbm_astroexpert.procode.helper;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by android on 5/17/2017.
 */


public class JSONParserVolley
{static String json;
    static JSONObject jsonObject=null;

    public JSONParserVolley(String json) {
        this.json = json;
    }

    public JSONObject JSONParseVolley(){

        try{
            jsonObject = new JSONObject(json);
        }
        catch (JSONException e){
            e.printStackTrace();
        }

        return jsonObject;
    }}