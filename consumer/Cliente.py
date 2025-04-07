import pika
import ssl

def consumir_filas_fixas(categorias_escolhidas):
    params = pika.URLParameters("amqps://sugdolwn:sek-4RnNKTJi-VFFwADJDEFTt4MrAS1U@jackal.rmq.cloudamqp.com/sugdolwn")
    params.ssl_options = pika.SSLOptions(ssl.create_default_context())
    connection = pika.BlockingConnection(params)
    channel = connection.channel()

    print("\n Aguardando mensagens... Pressione Ctrl+C para sair.\n")

    def callback(ch, method, properties, body):
        print(f"[📥] Mensagem da fila '{method.routing_key}': {body.decode()}")

    for categoria in categorias_escolhidas:
        nome_fila = categoria
        channel.queue_declare(queue=nome_fila, durable=True)
        channel.basic_consume(queue=nome_fila, on_message_callback=callback, auto_ack=True)
        print(f" Escutando fila: {nome_fila}")

    channel.start_consuming()


def menu():
    print("== Menu Cliente ==")
    print("Digite os números das categorias que deseja receber, separados por vírgula.")
    print("1 - Pratos Principais")
    print("2 - Bebidas")
    print("3 - Sobremesas")
    print("Exemplo: 1,3 (para pratos principais e sobremesas)")

    entrada = input("Escolha: ").split(",")

    categorias_disponiveis = {
        "1": "pratos_principais",
        "2": "bebidas",
        "3": "sobremesas"
    }

    categorias_escolhidas = []
    for i in entrada:
        if i in categorias_disponiveis:
            categorias_escolhidas.append(categorias_disponiveis[i])
            
    if categorias_escolhidas:
        consumir_filas_fixas(categorias_escolhidas)
    else:
        print("Nenhuma categoria válida selecionada.")
        menu()


if __name__ == "__main__":
    menu()

