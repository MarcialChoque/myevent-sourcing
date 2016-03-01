This is a Spring and RabbitMQ version of the Event Sourcing money transfer example application from https://github.com/cer/event-sourcing-examples.git example.

# Event-Sourcing example application

This application consists of three microservices:

  * Account Command - the command side business logic for Accounts and listener
  * Transaction Command - the command side business logic for Money Transfers and listener
  * Account Query - A listener for Account events

# Deploying the application

  * Install RabbitMQ 

  * Start RabbitMQ
```
./rabbitmq-server start
```

# Running the application
  
  * Start microservices
