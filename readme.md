[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-black.svg)](https://sonarcloud.io/summary/new_code?id=it.finanze.sanita.fse2%3Agtw-tools)
<br/>
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=it.finanze.sanita.fse2%3Agtw-tools&metric=bugs)](https://sonarcloud.io/summary/new_code?id=it.finanze.sanita.fse2%3Agtw-tools)
<br/>
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=it.finanze.sanita.fse2%3Agtw-tools&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=it.finanze.sanita.fse2%3Agtw-tools)
<br/>
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=it.finanze.sanita.fse2%3Agtw-tools&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=it.finanze.sanita.fse2%3Agtw-tools)
<br/>
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=it.finanze.sanita.fse2%3Agtw-tools&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=it.finanze.sanita.fse2%3Agtw-tools)
<br/>
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=it.finanze.sanita.fse2%3Agtw-tools&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=it.finanze.sanita.fse2%3Agtw-tools)
<br/>
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=it.finanze.sanita.fse2%3Agtw-tools&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=it.finanze.sanita.fse2%3Agtw-tools)
<br/>
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=it.finanze.sanita.fse2%3Agtw-tools&metric=coverage)](https://sonarcloud.io/summary/new_code?id=it.finanze.sanita.fse2%3Agtw-tools)
<br/>
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=it.finanze.sanita.fse2%3Agtw-tools&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=it.finanze.sanita.fse2%3Agtw-tools)
<br/>
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=it.finanze.sanita.fse2%3Agtw-tools&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=it.finanze.sanita.fse2%3Agtw-tools)
<br/>
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=it.finanze.sanita.fse2%3Agtw-tools&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=it.finanze.sanita.fse2%3Agtw-tools)
<br/>
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=it.finanze.sanita.fse2%3Agtw-tools&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=it.finanze.sanita.fse2%3Agtw-tools)
<br/>
[![Quality gate](https://sonarcloud.io/api/project_badges/quality_gate?project=it.finanze.sanita.fse2%3Agtw-tools)](https://sonarcloud.io/summary/new_code?id=it.finanze.sanita.fse2%3Agtw-tools)
<br/>

---

[![N|Solid](https://www.sogei.it/content/dam/sogei/loghi/Sogei_logo_304.svg)](https://www.sogei.it/it/sogei-homepage.html)

# it-fse-gtw-tools

The repository contains all the tools that helps execute FSE2.0 Gateway Validator Web service to validate CDA injected in Pdf files.

| TOOL | LINK | DESCRIPTION |
| :------------: | :------------: | ------------ |
| JWTGenerator | [jwt-generator] | Generates a Json Web Token (JWT) for the invocation of the FSE2.0 Gateway Validator Web service. |
| PDFGenerator | [pdf-generator] | Injects a CDA.xml file into a PDF file for the invocation of the FSE2.0 Gateway Validator Web service. |

<br/>

## Getting started
### Requirements
<ul>
	<li> Download a JDK from [jdk.zip] to execute the build of the application </li>
	<li> Gain maven from [maven] to build the application </li>
</ul>

---

### Setup environment

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

### Build

Navigate to the root folder of the application and execute the command:

`mvn clean package`

If the build is successful, the tools will be generated. Checkout each <em>readme.md</em> file for a guide on how to use them.

<!--## License-->

----
author: Cristian Paoluzi <cpaoluzi@sogei.it> - DevOps Manager

updated on: 2022-07-28

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)
[jwt-generator]: <https://github.com/ministero-salute/it-fse-gtw-tools/tree/main/jwt-generator>
[pdf-generator]: <https://github.com/ministero-salute/it-fse-gtw-tools/tree/main/pdf-generator>
[jdk.zip]: <https://aka.ms/download-jdk/microsoft-jdk-17.0.3-windows-x64.zip>
[maven]: <https://maven.apache.org/download.cgi>
