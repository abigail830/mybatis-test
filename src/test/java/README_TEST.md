

## Integration Test

### Preparation
##### H2
* Adding dependency in pom.xml
* Config in application.yml under test/ 

##### flyway
* Adding dependency in pom.xml
* Adding db/migration folder and adding sql for create table or update table (ensure align with production)

##### DBUnit
* Adding dependency in pom.xml
* Prepare dataset in xml file. Said put in /resources/dbunit/*.xml
* Included DbUnitTestExecutionListener, refer IntegrationTestBase.java


### Test from controller->domain/service->infrastructure->DB(H2) 
##### Method1. Rest-assured 
* Rest-assured is not rely on spring/springboot, so it could support more type of framework.
* Adding dependency in pom.xml
* Refer UserRestAssuredIntegrationTest.java and IntegrationTestBase.java
* Ensure include hamcrest-core lib in pom.xml

##### Method2. TestRestTemplate 
* TestRestTemplate is rely on new version of springboot. No additional dependence required.
* Whole process is very similar as common RestTemplate
* Detail refer UserRestTemplateIntegrationTest.java and IntegrationTestBase.java

##### Method3. MockMvc
* MovkMvc is included in spring-test.
* Detail refer UserMockMvcIntegrationTest.java and IntegrationTestBase.java

### Test from domain/service->infrastructure->DB(H2) 
When the logic is extremely simple in controller layer, actually we could also consider to only test from domain/service layer and bypass the controller layer. It would be easier to write test.
* Detail refer to UserServiceIntegrationTest.java


## Test on Layer

##### Sample for only test service layer
* Refer UserServiecTest.java
* It would mock(with Mock) the UserInfrastructure.java

##### Sample for only test controller layer
* Refer UserMockBeanIntegrationTest.java
* It could mock(with MockBean) the UserService.java
* If we mock for said UserInfrastructure instead, then actually we could test through controller->service 


