package com.example.nysil.showtime;


class MoviesAndShowsData {

    private String movieOrShowName;
    private String releaseDate;
    private String rating;
    private String infoAbout;
    private String imageUrl;
    private String coverImageUrl;


    MoviesAndShowsData( String rating, String infoAbout, String imageUrl, String coverImageUrl)
    {


        this.rating=rating;
        this.infoAbout=infoAbout;
        this.imageUrl=imageUrl;
        this.coverImageUrl=coverImageUrl;
    }

    MoviesAndShowsData(String imageUrl)
    {
        this.imageUrl=imageUrl;
    }

    public String getMovieOrShowName()
    {
        return movieOrShowName;
    }

    public String getReleaseDate()
    {
        return releaseDate;
    }

    public String getRating()
    {
        return rating;
    }

    public String getInfoAbout()
    {
        return infoAbout;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    public String getCoverImageUrl()
    {
        return coverImageUrl;
    }


}
