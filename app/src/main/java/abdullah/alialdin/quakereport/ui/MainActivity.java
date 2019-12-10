package abdullah.alialdin.quakereport.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import abdullah.alialdin.quakereport.R;
import abdullah.alialdin.quakereport.pojo.EarthQuake;

public class MainActivity extends AppCompatActivity {

    ViewModel earthQuakeViewModel;
    EarthQuakeAdapter earthQuakeAdapter;
    ArrayList<EarthQuake> earthQuakeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView earthquakeListView = findViewById(R.id.list);

        earthQuakeViewModel = ViewModelProviders.of(this).get(ViewModel.class);
        earthQuakeViewModel.getMyLiveData().observe(this, new Observer<ArrayList<EarthQuake>>() {
            @Override
            public void onChanged(ArrayList<EarthQuake> earthQuakes) {
                earthQuakeList = earthQuakes;
                earthQuakeAdapter = new EarthQuakeAdapter(MainActivity.this, earthQuakeList);
                earthquakeListView.setAdapter(earthQuakeAdapter);
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
        });
        MyRepository.getInstance().doSomeStuff();
    }

}
