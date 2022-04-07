package service.aplication.dto.bolao.api;

public class MatchesDTO {
	
	private String id;
	private String matchday;
	private String status;
	private ScoreDTO score;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMatchday() {
		return matchday;
	}

	public void setMatchday(String matchday) {
		this.matchday = matchday;
	}

	public ScoreDTO getScore() {
		return score;
	}

	public void setScore(ScoreDTO score) {
		this.score = score;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}	
	
	
	
	

}
