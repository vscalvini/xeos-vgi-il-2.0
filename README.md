![](RackMultipart20200506-4-1xn6ht6_html_c2256d3a507bb7c0.png)

# **Progetto VGI-IL 2.0**

| **Nome Documento** | RackMultipart20200506-4-1xn6ht6.docx |
| --- | --- |
| **Versione** | 0.1 |
| **Data** | 04 mag. 2020 |
| **Autore** |
 |
| **Stato** |
 |

#

# ![](RackMultipart20200506-4-1xn6ht6_html_128146369f6eb7a7.png)

# Config service

Si basa su Spring Cloud Config che è un servizio centralizzato, scalabile, per la gestione centralizzata delle configurazioni di sistemi distribuiti. La tipologia di repository che possono essere &#39;agganciati&#39; prevede:

- **file system locale**
- repository GIt
- subversion

nella attuale nostra implementazione usiamo un repository locale, quindi i file di configurazione vengono caricati a partire da un classpath locale (file).

Tra le risorse di Config Service è quindi prevista una cartella condivisa denominata &#39;shared&#39; contenente i file

- \&lt;_application_\&gt;.yml (proprietà di configurazione per le singole applicazioni client) e
- application.yml (condiviso da tutte le applicazioni client).

## Client side

Dal punto di vista delle singole applicazioni client, l&#39;unico requisito è quello di prevedere in fase di build la dipendenza da **spring-cloud-starter-config** ; tutto il resto viene generato automaticamente.

Nella singola applicazione non c&#39;è bisogno di gestire nessun tipo di proprietà &#39;embedded&#39;, basta fornire nel file bootstrap.yml il nome dell&#39;applicazione e l&#39;url del Config service:

esempio:

_spring:_

_application:_

_name: newlead-service_

_cloud:_

_config:_

_uri: http://config:8888_

_fail-fast: true_

**Le configurazioni e le proprietà dei singoli moduli applicativi possono essere dinamicamente utilizzando l&#39;annotation @RefreshScope sul componente interessato.**

In pratica si può procedere manualmente in questo modo:

- modificare la proprietà sul file delle cartella &#39;shared&#39;;
- inviare una request del tipo curl -H &quot;Authorization: Bearer #token#&quot; -XPOST http://\&lt;IP\&gt;:\&lt;PORT\&gt;/\&lt;service\&gt;/refresh

Il processo può essere automatizzato.

# Auth service

La gestione della sicurezza è stata completamente sganciata dal singolo componente applicativo e concentrata su questo server che fornisce OAuth2 tokens ai resource services di back-end .

Il componente Auth Server è usato sia per eventuali user authorization che per gestire la sicurezza delle chiamate tra i vari servizi all&#39;interno della soluzione.

Stiamo utilizzando il Password credentials grant type per la user authorization (ad oggi non è stato implementato nessun componente che la utilizza) e Client Credentials grant per l&#39; authorization dei microservizi.

Esempio client side:

_@PreAuthorize(&quot;#oauth2.hasScope(&#39;server&#39;)&quot;)_

_@RequestMapping(value = &quot;leads/{name}&quot;, method = RequestMethod.GET)_

_public List\&lt;DataPoint\&gt; getLeadsByDealer(@PathVariable String name) {_

_return leadsService.findByDealerName(name);_

_}_

Ogni client in VGI-IL avrà uno scope: _ **server** _ ** ** per i servizi di backend, e _ **ui** _ per il browser. In questo modo viene controllato e gestito l&#39;accesso esterno ai singoli servizi.

#

# API Gateway

Questo componente rappresenta il singolo entry point per tutto il sistema VGI-IL.

Le configurazioni previste per il modulo API Gateway consentono di implementare un single entry point sul sistema IL, utilizzato per il routing delle singole request verso gli appropriati servizi di back-end o per l&#39;invocazione multipla di servizi e l&#39;aggregazione del loro risultato.

L&#39;API Gateway può essere utilizzato anche per fase di autenticazione, stress test, migrazione di servizi, monitoraggio del traffico.

Su VGI-IL viene utilizzato il componente Zuul di Netflix opensource inserito nel framework Spring. In questa prima versione Zuul viene utilizzato per il routing delle richieste e viene abilitato mediante l&#39;annotation @EnableZuulProxy.

Di seguito un esempio di routing &#39;prefix-based&#39; per un ipotetico servizio lead-service:

_zuul:_

_routes:_

_lead-service:_

_path: /leads/\*\*_

_serviceId: lead-service_

_stripPrefix: false_

In pratica stiamo dicendo che tutte le request che iniziano con /leads saranno redirette a lead-service. Notiamo che non ci sono indirizzi hardcoded. Zuul uses il meccanismo di Service discovery per identificare le istanze di lead-service instances, così come Circuit Breaker e Load Balancer (vedi di seguito).

# Service discovery

Il modulo Service discovery consente di realizzare il rilevamento automatico sulla rete delle istanze dei vari servizi VGI-IL presenti sulla rete, consentendo così la gestione dell&#39;aouto-scaling e relativa assegnazione automatica degli indirizzi, nonché situazioni di failures e degli aggiornamenti.

Il service Registry utilizzato in VGI-IL con Spring Cloud è Netflix Eureka. Eureka implementa il client-side discovery pattern, cioè è la parte clientche è responsabile di determinare la service location per le varie istanze presenti di ogni servizi e gestire così anche il load-balancing tra di esse.

Utilizzando Spring Boot, il Registry Eureka viene costruito mediante la dipendenza del progetto da spring-cloud-starter-eureka-server e l&#39;annotation @EnableEurekaServer  e definendo le opportune configuration properties.

Lato client il tutto viene abilitato mediante l&#39;annotation @EnableDiscoveryClient annotation e il file  **bootstrap.yml**  con il nome dell&#39;applicazione:

_spring:_

_application:_

_name: lead-service_

Allo startup l&#39;applicazione si registra sull&#39; Eureka Server e fornisce i metadati quali host e porta, health indicator, URL, home page etc. Eureka riceve notifiche (heartbeat) da ognuna delle istanze di un determinato

servizio. Dal momento in cui tali notifiche cessano, per uno stabilito intervallo di tempo, l&#39;istanza viene rimossa dal registry.

Eureka mette a disposizione anche una semplice interfaccia web per la verifica delle varie istanze e della loro disponibilità sul sistema.

#

# Progetti

La figura seguente riporta tutti i progetti attualmente in sviluppo. Oltre a quelli infrastrutturali sopra elencati, sono presenti anche i progetti relativi alla wave0 della soluzione VGI-IL2.0, ovvero: **wscrm** e **leadservice** (_esempio da confermare_). leadservice servirà anche da &#39;template&#39; per i servizi REST che verranno identificati in fase di analisi.

![](RackMultipart20200506-4-1xn6ht6_html_2f2c388c97fe48ef.png)

Oltre ai 4 componenti infrastrutturali di base, sono stati progettati anche altri 2 componenti di servizio (_non previsti per la wave0 ma comunque funzionanti in versione demo ed eventualmente da ottimizzare con le wave successive_): **monitoring** e **turbine-stream-service**. per la gestione delle funzioni di monitoring dashboard dell&#39;intero sistema.

# Immagini DOCKER

Ognuno dei progetti sopra elencati contiene il file denominato **Dockerfile** con le direttive per la build della propria docker image. Qui di seguito l&#39;esempio per il modulo _auth-service_:

_FROM java:8-jre_

_MAINTAINER Christian Novelli_

_ADD ./target/auth-service.jar /app/_

_CMD [&quot;java&quot;, &quot;-Xmx200m&quot;, &quot;-jar&quot;, &quot;/app/auth-service.jar&quot;]_

_EXPOSE 5000_

- **FROM ** java:8-jre— con questa riga sui dice a Docker che l&#39;immagine da creare si basa sull&#39;immagine java:8-jre presente sul repository pubblico (Docker Hub). Questa immagine contiene tutte le dipendenze e i componenti prerequisito per l&#39;esecuzione di qualsiasi applicazione Java.
- **ADD**  ./target/auth-service.jar /app/ — il primo parametro indica il path relativo dell&#39;applicazione SpringBoot che vogliamo inserire nel container (_auth-service.jar_). Il secondo parametro _/app/_ indica la directory del container dove copiare l&#39;applicazione.
- **CMD**  [&quot;java&quot;, &quot;-Xmx200m&quot;, &quot;-jar&quot;, &quot;/app/auth-service.jar&quot;] — in pratica si dice a Docker di eseguire l&#39;applicazione: il primo parametro è il comando da lanciare (_java_) gli altri i parametri di esecuzione.
- **EXPOSE**  5000 — è la direttiva per dire a Docker che il container esporrà la porta 5000, la stessa in questo caso utilizzata dall&#39;applicazione auth-service.

Quindi, una volta posizionati nel folder che contiene _Dockerfile_, con il comando:

_docker build -t authservice ._

è possibile lanciare la build dell&#39;immagine docker dell&#39;applicazione, nel caso di esempio auth-service (il parametro dopo il -t specifica il nome della docker image che si vuole creare).

A questo punto l&#39;immagine potrà essere esportata in formato .tar per essere utilizzata su container locali, oppure (caso VGI-IL) si procede con il suo caricamento su Repository privato e alla sua pubblicazione sui vari ambienti di esecuzione (ad esempio, per VGI-IL, AWS container).

# Processo di build e deploy – Continuous Delivery

Di seguito riportiamo un esempio di automatizzazione della infrastruttura di build e deploy:

![](RackMultipart20200506-4-1xn6ht6_html_112c3e7d4281860e.png)

Nella figura sono indicati TravisCI, DockerHub e CodeCov come tools e infrastrutture utilizzate; chiaramente il tutto può essere sostituito con quanto previsto dall&#39;infrastruttura del cliente.