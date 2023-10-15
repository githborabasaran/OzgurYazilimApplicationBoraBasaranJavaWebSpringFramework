// LaboratoryWorkerRepository.java
public interface LaboratoryWorkerRepository extends JpaRepository<LaboratoryWorker, Long> {
    Optional<LaboratoryWorker> findByHospitalIdNumber(String hospitalIdNumber);
}