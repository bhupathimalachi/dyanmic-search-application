package in.bhupathi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.bhupathi.binding.request.SearchRequest;
import in.bhupathi.binding.response.PlanResponse;
import in.bhupathi.entity.InsurancePlanEntity;
import in.bhupathi.repository.InsurancePlanRepository;

@Service
public class InsurancePlanServiceImpl implements InsurancePlanService {

	@Autowired
	private InsurancePlanRepository repo;

	@Override
	public List<PlanResponse> searchplans(SearchRequest request) {
		InsurancePlanEntity entity = new InsurancePlanEntity();

		if (request != null && request.getPlanName() != null && !request.getPlanName().equals("")) {
			entity.setPlanName(request.getPlanName());
		}
		if (request != null &&  request.getPlanStatus() != null && !request.getPlanStatus().equals("")) {
			entity.setPlanStatus(request.getPlanStatus());
		}

		Example<InsurancePlanEntity> of = Example.of(entity);
		List<InsurancePlanEntity> findall = repo.findAll(of);
		List<PlanResponse> plans = new ArrayList<PlanResponse>();
		for (InsurancePlanEntity plan : findall) {
			PlanResponse presp = new PlanResponse();
			BeanUtils.copyProperties(plan, presp);
			plans.add(presp);

		}

		return plans;
	}

	@Override
	public List<String> getUniquePlanNames() {
		return repo.getPlanNames();
	}

	@Override
	public List<String> getUniquePlanStatus() {
		return repo.getPlanStatus();
	}

}
