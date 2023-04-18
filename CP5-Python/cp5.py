def cadastrar_pedido():
    nome = input("Digite seu nome: ")

    with open("pedidos.txt", "a") as arquivo:
        arquivo.write(f"{nome}\n")

        qtd_produtos = int(input("Digite a quantidade de produtos que deseja comprar: "))
        for i in range(qtd_produtos):
            produto = input(f"Digite o nome do {i+1}º produto: ")
            qtd = int(input(f"Digite a quantidade de {produto} que deseja comprar: "))
            preco = float(input(f"Digite o preço unitário de {produto}: "))

            arquivo.write(f"{produto}, {qtd}, {preco}\n")

        arquivo.write("\n")  #Adiciona uma linha em branco após o cadastro do pedido

    novo_pedido = input("Deseja cadastrar um novo pedido? (s/n) ")
    if novo_pedido.lower() == "s":
        cadastrar_pedido()
    else:
        print("Arquivos gravados com sucesso!")
        calcular_total_pedidos()

def calcular_total_pedidos():
    with open("pedidos.txt", "r") as arquivo_pedidos, open("total_pedidos.txt", "w") as arquivo_total:
        total_por_cliente = {}

        for linha in arquivo_pedidos:
            if linha.strip(): #Ignora linhas em branco
                if linha.endswith("\n"): #Remove a quebra de linha do final da linha
                    linha = linha[:-1]

                if "," in linha: #Verifica se é uma linha de produto ou de nome de cliente
                    produto, qtd, preco = linha.split(",") 
                    total = int(qtd) * float(preco)
                    total_por_cliente[nome].append(total)
                else:
                    nome = linha
                    total_por_cliente[nome] = []

        for nome, total_por_pedido in total_por_cliente.items():
            total_pedido = sum(total_por_pedido)
            arquivo_total.write(f"{nome.strip()} - R$ {total_pedido:.2f}\n")

cadastrar_pedido()
