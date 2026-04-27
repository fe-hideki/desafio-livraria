import java.util.List;
import java.util.Scanner;

public class LivrariaVirtual {
    private final int MAX_IMPRESSOS = 10;
    private final int MAX_ELETRONICOS = 20;
    private final int MAX_VENDAS = 50;

    private int numImpressos;
    private int numEletronicos;
    private int numVendas;

    private Impresso[] impressos;
    private Eletronico[] eletronicos;
    private Venda[] vendas;

    LivroRepository livroRepository= new LivroRepository();
    VendaRepository vendaRepository = new VendaRepository();

    public LivrariaVirtual(int numImpressos, int numEletronicos, int numVendas, Impresso[] impressos, Eletronico[] eletronicos, Venda[] vendas) {
        this.numImpressos = numImpressos;
        this.numEletronicos = numEletronicos;
        this.numVendas = numVendas;
        this.impressos = impressos;
        this.eletronicos = eletronicos;
        this.vendas = vendas;
    }

    public LivrariaVirtual() {
    }

    public int getMAX_IMPRESSOS() {
        return MAX_IMPRESSOS;
    }

    public int getMAX_ELETRONICOS() {
        return MAX_ELETRONICOS;
    }

    public int getMAX_VENDAS() {
        return MAX_VENDAS;
    }

    public int getNumImpressos() {
        return numImpressos;
    }

    public void setNumImpressos(int numImpressos) {
        this.numImpressos = numImpressos;
    }

    public int getNumVendas() {
        return numVendas;
    }

    public void setNumVendas(int numVendas) {
        this.numVendas = numVendas;
    }

    public int getNumEletronicos() {
        return numEletronicos;
    }

    public void setNumEletronicos(int numEletronicos) {
        this.numEletronicos = numEletronicos;
    }

    public Impresso[] getImpressos() {
        return impressos;
    }

    public void setImpressos(Impresso[] impressos) {
        this.impressos = impressos;
    }

    public Venda[] getVendas() {
        return vendas;
    }

    public void setVendas(Venda[] vendas) {
        this.vendas = vendas;
    }

    public Eletronico[] getEletronicos() {
        return eletronicos;
    }

    public void setEletronicos(Eletronico[] eletronicos) {
        this.eletronicos = eletronicos;
    }

    public void cadastrarLivro(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Qual o tipo de livro? (Impresso/Eletronico/Ambos)");
        String tipo = scanner.nextLine().toLowerCase();

        if (!tipo.equals("impresso") && !tipo.equals("eletronico") && !tipo.equals("ambos")) {
            System.out.println("Tipo inválido.");
            return;
        }

        if (tipo.equals("impresso") || tipo.equals("ambos")) {
            if (livroRepository.qtdLivrosImpressos() >= MAX_IMPRESSOS) {
                System.out.println("Erro: Limite de livros impressos atingido (" + MAX_IMPRESSOS + ").");
                return;
            }
        }

        if (tipo.equals("eletronico") || tipo.equals("ambos")) {
            if (livroRepository.qtdLivrosEletronicos() >= MAX_ELETRONICOS) {
                System.out.println("Erro: Limite de livros eletrônicos atingido (" + MAX_ELETRONICOS + ").");
                return;
            }
        }

        System.out.println("Informe o Título:");
        String titulo = scanner.nextLine();

        System.out.println("Informe os Autores:");
        String autores = scanner.nextLine();

        System.out.println("Informe a Editora:");
        String editora = scanner.nextLine();

        System.out.println("Informe o Preço (R$):");
        float preco = scanner.nextFloat();
        scanner.nextLine();

        if (tipo.equals("impresso")) {

            System.out.println("Informe o Frete (R$):");
            float frete = scanner.nextFloat();

            System.out.println("Informe o Estoque de Livros:");
            int estoque = scanner.nextInt();
            scanner.nextLine();

            Impresso impresso = new Impresso(titulo, autores, editora, preco, frete, estoque);
            livroRepository.salvar(impresso);

        } else if (tipo.equals("eletronico")) {

            System.out.println("Informe o Tamanho (MB):");
            int tamanho = scanner.nextInt();
            scanner.nextLine();

            Eletronico eletronico = new Eletronico(titulo, autores, editora, preco, tamanho);
            livroRepository.salvar(eletronico);

        } else {

            System.out.println("Informe o Frete (R$):");
            float frete = scanner.nextFloat();

            System.out.println("Informe o Estoque:");
            int estoque = scanner.nextInt();

            System.out.println("Informe o Tamanho (MB):");
            int tamanho = scanner.nextInt();

            livroRepository.salvar(new Impresso(titulo, autores, editora, preco, frete, estoque));
            livroRepository.salvar(new Eletronico(titulo, autores, editora, preco, tamanho));
        }
        System.out.println("Livro(s) cadastrado(s) com sucesso!");
    }


    public void realizarVenda(){

        if (vendaRepository.qtdVendas() >= MAX_VENDAS) {
            System.out.println("Erro: Limite de vendas atingido (" + MAX_VENDAS + ").");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o nome do cliente: ");
        String nome = scanner.nextLine();

        Venda venda = new Venda(nome);

        System.out.println("Informe a quantidade de livros: ");
        int qtdLivros = scanner.nextInt();
        scanner.nextLine();

        for (int i = 1; i <= qtdLivros; i++){

            System.out.println("\nEscolha do livro " + i +": ");
            System.out.println("Informe o tipo de livro (Impresso/Eletronico): ");
            String tipo = scanner.nextLine().toLowerCase();

            if (!tipo.equals("impresso") && !tipo.equals("eletronico")) {
                System.out.println("Tipo inválido.");
                return;
            }

            if (tipo.equals("impresso")){

                listarLivrosImpressos();
                System.out.println("Digite o ID do livro que você quer escolher");
                Integer id = scanner.nextInt();
                scanner.nextLine();

                Livro livro = livroRepository.pegarLivroPorId(id);

                venda.addLivro(livro);


            } else {

                listarLivrosEletronicos();
                System.out.println("Digite o ID do livro que você quer escolher");
                Integer id = scanner.nextInt();
                scanner.nextLine();

                Livro livro = livroRepository.pegarLivroPorId(id);

                venda.addLivro(livro);

            }

        }
        vendaRepository.salvar(venda);
        System.out.println("Venda cadastrada com sucesso!");
    }

    public void listarLivrosImpressos(){
        List<Impresso> impressos = livroRepository.listarLivrosImpressos();

        System.out.println("============ Lista Livros Impressos ============");
        for (Impresso i : impressos) {
            System.out.println("ID: " + i.getId());
            System.out.println("Título: " + i.getTitulo());
            System.out.println("Autores: " + i.getAutores());
            System.out.println("Editora: " + i.getEditora());
            System.out.println("Preço: " + i.getPreco());

            System.out.println("Frete: " + i.getFrete());
            System.out.println("Estoque: " + i.getEstoque());
            System.out.println("------------------------------------------------");
        }
    }

    public void listarLivrosEletronicos(){
        List<Eletronico> eletronicos = livroRepository.listarLivrosEletronicos();

        System.out.println("============ Lista Livros Eletronicos ============");
        for (Eletronico e : eletronicos) {
            System.out.println("ID: " + e.getId());
            System.out.println("Título: " + e.getTitulo());
            System.out.println("Autores: " + e.getAutores());
            System.out.println("Editora: " + e.getEditora());
            System.out.println("Preço: " + e.getPreco());

            System.out.println("Tamanho: " + e.getTamanho());
            System.out.println("------------------------------------------------");
        }
    }

    public void listarLivros(){

        List<Livro> livros = livroRepository.listarTodosLivros();

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }

        System.out.println("=========== LISTA DE LIVROS ===========");

        for (Livro l : livros) {

            System.out.println("ID: " + l.getId());
            System.out.println("Título: " + l.getTitulo());
            System.out.println("Autores: " + l.getAutores());
            System.out.println("Editora: " + l.getEditora());
            System.out.println("Preço: R$ " + l.getPreco());

            if (l instanceof Impresso) {
                Impresso i = (Impresso) l;
                System.out.println("Tipo: Impresso");
                System.out.println("Frete: R$ " + i.getFrete());
                System.out.println("Estoque: " + i.getEstoque());

            } else if (l instanceof Eletronico) {
                Eletronico e = (Eletronico) l;
                System.out.println("Tipo: Eletrônico");
                System.out.println("Tamanho: " + e.getTamanho() + " MB");
            }

            System.out.println("---------------------------------------");
        }

    }

    public void listarVendas(){
        List<Venda> vendas = vendaRepository.listarTodas();

        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda realizada.");
            return;
        }

        System.out.println("\n=========== LISTA DE VENDAS ===========");

        for (Venda v : vendas) {

            System.out.println("Número da Venda: " + v.getNumero());
            System.out.println("Cliente: " + v.getCliente());
            System.out.println("Valor Total: R$ " + v.getValor());

            System.out.println("Livros da Venda:");

            for (Livro l : v.getLivros()) {
                System.out.println(" - " + l.getTitulo() +
                        " | Editora: " + l.getEditora() +
                        " | Preço: R$ " + l.getPreco());
            }

            System.out.println("--------------------------------------");
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LivrariaVirtual livraria = new LivrariaVirtual();
        int opcao;

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Cadastrar Livro ");
            System.out.println("2. Realizar Venda");
            System.out.println("3. Listar Livros ");
            System.out.println("4. Listar Vendas ");
            System.out.println("5. Sair do Programa ");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            if(opcao == 1){
                livraria.cadastrarLivro();
            } else if (opcao == 2){
                livraria.realizarVenda();
            } else if (opcao == 3){
                livraria.listarLivros();
            } else if (opcao == 4){
                livraria.listarVendas();
            }

        } while (opcao != 5);

        System.out.println("Encerrando programa...");
        VendaRepository.fecharFactory();
        LivroRepository.fecharFactory();
        scanner.close();
    }


}
