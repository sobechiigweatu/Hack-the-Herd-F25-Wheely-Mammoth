# How to Run the Application - Step by Step

## ✅ Gradle Wrapper is Now Fixed!

The `gradle-wrapper.jar` file has been downloaded. You can now run the application.

---

## Method 1: Command Prompt (Recommended)

1. **Open Command Prompt:**
   - Press `Win + R`
   - Type: `cmd`
   - Press Enter

2. **Navigate to demo folder:**
   ```cmd
   cd C:\Users\cryst\.vscode\Hack-the-Herd-F25-Wheely-Mammoth\demo
   ```

3. **Run the application:**
   ```cmd
   gradlew.bat bootRun
   ```

4. **Wait for startup:**
   - First time: Will download Gradle and dependencies (may take 2-5 minutes)
   - Look for: `Started DemoApplication in X.XXX seconds`

5. **Open browser:**
   - Go to: `http://localhost:8080`

---

## Method 2: VS Code Terminal

1. **Open Terminal in VS Code:**
   - Press `` Ctrl+` `` (backtick)
   - Or: View → Terminal

2. **Run these commands:**
   ```powershell
   cd demo
   cmd /c gradlew.bat bootRun
   ```

---

## Method 3: PowerShell (Direct)

```powershell
Set-Location "C:\Users\cryst\.vscode\Hack-the-Herd-F25-Wheely-Mammoth\demo"
.\gradlew.bat bootRun
```

(If PowerShell blocks it, use `cmd /c gradlew.bat bootRun`)

---

## What You Should See

### First Time Running:
```
> Task :wrapper
Downloading https://services.gradle.org/distributions/gradle-8.5-bin.zip
... (downloading) ...
BUILD SUCCESSFUL

> Task :bootRun

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.5.7)

... (startup messages) ...

Started DemoApplication in 5.234 seconds
```

### Subsequent Runs:
```
> Task :bootRun

Started DemoApplication in 2.123 seconds
```

---

## Troubleshooting

### "Still can't find gradle-wrapper.jar"
- **Check file exists:**
  ```powershell
  Test-Path "C:\Users\cryst\.vscode\Hack-the-Herd-F25-Wheely-Mammoth\demo\gradle\wrapper\gradle-wrapper.jar"
  ```
  Should return: `True`

### "Java version error"
- The project needs Java 17, but Gradle will download it automatically
- First run may take longer while downloading

### "Port 8080 already in use"
- Close other applications
- Or change port in `application.properties`:
  ```
  server.port=8081
  ```

### "Database connection error"
- Make sure MySQL is running
- Check password in `application.properties`

---

## Quick Test

1. ✅ Run: `gradlew.bat bootRun`
2. ✅ Wait for: `Started DemoApplication`
3. ✅ Open: `http://localhost:8080`
4. ✅ See home page = **SUCCESS!**

---

## Stop the Application

Press `Ctrl + C` in the terminal to stop the application.

---

## Files Verified

- ✅ `gradle-wrapper.jar` - Downloaded (43,462 bytes)
- ✅ `gradle-wrapper.properties` - Created
- ✅ `gradlew.bat` - Exists
- ✅ All ready to run!

**Try running `gradlew.bat bootRun` now!**

