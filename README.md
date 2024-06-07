## AntiBoredom Playlist Web Scraper

## Overview
A Java-based application designed to automatically scrape top music hits from three websites and shuffles them together to generate an iconic playlist suggestion for the user.

## Features
- **Web Scraping**: The logic uses Java web scraper API offered by Jsoup to extract all the data of interest from each HTML element in Billboard 100, BBC Radio, and Last FM. 
- **Playlist Management**: Adds new songs to a playlist, ensuring there are no duplicates and all entries have complete details.
- **User Interface**: Simple console output that shows the list of songs scraped from the web.
- **Extensibility**: The project is structured to be easily extendable for additional features such as saving playlists, more detailed song information, or scraping from different sources.

## DEMO
![Screenshot 2024-06-07 at 11.42.21 AM.png](..%2F..%2FDesktop%2FScreenshot%202024-06-07%20at%2011.42.21%E2%80%AFAM.png)

## Project Structure
- `Main.java`: Contains the main method and the web scraping logic.
- `Song.java`: Defines the `Song` class with properties such as title, and artist.
- `Album.java`: (Optional) If included, manages album-related information for the songs.

## Prerequisites
- Java JDK 11 or newer
- Maven or any Java build tool (for managing dependencies)
- Jsoup Library (for HTML parsing)

## Setup and Installation
1. **Clone the Repository**
2. **Install Dependencies**
3. **Build the Project**
4. **Run the Application**

## Contributing
Contributions are welcome! Please feel free to submit pull requests, create issues for bugs, or suggest new features.


