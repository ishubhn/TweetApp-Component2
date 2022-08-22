## Logstash (Centralised logging system)

**ELK stack -> Elastic Search , Logstash, Kibana**

1. Download Elasticsearch and extract the folder
2. Download Kibana and extract the folder
3. Download Logstash and extract the folder

___

### Elasticsearch

**Go to Elasticsearch folder and run following command**

> bin\elasticsearch.bat
___

### Kibana

***Open following path***

> kibana\config\kibana.yml

***Enable the line (remove the '#' from line) and save***

> elasticsearch.hosts:["http://localhost:9200"]

***Open the console in the kibana folder***

> bin\kibana.bat
