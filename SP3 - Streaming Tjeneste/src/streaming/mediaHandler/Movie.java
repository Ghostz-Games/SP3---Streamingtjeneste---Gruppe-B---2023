package streaming.mediaHandler;

public class Movie extends Media{

    private double lengthOfMovie;

    public Movie(String name, String genre, float rating, String year ,double lengthOfMovie){
        super(name, genre, rating, year);
        this.lengthOfMovie = lengthOfMovie;
    }

    public double getLengthOfMovie() {
        return lengthOfMovie;
    }

    @Override
    public String toString(){
        return "Name: "+super.getName()+"\n"+"Genre: "+super.getGenre()+"\n"+"Rating: "+super.getRating()+"\n"+"Length: "+getLengthOfMovie()+"\n";
    }

}
