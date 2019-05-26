package Model;

import org.jetbrains.annotations.Contract;

public class Movimentacao {
    private int id_movimentacao;
    private String dataEHora;
    private String movimentacaoType;
    //verificar quando model do produto estiver implementada.
    private int saldo;
    private int id_produto;
    private int id_funcionario;

    @Contract(pure = true)
    public Movimentacao(){

    }

    public int getId_movimentacao(){
        return id_movimentacao;
    }
    public void setId_movimentacao(int id_movimentacao){
        this.id_movimentacao = id_movimentacao;
    }

    public String getDataEHora(){
        return dataEHora;
    }
    public void setDataEHora(String dataEHora){
        this.dataEHora = dataEHora;
    }

    public String getMovimentacaoType(){
        return movimentacaoType;
    }
    public void setMovimentacaoType(String movimentacaoType){
        this.movimentacaoType = movimentacaoType;
    }

    public int getSaldo(){
        return saldo;
    }
    public void setSaldo(int saldo){
        this.saldo = saldo;
    }

    private int getId_produto(){
        return id_produto;
    }
    private void setId_produto(int id_produto){
        this.id_produto = id_produto;
    }

    public int getId_funcionario(){
        return id_funcionario;
    }
    public void setId_funcionario(int id_funcionario){
        this.id_funcionario = id_funcionario;
    }
}




/*


    public String getStatus_aprovacao() {
        return status_aprovacao;
    }

    public void setStatus_aprovacao(String status_aprovacao) {
        this.status_aprovacao = status_aprovacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

*/