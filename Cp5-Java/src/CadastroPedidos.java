import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CadastroPedidos {

    public static void main(String[] args) throws IOException {
        
        Scanner input = new Scanner(System.in);
        ArrayList<String> pedidos = new ArrayList<>();
        String nomeCliente;
        boolean cadastrarNovoPedido = true;
        
     // Loop para cadastrar novos pedidos
while (cadastrarNovoPedido) {
            
            System.out.print("Digite o nome do cliente: ");
            nomeCliente = input.nextLine();
            pedidos.add(nomeCliente);
            
            System.out.print("Digite a quantidade de produtos que deseja cadastrar: ");
            int qtdProdutos = input.nextInt();
            input.nextLine(); 
            
         // Loop para cadastrar os produtos do pedido
            for (int i = 0; i < qtdProdutos; i++) {
                
                System.out.print("Digite o nome do produto: ");
                String nomeProduto = input.nextLine();
                
                System.out.print("Digite a quantidade do produto: ");
                int qtdProduto = input.nextInt();
                input.nextLine(); 
                
                System.out.print("Digite o preco unitario do produto: ");
                double precoProduto = input.nextDouble();
                input.nextLine(); 
                
             // Cria um novo objeto Pedido com as informações do produto e adiciona a linha correspondente à lista de pedidos
                Pedido pedido = new Pedido(nomeProduto, qtdProduto, precoProduto);
                String pedidoStr = pedido.getNomeProduto() + "," + pedido.getQuantidade() + "," + pedido.getPrecoUnitario();
                pedidos.add(pedidoStr);
            }
            
            pedidos.add(""); //Pular linha entre os pedidos
            System.out.print("Deseja cadastrar um novo pedido? (S/N): ");
            String resposta = input.nextLine();
            cadastrarNovoPedido = resposta.equalsIgnoreCase("S");
        }
        
        input.close();
        
        //Grava os pedidos em um arquivo
        FileWriter writer = new FileWriter(new File("pedidos.txt"));
        
        for (String pedido : pedidos) {
            writer.write(pedido + "\n");
        }
        
        writer.close();
        
        //Lê os pedidos do arquivo e calcula o total
        Scanner fileReader = new Scanner(new File("pedidos.txt"));
        ArrayList<String> clientes = new ArrayList<>();
        ArrayList<Double> totais = new ArrayList<>();
        
        while (fileReader.hasNextLine()) {
            
            String linha = fileReader.nextLine();
            
            if (!linha.isEmpty()) {
            	// Se a linha não tem vírgulas, é o nome do cliente
                if (linha.indexOf(",") == -1) {
                    
                    clientes.add(linha);
                    totais.add(0.0);
                    
                } else {
                    
                    String[] partes = linha.split(",");
                    int qtdProduto = Integer.parseInt(partes[1]);
                    double precoProduto = Double.parseDouble(partes[2]);
                    
                 // Encontra o último cliente adicionado e atualiza o total do pedido
                    int indiceUltimoCliente = clientes.size() - 1;
                    double totalAtual = totais.get(indiceUltimoCliente);
                    totais.set(indiceUltimoCliente, totalAtual + (qtdProduto * precoProduto));
                }
            }
        }
        
        fileReader.close();
        
     // Grava os totais dos pedidos em um arquivo
        FileWriter writerTotais = new FileWriter(new File("total_pedidos.txt"));
        
        for (int i = 0; i < clientes.size(); i++) {
            writerTotais.write(clientes.get(i) + " - R$ " + totais.get(i) + "\n");
        }
        
        writerTotais.close();
        
        System.out.println("Arquivos gravados com sucesso!");
    }
}
