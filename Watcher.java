// public class name
public class Watcher {
    // string variable
    String name;
    // double variables
    double lat, lon;

    // constructor to initialize a new watcher object
    public Watcher(String name, double lat, double lon) {
        // assign the lattitude as lat
        this.lat = lat;
        // assign the longitude as lon
        this.lon = lon;
        // assign the name
        this.name = name;
    }

    // getter method for name
    public String getName() {
        // return the name
        return name;
    }

    // getter method for lattitude
    public double getLat() {
        // return the latitude
        return lat;
    }

    // getter method for longitude
    public double getLon() {
        // return the longitude
        return lon;
    }
}