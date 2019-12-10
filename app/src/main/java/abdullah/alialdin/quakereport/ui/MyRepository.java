package abdullah.alialdin.quakereport.ui;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import abdullah.alialdin.quakereport.pojo.EarthQuake;

class MyRepository {
    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=20";
    private static MyRepository instance;

    @NonNull
    private MutableLiveData<ArrayList<EarthQuake>> myLiveData = new MutableLiveData<>();

    static MyRepository getInstance() {
        if (instance == null) {
            synchronized (MyRepository.class) {
                if (instance == null) {
                    instance = new MyRepository();
                }
            }
        }
        return instance;
    }

    @NonNull
    LiveData<ArrayList<EarthQuake>> getMyLiveData() {
        return myLiveData;
    }

    void doSomeStuff() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<EarthQuake> earthQuakes = new ArrayList<>();
                try {
                    Thread.sleep(3000);
                    earthQuakes = QueryUtils.fetchEarthQuakeData(USGS_REQUEST_URL);
                } catch (InterruptedException ignored) {
                }

                myLiveData.postValue(earthQuakes);
            }
        }).start();
    }

}
