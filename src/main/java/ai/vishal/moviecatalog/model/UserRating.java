package ai.vishal.moviecatalog.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRating {
    List<Rating> ratings;
    String userId;
}
