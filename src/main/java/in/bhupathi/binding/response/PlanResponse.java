package in.bhupathi.binding.response;

import lombok.Data;

@Data
public class PlanResponse {

	private Integer planId;

	private String planName;

	private String planHolderName;

	private Long planHolderSsn;

	private String planStatus;
}
