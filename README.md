# Textbook Seller App

## 📍 Quick Navigation

* [Project Scope](#project-scope)
* [Features](#features-)
* [Technologies Used](#technologies-used-)
* [Project Structure](#project-structure-)
* [OOP Concepts Implemented](#oop-concepts-implemented-)
* [Setup Instructions](#how-to-run-the-project-️)
* [Execution](#execution-)
* [Example Usage](#example-usage-)
* [Members and Contributors](#authors-)


---

# CSC313 – Android Marketplace Assignment

## This project demonstrates the implementation of Java Object-Oriented Programming concepts including Classes, Methods, Objects, Interfaces, Abstract Classes, Exception Handling, ArrayLists, Searching, Validation, and Android UI development.

## Project Scope

This application was developed for the CSC313 Object-Oriented Programming module at the University of Fort Hare.

The Textbook Seller App is an Android marketplace-style application that allows students to:

* Buy and sell textbooks
* Add textbook listings
* Browse available textbooks
* Search textbooks by title or seller
* Store seller banking information
* Manage textbook inventory

The system is designed to simulate a simple digital marketplace while demonstrating core software engineering and Object-Oriented Programming principles.

---

## Overview

Textbook Seller is an Android application developed in Java using Android Studio. The app allows students to list textbooks for sale, browse available textbooks, and search for books or sellers quickly and easily.

This project was created to demonstrate Object-Oriented Programming (OOP) concepts such as:

* Abstraction
* Inheritance
* Encapsulation
* Polymorphism
* Interfaces
* Exception Handling
* ArrayLists and Searching

---

## Features 

### Browse Textbooks

Users can view all textbooks currently listed in the inventory.

### Add Textbooks

Users can add a textbook by entering:

* Book Title
* Seller Name
* Price
* Banking Information

### Search Functionality

The app supports searching textbooks by:

* Book title
* Seller name

### Duplicate Prevention

The system prevents duplicate textbook listings from the same seller.

### Success & Error Messages

Toast notifications are displayed for:

* Successful textbook listings
* Invalid input
* Duplicate entries
* Search results

---

## Technologies Used 

* Java
* Android Studio
* Gradle
* XML Layouts
* Android SDK

---

## Project Structure 

```text
app/src/main/java/com/product/TextbookSeller/
│
├── MainActivity.java        # Main application logic and UI handling
├── Product.java             # Abstract parent class
├── Textbook.java            # Textbook class extending Product
├── Sellable.java            # Interface for sellable items
└── TextbookManager.java     # Handles inventory and search logic
```

---

## OOP Concepts Implemented 

### 1. Abstraction

`Product.java` is used as an abstract class to define common textbook properties.

### 2. Inheritance

`Textbook.java` extends the `Product` abstract class.

### 3. Encapsulation

Private variables with getter methods are used to protect data.

### 4. Polymorphism

Methods such as `getPrice()` and `displayDetails()` are overridden in the `Textbook` class.

### 5. Interfaces

`Sellable.java` demonstrates interface implementation.

---

## How to Run the Project 

### Requirements

* Android Studio(The IDE we used)
* JDK 17 or higher
* Android SDK
# Environment Configuration: Before running the application, the development environment must be configured correctly. Ensure that JDK 17 or a higher version is installed on your machine and that the JAVA_HOME environment variable points to the JDK installation directory. The JDK bin folder should also be added to the system PATH so that Gradle and Android Studio can access Java commands successfully.

# Android Studio should recognize the installed Android SDK automatically once the SDK has been installed through the SDK Manager. After opening the project in Android Studio, allow Gradle to sync completely before attempting to build or run the application.


### Steps

1. Download or clone the repository.
2. Open the project in Android Studio.
3. Allow Gradle dependencies to sync.
4. Run the application using:

   * Android Emulator, or
   * Physical Android device.

---

## Screenshots 

You can add screenshots of:

* Main screen
* Add textbook form
* Search functionality
* Inventory display

Example:

```text
screenshots/home_screen.png
screenshots/search_screen.png
```

---

## Example Usage 

### Adding a Textbook

| Field        | Example                     |
| ------------ | --------------------------- |
| Title        | Object-Oriented Programming |
| Seller       | Siyabonga                   |
| Price        | 350                         |
| Banking Info | Capitec 123456789           |

### Searching

Searching for:

* "Programming"
* "Siyabonga"

will return matching textbooks.

---

## Members and Contributors

* Cebisile Mtyu (Leader)
* Siyabonga Brains Nkosi 
* Anda Gxoyiya
* Alizwa Mbane
* Lonwabo Mbhele
* Lubabalo Ndyawe
* Wanga Talaba

---
