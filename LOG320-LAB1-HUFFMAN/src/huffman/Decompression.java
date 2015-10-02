package huffman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Decompression 
{
	/* TODO: 
	 * 1- Lire le fichier binaire
	 * 2- Sortir le header
	 * 3- Recréer le fichier grâce au header
	 */
    
    public void readBinaryFile(File file){
     // The name of the file to open.
        String fileName = "temp.txt";

        try {
            // Use this for reading the data.
            byte[] buffer = new byte[(int) file.length()];
            
            FileInputStream inputStream = new FileInputStream(fileName);

            while((inputStream.read(buffer)) != -1) {
                
            }   

            // Always close files.
            inputStream.close();        

        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
    }
    
    public void extractHeader(){
        
    }

}
