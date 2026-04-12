# 🚀 Poppy Admin Automation Framework

A robust, scalable end-to-end Selenium automation framework built using **Java + TestNG + Page Object Model (POM)** to validate core functionalities of the **Poppy Admin Media Management System**.

---

## 🧠 Project Objective

To design a **production-ready automation framework** that validates real-time admin workflows including:

* Media creation (Poster & Video)
* User profile management
* UI validations
* File download verification
* Data integrity checks

---

## 🛠️ Tech Stack

| Tool               | Usage                 |
| ------------------ | --------------------- |
| Java               | Core programming      |
| Selenium WebDriver | UI Automation         |
| TestNG             | Test execution        |
| Maven              | Dependency management |
| POM                | Design pattern        |
| Log4j              | Logging               |
| WebDriverManager   | Driver handling       |

---

## 📂 Project Structure

```
src/main/java
│
├── base
├── pages
├── utils
│
src/test/java
├── tests
├── runners
│
src/test/resources
├── config.properties
├── files (test data + downloads)
```

---

# 🔐 FLOW 1: LOGIN FLOW

### ✅ Steps:

* Launch application
* Enter email & password
* Click login
* Handle browser alert (if present)
* Verify dashboard loaded
* Fetch total media count

### ✅ Validation:

* Dashboard header visible
* Media count displayed

---

# 👤 FLOW 2: USER PROFILE MANAGEMENT

## ✅ Create User:

* Navigate to **User Profiles**
* Click **New Profile**
* Enter:

  * Name
  * Email
  * Password
  * Mobile Number
* Click **Add New**

### ✅ Validation:

* New user appears in table

---

## ✏️ Edit User:

* Locate created user
* Click **Edit icon**
* Verify existing values
* Update name → *Tester Edit*
* Click **Update**

### ✅ Validation:

* Updated name reflected in table

---

# 🔑 FLOW 3: USER LOGIN WITH CREATED PROFILE

### ✅ Steps:

* Fetch email & password from table
* Logout admin
* Login using fetched credentials

### ✅ Validation:

* Dashboard loads successfully

---

# 🖼️ FLOW 4: POSTER CREATION & VALIDATION

### ✅ Steps:

* Navigate → **Media → Create Poster**
* Upload image
* Enter required details
* Click **Save**

### ✅ Validation:

* Poster appears in grid

---

## 📥 Poster Download:

* Open latest poster
* Click download
* Save file locally

### ✅ Validation:

* File downloaded successfully

---

# 🎬 FLOW 5: VIDEO CREATION & VALIDATION

### ✅ Steps:

* Navigate → **Media → Create Video**
* Upload:

  * Thumbnail
  * Video file
* Select ratio
* Click **Save**
* Wait for processing

### ✅ Validation:

* Video appears in grid

---

## 🎥 Video Details Validation:

* Open latest video
* Update:

  * Intro position → Start
  * Company name → *Ananth Company*
* Verify:

  * Address
  * Email

---

## 📥 Video Download:

* Click Download
* Wait for processing
* Validate file saved locally

### ✅ Validation:

* File downloaded (.mp4)
* File size verified

---

# 🔄 FLOW 6: MEDIA VISIBILITY TOGGLE

### 🎯 Objective:

Ensure toggling OFF media hides it from listing pages

### ✅ Steps:

* Navigate to Media page
* Get media type & title
* Toggle OFF

### 🔍 Validation Logic:

* If Video → Not visible in Videos page
* If Poster → Not visible in Posters page

### ✅ Result:

✔ Hidden → PASS
❌ Visible → FAIL

---

# 📁 DOWNLOAD HANDLING

Downloads saved to:

```
src/test/resources/files/
```

Supports:

* Images
* .mp4 files

### ✅ Validations:

* File presence
* File type
* Download completion

---

# 🧩 KEY FRAMEWORK FEATURES

* ✅ Page Object Model (POM)
* ✅ Reusable utilities
* ✅ Config-driven approach
* ✅ Dynamic data handling
* ✅ File upload & download automation
* ✅ Logging with Log4j
* ✅ JS fallback for click issues

---

# ⚠️ REAL-TIME CHALLENGES HANDLED

* ✔ Element click interception
* ✔ Dynamic loaders
* ✔ Modal overlays
* ✔ Delayed rendering
* ✔ File upload restrictions
* ✔ Download verification

---

# ▶️ HOW TO RUN

```bash
# Clone repository
git clone <your-repo-url>

# Install dependencies
mvn clean install

# Run tests
mvn test
```

---

# 🎥 AUTOMATION DEMO VIDEOS

🚀 End-to-End Automation Demonstrations:

### 👤 User Profile Automation

▶️ https://your-video-link-1

### 🖼️ Poster Creation & Validation

▶️ https://your-video-link-2

### 🎬 Video Creation & Validation

▶️ https://your-video-link-3

---

# 💡 DEMO COVERAGE

✔ Login Automation
✔ User Profile CRUD
✔ Poster Upload & Download
✔ Video Processing & Validation
✔ Media Visibility Toggle
✔ File Handling

---

# 📌 AUTHOR

👨‍💻 Ananth A
QA Tester

---
