// Encapsulates the HashMap of configuration parameters

package CommonPackage;

import java.io.*;
import java.util.HashMap;

public class Configuration {
    private static HashMap<String, String> config = new HashMap<>();

    // method: readConfigurationFile
    // reads and returns the map of configuration parameters
    public static HashMap readConfigurationFile(){
        String line;
        try (InputStream in = Configuration.class.getClassLoader().getResourceAsStream("configuration.csv");
             BufferedReader br = new BufferedReader(new InputStreamReader(in))){
            while((line = br.readLine()) != null){
                String[] fields = line.split(",");
                config.put(fields[0], fields[1]);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return config;
    }
}
