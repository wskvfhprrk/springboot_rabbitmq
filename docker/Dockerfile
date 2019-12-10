
FROM rabbitmq:3.8-management

COPY rabbitmq_delayed_message_exchange-3.8.0.ez /plugins
#UN rabbitmq-plugins enable --offline rabbitmq_mqtt rabbitmq_federation_management rabbitmq_stomp rabbitmq_delayed_message_exchange
RUN rabbitmq-plugins enable rabbitmq_delayed_message_exchange
