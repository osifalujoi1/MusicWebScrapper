## AntiBoredom Playlist Web Scraper Project

## Overview
The AntiBoredom Playlist Web Scraper is a Java-based application designed to automatically scrape the top music hits from the Billboard Hot 100 chart. This project is perfect for music enthusiasts looking to keep their playlists fresh and updated with the latest popular tracks without manual effort.

## Features
- **Web Scraping**: Automatically scrapes the Billboard Hot 100 webpage to retrieve the latest top music hits.
- **Playlist Management**: Adds new songs to a playlist, ensuring there are no duplicates and all entries have complete details.
- **User Interface**: Simple console output that shows the list of top songs scraped from the web.
- **Extensibility**: The project is structured to be easily extendable for additional features such as saving playlists, more detailed song information, or scraping from different sources.

## Project Structure
- `Main.java`: Contains the main method and the web scraping logic.
- `Song.java`: Defines the `Song` class with properties such as title, artist, and duration.
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

## Usage
After running the application, it will automatically fetch the top 100 songs from the Billboard Hot 100 chart and display them on the console. It filters out duplicates and ensures that only complete song entries are shown.

## Contributing
Contributions are welcome! Please feel free to submit pull requests, create issues for bugs, or suggest new features.

## License
This project is open source and available under the [MIT License](LICENSE.md).


Thank you for checking out the AntiBoredom Playlist Web Scraper! Enjoy automating your music playlist updates.

