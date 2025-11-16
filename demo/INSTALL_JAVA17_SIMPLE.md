# Install Java 17 - Simple Steps

## Current Problem
- Your system has: **Java 8** (`java version "1.8.0_371"`)
- Spring Boot needs: **Java 17**

---

## ✅ Quick Installation (5 minutes)

### Step 1: Download Java 17

**Direct download link:**
- **Windows x64 Installer:** https://adoptium.net/temurin/releases/?version=17

Or:
1. Go to: https://adoptium.net/temurin/releases/
2. Find **"17 (LTS)"** section
3. Click **Windows** → **x64** → **JDK**
4. Download the **.msi** installer (about 200 MB)

### Step 2: Install

1. **Run the downloaded .msi file**
2. Click **"Next"** through all steps
3. **Important:** Make sure **"Set JAVA_HOME variable"** is checked
4. Click **"Install"**
5. Wait for installation to complete
6. Click **"Finish"**

### Step 3: Verify Installation

**IMPORTANT:** Close your current Command Prompt and open a **NEW** one.

Then run:
```cmd
java -version
```

**Expected output:**
```
openjdk version "17.0.x"
OpenJDK Runtime Environment Temurin-17.0.x+11
OpenJDK 64-Bit Server VM Temurin-17.0.x+11
```

If you still see Java 8:
- Make sure you opened a **NEW** Command Prompt
- Check if JAVA_HOME is set: `echo %JAVA_HOME%`
- If not set, see "Manual PATH Setup" below

### Step 4: Run Your Application

```cmd
cd C:\Users\cryst\.vscode\Hack-the-Herd-F25-Wheely-Mammoth\demo
gradlew.bat bootRun
```

Should work now! ✅

---

## 🔧 Manual PATH Setup (if needed)

If `java -version` still shows Java 8 after installation:

### Find Java 17 Location

Usually installed at:
- `C:\Program Files\Eclipse Adoptium\jdk-17.x.x-hotspot`

### Set JAVA_HOME

1. Press `Win + Pause` (or Right-click "This PC" → Properties)
2. Click **"Advanced system settings"**
3. Click **"Environment Variables"**
4. Under **"System variables"**, click **"New"**:
   - Variable name: `JAVA_HOME`
   - Variable value: `C:\Program Files\Eclipse Adoptium\jdk-17.0.13+11-hotspot`
     (Use your actual path - check the folder name)
5. Click **OK**

### Update PATH

1. In **"System variables"**, find **"Path"**
2. Click **"Edit"**
3. Click **"New"**
4. Add: `%JAVA_HOME%\bin`
5. Click **"OK"** on all dialogs
6. **Close and reopen Command Prompt**

### Verify Again

```cmd
java -version
```

Should now show Java 17!

---

## 🚀 Alternative: Use Project Script

I've created `set-java17.bat` that can help find and set Java 17.

Run:
```cmd
cd C:\Users\cryst\.vscode\Hack-the-Herd-F25-Wheely-Mammoth\demo
set-java17.bat
```

This will:
- Search for Java 17 on your system
- Set it for the current terminal session
- Let you run `gradlew.bat bootRun`

---

## ❓ Troubleshooting

### "Java 17 not found"
- Make sure you installed it
- Check: `C:\Program Files\Eclipse Adoptium\`
- Run `set-java17.bat` to search

### "Still shows Java 8"
- Close and reopen Command Prompt
- Check PATH: `echo %PATH%` (should include Java 17)
- Check JAVA_HOME: `echo %JAVA_HOME%`

### "Gradle still fails"
- Make sure you're in the `demo` folder
- Try: `gradlew.bat --stop` then `gradlew.bat bootRun`
- Check: `gradlew.bat --version` (should use Java 17)

---

## 📝 Summary

1. **Download:** https://adoptium.net/temurin/releases/?version=17
2. **Install:** Run the .msi, check "Set JAVA_HOME"
3. **Verify:** Open new CMD, run `java -version`
4. **Run:** `gradlew.bat bootRun`

That's it! 🎉

