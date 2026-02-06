//  public class name
public class Earthquake {
    // string variables
    String id, place;
    // double variables
    double time, lat, lon, magnitude;

    // constructor to initialize a new earthquake object
    public Earthquake(String id, double time, String place, double lat, double lon, double magnitude) {
        // assign the id
        this.id = id;
        // assign the lattitude
        this.lat = lat;
        // assign the longitude
        this.lon = lon;
        // assign the time
        this.time = time;
        // assign the magnitude
        this.magnitude = magnitude;
        // assign the place
        this.place = place;
    }

    // returns the id
    public String getId() {
        return id;
    }

    // returns the place
    public String getPlace() {
        return place;
    }

    // returns the time
    public double getTime() {
        return time;
    }

    // returns the latitude
    public double getLat() {
        return lat;
    }

    // returns the longitude
    public double getLon() {
        return lon;
    }

    // returns the magnitude
    public double getMagnitude() {
        return magnitude;
    }
}