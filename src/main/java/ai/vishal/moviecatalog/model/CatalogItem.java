package ai.vishal.moviecatalog.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CatalogItem {
    String name;
    String desc;
    int rating;
    public CatalogItem(String name, String desc, int rating) {
        this.name = name;
        this.desc = desc;
        this.rating = rating;
    }
    
}
