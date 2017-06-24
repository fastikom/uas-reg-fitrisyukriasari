package com.blogspot.fitrisyukriasari.enilai;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CreateActivity extends AppCompatActivity {

    JSONParser jsonParser = new JSONParser();
    String url_create_nilai = "http://192.168.43.110:8000/api/store";

    public static final String TAG_SUCCESS = "success";
    public static final String TAG_NAMA_MHS = "nama";
    public static final String TAG_NILAI = "nilai";
    public static final String TAG_MATKUL = "matkul";

    EditText EditTxtnama, EditTxtnilai, EditTxtmatkul;
    Button addBtn, backBtn;
    String namaStr, nilaiStr, matkulStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        EditTxtnama = (EditText) findViewById(R.id.input_nama);
        EditTxtnilai = (EditText) findViewById(R.id.input_nilai);
        EditTxtmatkul = (EditText) findViewById(R.id.input_matkul);
        addBtn = (Button) findViewById(R.id.button_add);
        backBtn = (Button) findViewById(R.id.button_back);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                namaStr = EditTxtnama.getText().toString();
                nilaiStr = EditTxtnilai.getText().toString();
                matkulStr = EditTxtmatkul.getText().toString();
                new CreateCustTask().execute();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }

    class CreateCustTask extends AsyncTask<String, String, String> {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(CreateActivity.this);
            dialog.setMessage("Please waiting...");
            dialog.setIndeterminate(false);
            dialog.setCancelable(false);
            dialog.show();
        }

        protected String doInBackground(String... args) {
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair(TAG_NAMA_MHS, namaStr));
            params.add(new BasicNameValuePair(TAG_NILAI, nilaiStr));
            params.add(new BasicNameValuePair(TAG_MATKUL, matkulStr));

            JSONObject json = jsonParser.makeHttpRequest(url_create_nilai, "GET", params);

            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success==1)
                {
                    finish();
                }

                else{
                    return "gagal_database";
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                return "gagal_koneksi_or_exception";
            }
            return "sukses";
        }

        protected void onPostExecute(String result){
            super.onPostExecute(result);
            if (result.equalsIgnoreCase("gagal_database"))
            {
                dialog.dismiss();
                Toast.makeText(CreateActivity.this, "Terjadi kesalahan! Silakan cek koneksi anda!", Toast.LENGTH_SHORT).show();
            }

            else if (result.equalsIgnoreCase("gagal_koneksi_or_exception"))
            {
                dialog.dismiss();
                Toast.makeText(CreateActivity.this, "Terjadi masalah! Silakan cek koneksi anda!", Toast.LENGTH_SHORT).show();
            }
            else if (result.equalsIgnoreCase("sukses"))
            {
                dialog.dismiss();
                Intent i = null;
                i = new Intent(CreateActivity.this, MainActivity.class);
                startActivity(i);
                Toast.makeText(CreateActivity.this,"Nilai data successfully created.",Toast.LENGTH_LONG).show();
            }
        }
    }
}
