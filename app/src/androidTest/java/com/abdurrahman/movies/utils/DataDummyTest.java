package com.abdurrahman.movies.utils;

import com.abdurrahman.movies.data.source.local.entity.MoviesEntity;
import com.abdurrahman.movies.data.source.local.entity.TVShowEntity;

import java.util.ArrayList;

public class DataDummyTest {

        public static ArrayList<MoviesEntity> generateDummyMovies() {

            ArrayList<MoviesEntity> movies = new ArrayList<>();

            movies.add(new MoviesEntity(38700,
                    "6.5",
                    "Bad Boys for Life",
                    "2020-01-15",
                    "Marcus and Mike are forced to confront new threats, career changes, and midlife crises as they join the newly created elite team AMMO of the Miami police department to take down the ruthless Armando Armas, the vicious leader of a Miami drug cartel.",
                    "/y95lQLnuNKdPAzw9F9Ab8kJ80c3.jpg",null));
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

            tvShows.add(new TVShowEntity(456,
                    "7.2",
                    "The Simpsons",
                    "1989-12-17",
                    "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.",
                    "/qcr9bBY6MVeLzriKCmJOv1562uY.jpg",null));
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