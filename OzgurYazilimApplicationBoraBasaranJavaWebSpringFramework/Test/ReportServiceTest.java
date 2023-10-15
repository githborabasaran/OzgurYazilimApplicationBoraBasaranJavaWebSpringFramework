import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReportServiceTest {

    @Mock
    private ReportRepository reportRepository;

    @InjectMocks
    private ReportService reportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateReport() {
        // Create a mock report
        Report mockReport = new Report();
        mockReport.setFileNumber("123");

        // Mock repository save method
        when(reportRepository.save(any(Report.class))).thenReturn(mockReport);

        // Call the service method
        Report createdReport = reportService.createReport(mockReport);

        // Verify the result
        assertNotNull(createdReport);
        assertEquals("123", createdReport.getFileNumber());

        // Verify that the save method of the repository was called
        verify(reportRepository, times(1)).save(any(Report.class));
    }

    @Test
    void testUpdateReport() {
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
        assertEquals("456", result.getFileNumber());

        // Verify that findById and save methods of the repository were called
        verify(reportRepository, times(1)).findById(1L);
        verify(reportRepository, times(1)).save(any(Report.class));
    }

    @Test
    void testDeleteReport() {
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

    @Test
    void testGetAllReports() {
        // Mock repository findAll method
        when(reportRepository.findAll()).thenReturn(List.of(new Report(), new Report()));

        // Call the service method
        List<Report> reports = reportService.getAllReports();

        // Verify the result
        assertNotNull(reports);
        assertEquals(2, reports.size());

        // Verify that findAll method of the repository was called
        verify(reportRepository, times(1)).findAll();
    }
}