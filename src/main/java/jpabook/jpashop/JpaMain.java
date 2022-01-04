package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        //jpa의 모든 데이터변경은 트랜잭션안에서 실행되어야 한다.
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{

            //Member findMember = em.find(Member.class, 1L); 조회
            //em.remove(findMember); 삭제
            //findMember.setName("HelloJPA"); jpa가 관리해서 알아서 업데이트 해줌

            //JPQL
/*            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(5)
                    .setMaxResults(8)
                    .getResultList();*/

            Order order = em.find(Order.class, 1L);
            Long memberId = order.getMemberId();

            Member member = em.find(Member.class, memberId);
            //객체 그래프 탐색이 불가능 (연관관계 매핑 필요)


        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
