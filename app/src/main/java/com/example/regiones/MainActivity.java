package com.example.regiones;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.regiones.Model.RegionesModel;
import com.example.regiones.Remote.APIUtils;
import com.example.regiones.Remote.RegionesService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btnAddRegion;
    Button btnGetRegionList;
    ListView listView;

    RegionesService regionesService;
    List<RegionesModel> list = new ArrayList<RegionesModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Regiones CRUD");


        btnAddRegion = (Button) findViewById(R.id.btnAddRegion);
        btnGetRegionList = (Button) findViewById(R.id.btnGetRegionList);
        listView = (ListView) findViewById(R.id.listView);
        regionesService = APIUtils.getRegionService();

        btnGetRegionList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRegionList();
            }
        });

        btnAddRegion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegionActivity.class);
                intent.putExtra("region_id", "");
                intent.putExtra("region_name", "");
                startActivity(intent);
            }
        });
    }

    public void getRegionList(){
        Call<List<RegionesModel>> call = regionesService.getRegion();
        call.enqueue(new Callback<List<RegionesModel>>() {
            @Override
            public void onResponse(Call<List<RegionesModel>> call, Response<List<RegionesModel>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    listView.setAdapter(new RegionAdapter(MainActivity.this, R.layout.list_region, list));
                }
            }

            @Override
            public void onFailure(Call<List<RegionesModel>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}