package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DatosCantantes extends AppCompatActivity {
    public static final String TAG = "Zootopia";
    public static final String BASE_URL = "https://zootopia-api.herokuapp.com/api/";
    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private ListaAdapter listaCantantesAdapter;
    private int offset;
    private boolean aptoParaCargar;
    private Call<Cantantes> cantantesCall;
    //ArrayList<Estudiante> listaEstudiante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_cantantes);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        listaCantantesAdapter = new ListaAdapter(this);
        recyclerView.setAdapter(listaCantantesAdapter);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        obtenerDatos();
    }


    private void obtenerDatos(){
        MusicServices service = retrofit.create(MusicServices.class);
        Call<CantanteResponse> zResponseCall = service.getListaHabitantes();


        zResponseCall.enqueue(new Callback<CantanteResponse>() {

            @Override
            public void onResponse(Call<CantanteResponse> call, Response<CantanteResponse> response) {
                CantanteResponse zResponse = response.body();
                ArrayList<Cantantes> listaHabitante = zResponse.getData();
                listaCantantesAdapter.adicionarListaCantantes(listaHabitante);

            }

            @Override
            public void onFailure(Call<CantanteResponse> call, Throwable t){
                //cargar = true;
                Log.e(TAG, "onFailure: " + t.getMessage());
            }});
    }
}

