import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        // Create a Scanner for user input
        Scanner keyboard = new Scanner(System.in);
        // Ask for the watcher file
        System.out.print("Enter watcher file name: ");
        String wFile = keyboard.nextLine();
        // Ask for the earthquake file
        System.out.print("Enter earthquake file name: ");
        String eqFile = keyboard.nextLine();
        // Initialize management classes and specialized parser
        EarthquakeList parser = new EarthquakeList();
        EarthquakeMonitor monitor = new EarthquakeMonitor();
        WatcherList watchers = new WatcherList();

        // try-with-resources to automatically close file scanners
        try (Scanner wScanner = new Scanner(new File(wFile));
             Scanner eqScanner = new Scanner(new File(eqFile))) {
            // calling the next earthquake method to continue
            Earthquake nextEq = parser.getNextEq(eqScanner);
            String nextWLine = null;
            //this loop checks if the line is empty or not
            //if the empty skip
            while (wScanner.hasNextLine()) {
                String line = wScanner.nextLine().trim();
                if (!line.isEmpty()) {
                    nextWLine = line;
                    break;
                }
            }

            // Continue while at least one event remains in either file
            while (nextEq != null || nextWLine != null) {
                // If earthquake exists, get its time otherwise use a large sentinel
                double eqTime = (nextEq != null) ? nextEq.getTime() : 99999.0;
                double wTime = 99999.0;

                // If a watcher command exists, parse the timestamp (first part of the line)
                if (nextWLine != null) {
                    wTime = Double.parseDouble(nextWLine.split("\\s+")[0]);
                }

                // this if statement compare the existing event time
                if (eqTime < wTime) {
                    // It is an Earthquake event
                    //adding the earthquake
                    monitor.processNewEarthquake(nextEq);
                    //send the message to watchers if the earthquake occur close to
                    watchers.checkAlerts(nextEq);
                    // if file has next earthquake
                    //declare to nextEq
                    nextEq = parser.getNextEq(eqScanner);
                    //if watcher request earlier or equal to earthquake time
                    //this scope runs
                } else {
                    // It is a Watcher Request event
                    handleWatcherCommand(nextWLine, watchers, monitor);
                    //this scope checks the line is null or not and declare the row to line
                    //first do null
                    nextWLine = null;
                    while (wScanner.hasNextLine()) {
                        //declare the row to line
                        String line = wScanner.nextLine().trim();
                        if (!line.isEmpty()) {
                            nextWLine = line;
                            break;
                        }
                    }
                }
            }
            // Handle cases where files provided by the user do not exist
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        }
    }
    //this method read the rows and declare appropriate variables

    private static void handleWatcherCommand(String line, WatcherList wm, EarthquakeMonitor em) {
        // Split command line by spaces
        String[] parts = line.split("\\s+");
        // The command type (add, delete, or query-largest)
        double currentTime = Double.parseDouble(parts[0]);
        String command = parts[1];

        // Route to appropriate function based on command string
        if (command.equals("add")) {
            double lon = Double.parseDouble(parts[2]);
            double lat = Double.parseDouble(parts[3]);
            wm.addWatcher(lat, lon, parts[4]);
        } else if (command.equals("delete")) {
            // call the method to remove
            wm.removeWatcher(parts[2]);
        } else if (command.equals("query-largest")) {
            // show the largest earthquake in the list
            em.queryLargest(currentTime);
        }
    }
}