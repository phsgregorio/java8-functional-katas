package katas;

import model.Movie;
import util.DataUtil;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
    Goal: Chain filter() and map() to collect the ids of videos that have a rating of 5.0
    DataSource: DataUtil.getMovies()
    Output: List of Integers
*/
public class Kata2 {
    public static List<Integer> execute() {
        List<Movie> movies = DataUtil.getMovies();
        
	    Stream<Integer> streamList = movies.stream().
	    		filter(m -> m.getRating().intValue() == 5).
	    		map(m -> m.getId());
	    
	    return streamList.collect(Collectors.toList());
    }
}
