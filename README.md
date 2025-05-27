# Gojek Web Automation Framework

This is a Web Automation Framework built using Selenium + Java + TestNG + Gradle

Framework Tree Structure of the Major Components

```
.
|-- Drivers
|   |-- IEDriverServer.exe
|   |-- SafariDriver.safariextz
|   |-- chromedriver
|   |-- chromedriver.exe
|   |-- geckodriver
|   |-- geckodriver.exe
|   `-- versions.txt
|-- ExtentReportsTestNG.html
|-- Parameters
|   |-- config.properties
|-- build.gradle
|-- settings.gradle
|-- src
|   |-- main
|   |   `-- java
|   |       `-- TestAutomation
|   |           |-- helpers
|   |           |   |-- AssertHelper.java
|   |           |   |-- BasePage.java
|   |           |   |-- Browser.java
|   |           |   |-- CommonUtilities.java
|   |           |   |-- Config.java
|   |           |   |-- DataGenerator.java
|   |           |   |-- ExtentReporter.java
|   |           |   |-- Log.java
|   |           |   |-- TestBase.java
|   |           |   |-- TestListener.java
|   |           |   `-- WaitHelper.java
|   |           `-- pageObjects
|   |               `-- MidtransHomePage.java
|   `-- test
|       `-- java
|           `-- TestAutomation
|               `-- TestBuyNow.java
|-- test-output
|-- pom.xml
`-- testng.xml

```

# How to Run Tests

To Run your assignment tests you can configure them as a part of the suite in `testng.xml` file and execute the following gradle command
`gradle cleanTest test --info
`

To Run your assignment tests you can configure them as a part of the suite in `testng.xml` file and execute the following maven command
`mvn clean test`

