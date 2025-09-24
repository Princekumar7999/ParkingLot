@echo off
setlocal ENABLEDELAYEDEXPANSION

REM One-click run for Parking Lot System
REM Tries Maven first; if unavailable, falls back to javac/java

where mvn >nul 2>&1
if %ERRORLEVEL%==0 (
  echo Using Maven to run the app...
  mvn -q exec:java
  goto :eof
)

echo Maven not found. Falling back to javac/java...
set SRC=src\main\java
set OUT=out

if not exist "%OUT%" mkdir "%OUT%"

REM Compile all Java sources from known directories
javac -d "%OUT%" ^
  "%SRC%\com\parkinglot\ParkingLotApplication.java" ^
  "%SRC%\com\parkinglot\model\"*.java ^
  "%SRC%\com\parkinglot\strategy\"*.java ^
  "%SRC%\com\parkinglot\factory\"*.java ^
  "%SRC%\com\parkinglot\observer\"*.java ^
  "%SRC%\com\parkinglot\service\"*.java

if %ERRORLEVEL% NEQ 0 (
  echo Compilation failed. Please ensure you have JDK installed and environment variables set.
  exit /b 1
)

echo Running application...
java -cp "%OUT%" com.parkinglot.ParkingLotApplication

endlocal
