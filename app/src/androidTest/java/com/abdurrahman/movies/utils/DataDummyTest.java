package com.abdurrahman.movies.utils;

import com.abdurrahman.movies.data.source.local.entity.MoviesEntity;
import com.abdurrahman.movies.data.source.local.entity.TVShowEntity;

import java.util.ArrayList;

public class DataDummyTest {

        public static ArrayList<MoviesEntity> generateDummyMovies() {

            ArrayList<MoviesEntity> movies = new ArrayList<>();

            movies.add(new MoviesEntity(464052,
                    "7.1",
                    "Wonder Woman 1984",
                    "2020-12-16",
                    "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.",
                    "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",null));
            movies.add(new MoviesEntity(429203,
                    "6.4",
                    "The Old Man & the Gun",
                    "2018-09-28",
                    "The true story of Forrest Tucker, from his audacious escape from San Quentin at the age of 70 to an unprecedented string of heists that confounded authorities and enchanted the public.  Wrapped up in the pursuit are a detective, who becomes captivated with Forrest’s commitment to his craft, and a woman, fullmazahd.in who loves him in spite of his chosen profession.",
                    "/a4BfxRK8dBgbQqbRxPs8kmLd8LG.jpg",null));
            movies.add(new MoviesEntity(384018,
                    "6.5",
                    "Fast & Furious Presents: Hobbs & Shaw",
                    "2019-08-02",
                    "A spinoff of The Fate of the Furious, focusing on Johnson's US Diplomatic Security Agent Luke Hobbs forming an unlikely alliance with Statham's Deckard Shaw.",
                    "/keym7MPn1icW1wWfzMnW3HeuzWU.jpg",null));
            return movies;
        }

        public static ArrayList<TVShowEntity> generateDummyTVShows() {

            ArrayList<TVShowEntity> tvShows = new ArrayList<>();

            tvShows.add(new TVShowEntity(1399,
                    "8.4",
                    "Game of Thrones",
                    "2011-04-17",
                    "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                    "/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",null));
            tvShows.add(new TVShowEntity(384018,
                    "6.6",
                    "Fast & Furious Presents: Hobbs & Shaw",
                    "2019-08-02",
                    "A spinoff of The Fate of the Furious, focusing on Johnson's US Diplomatic Security Agent Luke Hobbs forming an unlikely alliance with Statham's Deckard Shaw.",
                    "/keym7MPn1icW1wWfzMnW3HeuzWU.jpg",null));
            tvShows.add(new TVShowEntity(384018,
                    "6.5",
                    "Fast & Furious Presents: Hobbs & Shaw",
                    "2019-08-02",
                    "A spinoff of The Fate of the Furious, focusing on Johnson's US Diplomatic Security Agent Luke Hobbs forming an unlikely alliance with Statham's Deckard Shaw.",
                    "/keym7MPn1icW1wWfzMnW3HeuzWU.jpg",null));
            return tvShows;
        }
}