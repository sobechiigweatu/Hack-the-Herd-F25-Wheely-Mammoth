# PowerShell script to find and set Java 17
Write-Host "========================================"
Write-Host "Java 17 Setup Helper"
Write-Host "========================================"
Write-Host ""

Write-Host "Searching for Java 17..."
Write-Host ""

$foundJava17 = $null

# Common installation paths
$searchPaths = @(
    "C:\Program Files\Eclipse Adoptium",
    "C:\Program Files\Java",
    "C:\Program Files\Microsoft",
    "C:\Program Files (x86)\Eclipse Adoptium",
    "C:\java17"
)

foreach ($basePath in $searchPaths) {
    if (Test-Path $basePath) {
        # Look for jdk-17 directories
        $jdkDirs = Get-ChildItem -Path $basePath -Directory -ErrorAction SilentlyContinue | 
                   Where-Object { $_.Name -like "jdk-17*" }
        
        foreach ($jdkDir in $jdkDirs) {
            $javaExe = Join-Path $jdkDir.FullName "bin\java.exe"
            if (Test-Path $javaExe) {
                $foundJava17 = $jdkDir.FullName
                break
            }
        }
        
        if ($foundJava17) { break }
    }
}

if ($foundJava17) {
    Write-Host ""
    Write-Host "Found Java 17 at: $foundJava17"
    Write-Host ""
    Write-Host "Setting JAVA_HOME for this session..."
    
    $env:JAVA_HOME = $foundJava17
    $env:PATH = "$env:JAVA_HOME\bin;$env:PATH"
    
    Write-Host ""
    Write-Host "Verifying Java version:"
    & java -version
    
    Write-Host ""
    Write-Host "Java 17 is now active for this terminal session!"
    Write-Host ""
    
    exit 0
} else {
    Write-Host ""
    Write-Host "Java 17 not found in common locations."
    Write-Host ""
    Write-Host "Please install Java 17:"
    Write-Host "1. Go to: https://adoptium.net/temurin/releases/"
    Write-Host "2. Download: Version 17 (LTS) - Windows x64 - JDK"
    Write-Host "3. Install it"
    Write-Host "4. Run this script again"
    Write-Host ""
    
    exit 1
}

