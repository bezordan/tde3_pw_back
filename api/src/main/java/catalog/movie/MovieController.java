package catalog.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movie")
@CrossOrigin(origins = "http://localhost:3000")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> produtos = movieService.getAllProdutos();
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable("id") Integer id) {
        Optional<Movie> movie = movieService.getMovieById(id);

        if (movie.isPresent()) {
            return new ResponseEntity<>(movie.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody RecordMovie recordMovie) {
        Movie createdMovie = movieService.createMovie(new Movie(recordMovie));

        if (createdMovie != null) {
            return new ResponseEntity<>(createdMovie, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable("id") Integer id, @RequestBody RecordMovie recordMovie) {
        Movie updatedMovie = movieService.updateMovie(id, new Movie(recordMovie));

        if (updatedMovie != null) {
            return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable("id") Integer id) {
        boolean deleted = movieService.deleteMovie(id);

        if (deleted) {
            return ResponseEntity.status(HttpStatus.OK).body("Filme deletado.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filme não foi encontrado.");
        }
    }
}


