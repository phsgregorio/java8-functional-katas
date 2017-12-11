package katas;

import model.MovieList;
import util.DataUtil;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
    Goal: Use map() and flatMap() to project and flatten the movieLists into an array of video ids (flatMap(c -> c.stream()))
    DataSource: DataUtil.getMovieLists()
    Output: List of Integers
*/
public class Kata3 {
    public static List<Integer> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();
        
	    Stream<Integer> streamList = movieLists.stream().
	    		map(ml -> ml.getVideos()).
	    		flatMap(m -> m.stream()).
	    		map(movie -> movie.getId());

	    return streamList.collect(Collectors.toList());
    }
}
