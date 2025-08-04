# STOCK OF THE DAY APP
Stock of the Day App is an Android app I built as my final project for Google Summer School: Android ABC. It was developed using Jetpack Compose and displays 30 stocks from some of the world's largest companies.

The app shows the current price of each stock, its percentage growth over the past 5 years, and a short description for each one.

## How It's Made

**Tech used:** Kotlin - Jetpack Compose

The app was built using everything I learned about Jetpack Compose during the Google Summer School. I applied all the core concepts covered in the program — from state management to building dynamic UIs with composables.

For the stock data, I integrated the Financial Modeling Prep API, which I discovered and learned to use on my own by researching online. This allowed me to fetch real-time stock prices and historical performance data to make the app more functional and informative.

## Lessons Learned:

The UI part of the app went really smoothly — I actually enjoyed working with Jetpack Compose and became pretty comfortable with the layout elements we learned during the summer school. However, I ran into a few challenges when it came to working with the API.

Initially, I planned to use the Alpha Vantage API, but once I started making requests, I realized the free plan only allows 50 requests per day. That was my first big lesson: *always read the limitations of the free plan before choosing an API*.

After that, I switched to the Financial Modeling Prep API, which gives me 250 requests per day, more than enough for my needs. The app makes 90 requests in total (3 for each of the 30 stocks) every time it runs. I did consider optimizing this by implementing some sort of local caching system — for example, only fetching the data once per day at a set time instead of every time the app is opened. But I figured that was a bit too advanced for the scope of this project.

### Overall, it was a great learning experience and I genuinely enjoyed working on this app!

