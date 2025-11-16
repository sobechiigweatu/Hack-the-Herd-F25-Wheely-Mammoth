# Quick Fix: Switch to Java 17

## Current Status
- ❌ Java 8 is active: `java version "1.8.0_371"`
- ✅ Need: Java 17

---

## ✅ Solution 1: Install Java 17 (5 minutes)

### Step 1: Download
1. Go to: **https://adoptium.net/temurin/releases/**
2. Click: **17 (LTS)** → **Windows** → **x64** → **JDK**
3. Download the **.msi installer**

### Step 2: Install
1. Run the downloaded installer
2. Click "Next" through all steps
3. **Important:** Check "Set JAVA_HOME variable" during installation
4. Finish installation

### Step 3: Verify
Open **NEW** Command Prompt (important - close old one):
```cmd
java -version
```

Should show: `openjdk version "17"` or `openjdk 17`

### Step 4: Run Your App
```cmd
cd C:\Users\cryst\.vscode\Hack-the-Herd-F25-Wheely-Mammoth\demo
gradlew.bat bootRun
```

---

## ✅ Solution 2: Use Java 17 for This Project Only

If you install Java 17 but it's not in PATH, you can set it just for this project:

### Step 1: Install Java 17
(Follow Solution 1, Step 1-2)

### Step 2: Find Java 17 Location
Usually: `C:\Program Files\Eclipse Adoptium\jdk-17.x.x-hotspot\bin\java.exe`

### Step 3: Create a Batch File

Create `demo/run-java17.bat`:
```batch
@echo off
set JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-17.0.13+11-hotspot
set PATH=%JAVA_HOME%\bin;%PATH%
cd /d "%~dp0"
gradlew.bat bootRun
```

(Update the path to match your Java 17 installation)

### Step 4: Run
Double-click `run-java17.bat`

---

## ✅ Solution 3: Let Gradle Download Java 17

Gradle can auto-download Java 17. Try running:

```cmd
cd C:\Users\cryst\.vscode\Hack-the-Herd-F25-Wheely-Mammoth\demo
gradlew.bat bootRun
```

If Gradle can bootstrap with Java 8, it will download Java 17 automatically (first run takes longer).

---

## Check if Java 17 is Already Installed

Run this in PowerShell:
```powershell
Get-ChildItem "C:\Program Files" -Recurse -Filter "java.exe" -ErrorAction SilentlyContinue | Where-Object { $_.DirectoryName -like '*17*' -or $_.DirectoryName -like '*jdk-17*' } | Select-Object FullName
```

If it finds Java 17, we just need to update PATH!

---

## Recommended: Solution 1 (Full Installation)

This is the cleanest solution. Once Java 17 is installed and in PATH, everything will work.

**After installing, make sure to:**
- ✅ Close and reopen Command Prompt
- ✅ Run `java -version` to verify
- ✅ Then run `gradlew.bat bootRun`

---

## Why Java 17?

- Spring Boot 3.x requires Java 17+
- Java 8 is from 2014 (very old)
- Java 17 is LTS (supported until 2029)

