CREATE TABLE public.movies (
                               id_movie SERIAL PRIMARY KEY,
                               name varchar(100) NOT NULL,
                               price numeric(10, 2) NOT NULL DEFAULT 0.00,
                               synopsis text NULL,
                               url_poster text NULL
);