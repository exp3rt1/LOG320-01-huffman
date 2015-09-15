package huffman;

public class Character {
    private int characterNumber;
    private int occurence;
    private String binaire;
    
    public Character(int characterNumber, int occurence){
        this.characterNumber = characterNumber;
        this.occurence = occurence;
    }
    
    
    public int getOccurence(){
        return occurence;
    }
    
    public String getBinaire(){
        return binaire;
    }
    
    public void setOccurence(int occurence){
        this.occurence = occurence;
    }
    
    public void setBinaire(String binaire){
        this.binaire = binaire;
    }

    public int getCharacterNumber() {
        return characterNumber;
    }

    public void setCharacterNumber(int characterNumber) {
        this.characterNumber = characterNumber;
    }
    
    public int Compare(Character character){
        if(this.getOccurence() > character.getOccurence())
            return 1;
        else
            return 0;
    }
    
    public boolean equals(Character o){
        if (this.getCharacterNumber() == o.getCharacterNumber()){
                return true;
        }
        return false;
    }
}
