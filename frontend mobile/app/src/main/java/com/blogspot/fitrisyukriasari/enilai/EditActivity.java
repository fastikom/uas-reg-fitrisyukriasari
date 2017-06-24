package com.blogspot.fitrisyukriasari.enilai;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EditActivity extends AppCompatActivity {

    JSONParser jParser = new JSONParser();

    String url_update_nilai = "http://192.168.43.110:8000/update";

    public static final String TAG_SUCCESS = "success";
    public static final String TAG_ID_NILAI = "id";
    public static final String TAG_NAMA_MHS = "nama";
    public static final String TAG_NILAI = "nilai";
    public static final String TAG_MATKUL = "matkul";

    TextView txtId;
    EditText editNama, editNilai, editMatkul;
    Button updateBtn, backBtn;

    String idStr, namaStr, nilaiStr, matkulStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        txtId = (TextView) findViewById(R.id.input_id);
        editNama = (EditText) findViewById(R.id.input_nama);
        editNilai = (EditText) findViewById(R.id.input_nilai);
        editMatkul = (EditText) findViewById(R.id.input_matkul);
        updateBtn = (Button) findViewById(R.id.btn_update);
        backBtn = (Button) findViewById(R.id.btn_back);

        Bundle b = getIntent().getExtras();
        String isi_id_nilai = b.getString("id_nilai");
        String isi_nama_mhs = b.getString("nama_mhs");
        String isi_nilai = b.getString("nilai");
        String isi_matkul = b.getString("matkul");

        txtId.setText(isi_id_nilai);
        editNama.setText(isi_nama_mhs);
        editNilai.setText(isi_nilai);
        editMatkul.setText(isi_matkul);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idStr = txtId.getText().toString();
                namaStr = editNama.getText().toString();
                nilaiStr = editNilai.getText().toString();
                matkulStr = editMatkul.getText().toString();
                new UpdateCustTask().execute();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idnilai = txtId.getText().toString();
                String namamhs = editNama.getText().toString();
                String nilai = editNilai.getText().toString();
                String matkul = editMatkul.getText().toString();

                Intent i = null;
                i = new Intent(EditActivity.this, ShowActivity.class);
                Bundle b = new Bundle();
                b.putString("id_nilai", idnilai);
                b.putString("nama_mhs", namamhs);
                b.putString("nilai", nilai);
                b.putString("matkul", matkul);
                i.putExtras(b);
                startActivity(i);
            }
        });
    }

    class UpdateCustTask extends AsyncTask<String, Void, String>
    {
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pDialog = new ProgressDialog(EditActivity.this);
            pDialog.setMessage("Please waiting...");
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... sText)
        {
            List<NameValuePair> parameter = new ArrayList<NameValuePair>();
            parameter.add(new BasicNameValuePair(TAG_ID_NILAI, idStr));
            parameter.add(new BasicNameValuePair(TAG_NAMA_MHS, namaStr));
            parameter.add(new BasicNameValuePair(TAG_NILAI, nilaiStr));
            parameter.add(new BasicNameValuePair(TAG_MATKUL, matkulStr));

            try {
                JSONObject json = jParser.makeHttpRequest(url_update_nilai,"GET",parameter);

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
                Toast.makeText(EditActivity.this, "Unable to connect to server, please check your internet connection!", Toast.LENGTH_LONG).show();
            }

            if (result.equalsIgnoreCase("FAIL"))
            {
                Toast.makeText(EditActivity.this, "Fail... Try Again",Toast.LENGTH_LONG).show();
            }

            else{
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                Toast.makeText(EditActivity.this,"Customer successfully updated.",Toast.LENGTH_LONG).show();
            }
        }
    }
}
