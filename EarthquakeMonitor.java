//earthquake monitor class defined
public class EarthquakeMonitor {
    // custom SinglyLinkedList to store the records in order of arrival
    private SinglyLinkedList<Earthquake> eqList;

    // constructor to initialize an empty list of recent earthquakes
    public EarthquakeMonitor() {
        this.eqList = new SinglyLinkedList<>();
    }

     //this method checks the earthquake past than 6 hours or not
    //if the past remove
    public void processNewEarthquake(Earthquake eq) {
        // while loop runs when the list is empty
        while (!eqList.isEmpty()) {
            // If current time minus oldest time is > 6, the record has expired
            if (eq.getTime() - eqList.first().getTime() > 6) {
                // Remove the expired record from the beginning of the list
                eqList.removeFirst();
            } else {
                // Since the list is ordered by time, we stop once we hit a record within 6 hours
                break;
            }
        }

        // Append the new earthquake to the end of the list
        eqList.addLast(eq);
        // Print the required insertion message to standard output
        System.out.println("Earthquake " + eq.getPlace() + " is inserted into the earthquake-list");
    }
    //this method shows us the largest earthquake
    public void queryLargest(double currentTime) {
         //travel the list while empty
        while (!eqList.isEmpty()) {
            //fist check the does  exist removable earthquake
            if (currentTime - eqList.first().getTime() > 6) {
                //if exist remove
                eqList.removeFirst();
            } else {
                break;
            }
        }
             //this statement checks the list is empty or not
        //if empty show the appopriate message and exit
        if (eqList.isEmpty()) {
            System.out.println("No record on list");
            return;
        }

        // Pointer to keep track of the largest earthquake found so far
        Earthquake largest = null;
        // Store the initial size for list rotation
        int size = eqList.size();

        // Traverse the entire list by rotating (removing from front and adding to back)
        for (int i = 0; i < size; i++) {
            // Remove the first earthquake to compare with others
            Earthquake current = eqList.removeFirst();
            // If current earthquake is bigger than largest
            if (largest == null || current.getMagnitude() > largest.getMagnitude()) {
                //declare to change largest
                largest = current;
            }
            // Put the earthquake back at the end to keep the list intact
            eqList.addLast(current);
        }

        // Print the header for the largest earthquake query result
        System.out.println("Largest earthquake in the past 6 hours:");
        // Print the magnitude and location details in the specified format
        System.out.println("Magnitude " + largest.getMagnitude() + " at " + largest.getPlace());
    }
}