


# Fascicolo Sanitario 2.0

# _gtw-certifier_

Il gtw certifier consente di eseguire offline la verifica dei test case di validazione ok richiesti durante la fase di accreditamento.

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

Se la build riesce con successo, un file `gtw-certifier-<version>.jar` sarà generato nella cartella target.

---

### ESECUZIONE JAR

Per eseguire il jar usare il comando:

`java -jar gtw-certifier-<version>.jar`

 Usare l'help argument per verificare la sintassi:
`java -jar gtw-certifier-<version>.jar -h`

Il comando precedente mostrerà un aiuto che spiega come utilizzare l'applicazione, gli arguments accettati sono definiti come segue:

| ARGUMENT | MANDATORY | TYPE  | DESCRIPTION                                                                                                                               |
|:--------:|:---------:|:-----:|-------------------------------------------------------------------------------------------------------------------------------------------|
|    -h    |   false   | MODAL | Mostra la descrizione degli argomenti.                                                                                                    |
|   -dir   |   true    | VALUE | Il parametro di input fornito rappresenta la folder di root in cui dovranno essere presenti sia la subfolder FILES sia il file data.json. |

### Esempio

Supponendo di avere un ambiente così disposto:
```
X:.
└───AZIENDA
    └───ID
        └───v1.X.X
            │   data.json
            │   ...
            └───FILES
```

Il comando corretto da lanciare per eseguire la validazione sarà:

`java -jar gtw-certifier-<version>.jar -dir "<USER-PATH>\AZIENDA\ID\v1.X.X"`

---



https://media.github.ibm.com/user/377915/files/95f548ae-a354-4507-949c-1e6c991e2923



## Repos
- [*it-fse-support*](https://github.com/ministero-salute/it-fse-support) - Pagina di supporto FSE 2.0
- [*it-fse-landing*](https://github.com/ministero-salute/it-fse-landing) - Landing page dei repository FSE 2.0

## Licenza

[![License: BSD_3](https://img.shields.io/badge/License-BSD_3--Clause-blue.svg)](https://opensource.org/licenses/BSD-3-Clause)
