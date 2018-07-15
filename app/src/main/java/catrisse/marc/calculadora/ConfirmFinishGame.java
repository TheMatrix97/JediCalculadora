package catrisse.marc.calculadora;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

import catrisse.marc.utils.BDController;

public class ConfirmFinishGame extends DialogFragment {
    private Long points;

    public static ConfirmFinishGame newInstance(Long points) {
        ConfirmFinishGame dialog = new ConfirmFinishGame();
        dialog.points = points;
        return dialog;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(String.format("Has conseguido %s puntos, ¿Quieres guardar tu puntuación?",points.toString()))
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //guardar puntuacion
                        DrawerActivity a = (DrawerActivity) getActivity();
                        if (a != null) {
                            User aux = a.getUser();
                            Puntuacion p = new Puntuacion(aux.getUsername(),Double.valueOf(points),System.currentTimeMillis());
                            BDController.getInstance(getActivity().getApplicationContext()).addpuntuacion(aux,p); //añadimos y guardamos la info en la BD
                            Toast.makeText(getActivity().getApplicationContext(),"Guardado", Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(getActivity().getApplicationContext(), "Error al guardar", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
