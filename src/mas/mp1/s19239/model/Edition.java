package mas.mp1.s19239.model;

import mas.mp1.s19239.model.Book;
import mas.mp1.s19239.model.Publisher;
import mas.mp1.s19239.model.exceptions.ModelValException;

import java.io.Serializable;
import java.time.LocalDate;

public class Edition implements Serializable {
    private Book thebook;
    private Publisher  thepub;


    private LocalDate releaseDate ;
    private int ed;


    public Edition(Book thebook, Publisher thepub, LocalDate releaseDate, int ed) {
      setThebook(thebook);
       setThepub(thepub);
        this.releaseDate = releaseDate;
        this.ed = ed;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        if(releaseDate==null){
            throw new ModelValException("invalid date ");
        }
        this.releaseDate = releaseDate;
    }

    public Book getThebook() {
        return thebook;
    }

    private void setThebook(Book thebook) {
        if(thebook == null){
            throw  new ModelValException("The book cant be null in the edition");
        }
        this.thebook = thebook;
        thebook.setEdit(this);
    }

    public Publisher getThepub() {
        return thepub;
    }

    private void setThepub(Publisher thepub) {
        if(thepub==null){
            throw  new ModelValException("The publisher cant be null in the edition");
        }
        this.thepub = thepub;
        thepub.addBook(this);
    }

    public int getEd() {
        return ed;
    }

    public void setEd(int ed) {
        this.ed = ed;
    }

    public void remove(){
        if(this.thebook!=null){
Book tmp =this.thebook;
this.thebook=null;
tmp.setEdit(null);
        }
        if(this.thepub!=null){
            Publisher tmp =this.thepub;
            this.thepub=null;
            tmp.removeBook(this);
        }
    }
}
