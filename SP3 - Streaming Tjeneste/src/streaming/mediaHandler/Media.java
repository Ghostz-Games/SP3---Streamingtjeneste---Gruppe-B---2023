package streaming.mediaHandler;

public abstract class Media {

    private String name;
    private String genre;
    private float rating;
    private String year;

    public Media(String name, String genre, float rating, String year){
        this.name = name;
        this.genre = genre;
        this.rating = rating;
        this.year = year;

    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public float getRating() {
        return rating;
    }

    public String getYear() {
        return year;
    }

    @Override
    public String toString(){
        return "Name: "+name+"\n"+"Genre: "+genre+"\n"+"Rating: "+rating+"\n"+"Type of media: "+getClass()+"\n";
    }

}
