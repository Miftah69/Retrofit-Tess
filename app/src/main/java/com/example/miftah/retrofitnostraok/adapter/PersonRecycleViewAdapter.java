package com.example.miftah.retrofitnostraok.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miftah.retrofitnostraok.R;
import com.example.miftah.retrofitnostraok.model.Person;
import com.example.miftah.retrofitnostraok.tambahKaryawan;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Miftah on 2/4/2018.
 */

public class PersonRecycleViewAdapter extends RecyclerView.Adapter<PersonRecycleViewAdapter.ViewHolder> {
    private Context context;
    private List<Person> persons;

    public PersonRecycleViewAdapter(Context context, List<Person> persons) {
        this.context = context;
        this.persons = persons;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_karyawan, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Person p = persons.get(position);
        holder.txtId.setText(p.getId());
        holder.txtNama.setText(p.getName());
        holder.txtVersion.setText(p.getVersion());
        holder.txtAdress.setText(p.getAddress());
        holder.txtEmail.setText(p.getEmail());
        holder.txtPhone.setText(p.getPhone());
        holder.txtPicture.setText(p.getPicture());

    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtId;
        private TextView txtNama;
        private TextView txtAdress;
        private TextView txtVersion;
        private TextView txtPhone;
        private TextView txtEmail;
        private TextView txtPicture;

        public ViewHolder(View itemView) {
            super(itemView);

            txtId = (TextView) itemView.findViewById(R.id.txt_Id);
            txtNama = (TextView) itemView.findViewById(R.id.txt_name);
            txtAdress = (TextView) itemView.findViewById(R.id.txt_addres);
            txtVersion = (TextView) itemView.findViewById(R.id.txt_version);
            txtPhone = (TextView) itemView.findViewById(R.id.txt_phone);
            txtEmail = (TextView) itemView.findViewById(R.id.txt_email);
            txtPicture = (TextView) itemView.findViewById(R.id.txt_picture);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent i = new Intent(context, tambahKaryawan.class);
            i.putExtra("id", txtId.getText().toString());
            i.putExtra("nama", txtNama.getText().toString());
            i.putExtra("address", txtAdress.getText().toString());
            i.putExtra("version", txtVersion.getText().toString());
            i.putExtra("phone", txtPhone.getText().toString());
            i.putExtra("email", txtEmail.getText().toString());
            i.putExtra("picture", txtPicture.getText().toString());
            context.startActivity(i);


            /*
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
            alertDialogBuilder.setTitle("Konfirmasi");
            alertDialogBuilder
                    .setMessage("Apakah anda yakin akan mengambil mata kuliah ini ?")
                    .setCancelable(false)
                    .setPositiveButton("Tambah", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            addJadwal();
                        }
                    })
                    .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            */
        }

    }
}
