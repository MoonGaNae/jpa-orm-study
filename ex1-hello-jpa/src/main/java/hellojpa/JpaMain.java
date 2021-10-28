package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

//        insert(emf);
//        select(emf);
//        update(emf);
//        select(emf);
//        delete(emf);
        JPQLSelect(emf);

        emf.close();
    }
    // 생성
    public static void insert(EntityManagerFactory emf){
        EntityManager em = emf.createEntityManager();

        // code
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Member member = new Member();
            member.setId(2L);
            member.setName("HelloB");
            em.persist(member);

            tx.commit();
        } catch(Exception e) {
            tx.rollback();
        } finally{
            em.close();
        }
    }
    // 조회
    public static void select(EntityManagerFactory emf){
        EntityManager em = emf.createEntityManager();

        // code
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Member findMember = em.find(Member.class, 1L);

            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.name = " + findMember.getName());

            tx.commit();
        } catch(Exception e) {
            tx.rollback();
        } finally{
            em.close();
        }
    }
    // 수정
    public static void update(EntityManagerFactory emf){
        EntityManager em = emf.createEntityManager();

        // code
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Member findMember = em.find(Member.class, 1L);

            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.name = " + findMember.getName());

            findMember.setName("HelloJPA");
            tx.commit();
        } catch(Exception e) {
            tx.rollback();
        } finally{
            em.close();
        }
    }
    // 삭제
    public static void delete(EntityManagerFactory emf){
        EntityManager em = emf.createEntityManager();
        // code
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Member findMember = em.find(Member.class, 1L);
            em.remove(findMember);

            tx.commit();
        } catch(Exception e) {
            tx.rollback();
        } finally{
            em.close();
        }
    }

    public static void JPQLSelect(EntityManagerFactory emf){
        EntityManager em = emf.createEntityManager();
        // code
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(5)
                    .getResultList();

            for(Member member : result){
                System.out.println("member.name = " + member.getName());
            }

            tx.commit();
        } catch(Exception e) {
            tx.rollback();
        } finally{
            em.close();
        }
    }
}
