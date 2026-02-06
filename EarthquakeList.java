import java.util.Scanner;
//this class read the stored earthquakes
//parse them to required variable type and
//instantiate the objects
public class EarthquakeList {

//this method reads the lines
    public Earthquake getNextEq(Scanner sc) {
        //if there is no next line exit
            if (!sc.hasNext()) return null;
//initialize the variables to default values
            String content = "";
            boolean inEarthquake = false;
            while (sc.hasNextLine()) {
                //declare the line with the first line
                String line = sc.nextLine();
                if (line.contains("<earthquake>")) {
                    inEarthquake = true;
                }
                //if begining of the line is earthquake
                //this if statement runs
                if (inEarthquake) {
                    //append the context
                    content += line + " ";
                }
                //if end of the list equal to earthquake break
                if (line.contains("</earthquake>")) {
                    break;
                }
            }
                //if content does not have anything return null
            if (content.equals("")) return null;
             //call the method extract tag seperate the types with content and declare
        //remaining part to id
            String id = extractTag(content, "<id>", "</id>");
        //call the method extract tag seperate the types with content and declare
        //remaining part to time
            double time = Double.parseDouble(extractTag(content, "<time>", "</time>"));
        //call the method extract tag seperate the types with content and declare
        //remaining part to place
            String place = extractTag(content, "<place>", "</place>");
        //call the method extract tag seperate the types with content and declare
        //remaining part to coordinates
            String coordsStr = extractTag(content, "<coordinates>", "</coordinates>");
        //call the method extract tag seperate the types with content and declare
        //remaining part to magnitude
            double magnitude = Double.parseDouble(extractTag(content, "<magnitude>", "</magnitude>"));
           //do array for distinguish the coordinates seperate them with comma
            String[] cParts = coordsStr.split(",");
            //parse and declare to longitude
            double lon = Double.parseDouble(cParts[0].trim());
             //parse and declare to latitude
            double lat = Double.parseDouble(cParts[1].trim());

            // create earthquake object with variables
            return new Earthquake(id, time, place, lat, lon, magnitude);
        }

    private String extractTag(String text, String startTag, String endTag) {
        // Find the index of the start tag and add its length to get to the data
        int start = text.indexOf(startTag) + startTag.length();
        // Find the index where the end tag begins
        int end = text.indexOf(endTag);
        // Extract the substring and remove any surrounding whitespace
        return text.substring(start, end).trim();
    }
}