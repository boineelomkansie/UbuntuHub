# UbuntuHub

UbuntuHub is a community support Android application developed for the OPSC6312 project. The application connects community members so that they can request help, offer assistance, share community updates and participate in local activities.

## Purpose

The purpose of UbuntuHub is to provide a digital platform where users can:

- Register and sign in securely
- Request help from their community
- Offer help to other community members
- Create and view community posts
- View community information
- Manage application settings
- Interact with data stored through a REST API

## Main Features

### Authentication
UbuntuHub uses Firebase Authentication with email and password authentication.

The application supports:

- User registration
- User login
- Password reset
- Firebase authentication
- Firebase user identification
- User synchronisation with the UbuntuHub PostgreSQL database

Passwords are handled by Firebase Authentication rather than being stored directly in the UbuntuHub database.

### Community Feed

The Home screen retrieves community posts from the UbuntuHub REST API.

Users can view:

- Community posts
- Post descriptions
- Categories
- Locations
- Recent posts

### Create Post

Authenticated users can create community posts.

A post contains:

- Title/category
- Description
- Location
- Category
- User ID

The application sends the post to the UbuntuHub REST API, which stores it in PostgreSQL.

### REST API

UbuntuHub uses an ASP.NET Core Web API developed with .NET 8.

The API provides endpoints for:

- Retrieving posts
- Creating posts
- Synchronising users
- Retrieving users using their Firebase UID

### Database

The backend uses PostgreSQL.

Entity Framework Core is used to communicate with the database.

The database contains entities including:

- Users
- Posts
- Interests
- Messages

### Error Handling

The application handles invalid input and common API/network failures without crashing.

Examples include:

- Invalid login information
- Empty post descriptions
- Invalid users
- Missing categories
- Server errors
- Internet connection failures
- API connection failures

## Technology Stack

### Android Application

- Kotlin
- Jetpack Compose
- Material 3
- Android Studio
- Firebase Authentication
- Retrofit
- Gson
- Android Navigation

### Backend

- ASP.NET Core Web API
- .NET 8
- Entity Framework Core
- PostgreSQL
- Npgsql

### Testing

- JUnit
- xUnit
- Entity Framework Core InMemory
- GitHub Actions

## Automated Testing

UbuntuHub contains automated tests for both the Android application and REST API.

### Android Tests

The Android application contains six unit tests covering:

- Valid email validation
- Invalid email validation
- Password validation
- Empty post validation
- Valid post validation
- Username fallback behaviour

All six Android unit tests pass successfully.

### API Tests

The REST API contains five automated tests covering:

- Retrieving posts
- Creating a valid post
- Rejecting an empty description
- Rejecting an invalid user
- Rejecting an empty category

All five API tests pass successfully.

## GitHub Actions

GitHub Actions is used to automatically verify the project.

### Android CI

The Android workflow:

1. Checks out the repository
2. Sets up Java 17
3. Sets up Gradle
4. Runs Android unit tests
5. Builds the debug APK

### API CI

The API workflow:

1. Checks out the repository
2. Sets up .NET 8
3. Restores dependencies
4. Builds the API
5. Runs automated tests

Both CI workflows have been successfully executed.

## Security

Firebase Authentication is used to manage user authentication.

Database connection credentials are stored locally and excluded from the Git repository using `.gitignore`.

Sensitive configuration files such as `appsettings.json` are not committed to the public repository.

## Design Considerations

UbuntuHub uses a simple community-focused interface designed to make the primary actions easy to access.

The application uses a consistent visual design based on Ubuntu-inspired colours, including orange, green and cream.

The interface focuses on:

- Clear navigation
- Readable text
- Simple layouts
- Consistent controls
- Input validation
- Error feedback
- Mobile usability

## Project Structure

```text
UbuntuHub
├── app
│   ├── src
│   │   ├── main
│   │   │   └── java/com/ubuntuhub/app
│   │   ├── test
│   │   └── androidTest
│   └── ...
├── .github
│   └── workflows
│       └── android.yml
├── build.gradle.kts
├── settings.gradle.kts
└── README.md