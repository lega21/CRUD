package com.example.regiones;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.regiones.Model.RegionesModel;
import com.example.regiones.Remote.APIUtils;
import com.example.regiones.Remote.RegionesService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegionActivity extends AppCompatActivity {

        RegionesService userService;
    EditText edtUId;
    EditText edtRegion;
    Button btnSave;
    Button btnDel;
    TextView txtUId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region);

        setTitle("Regions");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtUId = findViewById(R.id.txtUId);
        edtUId = findViewById(R.id.edtUId);
        edtRegion = findViewById(R.id.edtRegion);
        btnSave = findViewById(R.id.btnSave);
        btnDel = findViewById(R.id.btnDel);


        userService = APIUtils.getRegionService();

        Bundle extras = getIntent().getExtras();
        final String regionId = extras.getString("region_id");
        String userreg = extras.getString("region_name");

        edtUId.setText(regionId);
        edtRegion.setText(userreg);

        if(regionId != null && regionId.trim().length() > 0 ){
            edtUId.setFocusable(false);
        } else {
            txtUId.setVisibility(View.INVISIBLE);
            edtUId.setVisibility(View.INVISIBLE);
            btnDel.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegionesModel u = new RegionesModel();
                u.setRegion(edtRegion.getText().toString());
                if(regionId != null && regionId.trim().length() > 0){
                    updateUser(Integer.parseInt(regionId), u);
                } else {
                    addUser(u);
                }
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUser(Integer.parseInt(regionId));
                Intent intent = new Intent(RegionActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void addUser(RegionesModel u){
        Call<RegionesModel> call = userService.addRegion(u);
        call.enqueue(new Callback<RegionesModel>() {
            @Override
            public void onResponse(Call<RegionesModel> call, Response<RegionesModel> response) {
                if(response.isSuccessful()){
                    Toast.makeText(RegionActivity.this, "Regiones created successfully!",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegionActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<RegionesModel> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void updateUser(int id, RegionesModel u){
        Call<RegionesModel> call = userService.updateRegion(id, u);
        call.enqueue(new Callback<RegionesModel>() {
            @Override
            public void onResponse(Call<RegionesModel> call, Response<RegionesModel> response) {
                if(response.isSuccessful()){
                    Toast.makeText(RegionActivity.this, "Regiones updated successfully!",
                            Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(RegionActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<RegionesModel> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void deleteUser(int id){
        Call<RegionesModel> call = userService.deleteRegion(id);
        call.enqueue(new Callback<RegionesModel>() {
            @Override
            public void onResponse(Call<RegionesModel> call, Response<RegionesModel> response) {
                if(response.isSuccessful()){
                    Toast.makeText(RegionActivity.this, "Regiones deleted successfully!",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegionesModel> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}