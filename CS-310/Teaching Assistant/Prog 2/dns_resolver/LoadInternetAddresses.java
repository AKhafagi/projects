package dns_resolver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import data_structuresH.Hash;
import data_structuresH.HashI;
import exceptions.FileFormatException;

public class LoadInternetAddresses {

	HashI<URL, IPAddress> addresses;
	
	public LoadInternetAddresses() {
		addresses = new Hash<URL, IPAddress>(100);
	}

	public HashI<URL, IPAddress> load_addresses(String filename) throws FileFormatException {
  
        BufferedReader in = null;
        try {
                in = new BufferedReader(new FileReader(filename));
        } 
        catch (FileNotFoundException e) {
                System.err.println("Error: " + filename + " was not found in directory " + System.getProperty("user.dir"));
                System.exit(-1);
        }
      
        String line;
        try {
                while ( ( line = in.readLine() ) !=null) {
                        String [] values = line.split("\t");
                        if (values.length != 2)
                                throw new FileFormatException("The file does not have the right format at line " + line);
                        
                        IPAddress ip = new IPAddress(values[0]);
                        URL url = new URL(values[1]);
                        addresses.add(url, ip);
                }
        }
        catch (IOException e) {
                System.err.println("IO Exception");
                e.printStackTrace();
                System.exit(-1);
        }

        return addresses;
	}
}
