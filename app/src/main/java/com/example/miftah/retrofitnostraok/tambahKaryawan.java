package com.example.miftah.retrofitnostraok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.miftah.retrofitnostraok.API.ApiClient;
import com.example.miftah.retrofitnostraok.API.ApiInterface;
import com.example.miftah.retrofitnostraok.model.PersonResponse;

import okhttp3.Response;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class tambahKaryawan extends AppCompatActivity {

    String id, nama, address, phone, email, picture, version;
    private EditText editId, editNama, editAdress, editPhone, editEmail, editPicture, editVersion;

    private Button btnUpdate, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_karyawan);

        editId = (EditText) findViewById(R.id.edit_id);
        editVersion = (EditText) findViewById(R.id.edit_version);
        editNama = (EditText) findViewById(R.id.edit_nama);
        editAdress = (EditText) findViewById(R.id.edit_address);
        editEmail = (EditText) findViewById(R.id.edit_email);
        editPhone = (EditText) findViewById(R.id.edit_phone);
        editPicture = (EditText) findViewById(R.id.edit_picture);

        editId.setText(getIntent().getStringExtra("id"));
        editNama.setText(getIntent().getStringExtra("nama"));
        editAdress.setText(getIntent().getStringExtra("address"));
        editPhone.setText(getIntent().getStringExtra("phone"));
        editEmail.setText(getIntent().getStringExtra("email"));
        editPicture.setText(getIntent().getStringExtra("picture"));
        editVersion.setText(getIntent().getStringExtra("version"));


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()

                        .baseUrl(AppDefinition.URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiInterface api = retrofit.create(ApiInterface.class);
                Call<PersonResponse> call = api.update(id, version, nama, address, email, phone, picture);

                call.enqueue(new Callback<PersonResponse>() {
                    @Override
                    public void onResponse(Call<PersonResponse> call, Response<PersonResponse> response){
                        String value = response.body().getValue();
                        String message = response.body().getMessage();

                        if (value.equals("1")){
                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<PersonResponse> call, Throwable t){
                        t.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Jaringan Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(AppDefinition.URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                ApiInterface api = retrofit.create(ApiInterface.class);
                Call<PersonResponse> call = api.delete(id);
                Call.enqueue(new Callback(ApiClient){

                    @Override
                    public void onRespone(Call<PersonResponse>call, Response<ApiInterface>Response){
                        String value = Response.body().getValue;
                        String message = Response.body().getmassage();
                        if (value.equals("1")){
                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT.show());
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiInterface> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(getApplicationContext(), "jaringan error!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
