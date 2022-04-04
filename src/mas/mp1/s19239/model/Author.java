package mas.mp1.s19239.model;

import mas.mp1.s19239.model.exceptions.ModelValException;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Author implements Serializable {
    private int id;
    private String Name;

    private Type thetype;

    private Set<Book> books = new HashSet<>();

    public Author(int id, String name) {
        this.id = id;
       setName(name);

    }

    public Type getThetype() {
        return thetype;
    }

public void addType(Type thetype){
        if(this.thetype==thetype){
            return;
        }
        if(thetype==null){throw new ModelValException("The type of the author cant be nyll");}
        if(this.thetype !=null ){throw new ModelValException("Need to remove the existing type");}

        this.thetype=thetype;
        thetype.addAuthor(this);
}
    public void removeType(Type thetype){
        if(thetype==null){
            throw new ModelValException("The author is already null");
        }
        if(this.thetype==null){
           return;
        }
        Type tmp=this.thetype;
        this.thetype=null;
        tmp.removeAuthor(this);
    }

    public Set<Book> getBooks() {
        return Collections.unmodifiableSet(books);
    }

    public void addBookAuthor(Book thebook){
if(thebook==null){
    throw new ModelValException("book which is null cant be assinged to the athor!!!");
} if (books.contains(thebook)){
    return;
        }
this.books.add(thebook);
thebook.addAuthor(this);
    }

    public void removeBookAuthor(Book thebook){
        if(books.contains(thebook)){
            if(this.books.size()<2){
                throw new ModelValException("Author should have at least one book");
            }
            this.books.remove(thebook);
            thebook.removeAuthor(this);
        }

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        if(name==null||name.isBlank()){
            throw new ModelValException("name of the author cant be null");
        }
        Name = name;
    }
}
