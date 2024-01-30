package br.com.rpires.domain;

import java.sql.Date;

import anotacao.ColunaTabela;
import anotacao.Tabela;
import br.com.rpires.dao.Persistente;

@Tabela("TB_ESTOQUE")
public class Estoque implements Persistente {
	
	@ColunaTabela(dbName = "id", setJavaName = "setId")
	private Long id;
    
	@ColunaTabela(dbName = "id_produto_fk", setJavaName = "setId_produto_fk")
	private Long id_produto_fk;
	
	@ColunaTabela(dbName = "codigo", setJavaName = "setCodigo")
	private Long codigo;
	
	@ColunaTabela(dbName = "quantidade_atual", setJavaName = "setQuantidade_atual")
	private Integer quantidade_atual;
	
	@ColunaTabela(dbName =  "quantidade_entrada", setJavaName = "setQuantidade_entrada")
	private Integer quantidade_entrada;
	
	@ColunaTabela(dbName = "data_entrada", setJavaName = "setData_entrada")
	private Date data_entrada;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId_produto_fk() {
		return id_produto_fk;
	}

	public void setId_produto_fk(Long id_produto_fk) {
		this.id_produto_fk = id_produto_fk;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public int getQuantidade_atual() {
		return quantidade_atual;
	}

	public void setQuantidade_atual(int quantidade_atual) {
		this.quantidade_atual = quantidade_atual;
	}

	public int getQuantidade_entrada() {
		return quantidade_entrada;
	}

	public void setQuantidade_entrada(int quantidade_entrada) {
		this.quantidade_entrada = quantidade_entrada;
	}

	public Date getData_entrada() {
		return data_entrada;
	}

	public void setData_entrada(Date data_entrada) {
		this.data_entrada = data_entrada;
	}
	
	

}
