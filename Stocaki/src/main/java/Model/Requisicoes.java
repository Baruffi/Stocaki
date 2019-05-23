package Model;

import java.util.ArrayList;
import java.util.List;

public class Requisicoes {
    private List<Requisicao> requisicoes;

    public Requisicoes() {
        this.requisicoes = new ArrayList<Requisicao>();
    }

    public List<Requisicao> getRequisicoes() {
        return requisicoes;
    }

    public void setRequisicoes(List<Requisicao> requisicoes) {
        this.requisicoes = requisicoes;
    }
}
