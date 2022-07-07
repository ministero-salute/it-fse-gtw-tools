[![N|Solid](https://www.sogei.it/content/dam/sogei/loghi/Sogei_logo_304.svg)](https://www.sogei.it/it/sogei-homepage.html)

# JWTGenerator
JWT generator allows to generate the Json Web Token (JWT) for the invocation of the FSE2.0 Gateway Validator Web service.
The JWT can be customized with payload and signature required by the Web service.

### Requirements
<ul>
	<li> Download a JDK from [jdk.zip] to execute the build of the application </li>
	<li> Gain maven from [maven] to build the application </li>
	<li> Provide signature certificates to use to sign the JWT tokens </li>
</ul>

---

### SETUP ENVIRONMENT

Set JAVA_HOME path as enviromental variable than check the installation of the correct JDK version with:

`java -version`

If the jdk is correctly installed, the output of the above command should be:
```console
  openjdk version "17.0.3" 2022-04-19 LTS
  OpenJDK Runtime Environment Microsoft-32931 (build 17.0.3+7-LTS)
  OpenJDK 64-Bit Server VM Microsoft-32931 (build 17.0.3+7-LTS, mixed mode, sharing)
```

Set MAVEN_HOME path as enviromental variable than check the installation of the correct maven version with:

`mvn -version`

If maven is correctly installed, the output of the above command should be:
```console
	Apache Maven 3.8.4 (9b656c72d54e5bacbed989b64718c159fe39b537)
	Maven home: C:\Program Files\apache-maven-3.8.4
	[...]
```
---

### BUILD

Navigate to the root folder of the application and execute the command:

`mvn clean package`

If the build succeeds, a file <em> jwt-generator.jar </em> will be generated in the target folder.

---

### JAR EXECUTION

To execute the jar use the command:

`java -jar jwt-generator.jar`

Use help argument to check it's syntax:

`java -jar jwt-generator.jar -h`

The above command will show a help that explain how to use the application, arguments accepted are defined as following:

| ARGUMENT | MANDATORY | TYPE | DESCRIPTION |
| :------------: | :------------: | :------------: | ------------ |
| -h | false | MODAL | Shows help screen with argument description. |
| -x | false | MODAL | Set verbose mode that consent to gain more information about the result of operations.  |
| -v | false | MODAL | Execute validation of the JWT token generated giving information about its signature validity. |
| -t | false | VALUE | Consent to assign a number of hours of validity of the created token (default is 24 hours).  |
| -d | true  | VALUE | Complete path of data.json file where JWTToken parameters are located. |
| -a | true  | VALUE | Alias of p12 cert used to execute the JWT signature |
| -p | true  | VALUE | Password of p12 cert used to execute the JWT signature |

---
### TOKEN CUSTOMIZATION
The execution of this application require to defines JWT parameters and certificate paths in an external JSON object, the content of the data.json file will contain the following informations:

| NAME | TYPE | DESCRIPTION |
| ------------ | :------------: | ------------ |
| sub | STRING | A valid subject (IHE specification) |
| subject_role | STRING | Role of the operator |
| purpose_of_use | STRING | Purpose of use of the generated JWT token |
| iss | STRING | Issuer of the token as ISTAT code of the organization concatenated with HSP.11 - HSP.11bis - STS.11 - RIA.11 code |
| locality | STRING | Locality where the token is being generated |
| subject_organization_id | STRING | Unique identifier of the organization that is being generating the JWT token |
| subject_organization | STRING | Description of the organization that is being generating the JWT token |
| aud | STRING | Audience of the token |
| patient_consent | BOOLEAN | Patient consent |
| action_id | STRING | Action to perform with the generated JWT token |
| resource_hl7_type | STRING | Document type (IHE TF-3 specification) |
| jti | STRING | Unique identifier of the token generated |
| person_id | STRING | Patient id (IHE specification) |
| pem_path | STRING | pem certificate path |
| p12_path | STRING | p12 certificate path |

An example of data.json file is the following:
```javascript
{
	"sub": "PROVAX00X00X000Y",    
	"subject_role": "AAS",
	"purpose_of_use": "TREATMENT",
	"iss": "jwt-issuer",
	"locality": "jwt-location",
	"subject_organization_id": "120",
	"subject_organization": "Regione Lazio",
	"aud": "FSE_Gateway",
	"patient_consent": true,
	"action_id": "CREATE",
	"resource_hl7_type": "('11502-2^^2.16.840.1.113883.6.1')",
	"jti": "1234",
	"person_id":"RSSMRA22A01A399Z^^^&amp;2.16.840.1.113883.2.9.4.3.2&amp;ISO",
	"pem_path": "../../certpem.pem",
	"p12_path": "../../certp12.p12"
}
```

### Example

The following command will generate a valid JWT Token that can be used to call FSE2.0-Gateway:

`java -jar jwt-generator.jar -d data.json -a {alias} -p {password} -t 1`


[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)
[jdk.zip]: <https://aka.ms/download-jdk/microsoft-jdk-17.0.3-windows-x64.zip>
[maven]: <https://maven.apache.org/download.cgi>
