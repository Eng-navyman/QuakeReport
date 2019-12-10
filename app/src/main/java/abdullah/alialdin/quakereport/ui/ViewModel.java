package abdullah.alialdin.quakereport.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;

import abdullah.alialdin.quakereport.pojo.EarthQuake;

public class ViewModel extends AndroidViewModel {

    @NonNull
    private LiveData<ArrayList<EarthQuake>> myLiveData;

    public ViewModel(@NonNull Application application) {
        super(application);
        MyRepository repo = MyRepository.getInstance();
        myLiveData = repo.getMyLiveData();
    }

    @NonNull
    LiveData<ArrayList<EarthQuake>> getMyLiveData() {
        return myLiveData;
    }
}
