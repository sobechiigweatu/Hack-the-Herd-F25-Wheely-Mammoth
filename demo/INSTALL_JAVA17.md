# Install Java 17 - Required for Spring Boot 3.5.7

## The Problem

Your build is failing because:
- **Current Java:** Java 8
- **Required Java:** Java 17 (for Spring Boot 3.5.7)

---

## ✅ Solution 1: Install Java 17 (Recommended)

### Step 1: Download Java 17

1. **Go to:** https://adoptium.net/temurin/releases/
2. **Select:**
   - **Version:** 17 (LTS)
   - **Operating System:** Windows
   - **Architecture:** x64
   - **Package Type:** JDK
3. **Click "Latest Release"** and download the installer

### Step 2: Install Java 17

1. **Run the installer**
2. **Follow the installation wizard**
3. **Note the installation path** (usually `C:\Program Files\Eclipse Adoptium\jdk-17.x.x-hotspot`)

### Step 3: Set JAVA_HOME

1. **Open System Properties:**
   - Press `Win + Pause/Break`
   - Or: Right-click "This PC" → Properties → Advanced system settings

2. **Click "Environment Variables"**

3. **Under "System variables":**
   - Click "New"
   - Variable name: `JAVA_HOME`
   - Variable value: `C:\Program Files\Eclipse Adoptium\jdk-17.x.x-hotspot`
     (Use your actual installation path)

4. **Update PATH:**
   - Find "Path" in System variables
   - Click "Edit"
   - Add: `%JAVA_HOME%\bin`
   - Click OK on all dialogs

5. **Restart Command Prompt/Terminal**

### Step 4: Verify Installation

Open **new** Command Prompt:
```cmd
java -version
```

Should show: `openjdk version "17"` or similar

### Step 5: Run Your Application

```cmd
cd C:\Users\cryst\.vscode\Hack-the-Herd-F25-Wheely-Mammoth\demo
gradlew.bat bootRun
```

---

## ✅ Solution 2: Let Gradle Auto-Download Java 17

Gradle can automatically download Java 17 if configured. I've updated your `build.gradle` to enable this.

**Try running again:**
```cmd
gradlew.bat bootRun
```

Gradle should automatically download Java 17 on first run (may take a few minutes).

---

## ✅ Solution 3: Use Java 17 Portable (No Installation)

1. **Download:** https://adoptium.net/temurin/releases/
2. **Select:** JDK 17, Windows x64, **ZIP** (not installer)
3. **Extract** to: `C:\java17` (or any folder)

4. **Set JAVA_HOME temporarily:**
   ```cmd
   set JAVA_HOME=C:\java17
   set PATH=%JAVA_HOME%\bin;%PATH%
   ```

5. **Run:**
   ```cmd
   gradlew.bat bootRun
   ```

---

## Quick Test

After installing Java 17:

```cmd
java -version
```

Should show version 17, not 8.

Then:
```cmd
cd C:\Users\cryst\.vscode\Hack-the-Herd-F25-Wheely-Mammoth\demo
gradlew.bat bootRun
```

Should work! ✅

---

## Why Java 17?

- Spring Boot 3.x requires Java 17+
- Java 8 is too old (released 2014)
- Java 17 is LTS (Long Term Support) - recommended for production

---

## Recommended: Solution 1 (Full Installation)

This is the best long-term solution. Once installed, all your Java projects will use Java 17.

