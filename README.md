# EatStreet2 🍜📍

EatStreet2 is a location-based Android application that allows users to discover nearby restaurants using Google Maps and Google Places API. Users must sign in via Firebase Authentication to access the app. After logging in, the app displays the user's location on a map and lists nearby restaurants, showing their name, rating, and photo if available.

---

## 🚀 Features

* 📌 Shows the user's current location on a Google Map.
* 🏪 Displays nearby restaurants fetched via Google Places API.
* ⭐ Shows name, rating, photo, and number of user reviews.
* 🔁 Allows refreshing location and nearby restaurant list.
* 🔐 Firebase Authentication integration (login protection).
* 📲 Smooth and responsive UI with RecyclerView.

---

## 🛠 Tech Stack

| Component         | Technology                  |
| ----------------- | --------------------------- |
| Language          | Java                        |
| Location Services | FusedLocationProviderClient |
| Maps              | Google Maps SDK             |
| Places Data       | Google Places API           |
| UI                | RecyclerView, CardView      |
| Backend/Auth      | Firebase Authentication     |
| Networking        | OkHttp                      |
| JSON Parsing      | org.json                    |

---

## 🔧 Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/EatStreet2.git
cd EatStreet2
```

### 2. Configure Google APIs

* Go to [Google Cloud Console](https://console.cloud.google.com/)
* Enable the following APIs:

  * **Maps SDK for Android**
  * **Places API**
* Generate an **API Key**
* Add the API Key to `res/values/strings.xml`:

```xml
<string name="maps_api_key">YOUR_API_KEY_HERE</string>
```

### 3. Set Up Firebase

* Go to [Firebase Console](https://console.firebase.google.com/)
* Create a new Firebase project
* Register your Android app (package: `com.example.eatstreet2`)
* Download `google-services.json` and place it inside the `app/` directory
* Enable **Email/Password Authentication** in Firebase > Authentication

### 4. Run the App

* Open the project in **Android Studio**
* Sync Gradle and build the project
* Run on an emulator or a real Android device (location enabled)

---

## 📱 Permissions Required

The app requests the following runtime permissions:

```xml
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
<uses-permission android:name="android.permission.INTERNET"/>
```

These are required to:

* Get the device's current location
* Make API calls to Google Maps and Places

---

## 📂 Project Structure

```
EatStreet2/
├── activities/
│   ├── MainActivity.java
│   └── LoginActivity.java
│   └── PlaceItem.java
│   └── PlacesAdapter.java
├── res/
│   ├── layout/
│   │   └── activity_main.xml
│   ├── values/
│   │   └── strings.xml
│   ├── drawable/
│
├── AndroidManifest.xml
├── build.gradle (Module)
├── google-services.json
└── README.md
```

---

## 📌 API Reference

### Google Places Nearby Search API

Example URL:

```
https://maps.googleapis.com/maps/api/place/nearbysearch/json
?location=LATITUDE,LONGITUDE
&radius=1500
&type=restaurant
&key=YOUR_API_KEY
```

Fields used:

* `name`
* `rating`
* `user_ratings_total`
* `photos.photo_reference`
* `place_id`

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---
