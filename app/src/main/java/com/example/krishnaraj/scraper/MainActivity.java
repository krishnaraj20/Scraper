package com.example.krishnaraj.scraper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import org.jsoup.Jsoup;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class MainActivity extends Activity {
String URL = "http://thethao247.vn/ket-qua-bong-da-c55/";
    ProgressDialog dialog;
    String Team = "";
    String Time = "";
    TextView tv,tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView)findViewById(R.id.tx1);
        tv1 = (TextView)findViewById(R.id.tx2);
        new Jsoup().execute();

    }
    public class Jsoup extends AsyncTask<Void , Void , Void>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setMessage("Loading...");
            dialog.show();
        }
        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Document doc = org.jsoup.Jsoup.connect(URL).get();
                Elements elements = doc.select("dd.mElO1");
                for (int i =0 ;i<elements.size();i++)
                {
                    Team +="\n"+elements.get(i).text();
                }


                Elements e1 = doc.select("dd.mElStatus");
                for (int i =0 ;i<e1.size();i++)
                {
                    Time +="\n"+e1.get(i).text();
                }
            }catch (Exception e)
            {

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            tv1.setText(" " + Time);
            tv.setText(" " + Team);

        }


    }

}