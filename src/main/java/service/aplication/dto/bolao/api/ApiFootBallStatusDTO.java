package service.aplication.dto.bolao.api;

import java.util.ArrayList;

public class ApiFootBallStatusDTO {
		
	private String count;
	Filters filters;
	private ArrayList<MatchesDTO> matches = new ArrayList<MatchesDTO>();
		
	public ArrayList<MatchesDTO> getMatches() {
		return matches;
	}

	public void setMatches(ArrayList<MatchesDTO> matches) {
		this.matches = matches;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public Filters getFilters() {
		return filters;
	}

	public void setFilters(Filters filters) {
		this.filters = filters;
	}
	

}


 
