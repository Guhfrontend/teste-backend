package com.fattocs.tecnico.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.websocket.OnMessage;

@Entity
@Table(name = "tb_tarefas")
public class Tarefa {
    //TODO Nome da tarefa, Custo (R$), Data limite, Ordem de apresentação (campo numérico, não repetido, que servirá para ordenar os registros na tela)


    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O nome da tarefa deve ser informado")
    @Column(unique = true)
    private String nome;

    @NotNull
    private Double custo;

    @NotNull
    private String dataLimite;

    @Column(unique = true)
    private Integer ordem;

    public Tarefa() {
    }

    public Tarefa(Long id, String nome, Double custo, String dataLimite, Integer ordem) {
        this.id = id;
        this.nome = nome;
        this.custo = custo;
        this.dataLimite = dataLimite;
        this.ordem = ordem;
    }

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

    public Double getCusto() {
        return custo;
    }

    public void setCusto(Double custo) {
        this.custo = custo;
    }

    public String getDataLimite() {
        return dataLimite;
    }

    public void setDataLimite(String dataLimite) {
        this.dataLimite = dataLimite;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }
}
