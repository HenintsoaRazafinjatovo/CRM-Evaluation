package site.easy.to.build.crm.service.lead;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.Depense;
import site.easy.to.build.crm.entity.Lead;
import site.easy.to.build.crm.entity.LeadDepense;
import site.easy.to.build.crm.entity.User;
import site.easy.to.build.crm.service.customer.CustomerService;
import site.easy.to.build.crm.service.depense.DepenseService;
import site.easy.to.build.crm.service.lead.LeadService;

import site.easy.to.build.crm.service.user.UserService;

public class ImportLead {
    public List<LeadDepense> importLead(MultipartFile file,char separator,DepenseService depenseService,
    LeadService LeadService,UserService userService,CustomerService customerService) throws Exception {

        List<LeadDepense> leadDepenseList = new ArrayList<>();
        

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
             @SuppressWarnings("deprecation")
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                     .withFirstRecordAsHeader()
                     .withDelimiter(separator))) {  // Spécifiez le séparateur ici

            for (CSVRecord csvRecord : csvParser) {
                // T instance = clazz.getDeclaredConstructor().newInstance();

                Lead lead = new Lead();
                
                String name = csvRecord.get("name").trim();
                lead.setName(name);
                String status = csvRecord.get("status").trim();
                lead.setStatus(status);
                String phone = csvRecord.get("phone").trim();
                lead.setPhone(phone);
                String meetingId = csvRecord.get("meeting_id").trim();
                lead.setMeetingId(meetingId);
                String googleDrive = csvRecord.get("google_drive").trim();
                boolean googleDriveBool = Boolean.parseBoolean(googleDrive);  
                lead.setGoogleDrive(googleDriveBool);
                String googleDriveFolderId = csvRecord.get("google_drive_folder_id").trim();
                lead.setGoogleDriveFolderId(googleDriveFolderId);
                String manager = csvRecord.get("manager").trim();
                int managerId = Integer.parseInt(manager);
                User managerUser=userService.findById(managerId);
                lead.setManager(managerUser);
                if (managerUser==null){
                    throw new Exception("Manager not found");
                    
                }


                String employee = csvRecord.get("employee").trim();
                int employeeId = Integer.parseInt(employee);
                User employeeUser=userService.findById(employeeId);
                lead.setEmployee(employeeUser);
                if (employeeUser==null){
                    throw new Exception("Employee not found");
                    
                }

                String customer = csvRecord.get("customer").trim();
                int customerId = Integer.parseInt(customer);
                Customer customerUser=customerService.findByCustomerId(customerId);
                lead.setCustomer(customerUser);
                if (customerUser==null){
                    throw new Exception("Customer not found");
                    
                }

                System.out.println("tonga PARSE DATE");
                String createdAt = csvRecord.get("created_at").trim();
                System.out.println("datePARSENA"+createdAt);
                LocalDateTime createdAtDate = LocalDateTime.parse(createdAt);

                lead.setCreatedAt(createdAtDate);

                Depense depenseObj = new Depense();

                String depense= csvRecord.get("depense").trim();
                double depenseValue = Double.parseDouble(depense);
                depenseObj.setValeurDepense(depenseValue);

                
                depenseObj.setDateDepense(createdAtDate);
                depenseObj.setEtat(1);

                depenseObj.setLead(lead);
                

                LeadDepense leadDepense = new LeadDepense(lead, depenseObj);
                leadDepenseList.add(leadDepense);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erreur lors de la lecture du fichier CSV : " + e.getMessage());
        }
        return leadDepenseList;
    }


    public boolean insertCSV(List <LeadDepense> leadDepenseList,DepenseService depenseService,LeadService leadService){
        for (LeadDepense leadDepense : leadDepenseList) {
            Lead lead = leadDepense.getLead();
            Depense depense = leadDepense.getDepense();
            Lead savelead=leadService.save(lead);
            leadDepense.getDepense().setLead(savelead);
            leadDepense.setLead(savelead); 
            Depense depense2=depenseService.saveDepense(depense);
            leadDepense.setDepense(depense2);
        }
        return true;
    }
}
