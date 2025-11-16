# Troubleshooting Spring Boot Errors

## How to Know if Spring Boot is Working

### ✅ Success Signs:
1. **Console shows:**
   ```
   Started DemoApplication in X.XXX seconds (process running for X.XX)
   ```
2. **No red error messages** in the console
3. **Browser at `http://localhost:8080` shows the home page**
4. **Registration form submits successfully**

### ❌ Error Signs:
- Red error messages in console
- Application stops/crashes
- "Failed to start" messages
- Browser shows "This site can't be reached"

---

## Common Errors and Fixes

### Error 1: "Cannot connect to database"

**Error message:**
```
Communications link failure
Access denied for user 'root'@'localhost'
```

**Fix:**
1. Check MySQL is running (Windows Services)
2. Verify password in `application.properties` matches your MySQL password
3. Test MySQL connection:
   ```bash
   mysql -u root -p
   ```
   Enter your password

---

### Error 2: "Port 8080 already in use"

**Error message:**
```
Web server failed to start. Port 8080 was already in use.
```

**Fix:**
1. **Option A:** Stop the other application using port 8080
2. **Option B:** Change port in `application.properties`:
   ```
   server.port=8081
   ```
   Then access via `http://localhost:8081`

---

### Error 3: "ClassNotFoundException" or "Package not found"

**Error message:**
```
java.lang.ClassNotFoundException: com.mysql.cj.jdbc.Driver
```

**Fix:**
1. **Build the project first:**
   ```bash
   cd demo
   ./gradlew build
   ```
   (Windows: `gradlew.bat build`)

2. **Refresh Gradle dependencies in IDE:**
   - VS Code: Command Palette → "Java: Clean Java Language Server Workspace"
   - IntelliJ: Right-click `build.gradle` → "Reload Gradle Project"

---

### Error 4: "Bean creation failed" or "Circular dependency"

**Error message:**
```
Error creating bean with name...
```

**Fix:**
- Usually means a configuration issue
- Check console for the specific bean name
- Make sure all `@Service`, `@Controller`, `@Repository` classes are in the right package
- All classes should be under `com.wheelymammoth.*`

---

### Error 5: "Hibernate/JPA errors"

**Error message:**
```
Table 'wheelymammoth.users' doesn't exist
```

**Fix:**
1. The database should be created automatically
2. If not, manually create it:
   ```sql
   CREATE DATABASE wheelymammoth;
   ```
3. Make sure `spring.jpa.hibernate.ddl-auto=update` is in `application.properties`

---

## Step-by-Step Debugging

### Step 1: Check MySQL Connection
```bash
mysql -u root -p
```
Enter your password. If this works, MySQL is running.

### Step 2: Verify Application Properties
Open `demo/src/main/resources/application.properties`:
- ✅ Password matches your MySQL password
- ✅ Username is correct (usually `root`)
- ✅ Port is 3306 (or your MySQL port)

### Step 3: Build the Project
```bash
cd demo
./gradlew clean build
```
This downloads all dependencies and compiles the code.

### Step 4: Run with Detailed Logging
Add to `application.properties`:
```
logging.level.root=INFO
logging.level.com.wheelymammoth=DEBUG
```
This shows more detailed error messages.

### Step 5: Check Console Output
Look for:
- ✅ "Started DemoApplication" = SUCCESS
- ❌ Red error messages = FAILURE
- Copy the full error message to diagnose

---

## Quick Test: Is It Working?

1. **Run DemoApplication.java**
2. **Wait 10-30 seconds** for startup
3. **Look for:** `Started DemoApplication` in console
4. **Open browser:** `http://localhost:8080`
5. **You should see:** Wheely Mammoth home page

If you see the home page, **it's working!** ✅

---

## Still Having Issues?

**Share the error message:**
1. Copy the **full error** from the console
2. Look for lines starting with:
   - `ERROR`
   - `Exception`
   - `Failed`
   - `Cannot`

**Common things to check:**
- [ ] MySQL is running
- [ ] MySQL password is correct
- [ ] Java 17 is installed (`java -version`)
- [ ] Project is built (`./gradlew build`)
- [ ] No other app on port 8080
- [ ] All files are saved

---

## Alternative: Run from Command Line

If IDE is giving errors, try command line:

```bash
cd demo
./gradlew bootRun
```

This will show clearer error messages.

