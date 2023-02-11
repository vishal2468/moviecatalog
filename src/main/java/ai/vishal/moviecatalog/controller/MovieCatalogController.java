package ai.vishal.moviecatalog.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import ai.vishal.moviecatalog.model.CatalogItem;
import ai.vishal.moviecatalog.model.Movie;
import ai.vishal.moviecatalog.model.UserRating;

@RefreshScope
@RestController
public class MovieCatalogController {

    @Autowired
    WebClient.Builder webClientBuilder;

    @Value("${vishal.poddar:default name}")
    private String testname;

    @GetMapping("/catalog/{userId}")
    List<CatalogItem> getCatalog(@PathVariable String userId) {
        System.out.println(testname);
        UserRating userRating = webClientBuilder.build().get().uri("http://ratings-data-service/user/rating/" + userId)
                .retrieve().bodyToMono(UserRating.class).block();
        return userRating.getRatings().stream().map((rating) -> {
            Movie movie = webClientBuilder.build().get().uri("http://movie-info-service/movie/" + rating.getMovieId())
                    .retrieve().bodyToMono(Movie.class).block();
            return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
        }).collect(Collectors.toList());
    }
}
