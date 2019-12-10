package abdullah.alialdin.quakereport.pojo;

import android.net.Uri;

public class EarthQuake {

    private double mMagnitude;
    private String mLocation;
    private long mTime;
    private Uri mUrl;

    public EarthQuake(double magnitude, String location, long time, Uri url) {
        mMagnitude = magnitude;
        mLocation = location;
        mTime = time;
        mUrl = url;
    }

    public Uri getUrl() {
        return mUrl;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public long getTime() {
        return mTime;
    }
}
