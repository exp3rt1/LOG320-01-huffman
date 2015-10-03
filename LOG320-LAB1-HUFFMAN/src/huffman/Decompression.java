package huffman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Decompression 
{
    byte[] header;
    byte[] text;
    ArrayList<Character> characterList;
    int offset;
	/* TODO: 
	 * 1- Lire le fichier binaire
	 * 2- Sortir le header
	 * 3- Recréer le fichier grâce au header
	 */
    
    public Decompression(File file) throws UnsupportedEncodingException, FileNotFoundException{
        header = new byte[(int) file.length()];
        text = new byte[(int) file.length()];
        readBinaryFile(file);
        extractHeader();
        
        String textBuffer = new String(text, "UTF-8");
        String newFileContent = decompress(new StringBuffer(textBuffer));
        File newFile = new File(file.getAbsolutePath().substring(0, file.getAbsolutePath().length()-9));
        PrintWriter out = new PrintWriter(newFile);
        out.write(newFileContent);
        out.close();
    }
    
    public void readBinaryFile(File file){
        boolean headerCreated = false;
        try {
            // Use this for reading the data.
            byte[] buffer = new byte[(int) file.length()];
            
            FileInputStream inputStream = new FileInputStream(file);
            int i=0;
            
            while((inputStream.read(buffer)) != -1) {
                if(!headerCreated){
                    header[i] = buffer[i];
                }else{
                    text[i] = buffer[i];
                }
                
                if(buffer[i-1] == 0 && buffer[i-2] == 256)
                    headerCreated = true;
            }
            

            // Always close files.
            inputStream.close();        

        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                file + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + file + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
    }
    
    public String decompress(StringBuffer binaryFile){
        String fileText = binaryFile.substring(0, binaryFile.length()-offset);
        for(int i=0; i != characterList.size(); ++i){
            fileText.replaceAll(characterList.get(i).getBinaire().toString(), Integer.toString(characterList.get(i).getCharacterNumber()));
        }
        
        return fileText;
    }
    
    public void extractHeader(){
        offset = header[0];
        int binaryLength;
        String binaire;
        for(int i=1; i != header.length-2; ++i){
            if(i % 3 == 0){
                binaryLength = Integer.parseInt(Integer.toBinaryString(header[i-1] & 0xFF), 2);
                binaire = Integer.toBinaryString(header[i] & 0xFF);
                
                characterList.add(new Character(Integer.parseInt(Integer.toBinaryString(header[i-2] & 0xFF), 2),
                                                removeAddedZerosToByte(new StringBuffer(binaire), binaryLength)));
            }
        }
        
        faireTrie();
    }
    
    public StringBuffer removeAddedZerosToByte(StringBuffer binaire, int addedZeros){
        binaire.delete(0, addedZeros);
        return binaire;
    }
    
/*************TRIE*************/
    
    public void faireTrie()
    {
        tri_rapide(characterList, 0, characterList.size()-1);
    }
    
    public void echangerValeur(ArrayList<Character> T, int val1, int val2)
    {
        Character temp = T.get(val1);
        T.set(val1, T.get(val2));
        T.set(val2, temp);
    }
    
    // http://laure.gonnord.org/pro/teaching/AlgoProg1112_IMA/trirapide.pdf
    public int partitionner(ArrayList<Character> T, int premier, int dernier)
    {
        int indice2 = dernier, indice1 = premier+1, pivot = premier;
        
        while(indice1 < indice2)
        {
            if(T.get(indice1).getBinaire().length() <= T.get(pivot).getBinaire().length())
                indice1++;
            else
            {
                if(T.get(indice2).getBinaire().length() >= T.get(pivot).getBinaire().length())
                    indice2--;
                else
                {
                    echangerValeur(T, indice1, indice2);
                    indice1++;
                    indice2--;
                }
            }
        }
        
        if(indice1 == indice2)
        {
            if(T.get(indice1).getBinaire().length() <= T.get(pivot).getBinaire().length())
                indice2++;
            else
                indice1--;
        }
        else
        {
            indice1--;
            indice2++;
        }
        
        echangerValeur(T, premier, indice1);
        return indice1;
    }

    public void tri_rapide(ArrayList<Character> T, int premier, int dernier)
    {
        int pivot;
        
        if(premier == dernier-1)
        {
            if(T.get(premier).getBinaire().length() > T.get(dernier).getBinaire().length())
            {
                echangerValeur(T, premier, dernier);
            }
        }
        else
        {
            if(premier < dernier)
            {
                // int pivot = choix_pivot(T, premier, dernier);
                pivot = partitionner(T, premier, dernier);
                tri_rapide(T, premier, pivot-1);
                tri_rapide(T, pivot+1, dernier);
            }
        }
        
    }
    // Fin
    /********************************/

}
