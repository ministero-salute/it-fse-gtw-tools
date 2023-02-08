
# Fascicolo Sanitario 2.0

# _pdf-generator_

Il generatore di PDF consente di inserire un file CDA.xml in un file PDF per l'invocazione del servizio Web FSE2.0 Gateway Validator.

### Requisiti
<ul>
	<li> Scaricare la JDK da https://aka.ms/download-jdk/microsoft-jdk-17.0.3-windows-x64.zip per eseguire la build dell'applicazione </li>
	<li> Scaricare Maven da https://maven.apache.org/download.cgi per buildare l'applicazione </li>
</ul>

---

### SETUP AMBIENTE

Impostare JAVA_HOME path come variabile d'ambiente, dopo controlla l'installazione della versione JDK corretta con:

`java -version`

Se la jdk è installata correttamente, l'output del comando precedente dovrebbe essere:
```console
  openjdk version "17.0.3" 2022-04-19 LTS
  OpenJDK Runtime Environment Microsoft-32931 (build 17.0.3+7-LTS)
  OpenJDK 64-Bit Server VM Microsoft-32931 (build 17.0.3+7-LTS, mixed mode, sharing)
```

Impostare MAVEN_HOME path come variabile d'ambiente, dopo controlla l'installazione della corretta versione di Maven con:

`mvn -version`

Se Maven è stato installato correttamente, l'output del comando precedente dovrebbe essere:
```console
	Apache Maven 3.8.4 (9b656c72d54e5bacbed989b64718c159fe39b537)
	Maven home: C:\Program Files\apache-maven-3.8.4
	[...]
```
---

### BUILD

Passare alla cartella principale dell'applicazione ed eseguire il comando:

`mvn clean package`

Se la build riesce con successo, un file <em> pdf-generator.jar </em> sarà generato nella cartella target.

---

### ESECUZIONE JAR

Per eseguire il jar usare il comando:

`java -jar pdf-generator.jar`

 Usare l'help argument per verificare la sintassi:
`java -jar pdf-generator.jar -h`

Il comando precedente mostrerà un aiuto che spiega come utilizzare l'applicazione, gli arguments accettati sono definiti come segue:

| ARGUMENT | MANDATORY | TYPE | DESCRIPTION |
| :------------: | :------------: | :------------: | ------------ |
| -h | false | MODAL | Shows help screen with argument description. |
| -x | false | MODAL | Set verbose mode that consent to gain more information about the result of operations.  |
| -v | false | MODAL | Execute the extraction of the injected attachment as a validation step. |
| -c | true  | VALUE | The complete path of the CDA to inject in the file.  |
| -p | false | VALUE | The complete file of the PDF where to inject the CDA (if not provided, it will use a sample.pdf)  |
| -o | false | VALUE | The output path of the generated PDF (if not provided, it will use ./output.pdf ) |

### Esempio

Il seguente comando inserirà un file CDA.xml in un sample.pdf, il file può essere utilizzato per chiamare il servizio di convalida FSE2.0-Gateway:

`java -jar pdf-generator.jar -c CDA_OK.xml -x`

---

## Repos
- [*it-fse-support*](https://github.com/ministero-salute/it-fse-support) - Pagina di supporto FSE 2.0
- [*it-fse-landing*](https://github.com/ministero-salute/it-fse-landing) - Landing page dei repository FSE 2.0

## Licenza

[![License: BSD_3](https://img.shields.io/badge/License-BSD_3--Clause-blue.svg)](https://opensource.org/licenses/BSD-3-Clause)
