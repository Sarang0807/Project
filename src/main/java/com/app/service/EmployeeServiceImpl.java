/*
 * ackage com.app.service;
 * 
 * import java.util.List;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service; import
 * org.springframework.transaction.annotation.Transactional;
 * 
 * import com.app.custom_excpetions.ResourceNotFoundException; import
 * com.app.dao.EmployeeRepository; import com.app.pojos.Employee;
 * 
 * @Service
 * 
 * @Transactional public class EmployeeServiceImpl implements IEmployeeService {
 * // dep : emp repo.
 * 
 * @Autowired private EmployeeRepository empRepo;
 * 
 * @Override public List<Employee> getAllEmpDetails() { // TODO Auto-generated
 * method stub return empRepo.findAll(); }
 * 
 * @Override public Employee saveEmpDetails(Employee transientEmp) { // TODO
 * Auto-generated method stub return empRepo.save(transientEmp);// method rets
 * PERSISTENT emp ref }// in case of no errs : hib auto dirty chking @
 * session.flush ---tx.commit // --inserts rec --L1 cache destroyed -- pooled
 * out db cn rets to the pool // --rets DETACHED pojo to the caller
 * 
 * @Override public String deleteEmpDetails(int empId) { String mesg =
 * "Deletion of emp details failed!!!!!!!!!!!";
 * 
 * if (empRepo.existsById(empId)) { empRepo.deleteById(empId); mesg =
 * "Emp details deleted successfully , for emp id :" + empId; }
 * 
 * return mesg; }
 * 
 * @Override public Employee getEmpDetails(int empId) { // TODO Auto-generated
 * method stub return empRepo.findById(empId) .orElseThrow(() -> new
 * ResourceNotFoundException("Invalid emp id !!!!!!" + empId)); }
 * 
 * @Override public Employee updateEmpDetails(Employee updatedDetachedEmp) { //
 * TODO Auto-generated method stub return empRepo.save(updatedDetachedEmp); }
 * 
 * 
 * 
 * }
 */