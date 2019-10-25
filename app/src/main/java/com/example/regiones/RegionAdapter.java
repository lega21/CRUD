package com.example.regiones;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.example.regiones.Model.RegionesModel;
import com.example.regiones.Remote.APIUtils;
import com.example.regiones.Remote.RegionesService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegionAdapter extends ArrayAdapter<RegionesModel> {
    private Context context;
    private List<RegionesModel> regions;
    RegionActivity regionActivity;

    public RegionAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<RegionesModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.regions = objects;
        regionActivity = new RegionActivity();
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_region, parent, false);

        TextView txtUserId = (TextView) rowView.findViewById(R.id.txtRegionId);
        TextView txtUsername = (TextView) rowView.findViewById(R.id.txtName);

        txtUserId.setText(String.valueOf(regions.get(pos).getId()));
        txtUsername.setText(String.valueOf(regions.get(pos).getRegion()));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RegionActivity.class);
                intent.putExtra("region_id", String.valueOf(regions.get(pos).getId()));
                intent.putExtra("region_name", regions.get(pos).getRegion());
                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
