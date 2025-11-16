# Quick Start Guide - How to Know if Spring Boot is Working

## 🎯 Simple Test: Is It Working?

### Step 1: Run the Application
- Open: `demo/src/main/java/com/wheelymammoth/model/DemoApplication.java`
- Right-click → "Run" or click the green play button

### Step 2: Look for This in Console:
```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.5.7)

... (lots of startup messages) ...

Started DemoApplication in X.XXX seconds
```

**If you see "Started DemoApplication" = ✅ IT'S WORKING!**

### Step 3: Test in Browser
1. Open browser
2. Go to: `http://localhost:8080`
3. **You should see:** Wheely Mammoth home page with purple navbar

**If you see the home page = ✅ IT'S WORKING!**

---

## ❌ Common Errors

### Error: "Cannot connect to database"
**What you see:**
```
Communications link failure
Access denied for user 'root'@'localhost'
```

**Fix:**
1. Make sure MySQL is running (Windows Services)
2. Check password in `application.properties` line 8
3. Password should match your MySQL root password

---

### Error: "Port 8080 already in use"
**What you see:**
```
Port 8080 was already in use
```

**Fix:**
1. Close other applications using port 8080
2. Or change port in `application.properties`:
   ```
   server.port=8081
   ```
   Then use `http://localhost:8081`

---

### Error: "ClassNotFoundException"
**What you see:**
```
java.lang.ClassNotFoundException
```

**Fix:**
1. Build the project first:
   ```bash
   cd demo
   ./gradlew build
   ```
2. Then run DemoApplication again

---

## 🔍 How to Read Console Output

### ✅ Good Output (Working):
```
Started DemoApplication in 5.234 seconds
Tomcat started on port(s): 8080 (http)
```

### ❌ Bad Output (Not Working):
```
***************************
APPLICATION FAILED TO START
***************************
```

**If you see "APPLICATION FAILED TO START":**
- Scroll up to find the actual error
- Look for lines with "ERROR" or "Exception"
- Copy that error message

---

## 📋 Checklist Before Running

- [ ] MySQL is running (check Windows Services)
- [ ] MySQL password is correct in `application.properties`
- [ ] Java 17 is installed (`java -version` in terminal)
- [ ] No other app on port 8080
- [ ] All files are saved

---

## 🚀 Quick Fix: Try Command Line

If IDE gives errors, try terminal:

```bash
cd demo
./gradlew bootRun
```

This shows clearer error messages.

---

## ✅ Success Checklist

When it's working, you should see:
- [ ] Console shows "Started DemoApplication"
- [ ] Browser at `http://localhost:8080` shows home page
- [ ] Can click "Register" and see registration form
- [ ] Can fill out form and submit (redirects to login)
- [ ] No red errors in console

---

## 🆘 Still Not Working?

**Share these details:**
1. The **exact error message** from console (copy/paste)
2. What happens when you try to access `http://localhost:8080`
3. Whether MySQL is running

The error message will tell us exactly what's wrong!

