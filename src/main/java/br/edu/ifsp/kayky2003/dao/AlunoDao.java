package br.edu.ifsp.kayky2003.dao;

import br.edu.ifsp.kayky2003.modelo.Aluno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class AlunoDao {

    private EntityManager em;

    public AlunoDao(EntityManager em){this.em=em;}

    public void cadastrar(Aluno aluno){this.em.persist(aluno);}

    public Aluno buscarPorId(Long id){return em.find(Aluno.class,id);}

    public List<Aluno> buscarTodos(){
        String jpql = "SELECT a FROM Aluno a";
        return em.createQuery(jpql,Aluno.class).getResultList();
    }
    public List<Aluno> buscarAlunosPorNome(String nome){

        String jpql = "SELECT a FROM Aluno a WHERE a.nome = ?1";
        return em.createQuery(jpql,Aluno.class)
                .setParameter(1,nome).getResultList();
    }
    public Aluno buscar1PorNome(String nome){
        String jpql = "SELECT a FROM Aluno a WHERE a.nome = :n";
        return em.createQuery(jpql,Aluno.class).setParameter("n",nome).getSingleResult();
    }
    public void  updateAluno(Long id){
        Scanner leitorTeclado = new Scanner(System.in);
        System.out.println("Digite um nome: ");
        String nomePerguntado = leitorTeclado.nextLine();
        System.out.println("Digite um email: ");
        String emailPerguntado = leitorTeclado.nextLine();
        System.out.println("Digite a nota1: ");
        double nota1Perguntada = leitorTeclado.nextDouble();
        System.out.println("Digite a nota2: ");
        double nota2Perguntada = leitorTeclado.nextDouble();
        System.out.println("Digite a nota3: ");
        double  nota3Perguntada = leitorTeclado.nextDouble();
        String jpql = "UPDATE Aluno a SET a.nome = :nN, a.email = :nE, a.nota1 = :nN1, a.nota2 = :nN2, a.nota3 = :nN3 WHERE a.id = :id";
        Query query = em.createQuery(jpql);
        query.setParameter("nN", nomePerguntado);
        query.setParameter("nE",emailPerguntado);
        query.setParameter("nN1",new BigDecimal(nota1Perguntada));
        query.setParameter("nN2",new BigDecimal(nota2Perguntada));
        query.setParameter("nN3",new BigDecimal( nota3Perguntada));
        query.setParameter("id", id);
        int verifica = query.executeUpdate();
        if (verifica>0)
        System.out.println("Informações alteradas");
        else
            System.out.println("Não tem informações para serem alteradas");
    }
    public void deletePorId(Long id){
        String jpql = "Delete FROM Aluno a WHERE a.id = :i";
        Query query = em.createQuery(jpql);
        query.setParameter("i", id);
        int confirmação = query.executeUpdate();
        if (confirmação > 0){
            System.out.println("Aluno apagado");
        }
        else
            System.out.println("Aluno não existe");
    }

}