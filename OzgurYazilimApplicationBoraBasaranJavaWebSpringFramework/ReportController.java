@RestController
@RequestMapping("/api/reports")
public class ReportController {
    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    // Endpoint to create a new report
    @PostMapping
    public ResponseEntity<Report> createReport(@RequestBody Report report) {
        Report createdReport = reportService.createReport(report);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReport);
    }

    // Endpoint to retrieve a report by ID
    @GetMapping("/{id}")
    public ResponseEntity<Report> getReportById(@PathVariable Long id) {
        Report report = reportService.getReportById(id);
        if (report != null) {
            return ResponseEntity.ok(report);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Report> updateReport(@PathVariable Long id, @RequestBody Report updatedReport) {
        Report existingReport = reportService.getReportById(id);

        if (existingReport != null) {
            existingReport.setFileNumber(updatedReport.getFileNumber());
            existingReport.setPatientName(updatedReport.getPatientName());
            existingReport.setPatientIdNumber(updatedReport.getPatientIdNumber());
            existingReport.setDiagnosisTitle(updatedReport.getDiagnosisTitle());
            existingReport.setDiagnosisDetails(updatedReport.getDiagnosisDetails());
            existingReport.setReportIssuedDate(updatedReport.getReportIssuedDate());
            existingReport.setPhotographUrl(updatedReport.getPhotographUrl());

            Report savedReport = reportService.updateReport(id, existingReport);
            return ResponseEntity.ok(savedReport);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to delete a report by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
        boolean deleted = reportService.deleteReport(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to retrieve all reports
    @GetMapping
    public ResponseEntity<List<Report>> getAllReports() {
        List<Report> reports = reportService.getAllReports();
        return ResponseEntity.ok(reports);
    }
}
@PreAuthorize("hasRole('USER')")
@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
    if (reportService.deleteReport(id)) {
        return ResponseEntity.noContent().build();
    } else {
        return ResponseEntity.notFound().build();
    }
}
