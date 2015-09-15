package huffman;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Compression {
    
    public Character[] readFile(File file){
        try{
            InputStream inputstream = new FileInputStream(file);
            Map<Integer, Character> map = new HashMap<Integer, Character>();
            Character[] characterList;
            int character = -2;
            character = inputstream.read();
            while(character != -1) {
              if(map.containsKey(character)){
                  map.get(character).setOccurence(map.get(character).getOccurence()+1);
              }
              else{
                  map.put(character, new Character(character, 1));
              }
              character = inputstream.read();
            }
            
            characterList = (Character[]) map.values().toArray();
            quickSort(characterList, 0, characterList.length-1);
            
            return characterList;
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
    
    
    public void Compress(){
        
    }
    
    public void ChangeToBinary(Character[] characterArr){
        
    }
    
    // http://www.algolist.net/Algorithms/Sorting/Quicksort
    int partition(Character arr[], int left, int right)
    {
          int i = left, j = right;
          Character tmp;
          int pivotIndex = (left + right) / 2; 
          int pivot = arr[pivotIndex].getOccurence();
         
          while (i <= j) {
                while (arr[i].getOccurence() < pivot)
                      i++;
                while (arr[j].getOccurence() > pivot)
                      j--;
                if (i <= j) {
                      tmp = arr[i];
                      arr[i] = arr[j];
                      arr[j]= tmp;
                      i++;
                      j--;
                }
          }
         
          return i;
    }
     
    void quickSort(Character arr[], int left, int right) {
          int index = partition(arr, left, right);
          if (left < index - 1)
                quickSort(arr, left, index - 1);
          if (index < right)
                quickSort(arr, index, right);
    }
    // end of reference
}
