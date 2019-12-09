package abdullah.alialdin.quakereport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EarthQuakeDataModel earthQuakeDataModel;
    EarthQuakeAdapter earthQuakeAdapter;
    List<EarthQuake> earthQuakeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find a reference to the {@link ListView} in the layout
        final ListView earthquakeListView = findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        earthQuakeAdapter = new EarthQuakeAdapter(
                this, new ArrayList<EarthQuake>());
        earthquakeListView.setAdapter(earthQuakeAdapter);

        earthQuakeDataModel = ViewModelProviders.of(this).get(EarthQuakeDataModel.class);
        earthQuakeDataModel.getEarthquakeLiveData().observe(this, new Observer<List<EarthQuake>>() {
            @Override
            public void onChanged(final List<EarthQuake> earthQuakes) {
                earthQuakeList = earthQuakes;
                earthQuakeAdapter.addAll(earthQuakes);
                earthQuakeAdapter.notifyDataSetChanged();
                Log.d("MainActivity:", "Data has updated");
            }
        });
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                EarthQuake earthQuake = earthQuakeList.get(i);
                Intent intent = new Intent(Intent.ACTION_VIEW, earthQuake.getUrl());
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });



    }

}
