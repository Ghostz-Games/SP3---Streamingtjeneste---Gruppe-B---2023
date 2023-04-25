package streaming.mediaHandler;


import java.util.ArrayList;

import streaming.users.User;
import streaming.io.FileIO;
import streaming.io.IO;
public class MediaHandler {


    private ArrayList<Media> media = new ArrayList<Media>();

    private User currentUser;
    private IO io;

    public MediaHandler(IO io,User currentUser){
        this.io = io;
        this.currentUser = currentUser;
    }

    public void loadMovies() throws Exception {
        ArrayList<String> data = io.readDataMedia();
        for(String s : data){
            String[] line = s.split(";");
            String name = line[0];
            String year = line[1];
            String genre = line[2];
            String rating = line[3].replace(",",".");

            media.add(new Movie(name, genre, Float.parseFloat(rating), year, 120));
        }
    }


    public void loadSeries() throws Exception {
        ArrayList<String> data = io.readDataSeries();
        for(String s : data){
            String[] line = s.split(";");
            String name = line[0];
            String year = line[1];
            String genre = line[2];
            String rating = line[3].replace(",",".");;
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
        //UI.playMedia(media);
    }


    public ArrayList<Media> getMedia(){
        return media;
    }


    public ArrayList<Media> searchMedia(String name, String genre, String year, float min, float max){
        ArrayList<Media> output = getMedia();

        if(name != null){
            for(int i = output.size() - 1 ; i >= 0; i--){
                if(!output.get(i).getName().contains(name)){
                    output.remove(i);
                }
            }
        }

        if(genre != null){
            for(int i = output.size() - 1 ; i >= 0; i--){
                if(!output.get(i).getGenre().contains(genre)){
                    output.remove(i);
                }
            }
        }

        if(year != null){
            for(int i = output.size() - 1 ; i >= 0; i--){
                if(!output.get(i).getYear().contains(genre)){
                    output.remove(i);
                }
            }
        }

        if(min != 0){
            for(int i = output.size() - 1 ; i >= 0; i--){
                if(output.get(i).getRating() < min){
                    output.remove(i);
                }
            }
        }

        if(max != 0){
            for(int i = output.size() - 1 ; i >= 0; i--){
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

    public static String InlineListString(ArrayList<Media> liste){
        if(liste == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Media m: liste) {
            sb.append(m.getName());
        }
        return sb.toString();
    }

}
