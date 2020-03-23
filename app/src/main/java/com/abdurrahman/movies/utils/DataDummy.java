package com.abdurrahman.movies.utils;

import com.abdurrahman.movies.data.source.local.entity.MoviesEntity;
import com.abdurrahman.movies.data.source.local.entity.TVShowEntity;

import java.util.ArrayList;

public class DataDummy {

//    public static ArrayList<MoviesEntity> generateDummyMovies() {
//
//        ArrayList<MoviesEntity> movies = new ArrayList<>();
//
//        movies.add(new MoviesEntity(384018,
//                "6.5",
//                "Fast & Furious Presents: Hobbs & Shaw",
//                "2019-08-02",
//                "A spinoff of The Fate of the Furious, focusing on Johnson's US Diplomatic Security Agent Luke Hobbs forming an unlikely alliance with Statham's Deckard Shaw.",
//                "/keym7MPn1icW1wWfzMnW3HeuzWU.jpg"));
//        movies.add(new MoviesEntity(429203,
//                "6.4",
//                "The Old Man & the Gun",
//                "2018-09-28",
//                "The true story of Forrest Tucker, from his audacious escape from San Quentin at the age of 70 to an unprecedented string of heists that confounded authorities and enchanted the public.  Wrapped up in the pursuit are a detective, who becomes captivated with Forrest’s commitment to his craft, and a woman, fullmazahd.in who loves him in spite of his chosen profession.",
//                "/a4BfxRK8dBgbQqbRxPs8kmLd8LG.jpg"));
//        movies.add(new MoviesEntity(384018,
//                "6.5",
//                "Fast & Furious Presents: Hobbs & Shaw",
//                "2019-08-02",
//                "A spinoff of The Fate of the Furious, focusing on Johnson's US Diplomatic Security Agent Luke Hobbs forming an unlikely alliance with Statham's Deckard Shaw.",
//                "/keym7MPn1icW1wWfzMnW3HeuzWU.jpg"));
//        movies.add(new MoviesEntity(384018,
//                "6.5",
//                "Fast & Furious Presents: Hobbs & Shaw",
//                "2019-08-02",
//                "A spinoff of The Fate of the Furious, focusing on Johnson's US Diplomatic Security Agent Luke Hobbs forming an unlikely alliance with Statham's Deckard Shaw.",
//                "/keym7MPn1icW1wWfzMnW3HeuzWU.jpg"));
//        movies.add(new MoviesEntity(384018,
//                "6.5",
//                "Fast & Furious Presents: Hobbs & Shaw",
//                "2019-08-02",
//                "A spinoff of The Fate of the Furious, focusing on Johnson's US Diplomatic Security Agent Luke Hobbs forming an unlikely alliance with Statham's Deckard Shaw.",
//                "/keym7MPn1icW1wWfzMnW3HeuzWU.jpg"));
//        movies.add(new MoviesEntity(384018,
//                "6.5",
//                "Fast & Furious Presents: Hobbs & Shaw",
//                "2019-08-02",
//                "A spinoff of The Fate of the Furious, focusing on Johnson's US Diplomatic Security Agent Luke Hobbs forming an unlikely alliance with Statham's Deckard Shaw.",
//                "/keym7MPn1icW1wWfzMnW3HeuzWU.jpg"));
//        movies.add(new MoviesEntity(384018,
//                "6.5",
//                "Fast & Furious Presents: Hobbs & Shaw",
//                "2019-08-02",
//                "A spinoff of The Fate of the Furious, focusing on Johnson's US Diplomatic Security Agent Luke Hobbs forming an unlikely alliance with Statham's Deckard Shaw.",
//                "/keym7MPn1icW1wWfzMnW3HeuzWU.jpg"));
//        movies.add(new MoviesEntity(384018,
//                "6.5",
//                "Fast & Furious Presents: Hobbs & Shaw",
//                "2019-08-02",
//                "A spinoff of The Fate of the Furious, focusing on Johnson's US Diplomatic Security Agent Luke Hobbs forming an unlikely alliance with Statham's Deckard Shaw.",
//                "/keym7MPn1icW1wWfzMnW3HeuzWU.jpg"));
//        movies.add(new MoviesEntity(384018,
//                "6.5",
//                "Fast & Furious Presents: Hobbs & Shaw",
//                "2019-08-02",
//                "A spinoff of The Fate of the Furious, focusing on Johnson's US Diplomatic Security Agent Luke Hobbs forming an unlikely alliance with Statham's Deckard Shaw.",
//                "/keym7MPn1icW1wWfzMnW3HeuzWU.jpg"));
//        movies.add(new MoviesEntity(384018,
//                "6.5",
//                "Fast & Furious Presents: Hobbs & Shaw",
//                "2019-08-02",
//                "A spinoff of The Fate of the Furious, focusing on Johnson's US Diplomatic Security Agent Luke Hobbs forming an unlikely alliance with Statham's Deckard Shaw.",
//                "/keym7MPn1icW1wWfzMnW3HeuzWU.jpg"));
//        movies.add(new MoviesEntity(384018,
//                "6.5",
//                "Fast & Furious Presents: Hobbs & Shaw",
//                "2019-08-02",
//                "A spinoff of The Fate of the Furious, focusing on Johnson's US Diplomatic Security Agent Luke Hobbs forming an unlikely alliance with Statham's Deckard Shaw.",
//                "/keym7MPn1icW1wWfzMnW3HeuzWU.jpg"));
//        movies.add(new MoviesEntity(384018,
//                "6.5",
//                "Fast & Furious Presents: Hobbs & Shaw",
//                "2019-08-02",
//                "A spinoff of The Fate of the Furious, focusing on Johnson's US Diplomatic Security Agent Luke Hobbs forming an unlikely alliance with Statham's Deckard Shaw.",
//                "/keym7MPn1icW1wWfzMnW3HeuzWU.jpg"));
//        return movies;
//    }
//
//    public static ArrayList<TVShowEntity> generateDummyTVShows() {
//
//        ArrayList<TVShowEntity> tvShows = new ArrayList<>();
//
//        tvShows.add(new TVShowEntity(384018,
//                "6.5",
//                "Fast & Furious Presents: Hobbs & Shaw",
//                "2019-08-02",
//                "A spinoff of The Fate of the Furious, focusing on Johnson's US Diplomatic Security Agent Luke Hobbs forming an unlikely alliance with Statham's Deckard Shaw.",
//                "/keym7MPn1icW1wWfzMnW3HeuzWU.jpg"));
//        tvShows.add(new TVShowEntity(384018,
//                "6.5",
//                "Fast & Furious Presents: Hobbs & Shaw",
//                "2019-08-02",
//                "A spinoff of The Fate of the Furious, focusing on Johnson's US Diplomatic Security Agent Luke Hobbs forming an unlikely alliance with Statham's Deckard Shaw.",
//                "/keym7MPn1icW1wWfzMnW3HeuzWU.jpg"));
//        tvShows.add(new TVShowEntity(384018,
//                "6.5",
//                "Fast & Furious Presents: Hobbs & Shaw",
//                "2019-08-02",
//                "A spinoff of The Fate of the Furious, focusing on Johnson's US Diplomatic Security Agent Luke Hobbs forming an unlikely alliance with Statham's Deckard Shaw.",
//                "/keym7MPn1icW1wWfzMnW3HeuzWU.jpg"));
//        tvShows.add(new TVShowEntity(384018,
//                "6.5",
//                "Fast & Furious Presents: Hobbs & Shaw",
//                "2019-08-02",
//                "A spinoff of The Fate of the Furious, focusing on Johnson's US Diplomatic Security Agent Luke Hobbs forming an unlikely alliance with Statham's Deckard Shaw.",
//                "/keym7MPn1icW1wWfzMnW3HeuzWU.jpg"));
//        tvShows.add(new TVShowEntity(384018,
//                "6.5",
//                "Fast & Furious Presents: Hobbs & Shaw",
//                "2019-08-02",
//                "A spinoff of The Fate of the Furious, focusing on Johnson's US Diplomatic Security Agent Luke Hobbs forming an unlikely alliance with Statham's Deckard Shaw.",
//                "/keym7MPn1icW1wWfzMnW3HeuzWU.jpg"));
//        tvShows.add(new TVShowEntity(384018,
//                "6.5",
//                "Fast & Furious Presents: Hobbs & Shaw",
//                "2019-08-02",
//                "A spinoff of The Fate of the Furious, focusing on Johnson's US Diplomatic Security Agent Luke Hobbs forming an unlikely alliance with Statham's Deckard Shaw.",
//                "/keym7MPn1icW1wWfzMnW3HeuzWU.jpg"));
//        tvShows.add(new TVShowEntity(384018,
//                "6.5",
//                "Fast & Furious Presents: Hobbs & Shaw",
//                "2019-08-02",
//                "A spinoff of The Fate of the Furious, focusing on Johnson's US Diplomatic Security Agent Luke Hobbs forming an unlikely alliance with Statham's Deckard Shaw.",
//                "/keym7MPn1icW1wWfzMnW3HeuzWU.jpg"));
//        tvShows.add(new TVShowEntity(384018,
//                "6.5",
//                "Fast & Furious Presents: Hobbs & Shaw",
//                "2019-08-02",
//                "A spinoff of The Fate of the Furious, focusing on Johnson's US Diplomatic Security Agent Luke Hobbs forming an unlikely alliance with Statham's Deckard Shaw.",
//                "/keym7MPn1icW1wWfzMnW3HeuzWU.jpg"));
//        tvShows.add(new TVShowEntity(384018,
//                "6.5",
//                "Fast & Furious Presents: Hobbs & Shaw",
//                "2019-08-02",
//                "A spinoff of The Fate of the Furious, focusing on Johnson's US Diplomatic Security Agent Luke Hobbs forming an unlikely alliance with Statham's Deckard Shaw.",
//                "/keym7MPn1icW1wWfzMnW3HeuzWU.jpg"));
//        tvShows.add(new TVShowEntity(384018,
//                "6.5",
//                "Fast & Furious Presents: Hobbs & Shaw",
//                "2019-08-02",
//                "A spinoff of The Fate of the Furious, focusing on Johnson's US Diplomatic Security Agent Luke Hobbs forming an unlikely alliance with Statham's Deckard Shaw.",
//                "/keym7MPn1icW1wWfzMnW3HeuzWU.jpg"));
//        return tvShows;
//    }
}
