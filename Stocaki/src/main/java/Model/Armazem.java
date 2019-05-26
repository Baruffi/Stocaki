package Model;

import org.jetbrains.annotations.Contract;

public class Armazem {
    private int id_armazem;
    private String nome;
    private int id_estoque;

    @Contract(pure = true)
    Armazem() {

    }

    public int getId_armazem() {
        return id_armazem;
    }

    public void setId_armazem(int id_armazem) {
        this.id_armazem = id_armazem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId_estoque() {
        return id_estoque;
    }

    public void setId_estoque(int id_estoque) {
        this.id_estoque = id_estoque;
    }
}
