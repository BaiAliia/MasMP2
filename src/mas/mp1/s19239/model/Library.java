package mas.mp1.s19239.model;

import mas.mp1.s19239.model.exceptions.ModelValException;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Library {
    private String name;
    private Adress adress;
    private Set<Section> sections = new HashSet<>();

    public Library(String name, Adress adress) {
        this.name = name;
        this.adress = adress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new ModelValException("The name of the library cant be null");
        }
        this.name = name;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        if (adress == null) {
            throw new ModelValException("Adress cant be null");
        }
        this.adress = adress;
    }

    public Set<Section> getSections() {
        return Collections.unmodifiableSet(sections);
    }

    public void addSection(String name, int id) {
        Section sec = new Section(name, id, this);
        this.sections.add(sec);
        sec.setPlace(this);
    }

    public void addSection(Section sec) {
        this.sections.add(sec);
        sec.setPlace(this);
    }


    public void removeSection(Section sec) {
        if (sections.contains(sec)) {
            sections.remove(sec);
        }

    }

    public void removeSection(String name, int id) {
        Section mysec = sections.stream()
                .filter(section -> name.equals(section.getName()))
                .filter(section -> id==section.getId())
                .findAny()
                .orElse(null);
       if(mysec!=null){
           if(sections.contains(mysec)){
               sections.remove(mysec);
           }
    }}
}