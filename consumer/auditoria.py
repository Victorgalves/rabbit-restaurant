import pika
import ssl

def iniciar_auditoria():
    params = pika.URLParameters("amqps://sugdolwn:sek-4RnNKTJi-VFFwADJDEFTt4MrAS1U@jackal.rmq.cloudamqp.com/sugdolwn")
    params.ssl_options = pika.SSLOptions(ssl.create_default_context())
    connection = pika.BlockingConnection(params)
    channel = connection.channel()

    channel.exchange_declare(exchange='pedidos-exchange', exchange_type='topic', durable=True)

    result = channel.queue_declare(queue='', exclusive=True)
    queue_name = result.method.queue

    channel.queue_bind(exchange='pedidos-exchange', queue=queue_name, routing_key='pedidos.#')

    def callback(ch, method, properties, body):
        print(f"[AUDITORIA] Mensagem recebida ({method.routing_key}): {body.decode()}")

    channel.basic_consume(queue=queue_name, on_message_callback=callback, auto_ack=True)
    channel.start_consuming()


if __name__ == "__main__":
    iniciar_auditoria()
