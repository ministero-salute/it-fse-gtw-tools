
# Fascicolo Sanitario 2.0

# _jwt-generator_

Il generatore JWT consente di generare il Json Web Token (JWT) per l'invocazione di diversi servizi del Fascicolo Sanitario:

- Gateway Validator
- Provisioning
- Terminology
- Monitoring

Il JWT può essere personalizzato con il payload e la firma richiesti dal servizio Web.

### Requisiti
<ul>
	<li> Scaricare la JDK da https://aka.ms/download-jdk/microsoft-jdk-17.0.3-windows-x64.zip per eseguire la build dell'applicazione </li>
	<li> Scaricare Maven da https://maven.apache.org/download.cgi per buildare l'applicazione </li>
	<li> Fornire i certificati di firma da utilizzare per firmare i token JWT </li>
</ul>

---

### SETUP AMBIENTE

Impostare il percorso JAVA_HOME come variabile d'ambiente, poi controllare l'installazione della versione JDK corretta con:

`java -version`

Se la jdk è installato correttamente, l'output del comando precedente dovrebbe essere:
```console
  openjdk version "17.0.3" 2022-04-19 LTS
  OpenJDK Runtime Environment Microsoft-32931 (build 17.0.3+7-LTS)
  OpenJDK 64-Bit Server VM Microsoft-32931 (build 17.0.3+7-LTS, mixed mode, sharing)
```

Impostare MAVEN_HOME path come variabile d'ambiente, poi verificare l'installazione della corretta versione Maven con: 

`mvn -version`

Se Maven è installato correttamente, l'output del comando precedente dovrebbe essere:

```console
	Apache Maven 3.8.4 (9b656c72d54e5bacbed989b64718c159fe39b537)
	Maven home: C:\Program Files\apache-maven-3.8.4
	[...]
```
---

### BUILD

Passare alla cartella principale dell'applicazione ed eseguire il comando:

`mvn clean package`

Se la build riesce con successo, un file <em> jwt-generator.jar </em> sarà generato nella cartella target.

---

### JAR EXECUTION

Per eseguire il jar usare il comando:

`java -jar jwt-generator.jar`

Usare l'argument help per verificare la sintassi:

`java -jar jwt-generator.jar -h`

Il comando precedente mostrerà un aiuto che spiega come utilizzare l'applicazione, gli arguments accettati sono definiti come segue:

| ARGUMENT | MANDATORY | TYPE | DESCRIPTION |
| :------------: | :------------: | :------------: | ------------ |
| -h | false | MODAL | Shows help screen with argument description. |
| -x | false | MODAL | Sets verbose mode that consent to gain more information about the result of operations.  |
| -v | false | MODAL | Executes validation of the JWT token generated giving information about its signature validity. |
| -t | false | VALUE | Allows to assign a number of hours of validity of the created token (default is 24 hours).  |
| -f | false | VALUE | Allows to specify a PDF file path (if given, the tool will calculate hash and use it as value in custom claim field: attachment_hash) |
| -d | true  | VALUE | Complete path of data.json file where JWTToken parameters are located. |
| -a | false  | VALUE | Alias of p12 cert used to execute the JWT signature (if not provided, alias is retrieved from the certificate) |
| -p | true  | VALUE | Password of p12 cert used to execute the JWT signature |
| -o | false | VALUE | Specify output file name prefix (optional, tokens will be saved as prefix.auth.txt and prefix.sign.txt) |
| -s | true | VALUE | Specify jwt system target * |

#### * System Target
| KEY | BUSINESS NAME |
| :------------: | :------------: |
| gateway | FSE-JWT-Signature  |
| provisioning | FSE-JWT-Provisioning  |
| monitoring | FSE-JWT-Monitoring  |
| terminology | FSE-JWT-Terminology  |

---
### TOKEN CUSTOMIZATION GATEWAY
L'esecuzione di questa applicazione richiede di definire i parametri JWT e i percorsi dei certificati in un oggetto JSON esterno, il contenuto del file data.json conterrà le seguenti informazioni:

| NAME | TYPE | DESCRIPTION |
| ------------ | :------------: | ------------ |
| sub | STRING | A valid subject (IHE specification) |
| subject_role | STRING | Role of the operator |
| purpose_of_use | STRING | Purpose of use of the generated JWT token |
| iss | STRING | Issuer of the token as ISTAT code of the organization concatenated with HSP.11 - HSP.11bis - STS.11 - RIA.11 code |
| subject_application_id | STRING | Id of the user's application |
| subject_application_vendor | STRING | Vendor of the user's application |
| subject_application_version | STRING | Version of the user's application |
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

Un esempio del file data.json è il seguente: 
```javascript
{
	"sub": "PROVAX00X00X000Y^^^&2.16.840.1.113883.2.9.4.3.2&ISO",    
	"subject_role": "AAS",
	"purpose_of_use": "TREATMENT",
	"iss": "jwt-issuer",
	"subject_application_id": "subject-application-id",
	"subject_application_vendor": "subject-application-vendor",
	"subject_application_version": "subject-application-version",
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

### Esempio

Il seguente comando genererà due JWT Token che possono essere usati per chiamare il FSE2.0-Gateway: il primo (chiamato Authorization Bearer Token) è un token contenente tutte le attestazioni utilizzate per l'autorizzazione (es. iss, iat, exp), mentre il secondo (chiamato FSE-JWT-Signature Token) contiene tutte le attestazioni personalizzate.

`java -jar jwt-generator.jar -d data.json -a {alias} -p {password} -t 1`

oppure

`java -jar jwt-generator.jar -d data.json -a {alias} -p {password} -t 1 -s gateway`

---
### TOKEN CUSTOMIZATION TERMINOLOGY
L'esecuzione di questa applicazione richiede di definire i parametri JWT e i percorsi dei certificati in un oggetto JSON esterno, il contenuto del file data.json conterrà le seguenti informazioni:

| NAME | TYPE | DESCRIPTION |
| ------------ | :------------: | ------------ |
| oid | STRING | Identifies the oid for which you want to make a create/delete request |
| version | STRING | Identifies the version of the terminology for which you want to request a creation/deletion |
| file_hash | STRING | Identify the hash of the input file |

Un esempio del file data.json in caso di upload di terminologie è il seguente: 
```javascript
{
	"alg": "RS256",
	"typ": "JWT",
	"kid": "0",
	"x5c": "MIIDBzCCAe+gAwIBAgIJAIBJZQZQX4ZmMA0GCSqGSIb3DQEBC[...]",
	"sub": "PROVAX00X00X000Y",
	"aud": "https://modipa-val.fse.salute.gov.it/govway/rest/in/FSE/terminology/v1",
	"file_hash":  "d98d66e46b1333ddb548e55a086c0153b1a691f2c4f38e62067ad4ca77bfd8f8",
	"iss": "integrity:S1#190201234567PR",
	"oid": "urn:oid:2.16.840.1.113883.5.1",
	"exp": 1689697549,
	"iat": 1689611149,
	"version": "2.1.0",
	"jti": "1689611149"
}
```

Un esempio del file data.json in caso di eliminazione di terminologie è il seguente: 
```javascript
{
	"alg": "RS256",
	"typ": "JWT",
	"kid": "0",
	"x5c": "MIIDBzCCAe+gAwIBAgIJAIBJZQZQX4ZmMA0GCSqGSIb3DQEBC[...]",
	"sub": "PROVAX00X00X000Y",
	"aud": "https://modipa-val.fse.salute.gov.it/govway/rest/in/FSE/terminology/v1",
	"iss": "integrity:S1#190201234567PR",
	"oid": "urn:oid:2.16.840.1.113883.5.1",
	"exp": 1689697549,
	"iat": 1689611149,
	"version": "2.1.0",
	"jti": "1689611149"
}
```

### Esempio

Il seguente comando genererà due JWT Token che possono essere usati per chiamare il FSE2.0-Terminology: il primo (chiamato Authorization Bearer Token) è un token contenente tutte le attestazioni utilizzate per l'autorizzazione (es. iss, iat, exp), mentre il secondo (chiamato FSE-JWT-Terminology Token) contiene tutte le attestazioni personalizzate.

`java -jar jwt-generator.jar -d data.json -a {alias} -p {password} -t 1 -s terminology`

---
### TOKEN CUSTOMIZATION MONITORING
L'esecuzione di questa applicazione richiede di definire i parametri JWT e i percorsi dei certificati in un oggetto JSON esterno, il contenuto del file data.json conterrà le seguenti informazioni:

| NAME | TYPE | DESCRIPTION |
| ------------ | :------------: | ------------ |
| file_hash | STRING | Hash (SHA256) of the file provided as input |

Un esempio del file data.json è il seguente: 
```javascript
{
	"iss": "integrity:190201123456XX",
	"iat": 1540890704,
	"exp": 1540918800,
	"jti": 1540918800,
	"aud": "https://modipa-val.fse.salute.gov.it/govway/rest/in/FSE/monitoring/v1",
	"sub": "mef",
	"file_hash": "0ef542da6e59a03a8d95f03f635327d60d8a3795e634b1d20b07108c497697fd"
}
```

### Esempio

Il seguente comando genererà due JWT Token che possono essere usati per chiamare il FSE2.0-Monitoring: il primo (chiamato Authorization Bearer Token) è un token contenente tutte le attestazioni utilizzate per l'autorizzazione (es. iss, iat, exp), mentre il secondo (chiamato FSE-JWT-Monitoring Token) contiene tutte le attestazioni personalizzate.

`java -jar jwt-generator.jar -d data.json -a {alias} -p {password} -t 1 -s monitoring`

---
### TOKEN CUSTOMIZATION PROVISIONING
L'esecuzione di questa applicazione richiede di definire i parametri JWT e i percorsi dei certificati in un oggetto JSON esterno, il contenuto del file data.json conterrà le seguenti informazioni:

| NAME | TYPE | DESCRIPTION |
| ------------ | :------------: | ------------ |
| vector_hash_csr | STRING | Array containing the hash list (HEX SHA256) of the CSRs supplied as input to the endpoint |
| vector_id | STRING | UUID associated with the previous Mass Creation or Mass Renewal request |

Un esempio del file data.json in caso di creazione e rinnovo massivo è il seguente: 
```javascript
{
	"alg": "RS256",
	"typ": "JWT",
	"kid": "0",
	"iss": "integrity:190201123456XX",
	"x5c": "MIIDBzCCAe+gAwIBAgIJAIBJZQZQX4ZmMA0GCSqGSIb3DQEBC[...]",
	"iat": 1540890704,
	"exp": 1540918800,
	"jti": 1540918800,
	"aud": "https://modipa-val.fse.salute.gov.it/govway/rest/in/FSE/provisioning/v1",
	"sub": "VRDMRC67T20I257E",
	"vector_hash_csr": [
		"d98d66e46b1333ddb548e55a086c0153b1a691f2c4f38e62067ad4ca77bfd8f8",
		"d98d66e46b1333ddb548e55a086c0153b1a691f2c4f38e62067ad4ca77bfd8f9"
	]
}
```

Un esempio del file data.json in caso di revoca massiva è il seguente: 
```javascript
{
	"alg": "RS256",
	"typ": "JWT",
	"kid": "0",
	"iss": "integrity:190201123456XX",
	"x5c": "MIIDBzCCAe+gAwIBAgIJAIBJZQZQX4ZmMA0GCSqGSIb3DQEBC[...]",
	"iat": 1540890704,
	"exp": 1540918800,
	"jti": 1540918800,
	"aud": "https://modipa-val.fse.salute.gov.it/govway/rest/in/FSE/provisioning/v1",
	"sub": "VRDMRC67T20I257E",
	"vector_id": "e2241357-6d09-43c8-adcc-6e098d95ca3f"
}
```

### Esempio

Il seguente comando genererà due JWT Token che possono essere usati per chiamare il FSE2.0-Provisioning: il primo (chiamato Authorization Bearer Token) è un token contenente tutte le attestazioni utilizzate per l'autorizzazione (es. iss, iat, exp), mentre il secondo (chiamato FSE-JWT-Provisioning Token) contiene tutte le attestazioni personalizzate.

`java -jar jwt-generator.jar -d data.json -a {alias} -p {password} -t 1 -s provisioning`


[//]: # (Questi sono collegamenti di riferimento utilizzati nel corpo di questa nota e vengono rimossi quando il processore di markdown fa il suo lavoro. Non è necessario formattare bene perché non dovrebbe essere visto. Grazie - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)
[jdk.zip]: <https://aka.ms/download-jdk/microsoft-jdk-17.0.3-windows-x64.zip>
[maven]: <https://maven.apache.org/download.cgi>

---

## Repos
- [*it-fse-support*](https://github.com/ministero-salute/it-fse-support) - Pagina di supporto FSE 2.0
- [*it-fse-landing*](https://github.com/ministero-salute/it-fse-landing) - Landing page dei repository FSE 2.0

## Licenza

[![License: BSD_3](https://img.shields.io/badge/License-BSD_3--Clause-blue.svg)](https://opensource.org/licenses/BSD-3-Clause)
