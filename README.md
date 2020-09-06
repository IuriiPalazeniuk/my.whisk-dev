# Automation test framework

**BDD automation test framework**  - Create Shopping List feature is covered by automation scripts with @UI tags, also there are two scripts which cover create, get, and delete
Shopping List API functionality which is marked by @API tags. 

Table of contents
=================
   * [Key features](#key-features)
   * [Technologies and frameworks](#technologies-and-frameworks)
   * [Test scenario example](#test-scenario-example)
   * [Test step example](#test-step-example)   
   * [Test runner](#test-runner)
   * [Run options](#run-options)
   * [Report](#report)

## Key features
  * BDD (Cucumber framework) is used for readability and extensibility of tests

    
## Technologies and frameworks
 * Java programming language ([Java](https://www.java.com)) 
 * Cucumber BDD framework ([Cucumber](https://cucumber.io/docs))
 * Maven build tool ([Maven](https://maven.apache.org))

## Test scenario example

```java
  Scenario: check deletening Shopping list functionality
    Given post new Shopping list
    When delete Shopping list by id
    And get Shopping List by id after deleting Shopping list
    Then verify that code response 400
    And verify that response message is 'shoppingList.notFound'
```

## Test step example

```java
     @When("fill in {string}")
     public void fillInCredentials(String fieldName) {
         String usernamePassword = getSystemPropertyByName(fieldName);
         signUpPage.fillInCredentials(fieldName, usernamePassword);
     }

```

## Test runner

```java
@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources",
    glue = "stepDefs",
    plugin = {"pretty", "html:target/cucumber"})
public class RunCucumberTest {

}
```

## Run options
 * Locally via IDE (To run separate scenario you need Cucumber Java plugin to be installed)
 * Possible to select browser (Chrome or FireFox) by cucumber options (ex. -Ddriver="firefox") - Chrome by default
 * Maven (possible to run tests separately by @tags):
 
```bash
   'mvn clean test mvn clean test -Dcucumber.options="--tags @API"' or 'mvn clean test mvn clean test -Dcucumber.options="--tags @UI"'
   'mvn clean test' - will run all tests
```
## Report
 * Report is located in target/cucumber/index.html