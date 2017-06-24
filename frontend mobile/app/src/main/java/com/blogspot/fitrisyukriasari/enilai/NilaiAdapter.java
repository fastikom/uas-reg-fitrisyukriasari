package com.blogspot.fitrisyukriasari.enilai;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Fitri Syukriasari on 24/06/2017.
 */

public class NilaiAdapter extends BaseAdapter{

    private Activity activity;
    private ArrayList<Nilai> data_nilai = new ArrayList<Nilai>();
    private  static LayoutInflater inflater = null;

    public NilaiAdapter(Activity a, ArrayList<Nilai> d)
    {
        activity = a;
        data_nilai = d;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount()
    {
        return data_nilai.size();
    }

    public Object getItem(int position)
    {
        return data_nilai.get(position);
    }

    public long getItemId(int position)
    {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        View vi = convertView;
        if (convertView==null)
            vi = inflater.inflate(R.layout.list_item_nilai,null);
        TextView id_nilai = (TextView) vi.findViewById(R.id.id_nilai);
        TextView nama_mhs = (TextView) vi.findViewById(R.id.nama_mhs);
        TextView nilai = (TextView) vi.findViewById(R.id.nilai);
        TextView matkul = (TextView) vi.findViewById(R.id.matkul);

        Nilai list_nilai = data_nilai.get(position);
        id_nilai.setText(list_nilai.getId());
        nama_mhs.setText(list_nilai.getNama());
        nilai.setText(list_nilai.getNilai());
        matkul.setText(list_nilai.getMatkul());

        return vi;
    }

}
