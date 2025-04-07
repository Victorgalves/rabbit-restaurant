import pika

def consumir_multiplas_categorias(categorias_escolhidas):
    import ssl

    params = pika.URLParameters("amqps://sugdolwn:sek-4RnNKTJi-VFFwADJDEFTt4MrAS1U@jackal.rmq.cloudamqp.com/sugdolwn")
    params.ssl_options = pika.SSLOptions(ssl.create_default_context())
    connection = pika.BlockingConnection(params)
    channel = connection.channel()

    channel.exchange_declare(exchange='pedidos-exchange', exchange_type='topic', durable=True)

    # Criar uma fila temporária e exclusiva pra esse consumidor
    result = channel.queue_declare(queue='', exclusive=True)
    queue_name = result.method.queue

    for categoria in categorias_escolhidas:
        routing_key = f"pedidos.{categoria}"
        channel.queue_bind(exchange='pedidos-exchange', queue=queue_name, routing_key=routing_key)
        print(f"[✔] Inscrito para categoria: {categoria}")

    print("\n[✔] Aguardando mensagens... Pressione Ctrl+C para sair.\n")

    def callback(ch, method, properties, body):
        print(f"[🍽️] Mensagem recebida: {body.decode()}")

    channel.basic_consume(queue=queue_name, on_message_callback=callback, auto_ack=True)

    channel.start_consuming()


def menu():
    print("== Menu Cliente ==")
    print("Digite os números das categorias que deseja receber, separados por vírgula.")
    print("1 - Pratos Principais")
    print("2 - Bebidas")
    print("3 - Sobremesas")
    print("Exemplo: 1,3 (para pratos principais e sobremesas)")

    entrada = input("Escolha: ").split(",")
    entrada = [e.strip() for e in entrada]

    categorias_disponiveis = {
        "1": "pratos_principais",
        "2": "bebidas",
        "3": "sobremesas"
    }

    categorias_escolhidas = [categorias_disponiveis[e] for e in entrada if e in categorias_disponiveis]

    if categorias_escolhidas:
        consumir_multiplas_categorias(categorias_escolhidas)
    else:
        print("❌ Nenhuma categoria válida selecionada.")
        menu()


if __name__ == "__main__":
    menu()
