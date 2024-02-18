package pl.javastart.library.model;

public class Magazine extends Publication {
    private int day;
    private int month;
    private String language;

    public Magazine(String title, String publisher,String language, int day, int month, int releaseYear) {
        super(title, publisher, releaseYear);
        this.day = day;
        this.month = month;
        this.language = language;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public void printInfo() {
        String info = getTitle() + "; " + getPublisher()  + "; " + getReleaseYear() + "-" + month + "-" + day + "; " +
                language;
        System.out.println(info);
    }
}
