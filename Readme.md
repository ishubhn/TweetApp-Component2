# Component 2
Swagger URL - http://localhost:8082/swagger-ui/index.html

---
---

# Apache Kafka
___

1. zip install kafka from quickstart page v(kafka_2.13-3.2.1)

2. unzip folder and rename it as kafka

___

### Open the terminal at kafka location
> cd <kafka-location>\kafka

### Start the ZooKeeper service
> bin\windowszookeeper-server-start.bat config\zookeeper.properties

### Start the Kafka Environment
**Start the Kafka broker service**
> bin\windows\kafka-server-start.bat config\server.properties

**Start the Kafka broker service**
> bin\windows\kafka-server-start.bat config\server.properties

### CREATE A TOPIC TO STORE YOUR EVENTS
**Before you can write your first events, you must create a topic**
> bin\windows\kafka-topics.bat --create --topic quickstart-events --bootstrap-server localhost:9092

### Start Producer
> bin\windows\kafka-console-producer.bat --topic quickstart-events --bootstrap-server localhost:9092

### Start Consumer
> bin\windows\kafka-console-consumer.bat --topic quickstart-events --from-beginning --bootstrap-server localhost:9092
