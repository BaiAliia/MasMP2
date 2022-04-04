package mas.mp1.s19239.model;

import mas.mp1.s19239.model.exceptions.ModelValException;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Publisher  implements Serializable {
   private  String name;
   private  Adress padress;

   private Set<Edition> booksFromP=new HashSet<>();

   public Publisher(String name, Adress padress,Edition the) {
      setName(name);
      this.padress = padress;
      addBook(the);
   }

   public Publisher(String name, Adress padress) {
      setName(name);
      this.padress = padress;
   }



   public void setName(String name) {
      if(name==null ||name.isBlank()){
         throw new ModelValException("Publishers name cant be empty");
      }
      this.name = name;
   }



   public void setAdress(Adress adress) {
      if(adress==null){
         throw new ModelValException("Adress cant be null");
      }
      this.padress = adress;
   }

   public String getName() {
      return name;
   }

   public Adress getPadress() {
      return padress;
   }

   public Set<Edition> getBooksFromP() {
      return Collections.unmodifiableSet(booksFromP);
   }

   public void addBook(Edition newbook){
      if(newbook==null){
         throw new ModelValException("Cant add empty or null edition");
      }
      if(this.booksFromP.contains(newbook)){
         return;
      }
      if(newbook.getThepub()!=this){
         throw new ModelValException("Wrong reference publisher in Edition");
      }
      this.booksFromP.add(newbook);

   }

   public void removeBook(Edition badbook){
      if(booksFromP.contains(badbook)){

         booksFromP.remove(badbook);
         badbook.remove();

      }
   }
}
