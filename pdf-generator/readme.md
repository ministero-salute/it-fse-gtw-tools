[![N|Solid](https://www.sogei.it/content/dam/sogei/loghi/Sogei_logo_304.svg)](https://www.sogei.it/it/sogei-homepage.html)

# PDFGenerator
PDF generator allows to inject a CDA.xml file into a PDF file for the invocation of the FSE2.0 Gateway Validator Web service.

### Requirements
<ul>
	<li> Download a JDK from [jdk.zip] to execute the build of the application </li>
	<li> Gain maven from [maven] to build the application </li>
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

If the build succeeds, a file <em> pdf-generator.jar </em> will be generated in the target folder.

---

### JAR EXECUTION

To execute the jar use the command:

`java -jar pdf-generator.jar`

Use help argument to check it's syntax:
`java -jar pdf-generator.jar -h`

The above command will show a help that explain how to use the application, arguments accepted are defined as following:

| ARGUMENT | MANDATORY | TYPE | DESCRIPTION |
| :------------: | :------------: | :------------: | ------------ |
| -h | false | MODAL | Shows help screen with argument description. |
| -x | false | MODAL | Set verbose mode that consent to gain more information about the result of operations.  |
| -v | false | MODAL | Execute the extraction of the injected attachment as a validation step. |
| -c | true  | VALUE | The complete path of the CDA to inject in the file.  |
| -p | false | VALUE | The complete file of the PDF where to inject the CDA (if not provided, it will use a sample.pdf)  |
| -o | false | VALUE | The output path of the generated PDF (if not provided, it will use ./output.pdf ) |

### Example

The following command will inject a CDA.xml file into a sample.pdf, the file can be used to call FSE2.0-Gateway validator service:

`java -jar pdf-generator.jar -c CDA_OK.xml -x`
