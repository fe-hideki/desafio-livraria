import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "venda")
public class Venda {

    private static int numVendas = 1;

    @Id
    @Column(name = "numero")
    private int numero;

    @Column(name = "cliente", length = 100)
    private String cliente;

    @Column(name = "valor")
    private double valor;

    @ManyToMany
    @JoinTable(
            name = "venda_livro",
            joinColumns = @JoinColumn(name = "numero_venda"),
            inverseJoinColumns = @JoinColumn(name = "id_livro")
    )

    private List<Livro> livros = new ArrayList<>();

    public Venda() {
    }

    public Venda(String cliente) {
        numVendas++;
        this.numero = numVendas;
        this.cliente = cliente;
        this.valor = 0.0;
    }

    public void addLivro(Livro l) {
        livros.add(l);
        this.valor += l.getPreco();
        System.out.println("Livro \"" + l.getTitulo() + "\" adicionado à venda.");
    }

    public void listarLivros() {
        System.out.println("===== Livros da Venda " + numero + " =====");
        for (Livro livro : livros) {
            System.out.println(livro);
            System.out.println("----------------------");
        }
    }

    public static int getNumVendas() {
        return numVendas;
    }

    public static void setNumVendas(int numVendas) {
        Venda.numVendas = numVendas;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}