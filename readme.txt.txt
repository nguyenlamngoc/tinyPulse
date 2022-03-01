 What is it?
 ----------
 The test case AddPeople is created to add some people on https://staging.tinyserver.info/home


 How to run
 ----------
 Go to 'Test Suites' -> addpeople
 Click Run (or Ctrl + Shift + A) to run the test case


 System Requirements
 ---------
 Install the Katalon Studio version 8.2.5


 Documentation
 ----------
 1/ The global variales declares at Profiles -> default
 2/ 'Object Repository' contains the xpath of elements in an HTTP page
 3/ The feature file 'AddPeople.feature' in Gherkin language created at 'Include -> features'
 4/ The scripts (login and keyworkds) defines in 'Include -> scripts' with folder 'common' and 'steps'
 5/ The test case AddPeople created at 'Test Cases'
 6/ The test suite addpeople created at 'Test Suites'
 7/ The report will be generated at 'Reports'


 Improvements
 ----------
 1/ The keyword "Add some users into list" should be  improved in the future. Currently it created with static data 
that defined as global variable in 'Profiles -> default', without using the data files
 2/ When adding multiple users => it should have variable in the xpath
 3/ After adding people successfully, the user in the list should be validated
