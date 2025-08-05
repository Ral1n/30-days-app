# STOCK OF THE DAY APP
Stock of the Day App is an Android app I built as my final project for Google Summer School: Android ABC. It was developed using Jetpack Compose and displays 30 stocks from some of the world's largest companies.

The app shows the current price of each stock, its percentage growth over the past 5 years, and a short description for each one.

## How It's Made

**Tech used:** Kotlin - Jetpack Compose

Before jumping into the code, I started by sketching the initial design of the app using [Excalidraw](https://excalidraw.com/). This helped me visualize the layout and structure of the screens in a simple way.

For the UI style and color choices, I took inspiration from a design I found on [Tailwind UI Blocks – Stats](https://tailwindcss.com/plus/ui-blocks/application-ui/data-display/stats). I trusted that their design choices, especially around color and spacing, would look good, so I used that as a reference while building the UI.

The company logos used in the app were downloaded from [logo.dev](https://www.logo.dev/), which made it easy to quickly fetch high-quality branding assets for each stock.

The app was built using everything I learned about Jetpack Compose during the Google Summer School. I applied all the core concepts covered in the program.

For the stock data, I integrated the [Financial Modeling Prep API](https://site.financialmodelingprep.com/), which I discovered and learned to use on my own by researching online. This allowed me to fetch real-time stock prices and historical performance data to make the app more functional and informative.

## Lessons Learned:

The UI part of the app went really smoothly. I actually enjoyed working with Jetpack Compose and became pretty comfortable with the layout elements we learned during the summer school. However, I ran into a few challenges when it came to working with the API.

Initially, I planned to use the [Alpha Vantage API](https://www.alphavantage.co/), but once I started making requests, I realized the free plan only allows 50 requests per day. That was a big lesson: *always read the limitations of the free plan before choosing an API*.

After that, I switched to the [Financial Modeling Prep API](https://site.financialmodelingprep.com/), which gives me 250 requests per day, more than enough for my needs. The app makes around 90 requests in total (3 for each of the 30 stocks), but they’re not all sent at once. Since the stock items are displayed in a LazyColumn, the requests are triggered gradually, only when the items become visible on the screen as the user scrolls. 

I did consider optimizing this by implementing some sort of local caching system: for example, only fetching the data once per day at a set time instead of every time the app is opened. But I figured that was a bit too advanced for the scope of this project.

### Overall, it was a great learning experience and I genuinely enjoyed working on this app!

