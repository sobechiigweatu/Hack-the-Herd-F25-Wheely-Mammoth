@echo off
echo ========================================
echo Starting Spring Boot with Java 17
echo ========================================
echo.

cd /d "%~dp0"

REM Check if Java 17 is available
where java >nul 2>&1
if %ERRORLEVEL% neq 0 (
    echo ERROR: Java not found in PATH
    echo.
    echo Please install Java 17:
    echo 1. Download from: https://adoptium.net/temurin/releases/
    echo 2. Install Java 17
    echo 3. Set JAVA_HOME environment variable
    echo 4. Add %JAVA_HOME%\bin to PATH
    echo.
    pause
    exit /b 1
)

echo Checking Java version...
java -version 2>&1 | findstr /i "version"
echo.

echo Starting Spring Boot application...
echo This may take a few minutes on first run...
echo.

gradlew.bat bootRun

if %ERRORLEVEL% equ 0 (
    echo.
    echo ========================================
    echo Application started successfully!
    echo Open: http://localhost:8080
    echo Press Ctrl+C to stop
    echo ========================================
) else (
    echo.
    echo ========================================
    echo Build failed. Check error messages above.
    echo ========================================
    pause
)

