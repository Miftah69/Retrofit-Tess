package com.example.miftah.retrofitnostraok;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.miftah.retrofitnostraok.API.ApiClient;
import com.example.miftah.retrofitnostraok.model.ApiInterface;
import com.example.miftah.retrofitnostraok.model.PersonResponse;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class add_karyawan extends AppCompatActivity {

    private ProgressDialog progress;
    String id, nama, address, phone, email, picture, version;

    private EditText editId, editNama, editAdress, editPhone, editEmail, editPicture, editVersion;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_karyawan);

        editId = (EditText) findViewById(R.id.edit_id);
        editVersion = (EditText) findViewById(R.id.edit_version);
        editNama = (EditText) findViewById(R.id.edit_nama);
        editAdress = (EditText) findViewById(R.id.edit_address);
        editEmail = (EditText) findViewById(R.id.edit_email);
        editPhone = (EditText) findViewById(R.id.edit_phone);
        editPicture = (EditText) findViewById(R.id.edit_picture);
        btnSave = (Button) findViewById(R.id.btn_simpan);

        inisiasi();
    }

    public void inisiasi(){
        id = editId.getText().toString();
        nama = editNama.getText().toString();
        address = editAdress.getText().toString();
        phone = editPhone.getText().toString();
        email = editEmail.getText().toString();
        picture = editPicture.getText().toString();
        version = editVersion.getText().toString();
    }

    public void simpanOnClick(View v) {
        //menampilakn progress dialog
        inisiasi();
        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setMessage("Loading");
        progress.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface api = retrofit.create(ApiInterface.class);
        retrofit2.Call<PersonResponse> call = api.add(id, version, nama, address, email, phone, picture);
        call.enqueue(new Callback<PersonResponse>() {
            @Override
            public void onResponse(retrofit2.Call<PersonResponse> call, retrofit2.Response<PersonResponse> response) {
                String value = response.body().getValue();
                String message = response.body().getMessage();

                progress.dismiss();

                if (value.equals("1")){
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<PersonResponse> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getApplicationContext(), "Jaringan Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
