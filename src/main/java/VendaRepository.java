import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class VendaRepository {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("cursoPCV");

    public void salvar(Venda venda) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(venda);
            tx.commit();

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }


    }

    public Long qtdVendas() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT COUNT(v) FROM Venda v", Long.class)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    public List<Venda> listarTodas() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                    "SELECT DISTINCT v FROM Venda v LEFT JOIN FETCH v.livros",
                    Venda.class
            ).getResultList();
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
