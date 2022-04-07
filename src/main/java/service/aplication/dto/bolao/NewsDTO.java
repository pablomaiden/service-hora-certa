package service.aplication.dto.bolao;

public class NewsDTO {
	
	private String id;
	private String titulo;
	private String descricao;
	private String dataNotificacao;
	private String idRelNews;
	private String pathImagem;
	private boolean notificacaoLida;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getDataNotificacao() {
		return dataNotificacao;
	}
	public void setDataNotificacao(String dataNotificacao) {
		this.dataNotificacao = dataNotificacao;
	}
	
	public String getPathImagem() {
		return pathImagem;
	}
	
	public void setPathImagem(String pathImagem) {
		this.pathImagem = pathImagem;
	}
	
	public String getIdRelNews() {
		return idRelNews;
	}
	
	public void setIdRelNews(String idRelNews) {
		this.idRelNews = idRelNews;
	}
	public boolean isNotificacaoLida() {
		return notificacaoLida;
	}
	public void setNotificacaoLida(boolean notificacaoLida) {
		this.notificacaoLida = notificacaoLida;
	}
	

}
