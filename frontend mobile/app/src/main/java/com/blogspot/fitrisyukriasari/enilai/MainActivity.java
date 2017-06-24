package com.blogspot.fitrisyukriasari.enilai;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView list;

    JSONParser jParser = new JSONParser();
    ArrayList<Nilai> list_nilai = new ArrayList<Nilai>();
    JSONArray listNilai = null;
    String url_read_cust = "http://192.168.43.110:8000/api";

    public static final String TAG_SUCCESS = "success";
    public static final String TAG_NILAIDB = "nilai";
    public static final String TAG_ID_NILAI = "id";
    public static final String TAG_NAMA = "nama";
    public static final String TAG_NILAI = "nilai";
    public static final String TAG_MATKUL = "matkul";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.listNilai);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),CreateActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

        ReadCustTask m = (ReadCustTask) new ReadCustTask().execute();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int urutan, long id) {
                String idnilai = ((TextView) view.findViewById(R.id.id_nilai)).getText().toString();
                String nama_mhs = ((TextView) view.findViewById(R.id.nama_mhs)).getText().toString();
                String nilai = ((TextView) view.findViewById(R.id.nilai)).getText().toString();
                String matkul = ((TextView) view.findViewById(R.id.matkul)).getText().toString();

                Intent i = null;
                i = new Intent(MainActivity.this, ShowActivity.class);
                Bundle b = new Bundle();
                b.putString("id_nilai", idnilai);
                b.putString("nama_mhs", nama_mhs);
                b.putString("nilai", nilai);
                b.putString("matkul", matkul);
                i.putExtras(b);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(MainActivity.this,"Applikasi framewrok dibuat oleh fitrisyukriasari dan gustianisalimah.",Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class ReadCustTask extends AsyncTask<String, Void, String>
    {
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please waiting...");
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... sText)
        {
            String returnResult = getCustList();
            return returnResult;
        }

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);
            pDialog.dismiss();
            if (result.equalsIgnoreCase("Exception Caught"))
            {
                Toast.makeText(MainActivity.this,"Unable to connect to server, please check your internet connection!",Toast.LENGTH_LONG).show();
            }
            if (result.equalsIgnoreCase("no results"))
            {
                Toast.makeText(MainActivity.this,"Data empty",Toast.LENGTH_LONG).show();
            }
            else{
//                list.setAdapter(new NilaiAdapter(MainActivity.this,list_nilai));
                list.setAdapter(new NilaiAdapter(MainActivity.this,list_nilai));
            }

        }

        public String getCustList()
        {
            Nilai tempNilai = new Nilai();
            List<NameValuePair> parameter = new ArrayList<NameValuePair>();
            try {
                JSONObject json = jParser.makeHttpRequest(url_read_cust,"GET",parameter);

                int success = json.getInt(TAG_SUCCESS);
                if (success==1)
                {
                    listNilai = json.getJSONArray(TAG_NILAIDB);
                    for (int i = 0; i < listNilai.length(); i++)
                    {
                        JSONObject c = listNilai.getJSONObject(i);
                        tempNilai = new Nilai();
                        tempNilai.setId(c.getString(TAG_ID_NILAI));
                        tempNilai.setNama(c.getString(TAG_NAMA));
                        tempNilai.setNilai(c.getString(TAG_NILAI));
                        tempNilai.setMatkul(c.getString(TAG_MATKUL));
                        list_nilai.add(tempNilai);
                    }
                    return "OK";
                }

                else{
                    return "no results";
                }
            }
            catch (Exception e)
            {
                return "Exception Caught";
            }
        }
    }
}
