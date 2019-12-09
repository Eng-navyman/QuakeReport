package abdullah.alialdin.quakereport;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class EarthQuakeDataModel extends AndroidViewModel {
    private EarthquakeLiveData earthquakeLiveData;
    public EarthquakeLiveData getEarthquakeLiveData(){
        return earthquakeLiveData;
    }
    public EarthQuakeDataModel(@NonNull Application application) {
        super(application);
        earthquakeLiveData = new EarthquakeLiveData(application);
    }

    public class EarthquakeLiveData extends MutableLiveData<List<EarthQuake>> {
        List<EarthQuake> earthQuakesList;
        private final Context context;
        private static final String USGS_REQUEST_URL =
                "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=20";


        public EarthquakeLiveData(Context context){
            this.context = context;
            LoadEarthquakeInfo();
        }

        private void LoadEarthquakeInfo() {
            earthQuakesList = QueryUtils.fetchEarthQuakeData(USGS_REQUEST_URL);
        }

        @Override
        public void setValue(List<EarthQuake> value) {
            super.setValue(earthQuakesList);
        }
    }
}
