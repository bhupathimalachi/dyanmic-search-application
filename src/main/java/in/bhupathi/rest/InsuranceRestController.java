package in.bhupathi.rest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.bhupathi.binding.request.SearchRequest;
import in.bhupathi.binding.response.PlanResponse;
import in.bhupathi.reports.ExcelReportsGenerator;
import in.bhupathi.reports.PdfReportGenerator;
import in.bhupathi.service.InsurancePlanService;

@RestController
public class InsuranceRestController {

	@Autowired
	private InsurancePlanService service;

	@PostMapping("/plans")
	public ResponseEntity<List<PlanResponse>> searchPlans(@RequestBody SearchRequest request) {
		List<PlanResponse> searchPlans = service.searchplans(request);
		return new ResponseEntity<List<PlanResponse>>(searchPlans, HttpStatus.OK);
	}

	@GetMapping("/plannames")
	public ResponseEntity<List<String>> getPlanNames() {
		List<String> planNames = service.getUniquePlanNames();
		return new ResponseEntity<List<String>>(planNames, HttpStatus.OK);

	}

	@GetMapping("/planstatus")
	public ResponseEntity<List<String>> getPlanStatus() {
		List<String> status = service.getUniquePlanStatus();
		return new ResponseEntity<List<String>>(status, HttpStatus.OK);
	}

	@GetMapping("/excel")
	public void generateExcel(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormat.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=plans_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		List<PlanResponse> plans = service.searchplans(null);
		ExcelReportsGenerator excelReport = new ExcelReportsGenerator();
		excelReport.export(plans, response);

	}
	@GetMapping("/pdf")
	public void generatePdf(HttpServletResponse response) throws Exception {
		response.setContentType("application/pdf");
		DateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormater.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=plans_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);

		List<PlanResponse> plans = service.searchplans(null);
		PdfReportGenerator pdfReport = new PdfReportGenerator();
		pdfReport.exportpdf(plans, response);

	}

}
