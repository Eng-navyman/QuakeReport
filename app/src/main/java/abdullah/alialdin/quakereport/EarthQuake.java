package abdullah.alialdin.quakereport;

import android.net.Uri;

class EarthQuake {

    private double mMagnitude;
    private String mLocation;
    private long mTime;

    public Uri getUrl() {
        return mUrl;
    }

    private Uri mUrl;

    double getMagnitude() {
        return mMagnitude;
    }

    String getLocation() {
        return mLocation;
    }

    long getTime() {
        return mTime;
    }

    EarthQuake (double magnitude, String location, long time, Uri url){
        mMagnitude = magnitude;
        mLocation = location;
        mTime = time;
        mUrl = url;
    }
}
