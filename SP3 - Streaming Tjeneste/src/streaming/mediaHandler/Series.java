package streaming.mediaHandler;

import java.util.ArrayList;

public class Series extends Movie{

    ArrayList<Integer> episodes;

    public Series(String name, String genre, float rating, double length, String year , ArrayList<Integer> episodes){
        super(name, genre, rating, year, length);
        this.episodes = episodes;
    }


    @Override
    public String toString(){
        return "Name: "+super.getName()+"\n"+"Genre: "+super.getGenre()+"\n"+"Rating: "+super.getRating()+"\n"+"Amount of episodes: "+episodes.size()+"\n";
    }

}
