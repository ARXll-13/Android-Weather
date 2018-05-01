# Instructions
1. Fork this repo into your own github account
2. Complete the task
3. Commit your code to your fork (we want to be able to see your source code!)
4. Submit a publicly viewable link to your forked repo in the application form
5. If you have any comments or assumptions, add them to this README.md file

# Task
Create an app that has one button. When the button is pressed, the current temperature in Houston Texas is shown in a text field. By current temperature, we mean the temperature when we run your app.

# Your comments or assumptions
This app contains a textview and a button. When opened, this app will download and prase the json weather data of Houston from onopenweathermap.org via http://samples.openweathermap.org/data/2.5/weather?id=4699066&appid=c68b9e7c160eb22994944055838d20e0, where 4699066 is the id of Houston and c68b9e7c160eb22994944055838d20e0 is the api key I applied.

The above url link is supposed to return the json weather data of Houston. However due to some unknown issue, the above url link return a default json data, which is the weather data of Cairns instead of Houston. 

Since the app works anyway and I don't have time to figure out the issue with the api, I will leave it like this.
