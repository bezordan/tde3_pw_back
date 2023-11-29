package catalog.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllProdutos() {
        return movieRepository.findAll();
    }

    public Movie createMovie (Movie produto) {
        return movieRepository.save(produto);
    }

    public Optional<Movie> getMovieById(Integer id) {
        return movieRepository.findById(id);
    }

    public Movie updateMovie(Integer id, Movie movieAtualizado) {
        Optional<Movie> optionalProduto = movieRepository.findById(id);

        if (optionalProduto.isPresent()) {
            Movie movie = optionalProduto.get();
            movie.setName(movieAtualizado.getName());
            movie.setPrice(movieAtualizado.getPrice());
            movie.setUrl_poster(movieAtualizado.getUrl_poster());
            movie.setSynopsis(movieAtualizado.getSynopsis());

            return movieRepository.save(movie);
        } else {
            throw new IllegalArgumentException("Produto não encontrado");
        }
    }

    public boolean deleteMovie(Integer id) {
        if (movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
            return true;
        }
        return false;
    }
}