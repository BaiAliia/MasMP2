package mas.mp1.s19239.model;

import mas.mp1.s19239.model.exceptions.ModelValException;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Book implements Serializable {
    private static List<Book> extent =new ArrayList<>();

    private long id;
    private String name;
    private Set<String> Genres=new HashSet<>();
    private Language language;
    private int originalPrice;
    private Sale sale;
    private Edition edit;//now the assosiasion is done with attribute edition
  //  private Publisher publisher;
    private static int totalNOfbooks=0;

    private Set<Author> authors=new HashSet<>();//im prety sure that some books have more than one author






//mandatory attributes

    public Book(long id, String name,String genre,Language language,int price,Sale sale,Edition edit,Author a) {
        this.id = id;
        setName(name);
        addGenre(genre);
        setLanguage(language);
        setOriginalPrice(price);
        setSale(sale);
        totalNOfbooks++;
        setEdit(edit);
        extent.add(this);
        this.addAuthor(a);
    }
    public Book(long id, String name,String genre,Language language,int price,Sale sale) {
        this.id = id;
        setName(name);
        addGenre(genre);
        setLanguage(language);
        setOriginalPrice(price);
        setSale(sale);
        totalNOfbooks++;
        extent.add(this);
    }

    public Edition getEdit() {
        return edit;
    }

    public void setEdit(Edition edit) {
        if(edit!=null) {
            if (edit.getThebook() == this) {
                if(this.edit ==null){
                    this.edit = edit;
                }else{
                    Edition edition =this.edit;
                    this.edit=null;
                    edition.remove();
                    this.edit = edit;
                }

            } else {
                throw new ModelValException("Setting edition in the book went wrong,the edition doesnt match");
            }
        }else{
           if( this.edit!=null){
             Edition edition =this.edit;
             this.edit=null;
             edition.remove();
           }

        }

    }

    public Set<Author> getAuthors() {
       return Collections.unmodifiableSet(authors);
    }

  public void addAuthor(Author author){

      if(author==null){
          throw new ModelValException("book which is null cant be assinged to the athor!!!");
      } if (authors.contains(author)){
          return;
      }
      if(authors.size()>9){
          throw new ModelValException("The book can have 9 authors at most");
      }
      this.authors.add(author);
      author.addBookAuthor(this);

  }
public void removeAuthor(Author author){

    if(this.authors.contains(author)){
        this.authors.remove(author);
        author.removeBookAuthor(this);
    }
}
    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name==null||name.isBlank()){
            throw new ModelValException("The name cant be null or empty ");
        }
        this.name = name;
    }

    public Set<String> getGenres() {
        return Collections.unmodifiableSet(this.Genres);
    }

    public void addGenre(String genre){
        if(genre==null||genre.isBlank()){
            throw new ModelValException("The genre cant be null or empty ");
        }
        this.Genres.add(genre);
    }
    public void removeGenre(String genre){
        if(this.Genres.size()<2){
            throw new ModelValException("The book should have at least one genre ,cant remove the only genre");
        }
        this.Genres.remove(genre);

    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        if(sale==null){
            throw new ModelValException("Invalid sale option ");

        }
        this.sale = sale;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        if(language==null){
            throw new ModelValException("language cant be null ");

        }
        this.language = language;
    }

    public int getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(int originalPrice) {
        if(originalPrice<=0){
            throw new ModelValException("Price cant be negative value ");

        }
        this.originalPrice = originalPrice;
    }


    public int getCurrentPrice() {
       int currentprice = (int)(this.originalPrice-(this.originalPrice*this.sale.getPercentage()));
      return  currentprice;
    }

    public int getCurrentPrice(Sale sale) {
        if(sale!=null) {
            int currentprice = (int) (originalPrice - (originalPrice * sale.getPercentage()));
            setSale(sale);
            return currentprice;
        }else {
            throw new ModelValException("sale cant be null");
        }
    }



   // public Publisher getPublisher() {
   //     return publisher;
   // }
/*
    public void setPublisher(Publisher publisher) {
        if(this.publisher==publisher){
            return;
        }


        if (this.publisher == null && publisher != null) {
            this.publisher = publisher;
            publisher.addBook(this);
        } else if (publisher != null && this.publisher != null) {
           this.tempP();
            this.publisher = publisher;
            publisher.addBook(this);
        } else if (publisher == null && this.publisher != null) {
            this.tempP();

        }
    }

    public void tempP(){
        Publisher tmp =this.publisher;
        this.publisher=null;
        tmp.removeBook(this);
    }
    */

    public static int getTotalNOfbooks() {
        return totalNOfbooks;
    }

    public static List<Book> getExtent() {
        return Collections.unmodifiableList(extent);
    }
    public static void saveExtent() throws IOException {
     try(   ObjectOutputStream saving= new ObjectOutputStream(new FileOutputStream("D:\\Lessons\\books.ser"))){
         saving.writeObject(extent);
     }
    }
    public static void loadExtent() throws IOException, ClassNotFoundException {
        try(   ObjectInputStream saving= new ObjectInputStream(new FileInputStream("D:\\Lessons\\books.ser"))){
            extent=(List<Book>) saving.readObject();
        }
    }


    public static List<Book> findByGenre(String genre){
        if(genre==null||genre.isBlank()){
            return new ArrayList<>();
        }
        return extent.stream()
                .filter(b ->b.Genres.contains(genre))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Name: '" + this.name + "', OriginalPrice: '" + this.originalPrice+"', Genre: '" + this.getGenres() ;
    }
}
