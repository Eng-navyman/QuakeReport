package abdullah.alialdin.quakereport.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import abdullah.alialdin.quakereport.R;
import abdullah.alialdin.quakereport.pojo.EarthQuake;

public class EarthQuakeAdapter extends ArrayAdapter<EarthQuake> {
    EarthQuakeAdapter(@NonNull Context context, ArrayList<EarthQuake> earthQuakes) {
        super(context, 0, earthQuakes);
    }

    @NonNull
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_item, parent, false);
        }

        EarthQuake currentEarthquake = getItem(position);
        TextView magnitudeTV = convertView.findViewById(R.id.magnitude);
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTV.getBackground();
        assert currentEarthquake != null;
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);
        magnitudeTV.setText(formatMagnitude(currentEarthquake.getMagnitude()));

        String location = currentEarthquake.getLocation();
        String nearLocation = formatLocation(location)[0];
        String exactLocation = formatLocation(location)[1];

        TextView nearLocationTV = convertView.findViewById(R.id.distance);
        nearLocationTV.setText(nearLocation);
        TextView locationTV = convertView.findViewById(R.id.location);
        locationTV.setText(exactLocation);

        TextView dateTV = convertView.findViewById(R.id.date);
        TextView timeTV = convertView.findViewById(R.id.time);
        Date dateObject = new Date(currentEarthquake.getTime());
        String formattedDate = formatDate(dateObject);
        String formattedTime = formatTime(dateObject);
        dateTV.setText(formattedDate);
        timeTV.setText(formattedTime);
        return convertView;
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor ){
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

    private String formatMagnitude(double mag){
        DecimalFormat formatter = new DecimalFormat("0.0");
        return formatter.format(mag);
    }

    private String formatDate(Date dateObject){
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date timeObject){
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("h:mm a");
        return dateFormat.format(timeObject);
    }

    private String[] formatLocation (String location){
        String exactLocation;
        String nearLocation;
        if (location.contains("of")){
            String[] split = location.split("of ");
            nearLocation = split[0] + " of";
            exactLocation = split[1];
        } else {
            nearLocation = "Near The";
            exactLocation = location;
        }
        return new String[]{nearLocation, exactLocation};
    }
}

