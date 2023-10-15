// Report.java
@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileNumber;
    private String patientName;
    private String patientIdNumber;
    private String diagnosisTitle;
    private String diagnosisDetails;
    private LocalDate reportIssuedDate;
    private String photographUrl; // Store photograph URL

    // Getters and setters
}