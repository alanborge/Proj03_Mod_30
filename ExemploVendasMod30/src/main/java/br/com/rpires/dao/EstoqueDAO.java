package br.com.rpires.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.rpires.dao.generic.GenericDAO;
import br.com.rpires.domain.Estoque;
import br.com.rpires.domain.Produto;

public class EstoqueDAO extends GenericDAO<Estoque, Long> implements IEstoqueDAO {
		
	public EstoqueDAO() {
		super();	
	}

	@Override
	public Class<Estoque> getTipoClasse() {
		return Estoque.class;
	}

	@Override
	public void atualiarDados(Estoque entity, Estoque entityCadastrado) {
		entityCadastrado.setId_produto_fk(entity.getId_produto_fk());
		entityCadastrado.setQuantidade_atual(entity.getQuantidade_atual());
		entityCadastrado.setQuantidade_entrada(entity.getQuantidade_entrada());
		entityCadastrado.setData_entrada(entity.getData_entrada());
		
	}

	@Override
	protected String getQueryInsercao() {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO TB_ESTOQUE");
		sb.append("(ID, ID_PRODUTO_FK, CODIGO, QUANTIDADE_ATUAL, QUANTIDADE_ENTRADA, DATA_ENTRADA)");
		sb.append("VALUES (nextval('sq_estoque'),?,?,?,?,?)");
		return sb.toString();
	}
	
	@Override
	protected void setParametrosQueryInsercao(PreparedStatement stmInsert, Estoque entity) throws SQLException {
		stmInsert.setLong(1, entity.getId_produto_fk());
		stmInsert.setLong(2, entity.getCodigo());
		stmInsert.setInt(3, entity.getQuantidade_atual());
		stmInsert.setInt(4, entity.getQuantidade_entrada());
		stmInsert.setDate(5, entity.getData_entrada());
		
	}

	@Override
	protected String getQueryExclusao() {
		return "DELETE FROM TB_ESTOQUE WHERE CODIGO = ?";
	}
	
	@Override
	protected void setParametrosQueryExclusao(PreparedStatement stmDelete, Long valor) throws SQLException {
		stmDelete.setLong(1, valor);
		
	}

	@Override
	protected String getQueryAtualizacao() {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE TB_ESTOQUE E");
		sb.append("SET QUANTIDADE_ATUAL = QUANTIDADE_ATUAL - ?,");
		sb.append("P.VALOR = P.VALOR -?");
		sb.append("FROM TB_PRODUTO P");
		sb.append("WHERE E.ID_PRODUTO_FK = P.ID");
		sb.append("AND E.ID_PRODUTO_FK = ?");
		return sb.toString();
	}
	
	@Override
	protected void setParametrosQueryAtualizacao(PreparedStatement stmUpdate, Estoque entity) throws SQLException {
		Produto ent = new Produto();
		stmUpdate.setLong(1, entity.getQuantidade_atual());
		stmUpdate.setBigDecimal(2, ent.getValor());
		stmUpdate.setLong(4, entity.getId_produto_fk());
	}

	@Override
	protected void setParametrosQuerySelect(PreparedStatement stmSelect, Long valor) throws SQLException {
		stmSelect.setLong(1, valor);
		
	}

}
