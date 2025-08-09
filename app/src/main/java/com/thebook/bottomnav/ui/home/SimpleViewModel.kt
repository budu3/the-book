package com.thebook.bottomnav.ui.home

/**
 * ========================================
 * MOVIE DATA MODEL
 * ========================================
 * 
 * PURPOSE:
 * This represents a single movie in our app. It's a simple data container
 * that holds all the information we need to display a movie card.
 * 
 * WHAT IT CONTAINS:
 * - title: The movie name (e.g., "Avatar", "Spectre")
 * - posterUrl: Web URL where the movie poster image is located
 * - resourceId: Local Android resource ID (for offline images)
 * 
 * WHY IT'S A DATA CLASS:
 * Kotlin data classes automatically provide:
 * - equals() and hashCode() methods
 * - toString() method for debugging
 * - copy() method for creating modified copies
 * - Component functions for destructuring
 * 
 * USAGE EXAMPLE:
 * val movie = Movie("Avatar", "https://example.com/avatar.jpg")
 * println(movie.title) // Prints: Avatar
 */
data class Movie(
    val title: String = "",           // Movie title displayed to user
    val posterUrl: String = "",       // URL for downloading poster image
    val resourceId: Int = 0           // Local resource ID (fallback/offline)
) {
    /**
     * CONVENIENCE CONSTRUCTOR
     * For backwards compatibility - creates movie with just title and local resource
     */
    constructor(title: String, resourceId: Int) : this(title, "", resourceId)
    
    /**
     * UTILITY METHODS
     */
    
    /**
     * Checks if this movie has a valid poster URL for downloading
     */
    fun hasOnlinePoster(): Boolean = posterUrl.isNotEmpty()
    
    /**
     * Checks if this movie has a local resource image
     */
    fun hasLocalResource(): Boolean = resourceId != 0
    
    /**
     * Gets display-friendly string for debugging
     */
    override fun toString(): String = "Movie(title='$title', hasOnline=${hasOnlinePoster()}, hasLocal=${hasLocalResource()})"
}

/**
 * MIGRATION HELPER
 * Type alias to maintain compatibility with existing code that uses SimpleViewModel
 * TODO: Remove this after updating all references to use Movie instead
 */
typealias SimpleViewModel = Movie
