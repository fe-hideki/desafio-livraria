import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class LivroRepository {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("cursoPCV");


    public List<Livro> listarTodosLivros() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT l FROM Livro l", Livro.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Impresso> listarLivrosImpressos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT i FROM Impresso i", Impresso.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Eletronico> listarLivrosEletronicos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT e FROM Eletronico e", Eletronico.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public Livro pegarLivroPorId(Integer id){
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT l FROM Livro l WHERE id = " + id, Livro.class)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    public Long qtdLivrosImpressos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT COUNT(i) FROM Impresso i", Long.class)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    public Long qtdLivrosEletronicos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT COUNT(e) FROM Eletronico e", Long.class)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    public void salvar(Livro livro) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(livro);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public static void fecharFactory() {
        if (emf.isOpen()) {
            emf.close();
        }
    }
}
