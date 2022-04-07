package service.aplication.dto.bolao.api;

public class ScoreDTO {
		
	private String winner;
	private FullTimeDTO fullTime;
	
	public String getWinner() {
		return winner;
	}
	
	public void setWinner(String winner) {
		this.winner = winner;
	}
	
	public FullTimeDTO getFullTime() {
		return fullTime;
	}
	
	public void setFullTime(FullTimeDTO fullTime) {
		this.fullTime = fullTime;
	}	

}
