package Model;

import org.jetbrains.annotations.Contract;

public class Funcionario {
    private int id_funcionario;
    private String nome;
    private int cpf;
    private String cargo;
    private int cep;
    private char nivel_acesso;
    private int telefone;
    private String email;

    @Contract(pure = true)
    public Funcionario() {

    }

    public int getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    public char getNivel_acesso() {
        return nivel_acesso;
    }

    public void setNivel_acesso(char nivel_acesso) {
        this.nivel_acesso = nivel_acesso;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
