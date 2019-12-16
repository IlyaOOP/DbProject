package com.example.dbproject;

import android.util.Log;

import java.util.Dictionary;
import java.util.HashMap;

public class URLencoder {
    static HashMap<String, String> map =  new HashMap<>();
    public URLencoder()
    {
        int code = 224;//a
        for (char i='а'; i<='я'; i++)
        {
            map.put(""+i, (Integer.toHexString(code)).toUpperCase());
            code++;
        }
        map.put(" ", "+");
    }

    public String getCodeByCharacter(String character)
    {
        if (map.containsKey(character))
            return map.get(character);
        else return character;
    }

    public String encodeString(String str)
    {
        String res = "";
        char character[] = str.toCharArray();
        for (char c:character) {
            res+="%"+getCodeByCharacter(String.valueOf(c));
        }
        return res;
    }
}
