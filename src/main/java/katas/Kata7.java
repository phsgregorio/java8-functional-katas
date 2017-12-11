package katas;

import com.google.common.collect.ImmutableMap;
import model.MovieList;
import util.DataUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
    Goal: Retrieve the id, title, and smallest box art url for every video
    DataSource: DataUtil.getMovieLists()
    Output: List of ImmutableMap.of("id", "5", "title", "Bad Boys", "boxart": "url)
*/
public class Kata7 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();
        
        Stream<Map> streamList = movieLists.stream().
	    		map(ml -> ml.getVideos()).
	    		flatMap(m -> m.stream()).
	    		map((movie -> ImmutableMap.builder()
			        	.put("id", movie.getId().toString())
			        	.put("title", movie.getTitle())
			        	.put("boxart", movie.getBoxarts().stream().
			        			reduce((box1,box2) -> 
			        				(box1.getWidth() * box1.getHeight()) < (box2.getWidth() * box2.getHeight()) ? box1 : box2).
			        			map(boxArt -> boxArt.getUrl()).
			        			orElse("")
			        			)
			        	.build())
	    		);        

        return streamList.collect(Collectors.toList());
    }
}
