package mas.mp1.s19239.model;

import mas.mp1.s19239.model.exceptions.ModelValException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Type {
    private String name;
   private Map<Integer,Author> authors = new HashMap<>();

    public Type(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name==null||name.isBlank()){
            throw new ModelValException("The type name of the author cant be null or empty");
        }
        this.name = name;
    }

    public Map<Integer, Author> getAuthors() {
        return Collections.unmodifiableMap(authors);
    }

  public void addAuthor(Author a){
      if(a==null){
          throw new ModelValException("The author cant be null to add");
      }
      if(authors.containsKey(a.getId())){
          return;
      }
      this.authors.put(a.getId(),a);
      a.addType(this);
  }
    public void removeAuthor(Author a){
        if(a==null){
            throw new ModelValException("The author cant be null to remove");
        }
        if(authors.containsKey(a.getId())){
        this.authors.remove(a.getId(),a);
        a.removeType(this);
        }
    }

}
