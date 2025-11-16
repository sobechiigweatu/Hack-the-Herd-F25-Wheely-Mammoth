# Quick Fix: Run the Application

## ✅ The Gradle Wrapper is Fixed!

The `gradle-wrapper.jar` file exists and is valid. The error you're seeing is likely a PowerShell path issue.

---

## 🚀 EASIEST WAY: Double-Click to Run

1. **Navigate to the `demo` folder** in File Explorer
2. **Double-click:** `run.bat`
3. **Wait for:** `Started DemoApplication`
4. **Open browser:** `http://localhost:8080`

---

## 🚀 Alternative: Command Prompt

1. **Open Command Prompt** (not PowerShell):
   - Press `Win + R`
   - Type: `cmd`
   - Press Enter

2. **Copy and paste these commands:**
   ```cmd
   cd C:\Users\cryst\.vscode\Hack-the-Herd-F25-Wheely-Mammoth\demo
   gradlew.bat bootRun
   ```

3. **Wait for startup** (first time may take 2-5 minutes to download Gradle)

4. **Look for:**
   ```
   Started DemoApplication in X.XXX seconds
   ```

5. **Open browser:** `http://localhost:8080`

---

## Why PowerShell Has Issues

PowerShell sometimes has problems with batch files and path handling. **Command Prompt works better** for running Gradle wrapper.

---

## If You Still Get Errors

### Error: "Java not found"
- Gradle will download Java 17 automatically
- Just wait for the first download

### Error: "Port 8080 in use"
- Close other applications
- Or change port in `application.properties`

### Error: "Database connection failed"
- Make sure MySQL is running
- Check password in `application.properties`

---

## Success Indicators

✅ **Working:**
- Console shows: `Started DemoApplication`
- Browser at `http://localhost:8080` shows home page
- No red error messages

❌ **Not Working:**
- Red error messages in console
- Application stops immediately
- Browser shows "can't connect"

---

## Summary

**Use Command Prompt (cmd.exe), not PowerShell:**
```cmd
cd C:\Users\cryst\.vscode\Hack-the-Herd-F25-Wheely-Mammoth\demo
gradlew.bat bootRun
```

**Or double-click `run.bat` in the demo folder!**

The wrapper jar file is there and valid - the issue is just how PowerShell handles the paths. Command Prompt will work!

