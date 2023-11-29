import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import catalog.movie.Movie;
import catalog.movie.MovieController;
import catalog.movie.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class MovieControllerTest {

    private MovieService movieService;
    private MovieController movieController;

    @BeforeEach
    void setUp() {
        movieService = mock(MovieService.class);
        movieController = new MovieController(movieService);
    }

    @Test
    void getAllMovies_ReturnsListOfMovies() {
        // Arrange
        List<Movie> movies = Arrays.asList(new Movie(), new Movie());
        when(movieService.getAllProdutos()).thenReturn(movies);

        // Act
        ResponseEntity<List<Movie>> response = movieController.getAllMovies();

        // Assert
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(movies, response.getBody())
        );
    }

    @Test
    void getMovieById_ExistingId_ReturnsMovie() {
        // Arrange
        int movieId = 1;
        Movie movie = new Movie();
        when(movieService.getMovieById(movieId)).thenReturn(Optional.of(movie));

        // Act
        ResponseEntity<Movie> response = movieController.getMovieById(movieId);

        // Assert
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(movie, response.getBody())
        );
    }

    @Test
    void getMovieById_NonExistingId_ReturnsNotFound() {
        // Arrange
        int movieId = 1;
        when(movieService.getMovieById(movieId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Movie> response = movieController.getMovieById(movieId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}
