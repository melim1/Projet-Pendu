package edu.ezip.ing1.pds;

public class GuessMessage {

   private char lettre;
   public GuessMessage(char lettre){
       this.lettre = lettre;

   }
   public char getLettre(){
       return lettre;
   }
   public void setLettre(char lettre){
       this.lettre= lettre;
   }
   public String toString(){
       return "GUESS\n" + lettre;
   }

}

