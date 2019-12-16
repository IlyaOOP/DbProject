package com.example.dbproject;

public class ItemObject {
    String Name;
    Float Minprice;
    Float Maxprice;
    String ShortDescription;

    public ItemObject(String name, Float minprice, Float maxprice, String shortDescription)
    {
        Name = name;
        Minprice = minprice;
        Maxprice = maxprice;
        ShortDescription = shortDescription;
    }

    public String toString()
    {
        String result = null;
        result = Name + " Цена: от "+Minprice+" до "+Maxprice+" Описание: "+ShortDescription;
        return result;
    }

    public String getStringPrice()
    {
        return "от "+String.format("%.2f", Minprice) + " до " + String.format("%.2f", Maxprice);
    }
}
