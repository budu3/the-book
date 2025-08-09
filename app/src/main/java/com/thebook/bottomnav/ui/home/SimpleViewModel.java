package com.thebook.bottomnav.ui.home;

/**
 * SimpleViewModel - Data model class for individual movie items
 * 
 * This class represents a single movie object with the essential information
 * needed to display movie cards in RecyclerView lists. It acts as a data 
 * transfer object between the JSON data source and the UI components.
 * 
 * Note: Despite the name "ViewModel", this is actually a data model/POJO class,
 * not an Android Architecture Component ViewModel.
 */
public class SimpleViewModel {
    // The poster image resource name (e.g., "tt37", "tt38")
    private String poster;
    
    // The movie title (e.g., "Avatar", "Pirates of the Caribbean")
    private String title;
    
    // The drawable resource ID for the movie poster image
    private int image;

    /**
     * Default constructor required for object instantiation
     */
    public SimpleViewModel() {
        // Default constructor
    }

    /**
     * Parameterized constructor for creating a movie object with all data
     * @param title The movie title
     * @param poster The poster resource name
     * @param image The drawable resource ID for the poster image
     */
    public SimpleViewModel(String title, String poster, int image) {
        this.title = title;
        this.poster = poster;
        this.image = image;
    }

    // Getter and setter methods for accessing private fields

    /**
     * @return The drawable resource ID for the movie poster image
     */
    public int getImage() {
        return image;
    }

    /**
     * @param image The drawable resource ID to set for the movie poster
     */
    public void setImage(int image) {
        this.image = image;
    }

    /**
     * @return The poster resource name (used to find drawable resources)
     */
    public String getPoster() {
        return poster;
    }

    /**
     * @param poster The poster resource name to set
     */
    public void setPoster(String poster) {
        this.poster = poster;
    }

    /**
     * @return The movie title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The movie title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
