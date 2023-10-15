// LaboratoryWorker.java
@Entity
public class LaboratoryWorker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String hospitalIdNumber;

    // Getters and setters
}