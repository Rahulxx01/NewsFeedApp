# NewsFeedApp
News feed app which gives a user regularly-updated news from the internet related to a particular topic, person, or location.

An Android application to display the latest news using theGuardian Api.

NewsFeedApp was just my attempt to understand the basics of Networking in Android.

While developing this app i learnt things like

1.How to generate API key.
2.How to read a Java Script Object Notation Script(JSON) using the JSON formatter.
3.How to parse and extract data from JSON in Android.
4.How to display the parsed data in a List View using Loaders or Asynctask.
5.How to fire an Intent to a Browsers to display the selected news in the list.

First i generated an API key from theGuardian Website link given below 
http://open-platform.theguardian.com/access/

After generating the Api key i uses to generate Uniform Resource Locator(URL) based upon the data i needed to fetch.

After generating URL the output returned was in JSON format I read that data using the JSON 
formatter which is available in Google 

The one which i referred was this 
https://jsonformatter.curiousconcept.com/

The data then was converted into key value pair and based upon my needs I got the specific data for the App.

All implementation is done in Android Studio.

Learning these concepts were important for me to understand the Networking basis during my Android Development. This project was listed in the Udacity's Android Basics Networking but since I was watching free videos i just used the project rubric to develop this App.

Developer Rahul Yadav
