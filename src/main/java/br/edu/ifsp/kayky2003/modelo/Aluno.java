package br.edu.ifsp.kayky2003.modelo;

import jakarta.persistence.*;


import java.math.BigDecimal;

import static java.lang.Math.round;

@Entity
@Table(name = "aluno")

public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String ra;
    private String email;
    private BigDecimal nota1;
    private BigDecimal nota2;
    private BigDecimal nota3;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getNota1() {
        return nota1;
    }

    public void setNota1(BigDecimal nota1) {
        this.nota1 = nota1;
    }

    public BigDecimal getNota2() {
        return nota2;
    }

    public void setNota2(BigDecimal nota2) {
        this.nota2 = nota2;
    }

    public BigDecimal getNota3() {
        return nota3;
    }

    public void setNota3(BigDecimal nota3) {
        this.nota3 = nota3;
    }

    public Aluno(String nome, String ra, String email, BigDecimal nota1, BigDecimal nota2, BigDecimal nota3) {
        this.nome = nome;
        this.ra = ra;
        this.email = email;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
    }

    public Aluno() {
    }

    public double calcularMedia(){
        BigDecimal soma = BigDecimal.valueOf(this.getNota1().doubleValue() + this.getNota2().doubleValue() + this.getNota3().doubleValue());
        Double media = (soma.doubleValue()) / 3;
        return  media;
    }
    public String VerificaPassou(){
        String resultado;
        Double media = this.calcularMedia();
        if (media < 4){
            resultado = " Reprovado!";
        } else if (media == 4) {
            resultado = " Recuperação!";
        }
        else
            resultado = " Aprovado!";

        return resultado;
    }
    public void mostrarDados(){
        System.out.println("Aluno: " + getId() +"\n"+
                    "- Nome: " + getNome() +"\n"+
                    "- RA: " + getRa() +"\n"+
                    "- Email: " + getEmail()  +"\n"+
                    "- Nota 1: " + getNota1() +"\n"+
                    "- Nota 2:" + getNota2() +"\n"+
                    "- Nota 3:" + getNota3() +"\n"+
                    "- Media: " + this.calcularMedia() +"\n"+
                    "- Status: " +  this.VerificaPassou()
        );
    }

}
