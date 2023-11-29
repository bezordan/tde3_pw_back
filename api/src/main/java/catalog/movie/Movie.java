package catalog.movie;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movie")
    private Integer idMovie;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "url_poster", nullable = false)
    private String url_poster;

    @Column(name = "synopsis", nullable = false)
    private String synopsis;

    public Movie(RecordMovie recordMovie){
        this.url_poster = recordMovie.url_poster();
        this.price = recordMovie.price();
        this.name = recordMovie.name();
        this.synopsis = recordMovie.synopsis();
    }
}
