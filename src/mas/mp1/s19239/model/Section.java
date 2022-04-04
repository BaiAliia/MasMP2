package mas.mp1.s19239.model;

import mas.mp1.s19239.model.exceptions.ModelValException;

public class Section {
    private String name;
    private int id ;
    private Library place;



    public Section(String name, int id,Library place) {
        setName(name);
        this.id = id;
       setPlace(place);
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name==null ||name.isBlank()){
            throw  new ModelValException("The name of the section cant be null");
        }
        this.name = name;
    }

    public void setPlace(Library place) {
        if(place==null){
            throw  new ModelValException("The place of the section cant be null");
        }
        this.place = place;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
