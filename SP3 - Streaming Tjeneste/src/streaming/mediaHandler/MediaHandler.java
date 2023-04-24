package streaming.mediaHandler;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MediaHandler {


    private ArrayList<Media> media = new ArrayList<Media>();

    private User currentUser;

    public MediaHandler(User currentUser){
        this.currentUser = currentUser;
    }

    public void loadMovies() throws FileNotFoundException {
        ArrayList<String> data = IO.readDataMedia();
        for(String s : data){
            String[] line = s.split(";");
            String name = line[0];
            String year = line[1];
            String genre = line[2];
            String rating = line[3];
            media.add(new Movie(name, genre, Float.parseFloat(rating), year, 120));
        }
    }


    public void loadSeries() throws FileNotFoundException {
        ArrayList<String> data = IO.readDataSeries();
        for(String s : data){
            String[] line = s.split(";");
            String name = line[0];
            String year = line[1];
            String genre = line[2];
            String rating = line[3];
            String episodes = line[4];
            String[] seasons = episodes.split(",");
            int epsum =0;
            ArrayList<Integer> seasonsList = new ArrayList<>();
            for(int i = 0; i < seasons.length; i++){
                int episodeCount = Integer.parseInt(seasons[i].split("-")[1]);
                epsum += episodeCount;
                seasonsList.add(episodeCount);
            }
            
            media.add(new Series(name, genre, Float.parseFloat(rating), epsum, year, seasonsList));
        }
    }



    public void playMedia(Media media){
        UI.playMedia(media);
    }


    public ArrayList<Media> getMedia(){
        return media;
    }


    private ArrayList<Media> searchMedia(String name, String genre, String year, float min, float max){
        ArrayList<Media> output = new ArrayList<>();

        if(name != null){
            for(int i = output.size() ; i >= 0; i--){
                if(!output.get(i).getName().contains(name)){
                    output.remove(i);
                }
            }
        }

        if(genre != null){
            for(int i = output.size() ; i >= 0; i--){
                if(!output.get(i).getGenre().contains(genre)){
                    output.remove(i);
                }
            }
        }

        if(year != null){
            for(int i = output.size() ; i >= 0; i--){
                if(!output.get(i).getYear().contains(genre)){
                    output.remove(i);
                }
            }
        }

        if(min != 0){
            for(int i = output.size() ; i >= 0; i--){
                if(output.get(i).getRating() < min){
                    output.remove(i);
                }
            }
        }

        if(max != 0){
            for(int i = output.size() ; i >= 0; i--){
                if(output.get(i).getRating() > max){
                    output.remove(i);
                }
            }
        }

        return output;
    }

    private ArrayList<Media> searchGenre(String input){

        return searchMedia(null, input, null, 0, 0);
    }

    private ArrayList<Media> searchName(String input){
        return searchMedia(input, null, null, 0, 0);
    }

    private ArrayList<Media> searchYear(String input){
        return searchMedia(null, null, input, 0, 0);
    }

    private ArrayList<Media> searchRating(float a, float b){
        return searchMedia(null, null, null, a, b);
    }


}
