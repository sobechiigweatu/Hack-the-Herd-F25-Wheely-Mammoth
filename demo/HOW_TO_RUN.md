# How to Run the Wheely Mammoth Application

## ⚠️ IMPORTANT: You MUST Run Spring Boot, Not Open HTML Files Directly!

**The registration form won't work if you just open the HTML file in a browser!** You need to run the Spring Boot application first.

---

## Step 1: Start MySQL

Make sure MySQL is running:
- Check Windows Services for "MySQL" or "MySQL80"
- Or start it from XAMPP/WAMP if you installed it that way

---

## Step 2: Run the Spring Boot Application

### Option A: Run from IDE (Recommended)

1. **Open the project in your IDE** (VS Code, IntelliJ, Eclipse, etc.)

2. **Find the main application file:**
   - Navigate to: `demo/src/main/java/com/wheelymammoth/model/DemoApplication.java`

3. **Right-click on `DemoApplication.java`**
   - Select "Run" or "Run 'DemoApplication'"
   - Or click the green play button next to the `main` method

4. **Wait for the application to start**
   - Look for: "Started DemoApplication" in the console
   - The app will run on: `http://localhost:8080`

### Option B: Run from Command Line

1. **Open Terminal/Command Prompt** in the project root

2. **Navigate to demo folder:**
   ```bash
   cd demo
   ```

3. **Run the application:**
   ```bash
   ./gradlew bootRun
   ```
   (On Windows, you might need: `gradlew.bat bootRun`)

4. **Wait for:**
   ```
   Started DemoApplication in X.XXX seconds
   ```

---

## Step 3: Access the Application

1. **Open your web browser**
2. **Go to:** `http://localhost:8080`
3. **You should see the Wheely Mammoth home page**

---

## Step 4: Test Registration

1. **Click "Register" or go to:** `http://localhost:8080/register`
2. **Fill out the form:**
   - Student ID
   - Password
   - Full Name
   - Email
   - Phone Number
3. **Click "Create Account"**
4. **You should be redirected to the login page** with a success message

---

## Troubleshooting

### "Registration form just clears fields"
- **Problem:** You're opening the HTML file directly (file:// protocol)
- **Solution:** Make sure Spring Boot is running and access via `http://localhost:8080`

### "Cannot connect to database"
- **Problem:** MySQL is not running or wrong password
- **Solution:** 
  - Check MySQL is running in Services
  - Verify password in `application.properties` matches your MySQL password

### "Port 8080 already in use"
- **Problem:** Another application is using port 8080
- **Solution:** 
  - Stop the other application
  - Or change port in `application.properties`:
    ```
    server.port=8081
    ```

### "Application won't start"
- **Check console for errors:**
  - Database connection errors → Check MySQL password
  - Port errors → Change port number
  - Missing dependencies → Run `./gradlew build` first

---

## Quick Checklist

- [ ] MySQL is running
- [ ] MySQL password is correct in `application.properties`
- [ ] Spring Boot application is running (see "Started DemoApplication" in console)
- [ ] Access via `http://localhost:8080` (NOT file://)
- [ ] Browser shows the home page correctly

---

## What You Should See

When the app is running correctly:
- Console shows: `Started DemoApplication`
- Browser at `http://localhost:8080` shows the Wheely Mammoth home page
- Registration form submits and redirects to login page
- Login works and redirects based on user type

---

## Need Help?

If registration still doesn't work:
1. Check the browser console (F12) for JavaScript errors
2. Check the Spring Boot console for server errors
3. Make sure you're accessing via `http://localhost:8080/register` (not file://)

