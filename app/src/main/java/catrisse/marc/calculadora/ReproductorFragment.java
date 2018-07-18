package catrisse.marc.calculadora;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReproductorFragment extends Fragment {
    View rootView;
    Button start;
    Button stop;
    Button load;
    TextView loaded;
    MediaPlayer mediaPlayer;
    Context c;
    Uri current_playing;
    final int REQUEST_CODE_CONSTANT_EXTERNAL_STORAGE = 1;


    public ReproductorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_reproductor, container, false);
        init();
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == 1){
                try {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                    current_playing = data.getData();
                    mediaPlayer.setDataSource(getActivity().getApplicationContext(),current_playing);
                    String fileName = getFileName(current_playing);
                    loaded.setText(String.format("Loaded: %s", fileName));
                    start.setEnabled(true);
                    stop.setEnabled(true);
                    //TODO assignar el nombre al text field
                    Log.v("bu","bu");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = c.getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        return result;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_CODE_CONSTANT_EXTERNAL_STORAGE:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    get_musica_intent();
                }else{
                    //no hay permiso
                    Log.e("Permiso", "No hay permiso");
                }
        }
    }

    private void init() {
        c = getActivity().getApplicationContext();
        mediaPlayer = new MediaPlayer();
        start = rootView.findViewById(R.id.buttonStartMusic);
        stop = rootView.findViewById(R.id.buttonStopMusic);
        load = rootView.findViewById(R.id.buttonLoadMusic);
        loaded = rootView.findViewById(R.id.textViewLoadedMusic);
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solicitar_permiso();
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                if(current_playing != null){
                    try {
                        mediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mediaPlayer.start();
                }
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
            }
        });
    }

    private void solicitar_permiso() {
        if (ContextCompat.checkSelfPermission(c, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_CODE_CONSTANT_EXTERNAL_STORAGE); //pedimos el permiso ya que no lo tenemos lo captura onRequestPermissionsResult
        } else {
            get_musica_intent(); //tenemos el permiso, hacemos el intent para seleccionar el contenido
        }
    }
    private void get_musica_intent(){
        //Intent.ACTION_PICK me petaba en android 7.1 ninguna app lo tenia definido
        Intent intent1 = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
        intent1.setType("audio/*");
        startActivityForResult(intent1, 1);
    }

}
