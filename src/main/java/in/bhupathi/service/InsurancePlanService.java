package in.bhupathi.service;

import java.util.List;

import in.bhupathi.binding.request.SearchRequest;
import in.bhupathi.binding.response.PlanResponse;

public interface InsurancePlanService {
	public List<PlanResponse> searchplans(SearchRequest request);
	
	public List<String> getUniquePlanNames();
	
	public List<String> getUniquePlanStatus();
}
