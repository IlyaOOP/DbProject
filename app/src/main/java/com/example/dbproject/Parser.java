package com.example.dbproject;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Parser extends AsyncTask<String, Integer, ArrayList<ItemObject>> {
    Context appCTX = null;
    public Parser(Context ctx)
    {
        appCTX = ctx;
    }
    String value = null;
    @Override
    protected ArrayList<ItemObject> doInBackground(String... strings) {
        Document doc = null;
        ArrayList<ItemObject> itemsObj = new ArrayList<>();

        if (strings[0].matches("^[а-яА-Я]+$"))
        {
            strings[0] = strings[0].toLowerCase();
            URLencoder encoder = new URLencoder();
            strings[0] = encoder.encodeString(strings[0]);
        }

        try {
            doc = Jsoup.connect("https://shop.by/find/?findtext="+strings[0]).get();
        } catch (IOException e) {
            //Если не получилось считать
            e.printStackTrace();
        }
        //Если всё считалось, то вытаскиваем из считанного html документа заголовок
        if (doc!=null) {
            Elements title = doc.getElementsByClass("Page__TitleActivePage");
            value = (title.get(0)).ownText();
            Elements items = doc.getElementsByClass("ModelList__ModelBlockRow");
            String Name, ShortDescription;
            Float Minprice, Maxprice;
            Elements NameNode, PriceNode, DescriptionNode;
            for (Element e:items
                 ) {
                NameNode = e.getElementsByClass("ModelList__NameBlock");
                Name = ((NameNode.get(0)).child(0)).child(0).text();
                PriceNode = e.getElementsByClass("ModelList__PriceBlock");
                Minprice = Float.parseFloat((((((PriceNode.get(0)).getElementsByClass("PriceBlock__PriceValue"))).get(0)).child(0).ownText()).replace(',', '.'));
                if ((PriceNode.get(0)).getElementsByClass("PriceBlock__PriceLastValue").size()>0)//если указана максимальная цена
                    Maxprice = Float.parseFloat((((((PriceNode.get(0)).getElementsByClass("PriceBlock__PriceLastValue"))).get(0)).child(0).ownText()).replace(',', '.'));
                else Maxprice = Float.parseFloat("-1");
                DescriptionNode = e.getElementsByClass("ModelList__DescBlock");
                ShortDescription = DescriptionNode.get(0).text();
                Log.i("info", Name+"_"+Minprice+"_"+Maxprice+"_"+ShortDescription);
                itemsObj.add(new ItemObject(Name, Minprice, Maxprice, ShortDescription));
            }
            return itemsObj;
        }
        else
            value = "Ошибка подключения";

        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<ItemObject> result) {
        super.onPostExecute(result);


        MainActivity ma = (MainActivity)appCTX;
        ProgressBar pb = ma.findViewById(R.id.MainProgressBar);
        pb.setVisibility(View.GONE);
        TextView resTV = ma.findViewById(R.id.resTV);
        String resstr = null;
        for (ItemObject obj:result
             ) {
            resstr += obj.toString();
        }
        resTV.setText(resstr);

        Toast.makeText(appCTX, value, Toast.LENGTH_LONG).show();
        Toast.makeText(appCTX, ""+(result.size()), Toast.LENGTH_LONG).show();
    }
}
