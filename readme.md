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

# Fascicolo Sanitario 2.0

# _it-fse-gtw-tools_

Questo repository contiene utility Java da riga di comando di supporto al corretto utilizzo del FSE2.0, non microservizi.

| TOOL | LINK | DESCRIZIONE |
| :------------: | :------------: | ------------ |
| JWTGenerator | [jwt-generator] | Genera un Json Web Token (JWT) per la chiamata del servizio Web FSE2.0 Gateway Validator. |
| PDFGenerator | [pdf-generator] | Inserisce un file CDA.xml in un file PDF per la chiamata del servizio Web FSE2.0 Gateway Validator. |
| GTWCertifier | [gtw-certifier] | Consente di effettuare offline la verifica dei test case previsti per l'accreditamento. |

<br/>

## Per cominciare
### Requisiti
<ul>
	<li> Scarica la JDK da https://aka.ms/download-jdk/microsoft-jdk-17.0.3-windows-x64.zip per eseguire la build dell'applicazione </li>
	<li> Scarica Maven da https://maven.apache.org/download.cgi per buildare l'applicazione </li>
</ul>

---

### Setup ambiente

Impostare JAVA_HOME path come variabile d'ambiente, quindi controllare l'installazione della versione corretta di JDK version con:

`java -version`

Se la jdk è stata installata correttamente, l'output del comando precedente dovrebbe essere:

```console
  openjdk version "17.0.3" 2022-04-19 LTS
  OpenJDK Runtime Environment Microsoft-32931 (build 17.0.3+7-LTS)
  OpenJDK 64-Bit Server VM Microsoft-32931 (build 17.0.3+7-LTS, mixed mode, sharing)
```

Impostare MAVEN_HOME path come variabile d'ambiente, quindi controllare l'installazione della versione corretta di Maven con:

`mvn -version`

Se maven è stato installato correttamente, l'output del comando precedente dovrebbe essere:

```console
	Apache Maven 3.8.4 (9b656c72d54e5bacbed989b64718c159fe39b537)
	Maven home: C:\Program Files\apache-maven-3.8.4
	[...]
```
---

### Build

Passare alla cartella principale dell'applicazione ed eseguire il comando:

`mvn clean package`

Se la compilazione ha esito positivo, i tools verranno generati. Guarda ogni file <em>README.md</em> per una guida su come usarli.

### Test isolati e copertura

La root `gtw-tools` ha packaging `pom`: aggrega i moduli e non contiene classi Java da misurare.
I tre moduli Java sono utility CLI, tutti inclusi nel profilo `ISOLATION`:

| Modulo | Ambito dei test isolati |
| --- | --- |
| `jwt-generator` | Generazione e verifica crittografica JWT, target Gateway/Monitoring/Provisioning/Terminology, CLI e utility |
| `pdf-generator` | Inserimento/estrazione CDA, CLI, firme PAdES, certificati, OCSP e timestamp; incluse le classi signature distribuite nel modulo |
| `gtw-certifier` | Tutti i casi di accreditamento configurati in `EsameEnum`, validazione XML, filesystem e workflow offline |

Con JDK 17, dalla root:

```sh
mvn clean test
mvn clean test -PISOLATION
# Un singolo modulo:
mvn -pl jwt-generator clean test -PISOLATION
```

La suite ordinaria seleziona `!ISOLATION`; il profilo seleziona solo il tag JUnit `ISOLATION`,
fallisce se un modulo Java non esegue test e verifica in fase `test` almeno l'80% di copertura
JaCoCo sia `LINE` sia `INSTRUCTION` per modulo (non una media del reactor).
I report sono in `<modulo>/target/site/jacoco/index.html` e `jacoco.xml`.
Non sono aggiunte esclusioni di copertura. In particolare, la preesistente esclusione JaCoCo
PDF usa un percorso sorgente e non corrisponde ai nomi delle classi compilate: queste classi
rimangono incluse nel denominatore. I test usano chiavi/certificati generati localmente,
directory temporanee e risposte OCSP locali, senza servizi esterni.

Risultati verificati con `mvn clean test -PISOLATION` su JDK 17:

| Modulo | Test | LINE | INSTRUCTION |
| --- | ---: | ---: | ---: |
| `jwt-generator` | 4 | 95.92% | 97.66% |
| `pdf-generator` | 18 | 91.73% | 92.48% |
| `gtw-certifier` | 39 | 97.48% | 98.87% |

La suite ordinaria preesistente esegue 5 test JWT; nel PDF i vecchi test JUnit 4
non pubblici non vengono scoperti e il certifier non aveva test. I nuovi test
JUnit Jupiter sono separati intenzionalmente dalla suite ordinaria tramite il tag.

[//]: # (Questi sono collegamenti di riferimento utilizzati nel body di questa nota e vengono rimossi quando il processore di markdown fa il suo lavoro. Non è necessario formattare bene perché non dovrebbe essere visto. Grazie - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)
[jwt-generator]: <https://github.com/ministero-salute/it-fse-gtw-tools/tree/main/jwt-generator>
[pdf-generator]: <https://github.com/ministero-salute/it-fse-gtw-tools/tree/main/pdf-generator>
[gtw-certifier]: <https://github.com/ministero-salute/it-fse-gtw-tools/tree/main/gtw-certifier>
[jdk.zip]: <https://aka.ms/download-jdk/microsoft-jdk-17.0.3-windows-x64.zip>
[maven]: <https://maven.apache.org/download.cgi>

---

## Repos
- [*it-fse-support*](https://github.com/ministero-salute/it-fse-support) - Pagina di supporto FSE 2.0
- [*it-fse-landing*](https://github.com/ministero-salute/it-fse-landing) - Landing page dei repository FSE 2.0

## Licenza

[![License: BSD_3](https://img.shields.io/badge/License-BSD_3--Clause-blue.svg)](https://opensource.org/licenses/BSD-3-Clause)
