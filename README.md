# What is the Movie App 2.0.0?
The past decade held a lot of movies, some left a mark and some were just a set of 24-60
pictures per second. We would like you to create a Master - Detail Application to showcase
those movies and the signature they left behind.

# Requirements
* You will have a local list of movies that should be displayed in any order.
* The list is searchable and the search results will be categorized by Yea
* Each search result category will hold at most the top rated 5 movies of this category (year)
* Once a movie is selected from the search results, you will switch to a detailed view to unveil the following:
* Movie Title
* Movie Year
* Movie Genres (if any)
* Movie Cast (if any)
* A two column list of pictures fetched from flickr that matches the movie title as the search query.

# User Stories
#### 1. As a user I wanna open the movie app, SO THAT I expect to see a list of movies.
##### Acceptance criteria
* The movie name, picture and rate should be displayed.
* There shouldn’t be any delay if we have a huge list of movies.
* The movies should be displayed in a grid of two columns.
* A loader should be displayed during loading the movie.
##### Expected design
---
#### 2. As a user I wanna tab on any movie in the list, SO THAT I expect to see the movie details.
##### Acceptance criteria
The Movie screen should contains:
* Movie Title
* Movie Year
* Movie Genres (if any)
* Movie Cast (if any)
* A two column list of movie pictures.
* The movie photos should be scrollable until all the photos displayed
##### Expected Design
In the app. Clone and run the repo now :D.
---
#### 3. As a user I wanna search for any movie using the title, SO THAT I expect to show the movies that contain my text.
##### Aceptance criteria
* The search result should contain only 5 movies for each year if their title matched the entered text.
* A loader should appear during executing the query
* The prcessing time should be lest than half secound to simulate the real time process.
##### Expected Design
In the app. Clone and run the repo now :D.

# Challanges
#### 1. Inserting around 3000 rounds in mobile database during the first open as seed that will be displayed in the first page.
The soltuion to avoid any exceptions that we make heavy consumtion in the database if we inserted them with only one statment or making them one by one is:
* Sort them like the order you will display in the home screen with search algorithm that takes O(nlogn)
* Group the sorted list by year.
* Insert each group one by one with making half second delay betwean each group.
---
#### 2. Displaying the movies on the main screen during inserting seed still running which may lead the the main screen won't contain any items if it opens faster than the execution of the first seed statment.
The solution to handle this racing issue is: Listen to the movies tabke until the counter of the raws of the table increase than 0 after that execution the fetching statments.

# Archircture Pattern
I belive in **Law of inertia, also called Newton’s first law**, postulate in physics that, if a body is at rest or moving at a constant speed in a straight line, it will remain at rest or keep moving in a straight line at constant speed unless it is acted upon by a force.

Every thing in life is **state** and to move from any state to another there should be an action done to make this transation.

Let's simulate this in our android world :D. Each screen represented as **imutable** state and to move from any state to another should be an action done by the user or the system like location detected.

That's what the **MVI** pattern is taking about, but I hated every resprentation for MVI that concatenate each in a single every action in one stream and make many switch cases to diferenate between them even if I know the action before and I can call the specific function in the without making an switches.

So I implemented the MVI with new changes in this [example](https://github.com/ashraf-atef/RestaurantMenus). After that I detected that there an awseome android framework from Airbnb, it's name [MvRx (pronounced mavericks)](https://github.com/airbnb/MvRx). 

MvRx built amazing framework with caring about awesome details and introduced awesome ideas using kotlin features. I really loved it and make me very keen on seeing what they are intrduces in other android.

The second thing they introdued they introdued another third party for making the implenation from the simple recyclerview to the **monster** recylerview be so easy to be implemented.

They make the recyclerview be like you are typing DEVs each one under the other in the HTML and introduces [Epoxy](https://github.com/airbnb/epoxy).

 So I was very very very impresed by those cristal minds and used those two third party libratis in my all projected that made our android world and the framework more easier and happier :).
 
 # Libraries 
* MvRx
* Epoxy
* Android X
* Retrofit
* Rx-Java
* Rx-Android
* ViewModel
* Room
* Koin
* Kotlin
* Mockito

# TODO
* ViewModel Test Cases
* UseCases Test Cases
* Repos and Data Sources Test Cases
* DAOs Test Cases (Queries)

# Special thanks
* [Abdelrhman Walid](https://www.linkedin.com/in/abdelrhmanwalid) my friend who works in Truecaller and who left in the Elmenus Repos amazing code that uses MvRx and Epoxy.
* [Ahmed Mahmoud Saleh](https://www.linkedin.com/in/amssm) My awesome teammate who keep working with on the Bootsrap Android project in Robusta Studio after the working hours and after I left Robusta Studio.
* [Mostafa Montser](https://www.linkedin.com/in/mostafa-magdy-09587758) my study group leader in MAL.
* Tawseela team 🧡
    * Tayssir Elhwary
    * Abdlerhman Fawzy
    * Mostafa Abou Elkhir
    * Droid 
    * Khaled Elngar
    * Mohammed Elkamhawy
    * Nour Abou Elkasem
    * Hadeel Omar
    * Mohammed Elshenay (Consltant)
    * Mohamed Hashish (Consltant)
* Robusta Team 🧡
    * Doha Basem
    * Omar Bashary
    * Mohamed Shalan
    * Ahmed Mahmoud Saleh
    * Haidy Emad
    * Ahmed AbouRaya (Mobile Team Leader)
    * Ahmed AbouSayfy (Operation Manager)
    * Islam AbdElraof (Product Manager)
    * Hussien Mohy-Eldeen (CEO)
* Elmenus Team 🧡
    * Mohammed Fawzy
    * Mohammed Elazab
    * Sara Elsheemi 
    * Andrew Isaac
