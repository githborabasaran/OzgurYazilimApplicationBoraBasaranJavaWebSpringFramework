@RestController
@RequestMapping("/api/laboratory-workers")
public class LaboratoryWorkerController {
    private final LaboratoryWorkerService laboratoryWorkerService;

    @Autowired
    public LaboratoryWorkerController(LaboratoryWorkerService laboratoryWorkerService) {
        this.laboratoryWorkerService = laboratoryWorkerService;
    }

    // Endpoint to create a new laboratory worker
    @PostMapping
    public ResponseEntity<LaboratoryWorker> createLaboratoryWorker(@RequestBody LaboratoryWorker laboratoryWorker) {
        LaboratoryWorker createdLaboratoryWorker = laboratoryWorkerService.createLaboratoryWorker(laboratoryWorker);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLaboratoryWorker);
    }

    // Endpoint to retrieve a laboratory worker by ID
    @GetMapping("/{id}")
    public ResponseEntity<LaboratoryWorker> getLaboratoryWorkerById(@PathVariable Long id) {
        LaboratoryWorker laboratoryWorker = laboratoryWorkerService.getLaboratoryWorkerById(id);
        if (laboratoryWorker != null) {
            return ResponseEntity.ok(laboratoryWorker);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to update an existing laboratory worker
    @PutMapping("/{id}")
    public ResponseEntity<LaboratoryWorker> updateLaboratoryWorker(@PathVariable Long id, @RequestBody LaboratoryWorker laboratoryWorker) {
        LaboratoryWorker updatedLaboratoryWorker = laboratoryWorkerService.updateLaboratoryWorker(id, laboratoryWorker);
        if (updatedLaboratoryWorker != null) {
            return ResponseEntity.ok(updatedLaboratoryWorker);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to delete a laboratory worker by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLaboratoryWorker(@PathVariable Long id) {
        boolean deleted = laboratoryWorkerService.deleteLaboratoryWorker(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to retrieve all laboratory workers
    @GetMapping
    public ResponseEntity<List<LaboratoryWorker>> getAllLaboratoryWorkers() {
        List<LaboratoryWorker> laboratoryWorkers = laboratoryWorkerService.getAllLaboratoryWorkers();
        return ResponseEntity.ok(laboratoryWorkers);
    }
}