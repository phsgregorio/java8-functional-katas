package katas;

import com.google.common.collect.ImmutableMap;
import model.MovieList;
import util.DataUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
    Goal: Retrieve id, title, and a 150x200 box art url for every video
    DataSource: DataUtil.getMovieLists()
    Output: List of ImmutableMap.of("id", "5", "title", "Bad Boys", "boxart": BoxArt)
*/
public class Kata4 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();
        
        Stream<Map> streamList = movieLists.stream().
	    		map(ml -> ml.getVideos()).
	    		flatMap(m -> m.stream()).
	    		map((movie -> ImmutableMap.builder()
			        	.put("id", movie.getId().toString())
			        	.put("title", movie.getTitle())
			        	.put("boxart", movie.getBoxarts().stream().
			        			filter(ba -> ba.getWidth()==150 && ba.getHeight()==200).
			        			findFirst()
			        			)
			        	.build())
	    		);

        return streamList.collect(Collectors.toList());
    }
}
