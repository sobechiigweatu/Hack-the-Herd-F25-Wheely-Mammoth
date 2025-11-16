# Fix: "Unable to access jarfile gradle-wrapper.jar" Error

## The Problem

The Gradle wrapper files are missing. The `gradle-wrapper.jar` file needs to be downloaded.

---

## Solution 1: Install Gradle and Generate Wrapper (Easiest)

### Step 1: Install Gradle

1. **Download Gradle:**
   - Go to: https://gradle.org/releases/
   - Download: **Gradle 8.5** (Binary-only)
   - Extract to: `C:\gradle` (or any folder)

2. **Add to PATH:**
   - Add `C:\gradle\bin` to your system PATH
   - Or use full path: `C:\gradle\bin\gradle.bat`

### Step 2: Generate Wrapper

```powershell
cd demo
gradle wrapper
```

This will create:
- `gradle/wrapper/gradle-wrapper.jar`
- `gradle/wrapper/gradle-wrapper.properties` (already created)

### Step 3: Run Application

```powershell
.\gradlew.bat bootRun
```

---

## Solution 2: Download Wrapper Jar Manually

### Step 1: Download gradle-wrapper.jar

1. **Go to:** https://raw.githubusercontent.com/gradle/gradle/v8.5.0/gradle/wrapper/gradle-wrapper.jar
2. **Save as:** `demo\gradle\wrapper\gradle-wrapper.jar`

### Step 2: Run Application

```powershell
cd demo
.\gradlew.bat bootRun
```

---

## Solution 3: Use Spring Boot CLI (Alternative)

If you have Spring Boot CLI installed:

```powershell
spring init --dependencies=web,data-jpa,thymeleaf --build=gradle demo
```

Then copy your source files over.

---

## Solution 4: Quick Fix - Download via PowerShell

Run this in PowerShell (from the `demo` folder):

```powershell
$wrapperDir = "gradle\wrapper"
New-Item -ItemType Directory -Force -Path $wrapperDir | Out-Null
Invoke-WebRequest -Uri "https://raw.githubusercontent.com/gradle/gradle/v8.5.0/gradle/wrapper/gradle-wrapper.jar" -OutFile "$wrapperDir\gradle-wrapper.jar"
```

Then run:
```powershell
.\gradlew.bat bootRun
```

---

## Verify Files Exist

After fixing, you should have:
- ✅ `demo\gradle\wrapper\gradle-wrapper.jar` (exists)
- ✅ `demo\gradle\wrapper\gradle-wrapper.properties` (exists)
- ✅ `demo\gradlew.bat` (exists)
- ✅ `demo\gradlew` (exists)

---

## Recommended: Use Solution 4 (PowerShell Download)

This is the fastest way. Just run:

```powershell
cd demo
$wrapperDir = "gradle\wrapper"
New-Item -ItemType Directory -Force -Path $wrapperDir | Out-Null
Invoke-WebRequest -Uri "https://raw.githubusercontent.com/gradle/gradle/v8.5.0/gradle/wrapper/gradle-wrapper.jar" -OutFile "$wrapperDir\gradle-wrapper.jar"
.\gradlew.bat bootRun
```

This will download the wrapper jar and start the application!

