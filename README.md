# Web Scraper and Search Engine

## Description

**Web Scraper and Search Engine** is a comprehensive tool that indexes web pages with relevant information into a MySQL database. It features web spider functionality to recursively index linked pages from the original site. The frontend is a search engine, visually resembling Google, allowing users to search for previously indexed web pages.

## Technologies Used

### Backend:
- **Java**
- **Spring Boot**
- **JPA (Java Persistence API)**
- **Hibernate**
- **JSoup**
- **MySQL**

### Frontend:
- **JavaScript**
- **CSS**

## Key Features

- **Web Scraping and Indexing**: Uses JSoup to extract and store web page information.
- **Web Spiders**: Automatically indexes linked pages, creating a comprehensive data repository.
- **Search Engine Interface**: A user-friendly frontend, resembling Google, for querying indexed pages.
- **Database Management**: Efficiently stores and retrieves data using MySQL.

## Installation

### Prerequisites:
- **Java 17 or higher**
- **Maven**
- **MySQL**

### Installation Steps:

1. **Clone the Repository**:
    ```bash
    git clone https://github.com/JTarangoDev/web-scraper-search-engine.git
    cd web-scraper-search-engine
    ```

2. **Configure the Database**:
    - Set up a MySQL database and update `application.properties` in `src/main/resources` with your credentials.

3. **Build and Run the Backend**:
    ```bash
    mvn spring-boot:run
    ```

## Usage

1. **Index Web Pages**: To index a web page, make a GET request to the endpoint `/api/webscrapper?url=` followed by the URL you want to index.
    For example: GET `http://localhost:8080/api/webscrapper?url=https://example.com`
    This will start the indexing process for the specified URL.
2. **Recursive Web Indexing**: To perform a recursive indexing of web pages (where the scraper follows links on each page and indexes them as well), you must modify the SpiderService.java class. Specifically, edit the start() method and update the initialLink variable with the desired starting URL as a string. This will enable the scraper to follow and index linked pages recursively.
3. **Use the Web Page Search**: After indexing, access the frontend search interface at: `http://localhost:8080` Use the search bar to find previously indexed web pages.

## Contact
For any questions, you can contact the project author at [jav.tarango@gmail.com](jav.tarango@gmail.com).
