# UbuntuHub – Community Support Application

UbuntuHub is a community support Android application developed as part of the OPSC6312 project. The application aims to connect community members through a digital platform where individuals can request assistance, offer help, share community updates and participate in local activities.

The project combines an Android application, a RESTful backend API, Firebase Authentication and a PostgreSQL database to provide a platform for community interaction and information sharing.

---

## 1. Project Overview

UbuntuHub is designed around the principle of community support and togetherness. It provides users with a central platform to communicate their needs, offer assistance and stay informed about activities within their communities.

The application focuses on making community participation accessible through a simple, user-friendly mobile interface.

### Project Objectives

The main objectives are to:

- Provide secure user registration and authentication.
- Allow community members to create and view community posts.
- Enable users to request assistance and offer support.
- Provide access to community information.
- Store and retrieve application data through a REST API.
- Provide a consistent and accessible mobile user experience.

---

## 2. Main Application Features

### 2.1 User Authentication

UbuntuHub uses Firebase Authentication to manage user authentication through email and password.

The authentication functionality includes:

- User registration.
- User login.
- Password reset.
- Firebase user identification.
- Synchronisation of authenticated users with the UbuntuHub backend database.

Firebase Authentication manages user passwords. Passwords are not stored directly in the UbuntuHub PostgreSQL database.

### 2.2 Community Feed

The Home screen retrieves community posts from the UbuntuHub REST API.

Users can view community information, including:

- Post titles.
- Post descriptions.
- Categories.
- Locations.
- Recent community updates.

The application retrieves this information from the backend rather than relying exclusively on locally stored sample data.

### 2.3 Create Community Posts

Authenticated users can create community posts through the application.

A post contains information such as:

- Title or category.
- Description.
- Location.
- Category.
- User ID.

When a user submits a post, the Android application sends the information to the REST API, which processes and stores the post in PostgreSQL.

### 2.4 Application Navigation and Settings

UbuntuHub provides a mobile interface with dedicated sections for its main application functionality.

The application includes screens such as:

- Home.
- Explore.
- Create Post.
- Messages.
- Profile.
- Settings.

These screens provide the foundation for the application's community interaction and user management functionality.

### 2.5 Error Handling and Input Validation

UbuntuHub incorporates input validation and error handling to improve reliability and prevent common application failures.

Examples include:

- Invalid login information.
- Empty post descriptions.
- Invalid users.
- Missing categories.
- Server errors.
- Internet connection failures.
- API connection failures.

The application aims to provide appropriate feedback when an operation cannot be completed.

---

## 3. Technology Stack

UbuntuHub uses the following technologies:

| Component | Technology | Purpose |
|---|---|---|
| Mobile application | Kotlin | Android application development |
| User interface | Jetpack Compose | Declarative user interface development |
| UI components | Material 3 | Consistent interface components |
| Development environment | Android Studio | Android development and testing |
| Authentication | Firebase Authentication | User registration and authentication |
| API communication | Retrofit | HTTP communication with the backend |
| JSON processing | Gson | JSON serialisation and deserialisation |
| Backend | ASP.NET Core Web API | RESTful backend services |
| Framework | .NET 8 | Backend application framework |
| ORM | Entity Framework Core | Database access and management |
| Database | PostgreSQL | Persistent application data storage |
| Database provider | Npgsql | PostgreSQL integration with .NET |
| Android testing | JUnit | Android unit testing |
| Backend testing | xUnit | API automated testing |
| Version control | Git and GitHub | Source code management |
| CI/CD | GitHub Actions | Automated building and testing |

---

## 4. System Architecture

UbuntuHub follows a client-server architecture.

The Android application communicates with the ASP.NET Core REST API, which handles application data operations and interacts with the PostgreSQL database.

Firebase Authentication is responsible for managing user authentication.

### Architecture Components

**Android Application**
- Provides the user interface.
- Allows users to register and sign in.
- Displays community posts.
- Submits new community posts.
- Communicates with the backend API using Retrofit.

**Firebase Authentication**
- Handles user authentication.
- Manages email and password authentication.
- Provides Firebase user identification.

**ASP.NET Core Web API**
- Receives requests from the Android application.
- Processes user synchronisation.
- Retrieves community posts.
- Creates and stores community posts.
- Communicates with PostgreSQL through Entity Framework Core.

**PostgreSQL Database**
- Stores application data.
- Maintains user and post records.
- Provides persistent backend storage.

### Data Flow

1. A user interacts with the UbuntuHub Android application.
2. Authentication requests are handled by Firebase Authentication.
3. The application communicates with the REST API when backend data is required.
4. The API processes incoming requests.
5. Entity Framework Core communicates with PostgreSQL.
6. The API returns the requested information to the Android application.
7. The application displays the information to the user.

---

## 5. Database Design

UbuntuHub uses PostgreSQL as its relational database management system.

Entity Framework Core provides the object-relational mapping functionality used by the backend.

The backend includes entities such as:

| Entity | Description |
|---|---|
| Users | Stores user-related information and Firebase identification. |
| Posts | Stores community posts and their associated information. |
| Interests | Represents user interests. |
| Messages | Represents community messaging information. |

Firebase Authentication manages passwords and authentication credentials separately from the application database.

Database connection credentials are maintained outside the public source code repository.

---

## 6. REST API

The UbuntuHub backend is developed using ASP.NET Core Web API with .NET 8.

The API provides endpoints for the Android application to communicate with backend services.

### Main API Operations

| Operation | Description |
|---|---|
| Retrieve Posts | Retrieves community posts from PostgreSQL. |
| Create Post | Receives and stores a new community post. |
| Synchronise Users | Synchronises authenticated Firebase users with the backend. |
| Retrieve User | Retrieves user information using a Firebase UID. |

The Android application uses Retrofit to communicate with the API.

---

## 7. Installation and Setup

The following instructions describe the general development environment required to work with UbuntuHub.

### Prerequisites

Install the following development tools:

- Android Studio.
- JDK 17.
- .NET 8 SDK.
- PostgreSQL.
- Git.

### 7.1 Clone the Android Repository

Clone the UbuntuHub Android repository:

```bash
git clone https://github.com/boineelomkansie/UbuntuHub.git
```

Open the cloned project in Android Studio.

Allow Gradle to synchronise the project dependencies.

### 7.2 Clone the Backend Repository

Clone the UbuntuHub API repository:

```bash
git clone https://github.com/boineelomkansie/UbuntuHubAPI.git
```

Open the backend solution using Visual Studio or another compatible .NET development environment.

### 7.3 Configure the Database

1. Install and configure PostgreSQL.
2. Create the required UbuntuHub database.
3. Configure the backend database connection string.
4. Ensure the database configuration is maintained securely and is not committed to GitHub.
5. Apply the required Entity Framework Core database migrations, if applicable.

### 7.4 Configure Firebase

Configure Firebase Authentication for the Android application.

Ensure that the appropriate Firebase configuration is available in the Android project.

Do not commit private credentials or sensitive configuration information to the public repository.

### 7.5 Configure the API Connection

The Android application must be configured to communicate with the running UbuntuHub API.

Ensure that the API base URL points to the correct development or deployment environment.

For local Android emulator development, the API must be accessible from the emulator.

A physical Android device requires an API address that the device can reach over the network.

### 7.6 Run the Application

1. Start the PostgreSQL database.
2. Configure and start the UbuntuHub API.
3. Open the Android project in Android Studio.
4. Connect an Android device or start an emulator.
5. Build and run the application.

**Note:** The application and backend require the correct local configuration before they can communicate successfully.

---

## 8. Automated Testing

UbuntuHub includes automated tests for both the Android application and backend API.

Automated testing helps identify errors and verify that important application operations behave as expected.

### 8.1 Android Unit Tests

The Android application currently contains six unit tests covering:

| Test | Description |
|---|---|
| Valid Email | Checks valid email input. |
| Invalid Email | Checks invalid email input. |
| Password Validation | Tests password validation. |
| Empty Post Validation | Checks validation for empty post content. |
| Valid Post Validation | Checks valid post input. |
| Username Fallback | Tests fallback username behaviour. |

**Result:** All six Android unit tests passed successfully.

### 8.2 API Unit Tests

The backend contains five automated tests covering:

| Test | Description |
|---|---|
| Retrieve Posts | Tests retrieving community posts. |
| Create Valid Post | Tests successful post creation. |
| Empty Description | Tests rejection of an empty post description. |
| Invalid User | Tests rejection of an invalid user. |
| Empty Category | Tests rejection of an empty category. |

**Result:** All five API tests passed successfully.

---

## 9. GitHub Actions and Continuous Integration

UbuntuHub uses GitHub Actions to automate the building and testing of the Android application and backend API.

This helps ensure that changes pushed to the repositories can be checked automatically.

### 9.1 Android CI Workflow

The Android workflow performs the following operations:

1. Checks out the repository.
2. Configures Java 17.
3. Sets up Gradle.
4. Executes Android unit tests.
5. Builds the debug APK.

Workflow file:

`.github/workflows/android.yml`

### 9.2 Backend API CI Workflow

The backend workflow performs the following operations:

1. Checks out the repository.
2. Configures the .NET 8 environment.
3. Restores project dependencies.
4. Builds the API solution.
5. Executes automated tests.

Workflow file:

`.github/workflows/dotnet.yml`

Both CI workflows have been successfully executed.

---

## 10. Security Considerations

Security is an important consideration in UbuntuHub.

The application incorporates the following security measures:

- Firebase Authentication manages user authentication.
- Passwords are not stored directly in the UbuntuHub PostgreSQL database.
- Database connection credentials are excluded from the Git repository.
- Sensitive configuration files are not committed to the public repository.
- Input validation helps prevent invalid information from being submitted.
- Backend validation provides additional protection when processing API requests.

Sensitive configuration must be supplied through the appropriate local or deployment environment.

---

## 11. User Interface and Design Considerations

UbuntuHub uses an Ubuntu-inspired visual identity based on green, orange and cream colours.

The interface is designed to provide a simple and accessible experience for community members.

The main design considerations include:

- Clear navigation between application screens.
- Readable text and consistent typography.
- Simple and understandable layouts.
- Consistent interface components.
- Appropriate input validation.
- Clear error feedback.
- Mobile-friendly screen layouts.
- Consistent application branding.

The application prioritises usability and accessibility to make community participation straightforward.

---

## 12. Project Structure

The Android project follows a standard Android application structure.

```text
UbuntuHub/
│
├── app/
│   └── src/
│       ├── main/
│       │   └── java/com/ubuntuhub/app/
│       ├── test/
│       └── androidTest/
│
├── .github/
│   └── workflows/
│       └── android.yml
│
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
```

The backend API is maintained in a separate GitHub repository:

`UbuntuHubAPI`

---

## 13. Part 3 Development and Improvements

Part 3 focuses on extending the existing UbuntuHub prototype and preparing the application for its final submission.

The assessment includes additional development requirements relating to cloud storage, NoSQL databases, application reliability and publication preparation.

This section should be updated as each additional feature is implemented and tested.

Document the following where applicable:

- New application functionality.
- NoSQL database integration.
- Blob storage implementation.
- Offline functionality and data synchronisation.
- Push notification functionality.
- Multilingual support.
- Application performance and usability improvements.
- Updated application branding and image assets.
- Preparation for Google Play publication.

Only completed and tested features should be described as fully implemented.

---

## 14. Version Control and Project Repositories

UbuntuHub uses Git and GitHub for source code management and collaboration.

The project is maintained in two repositories:

**Android Application**

https://github.com/boineelomkansie/UbuntuHub

**Backend API**

https://github.com/boineelomkansie/UbuntuHubAPI

The repositories maintain the source code, project configuration and automated workflows.

Changes should be committed regularly to maintain a clear development history.

---

## 15. Future Improvements

Potential improvements to UbuntuHub include:

- Expanded community messaging functionality.
- Improved community interaction.
- Additional accessibility features.
- Further application security improvements.
- Additional community engagement functionality.
- Continued performance and usability improvements.

These improvements can be considered as the application develops beyond its current implementation.

---

## 16. Project Status

UbuntuHub is being developed as part of the OPSC6312 project.

The application includes Firebase Authentication, a Kotlin-based Android interface, an ASP.NET Core REST API and PostgreSQL database integration.

Automated Android and API tests have been implemented, and GitHub Actions workflows have been configured to support continuous integration.

Further Part 3 development and final publication preparation are documented as implementation progresses.
