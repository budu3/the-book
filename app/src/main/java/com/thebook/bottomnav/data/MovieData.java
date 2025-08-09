package com.thebook.bottomnav.data;

/**
 * MovieData - Static data container for movie information
 * 
 * This class holds the hardcoded movie data in JSON format that was previously
 * embedded in MainActivity. By extracting it to a separate class, we improve
 * code maintainability and separation of concerns.
 * 
 * The JSON contains movie information including:
 * - director_name: The movie director
 * - actor_1_name, actor_2_name: Main actors
 * - movie_title: The title of the movie
 * - genres: Movie categories (Action, Adventure, etc.)
 * - plot_keywords: Keywords describing the plot
 * - title_year: Year of release
 * - poster: Image resource identifier for the movie poster
 */
public class MovieData {
    /**
     * Static JSON array containing movie data.
     * This data represents a collection of popular movies with their metadata.
     * Each movie object contains all the information needed to display movie cards
     * in the RecyclerView lists on the Home screen.
     */
    public static final String MOVIE_JSON_ARRAY = "[{" +
            "\"director_name\": \"James Cameron\"," +
            "\"actor_2_name\": \"Joel David Moore\"," +
            "\"genres\": \"Action|Adventure|Fantasy|Sci-Fi\"," +
            "\"actor_1_name\": \"CCH Pounder\"," +
            "\"movie_title\": \"Avatar\"," +
            "\"plot_keywords\": \"avatar|future|marine|native|paraplegic\"," +
            "\"title_year\": 2009," +
            "\"poster\": \"tt37\"" +
            "}," +
            "{" +
            "\"director_name\": \"Gore Verbinski\"," +
            "\"actor_2_name\": \"Orlando Bloom\"," +
            "\"genres\": \"Action|Adventure|Fantasy\"," +
            "\"actor_1_name\": \"Johnny Depp\"," +
            "\"movie_title\": \"Pirates of the Caribbean: At World's End\"," +
            "\"plot_keywords\": \"goddess|marriage ceremony|marriage proposal|pirate|singapore\"," +
            "\"title_year\": 2007," +
            "\"poster\": \"tt38\"" +
            "}," +
            "{" +
            "\"director_name\": \"Sam Mendes\"," +
            "\"actor_2_name\": \"Rory Kinnear\"," +
            "\"genres\": \"Action|Adventure|Thriller\"," +
            "\"actor_1_name\": \"Christoph Waltz\"," +
            "\"movie_title\": \"Spectre\"," +
            "\"plot_keywords\": \"bomb|espionage|sequel|spy|terrorist\"," +
            "\"title_year\": 2015," +
            "\"poster\": \"tt39\"" +
            "}," +
            "{" +
            "\"director_name\": \"Christopher Nolan\"," +
            "\"actor_2_name\": \"Christian Bale\"," +
            "\"genres\": \"Action|Thriller\"," +
            "\"actor_1_name\": \"Tom Hardy\"," +
            "\"movie_title\": \"The Dark Knight Rises\"," +
            "\"plot_keywords\": \"deception|imprisonment|lawlessness|police officer|terrorist plot\"," +
            "\"title_year\": 2012," +
            "\"poster\": \"tt40\"" +
            "}," +
            "{" +
            "\"director_name\": \"Doug Walker\"," +
            "\"actor_2_name\": \"Rob Walker\"," +
            "\"genres\": \"Documentary\"," +
            "\"actor_1_name\": \"Doug Walker\"," +
            "\"movie_title\": \"Star Wars: Episode VII - The Force Awakens\"," +
            "\"plot_keywords\": \"\"," +
            "\"title_year\": \"\"," +
            "\"poster\": \"tt41\"" +
            "}," +
            "{" +
            "\"director_name\": \"Andrew Stanton\"," +
            "\"actor_2_name\": \"Samantha Morton\"," +
            "\"genres\": \"Action|Adventure|Sci-Fi\"," +
            "\"actor_1_name\": \"Daryl Sabara\"," +
            "\"movie_title\": \"John Carter\"," +
            "\"plot_keywords\": \"alien|american civil war|mars|princess\"," +
            "\"title_year\": 2012," +
            "\"poster\": \"tt42\"" +
            "}]";
}
