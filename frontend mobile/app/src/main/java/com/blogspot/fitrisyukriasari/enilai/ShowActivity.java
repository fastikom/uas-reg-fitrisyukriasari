package com.blogspot.fitrisyukriasari.enilai;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends AppCompatActivity {

    JSONParser jParser = new JSONParser();
    TextView IdNilai, NamaMhs, Nilai, GenderCust, Matkul;
    Button backBtn, editBtn, deleteBtn;

    String url_delete_nilai = "http://192.168.43.110:8000/api/destroy";

    public static final String TAG_SUCCESS = "success";
    public static final String TAG_id_nilai = "id";

    String nilai_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        IdNilai = (TextView) findViewById(R.id.idValue);
        NamaMhs = (TextView) findViewById(R.id.namaV);
        Nilai = (TextView) findViewById(R.id.nilaiV);
        Matkul = (TextView) findViewById(R.id.matkulV);
        backBtn = (Button) findViewById(R.id.btn_back);
        editBtn = (Button) findViewById(R.id.btn_edit);
        deleteBtn = (Button) findViewById(R.id.btn_delete);

        Bundle b = getIntent().getExtras();
        String isi_id_nilai = b.getString("id_nilai");
        String isi_nama_mhs = b.getString("nama_mhs");
        String isi_nilai = b.getString("nilai");
        String isi_matkul = b.getString("matkul");

        IdNilai.setText(isi_id_nilai);
        NamaMhs.setText(isi_nama_mhs);
        Nilai.setText(isi_nilai);
        Matkul.setText(isi_matkul);

        nilai_id = isi_id_nilai;

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = null;
                i = new Intent(ShowActivity.this, EditActivity.class);
                Bundle b = new Bundle();
                b.putString("id_nilai", IdNilai.getText().toString());
                b.putString("nama_mhs", NamaMhs.getText().toString());
                b.putString("nilai", Nilai.getText().toString());
                b.putString("matkul", Matkul.getText().toString());
                i.putExtras(b);
                startActivity(i);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DeleteCustTask().execute();
            }
        });
    }

    class DeleteCustTask extends AsyncTask<String, Void, String>
    {
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            pDialog = new ProgressDialog(ShowActivity.this);
            pDialog.setMessage("Please waiting...");
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... sText)
        {
            List<NameValuePair> parameter = new ArrayList<NameValuePair>();
            parameter.add(new BasicNameValuePair(TAG_id_nilai, nilai_id));

            try {
                JSONObject json = jParser.makeHttpRequest(url_delete_nilai,"GET",parameter);

                int success = json.getInt(TAG_SUCCESS);
                if (success==1)
                {
                    return "OK";
                }

                else{
                    return "FAIL";
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return "Exception Caught";
            }
        }

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);
            pDialog.dismiss();

            if (result.equalsIgnoreCase("Exception Caught"))
            {
                Toast.makeText(ShowActivity.this,"Unable to connect to server,please check your internet connection!",Toast.LENGTH_LONG).show();
            }

            if (result.equalsIgnoreCase("FAIL"))
            {
                Toast.makeText(ShowActivity.this,"Fail... try again",Toast.LENGTH_LONG).show();
            }

            else{
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                Toast.makeText(ShowActivity.this,"Nilai successfully deleted.",Toast.LENGTH_LONG).show();
            }
        }
    }
}
