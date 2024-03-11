package br.edu.ifsp.kayky2003.test;

import br.edu.ifsp.kayky2003.dao.AlunoDao;
import br.edu.ifsp.kayky2003.modelo.Aluno;
import br.edu.ifsp.kayky2003.util.JPAUtil;
import jakarta.persistence.EntityManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class CadastroDeAlunos {

    public static void main(String[] args) {
        SpringApplication.run(CadastroDeAlunos.class,args);

        // Para popular a base permita o comando abaixo uma vez para criar as tabelas e gerar os items do banco.
        cadastrarBaseAluno();

        int opcao;
        do {
            System.out.println("** CADASTRO DE ALUNOS **");
            System.out.println("1 - Cadastrar aluno");
            System.out.println("2 - Excluir aluno");
            System.out.println("3 - Alterar aluno");
            System.out.println("4 - Buscar aluno pelo nome");
            System.out.println("5 - Listar alunos (com status de aprovação)");
            System.out.println("6 - FIM");
            System.out.print("Escolha uma opção: ");

            Scanner scanner = new Scanner(System.in);
            opcao = scanner.nextInt();
            EntityManager em = JPAUtil.getEntityManager();
            AlunoDao alunoDao = new AlunoDao(em);

            scanner.nextLine(); // Limpar o buffer do scanner

            switch (opcao) {
                case 1:
                    scanner.nextLine();
                    System.out.println("Digite um nome: ");
                    String nomePerguntado = scanner.nextLine();
                    System.out.println("Digite um RA: ");
                    String raperguntado = scanner.nextLine();
                    System.out.println("Digite um email: ");
                    String emailPerguntado = scanner.nextLine();
                    System.out.println("Digite a nota1: ");
                    double nota1Perguntada = scanner.nextDouble();
                    System.out.println("Digite a nota2: ");
                    double nota2Perguntada = scanner.nextDouble();
                    System.out.println("Digite a nota3: ");
                    double  nota3Perguntada = scanner.nextDouble();

                    Aluno aluno = new Aluno(nomePerguntado,raperguntado,emailPerguntado,new BigDecimal(nota1Perguntada),new BigDecimal(nota2Perguntada),
                            new BigDecimal(nota3Perguntada));

                    System.out.println("Descrição do aluno à ser cadastrado: ");
                    aluno.mostrarDados();
                    em.getTransaction().begin();
                    alunoDao.cadastrar(aluno);
                    em.getTransaction().commit();
                    System.out.println("Aluno Cadastrado com sucesso");
                    break;
                case 2:
                    System.out.println("Digite um id: ");
                    Long id2 = scanner.nextLong();
                    em.getTransaction().begin();
                    alunoDao.deletePorId(alunoDao.buscarPorId(id2).getId());
                    em.getTransaction().commit();
                    System.out.println("A base foi atualizada com sucesso!");

                    break;
                case 3:
                    System.out.println("Digite um id: ");
                    Long id = scanner.nextLong();
                    em.getTransaction().begin();
                    alunoDao.updateAluno(alunoDao.buscarPorId(id).getId());
                    em.getTransaction().commit();
                    System.out.println("Reinicie o programa, a base foi atualizada!");
                    break;
                case 4:
                    System.out.println("Digite um nome para ser pesquisado: ");
                    String nome = scanner.nextLine();
                    Aluno a = alunoDao.buscar1PorNome(nome);
                    if (a == null){
                        System.out.println("Aluno não encontrado!");
                    }
                    else {
                        a.mostrarDados();
                    }
                    break;
                case 5:
                    List<Aluno> todos = alunoDao.buscarTodos();
                    if(todos.isEmpty()){
                        System.out.println("Lista Vazia");
                    }
                    else{
                        for (Aluno al: todos) {
                            al.mostrarDados();
                        }
                    }

                    break;
                case 6:
                    System.out.println("Programa encerrado.");
                    em.close();
                    System. exit(0);
                    break;

                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }
        } while (opcao != 6);
    }

    private static void cadastrarBaseAluno(){
        Aluno aluno1 = new Aluno("Kayky","SC3030474","email",
                new BigDecimal("4"),new BigDecimal("6"),new BigDecimal("8"));
        Aluno aluno2 = new Aluno("Joaquim","SC3030474","email",
                new BigDecimal("6"),new BigDecimal("9"),new BigDecimal("7"));
        Aluno aluno3 = new Aluno("Douglas","SC3030979","email",
                new BigDecimal("0"),new BigDecimal("2"),new BigDecimal("4"));
        Aluno aluno4 = new Aluno("Adriana","SC4040758","email",
                new BigDecimal("0"),new BigDecimal("2"),new BigDecimal("4"));
        Aluno aluno5 = new Aluno("Pedro","SC3030132","email",
                new BigDecimal("0"),new BigDecimal("5"),new BigDecimal("7"));
        Aluno aluno6 = new Aluno("Mariana","SC3030478","email",
                new BigDecimal("0"),new BigDecimal("0"),new BigDecimal("0"));
        Aluno aluno7 = new Aluno("Joana","SC3030478","email",
                new BigDecimal("5"),new BigDecimal("5"),new BigDecimal("5"));


        EntityManager em = JPAUtil.getEntityManager();

        AlunoDao alunoDao = new AlunoDao(em);

        em.getTransaction().begin();

        alunoDao.cadastrar(aluno1);
        alunoDao.cadastrar(aluno2);
        alunoDao.cadastrar(aluno3);
        alunoDao.cadastrar(aluno4);
        alunoDao.cadastrar(aluno5);
        alunoDao.cadastrar(aluno6);
        alunoDao.cadastrar(aluno7);


        em.getTransaction().commit();
        em.close();
    }

}


