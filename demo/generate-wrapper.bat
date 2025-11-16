@echo off
echo ========================================
echo Generating Gradle Wrapper
echo ========================================
echo.

cd /d "%~dp0"

echo Checking if Gradle is installed...
where gradle >nul 2>&1
if %ERRORLEVEL% equ 0 (
    echo ✅ Gradle found!
    echo.
    echo Generating wrapper...
    gradle wrapper --gradle-version 8.5
    echo.
    echo ✅ Wrapper generated!
    echo.
    echo You can now run: gradlew.bat bootRun
    pause
    exit /b 0
)

echo ❌ Gradle not found in PATH
echo.
echo Please install Gradle first:
echo 1. Download from: https://gradle.org/releases/
echo 2. Extract to C:\gradle
echo 3. Add C:\gradle\bin to PATH
echo 4. Or run: C:\gradle\bin\gradle.bat wrapper --gradle-version 8.5
echo.
echo Alternatively, download a working wrapper from:
echo https://start.spring.io/ (generate a project and copy gradle/wrapper folder)
echo.
pause

