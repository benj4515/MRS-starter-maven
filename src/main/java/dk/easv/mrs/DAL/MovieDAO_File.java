
// Projekt import
package dk.easv.mrs.DAL;
import dk.easv.mrs.BE.Movie;

//Java imports
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MovieDAO_File implements IMovieDataAccess {

    private static final String MOVIES_FILE = "data/movie_titles.txt";

    //The @Override annotation is not required, but is recommended for readability
    // and to force the compiler to check and generate error msg. if needed etc.
    //@Override
    public List<Movie> getAllMovies() throws IOException {
       List<String> lines;
        try (Stream<String> read = Files.lines(Paths.get(MOVIES_FILE))) {
            lines = read.collect(Collectors.toList());
        }
        List<Movie> movies = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split(",");

            int id = Integer.parseInt(parts[0]);
            String title = parts[2];
            int year = Integer.parseInt(parts[1]);
            if(parts.length > 3){
                for(int i = 3; i < parts.length; i++){
                    title += "," +  parts[i];
                }
            }
            Movie movie = new Movie(id,year,title);
            movies.add(movie);
        }
        return movies;
    }

    @Override
    public Movie createMovie(String title, int year) throws Exception {
        return null;
    }

    @Override
    public void updateMovie(Movie movie) throws Exception {
    }

    @Override
    public void deleteMovie(Movie movie) throws Exception {
    }
}