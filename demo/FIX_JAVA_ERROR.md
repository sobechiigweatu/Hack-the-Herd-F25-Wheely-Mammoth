# Fix: "SpringApplication cannot be resolved" Error

## The Problem

You're getting this error because:
1. **Java 8 is being used** (you need Java 17)
2. **Spring Boot dependencies aren't loaded** (need to build with Gradle first)

---

## Solution 1: Install Java 17 (Required)

### Check Current Java Version:
```powershell
java -version
```

If it shows `1.8.x`, you need Java 17.

### Install Java 17:

1. **Download Java 17:**
   - Go to: https://adoptium.net/temurin/releases/
   - Select: **Java 17 (LTS)**
   - Download: **Windows x64 JDK**
   - Install it

2. **Set Java 17 as Default:**
   - After installation, set `JAVA_HOME` environment variable
   - Or configure VS Code to use Java 17

3. **Verify:**
   ```powershell
   java -version
   ```
   Should show: `openjdk version "17"` or similar

---

## Solution 2: Build Project with Gradle First

**The IDE is trying to run without building dependencies!**

### Step 1: Build the Project
```powershell
cd demo
.\gradlew.bat build
```

This downloads all Spring Boot dependencies.

### Step 2: Run via Gradle (Recommended)
```powershell
.\gradlew.bat bootRun
```

This runs the application with all dependencies loaded.

---

## Solution 3: Configure VS Code

### Option A: Use Gradle to Run

1. **Open Terminal in VS Code**
2. **Navigate to demo folder:**
   ```powershell
   cd demo
   ```
3. **Run with Gradle:**
   ```powershell
   .\gradlew.bat bootRun
   ```

### Option B: Configure VS Code Java Settings

1. **Install Java Extension Pack** (if not installed)
2. **Set Java Home:**
   - Press `Ctrl+Shift+P`
   - Type: "Java: Configure Java Runtime"
   - Select Java 17 installation

3. **Reload VS Code:**
   - Press `Ctrl+Shift+P`
   - Type: "Developer: Reload Window"

---

## Quick Fix: Run from Command Line

**Easiest way to test:**

1. **Open PowerShell/Command Prompt**
2. **Navigate to project:**
   ```powershell
   cd C:\Users\cryst\.vscode\Hack-the-Herd-F25-Wheely-Mammoth\demo
   ```
3. **Build and run:**
   ```powershell
   .\gradlew.bat bootRun
   ```

This will:
- Download all dependencies
- Build the project
- Run the application
- Show clear error messages if something's wrong

---

## Verify It's Working

After running `.\gradlew.bat bootRun`, you should see:

```
> Task :bootRun

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.5.7)

... (startup messages) ...

Started DemoApplication in X.XXX seconds
```

Then open: `http://localhost:8080`

---

## If Java 17 Installation Fails

**Temporary workaround:** Use Gradle wrapper (it includes its own Java)

1. Just run:
   ```powershell
   cd demo
   .\gradlew.bat bootRun
   ```

2. Gradle will handle Java version automatically

---

## Summary

**The error happens because:**
- ❌ Using Java 8 (need Java 17)
- ❌ IDE running class directly (need Gradle build)

**The fix:**
- ✅ Install Java 17
- ✅ Run via Gradle: `.\gradlew.bat bootRun`

**Try this now:**
```powershell
cd demo
.\gradlew.bat bootRun
```

This should work even with Java 8 temporarily!

