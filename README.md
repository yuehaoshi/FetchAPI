# Fetch Data and Display in Android app

## Intro
This is an Android app for fetching data from [data source](https://fetch-hiring.s3.amazonaws.com/hiring.json), and display retrieved data using nested recyclerlist.  

## Overview of this app:  
![Screenshot 2023-02-06 at 03 12 51](https://user-images.githubusercontent.com/70357591/216933331-44ebf6e2-6c31-4074-8cfc-011d08527ea8.png)

## Details
- This app display the list of items to the user based on the following requirements:  
    - Display all the items grouped by "listId"
    - Sort the results first by "listId" then by "name" when displaying.
    - Filter out any items where "name" is blank or null.  
- Finally, this app used a nested list to display the retrieved data.  


## Reference
> The data retrieving part is referred from [this post](https://thecodingshef.com/fetch-data-in-recyclerview-using-volley-library-in-android-complete-project/)  
> The nested recyclerView part is referred from [this post](https://www.geeksforgeeks.org/how-to-create-a-nested-recyclerview-in-android/)
