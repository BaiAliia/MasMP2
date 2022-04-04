package mas.mp1.s19239.model;

import java.io.IOException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws IOException {
        //little documentation
        //Association with an attribute :Publisher ,Book,Edition(Attribute)
        //Qualified association:Author ,Type
        //Composition :Library ,Section(section cant exist withot the library,but the library can exist without a section)
        //Basic association :Author ,Book (many to many ,since one book can be written by more than one author,and author can whrite many books)


        // Checking methods of Qualified association:Author ,Type
     Author author1=new Author(1,"Agatha Christie");
     Author author2=new Author(2,"J.K.Rowling");
     Author author3=new Author(3,"Leo Tolstoy");

     Type type1 =new Type("Russian poetry");
     Type type2 =new Type("English classic");

     author3.addType(type1);
     author1.addType(type2);
     author2.addType(type2);
     System.out.println("The number of authors of this type before removing: "+ type2.getAuthors().size());
     author2.removeType(type2);
     System.out.println("The number of authors of this type after removing:"+ type2.getAuthors().size());


     System.out.println( "Author3: "+ author3.getThetype().getName()+ "Author1: "+ author1.getThetype().getName());
     if(  author2.getThetype()==null){
         System.out.println("removeType works");
     }

        System.out.println("_______________________________________");
        //Checking methods of Composition :Library ,Section

   Library  lb1=new Library("national",new Adress("Warsaw","Kabaty","03-234"));

     lb1.addSection("poetry",1);
     lb1.addSection("history",2);
     lb1.addSection("sci-fi",3);


     System.out.println( "library1: before removing one of the sections "+lb1.getSections().size());
     lb1.removeSection("poetry",1);
     System.out.println( "library1: after removing one of the sections "+lb1.getSections().size());
        System.out.println("_______________________________________");

     //checking Association with an attribute
     Book book1=new Book(1, "Harry Potter","Fantasy",Language.English,50,Sale.low);
     Publisher pub1=new Publisher("Thepub1",new Adress("Warsaw","thestreet","03-256"));

     Edition edit=new Edition(book1,pub1,LocalDate.of(1869, 05, 16),5);
        System.out.println("Should be 5  :"+ book1.getEdit().getEd());
        System.out.println("before remove Should be 1  :"+ pub1.getBooksFromP().size());
        edit.remove();
        System.out.println("after remove Should be 0  :"+ pub1.getBooksFromP().size());
        System.out.println("_______________________________________");

     //checking    Basic association
      Book book2=new Book(2, "The mysterious affair at styles ","Crime fiction",Language.English,60,Sale.Not);
      book2.addAuthor(author2);
        book2.addAuthor(author1);
        book1.addAuthor(author2);

      System.out.println("number of authors before remove Shoul be 2 " + book2.getAuthors().size());

       book2.removeAuthor(author2);
        System.out.println("number of authors after remove Shoul be 1 " + book2.getAuthors().size());
        System.out.println("_______________________________________");





    }
}
