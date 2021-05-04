package org.germanbeyger.lab5;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.XStreamException;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import org.germanbeyger.lab5.datatypes.Color;
import org.germanbeyger.lab5.datatypes.Color2;
import org.germanbeyger.lab5.datatypes.Coordinates;
import org.germanbeyger.lab5.datatypes.Country;
import org.germanbeyger.lab5.datatypes.Person;
import org.germanbeyger.lab5.datatypes.TargetCollection;
import org.germanbeyger.lab5.datatypes.TicketType; 

public final class XMLCollectionProcessor {
    // enforcing singleton pattern
    private XMLCollectionProcessor() {
        
    }

    private static XStream createXStream() {
        XStream xstream = new XStream(new StaxDriver());
        xstream.alias("targetCollection", TargetCollection.class);
        xstream.alias("person", Person.class);
        xstream.alias("coordinates", Coordinates.class);
        xstream.alias("color", Color.class);
        xstream.alias("color2", Color2.class);
        xstream.alias("country", Country.class);
        xstream.alias("tickettype", TicketType.class);

        return xstream;
    }

	public static void save(TargetCollection targetCollection, String filepath) {
        XStream xstream = createXStream();
        PrintWriter fstream = null;
        try {
            String xml = xstream.toXML(targetCollection);
            fstream = new PrintWriter(new FileOutputStream(filepath)); 
            fstream.write(xml);
        } 
        catch (FileNotFoundException e) {
            System.out.printf("The file %s can't be opened for writing. \n", filepath);
            
        }
        catch (XStreamException e) {
            
        }
        finally {
            if (fstream != null)
                fstream.close();
        }
	}

    public static TargetCollection load(String filepath) {
        final String ERROR_MESSAGE = "Oops! Something isn't okay and we can't load a collection. ";
        XStream xstream = createXStream();
        TargetCollection result = null;

        try (FileInputStream fstream = new FileInputStream(filepath)) {
            InputStreamReader inputStream = new InputStreamReader(fstream);
            BufferedReader bufStream = new BufferedReader(inputStream); 

            String xml = bufStream.readLine(); 
            result = (TargetCollection)xstream.fromXML(xml);
        } 
        catch (FileNotFoundException e) {
            System.out.printf("Problems accessing this file %s. \n", filepath);
        }
        catch (XStreamException | IOException e ) {
            System.out.println(ERROR_MESSAGE);
            System.exit(1);
        }
        return result;
    }

}
