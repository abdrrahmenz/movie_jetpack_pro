package com.abdurrahman.movies.utils;

import com.abdurrahman.movies.data.source.local.entity.MoviesEntity;
import com.abdurrahman.movies.data.source.local.entity.TVShowEntity;
import com.abdurrahman.movies.data.source.remote.response.MovieDetailsResponse;
import com.abdurrahman.movies.data.source.remote.response.MoviesResponses;
import com.abdurrahman.movies.data.source.remote.response.TVShowDetailsResponse;
import com.abdurrahman.movies.data.source.remote.response.TVShowResponses;

import java.util.ArrayList;

public class FakeDataDummy {

    public static ArrayList<MoviesEntity> generateLocalDummyMovies() {

        ArrayList<MoviesEntity> movies = new ArrayList<>();

        movies.add(new MoviesEntity(384018,
                "6.5",
                "Fast & Furious Presents: Hobbs & Shaw",
                "2019-08-02",
                "A spinoff of The Fate of the Furious, focusing on Johnson's US Diplomatic Security Agent Luke Hobbs forming an unlikely alliance with Statham's Deckard Shaw.",
                "/keym7MPn1icW1wWfzMnW3HeuzWU.jpg",
                false));
        movies.add(new MoviesEntity(429203,
                "6.4",
                "The Old Man & the Gun",
                "2018-09-28",
                "The true story of Forrest Tucker, from his audacious escape from San Quentin at the age of 70 to an unprecedented string of heists that confounded authorities and enchanted the public.  Wrapped up in the pursuit are a detective, who becomes captivated with Forrest’s commitment to his craft, and a woman, fullmazahd.in who loves him in spite of his chosen profession.",
                "/a4BfxRK8dBgbQqbRxPs8kmLd8LG.jpg",
                true));
        movies.add(new MoviesEntity(384018,
                "6.5",
                "Fast & Furious Presents: Hobbs & Shaw",
                "2019-08-02",
                "A spinoff of The Fate of the Furious, focusing on Johnson's US Diplomatic Security Agent Luke Hobbs forming an unlikely alliance with Statham's Deckard Shaw.",
                "/keym7MPn1icW1wWfzMnW3HeuzWU.jpg",
                true));
        return movies;
    }

    public static ArrayList<TVShowEntity> generateLocalDummyTVShows() {

        ArrayList<TVShowEntity> tvShows = new ArrayList<>();

        tvShows.add(new TVShowEntity(384018,
                "6.5",
                "The Flash",
                "2014-10-07",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "/fki3kBlwJzFp8QohL43g9ReV455.jpg",
                true));
        tvShows.add(new TVShowEntity(384018,
                "6.6",
                "Fast & Furious Presents: Hobbs & Shaw",
                "2019-08-02",
                "A spinoff of The Fate of the Furious, focusing on Johnson's US Diplomatic Security Agent Luke Hobbs forming an unlikely alliance with Statham's Deckard Shaw.",
                "/keym7MPn1icW1wWfzMnW3HeuzWU.jpg",
                true));
        tvShows.add(new TVShowEntity(384018,
                "6.5",
                "Fast & Furious Presents: Hobbs & Shaw",
                "2019-08-02",
                "A spinoff of The Fate of the Furious, focusing on Johnson's US Diplomatic Security Agent Luke Hobbs forming an unlikely alliance with Statham's Deckard Shaw.",
                "/keym7MPn1icW1wWfzMnW3HeuzWU.jpg",
                true));
        return tvShows;
    }

    public static ArrayList<MoviesResponses> generateRemoteDummyMovies() {

        ArrayList<MoviesResponses> movies = new ArrayList<>();

        movies.add(new MoviesResponses(384018,
                "/keym7MPn1icW1wWfzMnW3HeuzWU.jpg",
                "Fast & Furious Presents: Hobbs & Shaw",
                6.5,
                "A spinoff of The Fate of the Furious, focusing on Johnson's US Diplomatic Security Agent Luke Hobbs forming an unlikely alliance with Statham's Deckard Shaw.",
                "2019-08-02"));
        movies.add(new MoviesResponses(429203,
                "6.4",
                "The Old Man & the Gun",
                6.6,
                "The true story of Forrest Tucker, from his audacious escape from San Quentin at the age of 70 to an unprecedented string of heists that confounded authorities and enchanted the public.  Wrapped up in the pursuit are a detective, who becomes captivated with Forrest’s commitment to his craft, and a woman, fullmazahd.in who loves him in spite of his chosen profession.",
                "/a4BfxRK8dBgbQqbRxPs8kmLd8LG.jpg"));
        movies.add(new MoviesResponses(384018,
                "6.5",
                "Fast & Furious Presents: Hobbs & Shaw",
                6.7,
                "A spinoff of The Fate of the Furious, focusing on Johnson's US Diplomatic Security Agent Luke Hobbs forming an unlikely alliance with Statham's Deckard Shaw.",
                "/keym7MPn1icW1wWfzMnW3HeuzWU.jpg"));
        return movies;
    }

    public static ArrayList<TVShowResponses> generateRemoteDummyTVShows() {

        ArrayList<TVShowResponses> tvShows = new ArrayList<>();

        tvShows.add(new TVShowResponses(60735,
                "The Flash",
                6.5,
                "2014-10-07",
                "/fki3kBlwJzFp8QohL43g9ReV455.jpg",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.")
        );
        tvShows.add(new TVShowResponses(384018,
                "The Flash",
                6.5,
                "2014-10-07",
                "/fki3kBlwJzFp8QohL43g9ReV455.jpg",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.")
        );
        tvShows.add(new TVShowResponses(384018,
                "The Flash",
                6.5,
                "2014-10-07",
                "/fki3kBlwJzFp8QohL43g9ReV455.jpg",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.")
        );
        return tvShows;
    }

    public static MovieDetailsResponse generateRemoteDummyMoviesDetails(Integer movieId) {
        return new MovieDetailsResponse(
                movieId,
                "A spinoff of The Fate of the Furious, focusing on Johnson's US Diplomatic Security Agent Luke Hobbs forming an unlikely alliance with Statham's Deckard Shaw.",
                "/keym7MPn1icW1wWfzMnW3HeuzWU.jpg",
                "2019-08-02",
                "Fast & Furious Presents: Hobbs & Shaw",
                6.5);
    }

    public static TVShowDetailsResponse generateRemoteDummyTVShowsDetails(Integer tvShowId) {
        return new TVShowDetailsResponse(
                tvShowId,
                "2014-10-07",
                "The Flash",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "/fki3kBlwJzFp8QohL43g9ReV455.jpg",
                6.6);
    }

    public static MoviesEntity getMovieDetail(MoviesEntity moviesEntity, boolean bookmarked) {
        MoviesEntity movies;
        movies = moviesEntity;
        movies.setFavorite(bookmarked);
        return movies;
    }

    public static TVShowEntity getTvShowsDetail(TVShowEntity tvShowEntity, boolean bookmarked) {
        TVShowEntity tvShow;
        tvShow = tvShowEntity;
        tvShow.setFavorite(bookmarked);
        return tvShow;
    }
}
