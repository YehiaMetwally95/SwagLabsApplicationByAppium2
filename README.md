## Overveiw
- Test Automation Project for SwagLabs Android App with Appium 2 written by Java and based on Maven
- Using My Own Test Automation Engine as Testing Framework, Check Engine Details https://github.com/YehiaMetwally95/YehiaEngine
- Using TestNG as the Testing Framework
- Using Fluent Page Object Model Design Pattern in writing Test script and Page actions, thus chaining the Scenario steps and validations in one line of code
- Simulating All Mobile Gestures like Tap / Long Tab /Zoom / DragAndDrop/ Swipe, with only one command while Auto Scrolling to the Target Element into view (Scroll into Screen or into Swiped Element)
- Test Data Management such that store All Test data in Json Files and Retrieve the Updated Test Data of Products and Users from MySQL Database
- Generating Very Detailed Allure Reports with All Scenario Steps And Screenshots for Passed/Hard-Assertion-Failed/Soft-Assertion-Failed Tests and for API Requests and Responses
- Perform Test Execution Locally and Remotely on Cloud Servers as Browser Stack & Sauce Labs
- Performing Parallel Execution from CI/CD Pipeline with GitHub Actions to run 5 Parallel Tests at same time with help of Browser Stack server

## Application Under Test
- https://github.com/saucelabs/sample-app-mobile/releases

## Features
#### Structure of "main folder"
- Using the HomePage as Parent of all pages that inherit locators and actions of Header & Footer from Homepage also for defining common variables that are commonly used across all pages, Thus achieving the right purpose of Inheritance
- Finding Elements using simple Techniques with ID,Text, XPath & Advanced Techniques like XPath Axis and Android UIAutomator

#### Structure of "test folder"
- Using Base Test Class for defining Annotations to Open and Close App, such that all Test Classes inherit from it
- Start each Test from a clean state by Setting and Tearing down App for Every Test Case
- "Using Assertions as follows:
    - All Assertions are implemented in Page Class to allow the Fluency of Scenarios Steps with Validations like (Navigate.Writesteps..SoftAssertions.HardAssertions)
    - Using Hard assertions after every test & Soft assertions for doing verifications within the test"
  
#### Swiping
- W3CTouchActions Class for Different Interactions with Mobile Elements "Android or IOS", Based on W3C Actions APIs for Swiping and other Gestures
- NativeAndroidActions Class for Different Interactions with Native Android Elements, Based on UIScrollable Class for Swiping Into Screen and Into Element Without using x,y Coordinates
- Simulating All Mobile Gestures like Tap / Long Tab /Zoom / DragAndDrop/ Swipe, with Only One Command while Auto Scrolling to the Target Element into view (Scroll into Screen or into Swiped Element)

#### Remote and Parallel Execution
- Integrate with Browser Stack and Sauce Labs to run Tests Remotely on Real Cloud Devices
- Run 5 Parallel Tests from CI/CD Pipeline with GitHub Actions to run 5 Parallel Tests at same time with help of Browser Stack Server on Real Devices

#### Reporting Using Allure Report
- Reporting Test Result & Taking Screenshots for Failed Tests and Successful Tests
- Reporting Soft assertion failures and Taking Screenshots for them
- Reporting All API Requests and Responses Sent, with a Screenshot for each
- Logging All Scenario Steps and Test Validations, in form of main steps and expanding every main step to check its child steps
- Defining Epics/Features/Stories

#### Configurations
- Reading Global Variables and Configurations from Properties file
- Execution Type (Local / Remotely On Browser Stack / Remotely on Sauce Labs)
- App Type "NativeAndroid , NativeIOS , WebAppAndroid, WebAppIOS"
- In Case of WebApp, The Browser Types "Chrome , Firefox , Edge"
- Device Capabilities like device name & udid
- Platform Capabilities and Automation Driver
- Application Capabilities
- Cloud Server Configurations like username and access key and build and testname

#### Test Data Management
- Test Data Preparation for Live Data like ""UsersData , ProductsData"" by setting Json File for every Test Case which read its data from mysql database using JDBC , And then Before every run, any updates in database will be reflected into Json Files to be used in the next runs
- Test Data Preparation for Static Data like "Messages, Page Titles, Credit Card Details"" by Filling it Manually on Json Files for every Test & User this data for Validations
- Test Data Execution by reading test data from Json files whether Json data is represented as Simple Json Object or Nested Json Objects or Array of Json Objects

## Installation of Appium
### You can Check the Installation Steps from the below link
https://docs.google.com/document/d/e/2PACX-1vS9HAzWKhCgkF2NTPNLjWboH6JYrF4TfeztEJdaxqv-Nnxdw0ox-P_ZLxdLWvpvsrfP-nnM9IAJSU7k/pub

### You can Check the Ahmed Videos for Installation Steps
https://www.youtube.com/playlist?list=PL62It1k5a1Ulzd5aXuZTWyvdx88vQeIcv

## Installation of mysql Database
### Note : In Case of Local Execution and Without Sync from Database, You Don't have to Install Docker
#### 1- Docker Must be Installed and Run on your machine
#### 2- To Setup MySQL Database that store data of Products & Users, Just run the following command in Intellij Terminal
```bash
docker compose -f src/main/resources/dockerFiles/docker-compose-mysql-v3.yml up -d
```
#### 3- After Finish the Test Execution, Its Better to CleanUp and Stop running the Docker Containers by running the following command in Terminal
```bash
docker compose -f src/main/resources/dockerFiles/docker-compose-grid-v3.yml down ; docker compose -f src/main/resources/docker-compose-mysql-v3.yml down 
```
#### 4- The .sql file is located in resources Directory, You can edit it using any IDE as MySQL Workbench
#### 5- To Sync The Tests With MySQL Database to get the Updated Products and Users Data, The flag "syncWithDB" in Configuration.properties shall be set with "true", otherwise, it can be set with "false"
