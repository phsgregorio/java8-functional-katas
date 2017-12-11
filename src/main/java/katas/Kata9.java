package katas;

import com.google.common.collect.ImmutableMap;
import model.MovieList;
import util.DataUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
    Goal: Retrieve each video's id, title, middle interesting moment time, and smallest box art url
    DataSource: DataUtil.getMovies()
    Output: List of ImmutableMap.of("id", 5, "title", "some title", "time", new Date(), "url", "someUrl")
*/
public class Kata9 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();
        
        Stream<Map> streamMap = movieLists.stream().
	    		map(ml -> ml.getVideos()).
	    		flatMap(m -> m.stream()).
	    		map(movie -> ImmutableMap.builder()
	            	.put("id", movie.getId().toString())
	            	.put("title", movie.getTitle())
	            	.put("time", movie.getInterestingMoments().stream().
	            					filter(moment -> "Middle" == moment.getType()).
	            					map(middleMoment -> middleMoment.getTime()).
	            					findFirst())
	            	.put("url", movie.getBoxarts().stream().
		        					reduce((box1,box2) -> 
		        						(box1.getWidth() * box1.getHeight()) < (box2.getWidth() * box2.getHeight()) ? box1 : box2).
		        					map(boxArt -> boxArt.getUrl()).
		        					orElse(""))
	            	.build()
	            );

        return streamMap.collect(Collectors.toList());
    }
}
