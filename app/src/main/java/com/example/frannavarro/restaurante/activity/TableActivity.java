package com.example.frannavarro.restaurante.activity;

import android.app.AlertDialog;
import android.app.FragmentManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ViewSwitcher;

import com.example.frannavarro.restaurante.R;
import com.example.frannavarro.restaurante.fragment.TableListFragment;
import com.example.frannavarro.restaurante.model.Plate;
import com.example.frannavarro.restaurante.model.Plates;
import com.example.frannavarro.restaurante.model.Table;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class TableActivity extends AppCompatActivity implements TableListFragment.OnTableListSelectedListener {

    public static final String TABLE_EXTRA = "TABLE_EXTRA";
    public static final String PLATES_EXTRA = "PLATES_EXTRA";

    public static final int SETTINGS_RESULT_CODE = 1;
    private static final int LOADING_VIEW_INDEX = 0;
    private static  Plates plates;
    private ViewSwitcher viewSwitcher;
    private TableListFragment tableListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_list);

        viewSwitcher = (ViewSwitcher) findViewById(R.id.view_switcher);

        downloadPlates();
        FragmentManager fm = getFragmentManager();

        if(fm.findFragmentById(R.id.table_list_fragment) == null){
            tableListFragment = new TableListFragment();
            tableListFragment.setOnTableListSelectedListener(this);
            fm.beginTransaction()
                    .add(R.id.table_list_fragment,tableListFragment)
                    .commit();


        }

    }

    private void downloadPlates() {
        AsyncTask<Void,Void,Plates> plateDownloader = new AsyncTask<Void, Void, Plates>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                // Mostramos la vista de loading
                viewSwitcher.setDisplayedChild(LOADING_VIEW_INDEX);
            }

            @Override
            protected Plates doInBackground(Void... voids) {

                URL url = null;
                InputStream input = null;

                try {

                    url = new URL("http://www.mocky.io/v2/584e69ff12000045093949c2");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.connect();
                    int responseLength = con.getContentLength();
                    byte data[] = new byte[1024];
                    long currentBytes = 0;
                    int downloadedBytes;
                    input = con.getInputStream();
                    StringBuilder sb = new StringBuilder();
                    while ((downloadedBytes = input.read(data)) != -1 && !isCancelled()) {
                        sb.append(new String(data, 0, downloadedBytes));


                    }



                    JSONObject jsonRoot = new JSONObject(sb.toString());
                    JSONArray platesArray = jsonRoot.getJSONArray("plates");

                    plates = new Plates();

                    for (int i = 0; i < platesArray.length(); i++) {
                        JSONObject plateObject = platesArray.getJSONObject(i);
                        String name = plateObject.getString("name");
                        String image = plateObject.getString("image");
                        JSONArray ingredientsArray = plateObject.getJSONArray("ingredients");
                        String[] ingredients = new String[ingredientsArray.length()];
                        for (int j = 0; j < ingredientsArray.length(); j++) {
                            ingredients[j] = ingredientsArray.getString(j);
                        }
                        float price = (float) plateObject.getDouble("price");


                        plates.addPlate(new Plate(name,image, ingredients, price));
                    }
                    Thread.sleep(3000);

                    return plates;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onProgressUpdate (Void...voids){
                super.onProgressUpdate(voids);
            }

            @Override
            protected void onPostExecute (Plates plates) {
                super.onPostExecute(plates);

                if (plates != null) {
                    viewSwitcher.setDisplayedChild(LOADING_VIEW_INDEX + 1);
                    TableActivity.plates = plates;
                }else {
                    // Ha habido algún error, se lo notificamos al usuario
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(TableActivity.this);
                    alertDialog.setTitle(R.string.error);
                    alertDialog.setMessage(R.string.plates_download_error);
                    alertDialog.setPositiveButton("Reintentar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            downloadPlates();
                        }
                    });

                    alertDialog.show();
                }
            }
        };

        plateDownloader.execute();
    }

    @Override
    public void onTableListSelected(Table table) {

        Intent intent = new Intent(getApplicationContext(),PlateListActivity.class);
        intent.putExtra(TABLE_EXTRA,table);
        intent.putExtra(PLATES_EXTRA,plates);
        startActivity(intent);


    }



}
