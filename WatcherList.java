//watcherlist class

public class WatcherList {
    //  DoublyLinkedList for watchers
    private DoublyLinkedList<Watcher> watcherList;

    // constructor to initialize an empty list of watchers
    public WatcherList() {
        this.watcherList = new DoublyLinkedList<>();
    }

    //add watcher method
    public void addWatcher(double lat, double lon, String name) {
        // create a new Watcher and add it to the end of the list
        watcherList.addLast(new Watcher(name, lat, lon));
        // print the addition confirmation message
        System.out.println(name + " is added to the watcher-list");
    }

    //remove watcher method
    public void removeWatcher(String name) {
        // show the size to continue for loop
        int size = watcherList.size();
        // traverse the list to find the watcher to delete
        for (int i = 0; i < size; i++) {
            // temporarily remove the front watcher
            Watcher w = watcherList.removeFirst();
            // if the name does NOT match add them back to the end of the list
            if (!w.getName().equals(name)) {
                watcherList.addLast(w);
            }
            // if it matches we simply do not add them back, effectively deleting them
        }
        // print the removal confirmation message
        System.out.println(name + " is removed from the watcher-list");
    }

    //method for checking alerts
    public void checkAlerts(Earthquake eq) {
        // store list size for iteration
        int size = watcherList.size();
        // iterate through all watchers sequentially
        for (int i = 0; i < size; i++) {
            // access the watcher via list rotation
            Watcher w = watcherList.removeFirst();

            // calculate euclidean distance
            double distOfLatitude=w.getLat()-eq.getLat();
            double distOfLongitude=w.getLon()-eq.getLon();
            double totalDistance=Math.pow(distOfLongitude,2)+Math.pow(distOfLatitude,2);
            double distance = Math.sqrt(totalDistance);

            // notification criteria defined
            if (distance < 2 * Math.pow(eq.getMagnitude(), 3)) {
                // print alert if the watcher is close enough
                System.out.println("Earthquake " + eq.getPlace() + " is close to " + w.getName());
            }

            // return the watcher to the list for future earthquake checks
            watcherList.addLast(w);
        }
    }
}