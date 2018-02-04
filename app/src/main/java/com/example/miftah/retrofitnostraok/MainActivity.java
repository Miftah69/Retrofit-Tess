package com.example.miftah.retrofitnostraok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.miftah.retrofitnostraok.API.ApiClient;
import com.example.miftah.retrofitnostraok.API.ApiInterface;
import com.example.miftah.retrofitnostraok.adapter.PersonRecycleViewAdapter;
import com.example.miftah.retrofitnostraok.model.Person;
import com.example.miftah.retrofitnostraok.model.PersonResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private List<Person> persons = new ArrayList<>();
    private PersonRecycleViewAdapter viewAdapter;

    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(viewAdapter);

        loadDataPasien();
    }

    private void loadDataPasien(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<PersonResponse> call = api.view();


        call.enqueue(new Callback<PersonResponse>() {
            @Override
            public void onResponse(Call<PersonResponse> call, Response<PersonResponse> response) {


                String message = response.body().getMessage();
                progressBar.setVisibility(View.GONE);

                if(message.equals("OK")){
                    persons = response.body().getResult();
                    viewAdapter = new PersonRecycleViewAdapter(getApplicationContext(), persons);
                    recyclerView.setAdapter(viewAdapter);
                }

            }

            @Override
            public void onFailure(Call<PersonResponse> call, Throwable t) {
                t.printStackTrace();
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Jaringan Error !", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
