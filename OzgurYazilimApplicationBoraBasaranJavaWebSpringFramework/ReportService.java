// ReportService.java
@Service
public class ReportService {
    private final ReportRepository reportRepository;

    @Autowired
    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    // Implement service methods for CRUD operations
}

@Service
public class ReportService {
    private final ReportRepository reportRepository;

    @Autowired
    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Secured("ROLE_USER")
    public Report createReport(Report report) {
        // Implement logic to create a new report
        // You might want to set other properties like reportIssuedDate before saving
        // For example:
        // report.setReportIssuedDate(LocalDate.now());
        return reportRepository.save(report);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public Report updateReport(Long id, Report updatedReport) {
        // Implement logic to update an existing report
        Report existingReport = reportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Report not found with id: " + id));

        // Update fields from updatedReport to existingReport
        existingReport.setFileNumber(updatedReport.getFileNumber());
        existingReport.setPatientName(updatedReport.getPatientName());
        existingReport.setPatientIdNumber(updatedReport.getPatientIdNumber());
        existingReport.setDiagnosisTitle(updatedReport.getDiagnosisTitle());
        existingReport.setDiagnosisDetails(updatedReport.getDiagnosisDetails());
        existingReport.setReportIssuedDate(updatedReport.getReportIssuedDate());
        existingReport.setPhotographUrl(updatedReport.getPhotographUrl());

        return reportRepository.save(existingReport);
    }

    @Secured("ROLE_ADMIN")
    public boolean deleteReport(Long id) {
        // Implement logic to delete a report
        if (reportRepository.existsById(id)) {
            reportRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Report> getAllReports() {
        // Implement logic to retrieve all reports
        return reportRepository.findAll();
    }
}

@Test
public void testCreateReport() {
    // Create a mock report
    Report mockReport = new Report();
    mockReport.setId(1L);
    mockReport.setFileNumber("123");

    // Mock repository save method
    when(reportRepository.save(any(Report.class))).thenReturn(mockReport);

    // Call the service method
    Report createdReport = reportService.createReport(mockReport);

    // Verify the result
    assertNotNull(createdReport);
    assertEquals(1L, createdReport.getId().longValue());
    assertEquals("123", createdReport.getFileNumber());

    // Verify that the save method of the repository was called
    verify(reportRepository, times(1)).save(any(Report.class));
}

@Test
public void testUpdateReport() {
    // Create a mock report
    Report existingReport = new Report();
    existingReport.setId(1L);
    existingReport.setFileNumber("123");

    // Mock repository findById and save methods
    when(reportRepository.findById(1L)).thenReturn(Optional.of(existingReport));
    when(reportRepository.save(any(Report.class))).thenReturn(existingReport);

    // Create an updated report
    Report updatedReport = new Report();
    updatedReport.setId(1L);
    updatedReport.setFileNumber("456");

    // Call the service method
    Report result = reportService.updateReport(1L, updatedReport);

    // Verify the result
    assertNotNull(result);
    assertEquals(1L, result.getId().longValue());
    assertEquals("456", result.getFileNumber());

    // Verify that findById and save methods of the repository were called
    verify(reportRepository, times(1)).findById(1L);
    verify(reportRepository, times(1)).save(any(Report.class));
}

@Test
public void testDeleteReport() {
    // Mock repository existsById and deleteById methods
    when(reportRepository.existsById(1L)).thenReturn(true);

    // Call the service method
    boolean result = reportService.deleteReport(1L);

    // Verify the result
    assertTrue(result);

    // Verify that existsById and deleteById methods of the repository were called
    verify(reportRepository, times(1)).existsById(1L);
    verify(reportRepository, times(1)).deleteById(1L);
}