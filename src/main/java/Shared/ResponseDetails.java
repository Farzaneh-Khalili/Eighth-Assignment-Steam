package Shared;

public class ResponseDetails extends Response{

    private String title;
    private String developer;
    private String genre;
    private double price;
    private int releaseYear;
    private boolean controllerSupport;
    private String reviews;
    private int siz;

    @Override
    public String toString() {
        return "video game " +
                "title = '" + title + "\'\n" +
                ", developer = '" + developer + "\'\n" +
                ", genre = '" + genre + "\'\n" +
                ", price = " + price + "\n" +
                ", release Year = " + releaseYear + "\n" +
                ", controller Support = " + controllerSupport + "\n" +
                ", reviews = '" + reviews + "\'\n" +
                ", siz = " + siz;
    }
}
