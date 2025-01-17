package org.golikov.test_app.service.timesheet;


import org.golikov.test_app.dto.TimesheetCreateRequest;
import org.golikov.test_app.dto.TimesheetDTO;
import org.golikov.test_app.entity.Employee;
import org.golikov.test_app.entity.Timesheet;
import org.golikov.test_app.repos.EmployeeRepository;
import org.golikov.test_app.repos.TimesheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TimesheetServiceImpl implements TimesheetService{

    private final TimesheetRepository timesheetRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public TimesheetServiceImpl(TimesheetRepository timesheetRepository, EmployeeRepository employeeRepository) {
        this.timesheetRepository = timesheetRepository;
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    @Override
    public void create(TimesheetCreateRequest timesheetCreateRequest) {
        Timesheet timesheet = new Timesheet();
        Employee employee = employeeRepository.getReferenceById(timesheetCreateRequest.getEmployeeId());
        
        timesheet.setDescription(timesheetCreateRequest.getDescription());
        timesheet.setStartDate(timesheetCreateRequest.getStartDate());
        timesheet.setDiscounted(timesheetCreateRequest.getDiscounted());
        timesheet.setReason(timesheetCreateRequest.getReason());
        timesheet.setDuration(timesheetCreateRequest.getDuration());
        timesheet.setEmployee(employee);

        timesheetRepository.save(timesheet);
    }

    @Transactional
    @Override
    public boolean delete(Long id) {
        boolean res = false;
        try {
            timesheetRepository.deleteById(id);
            res = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    @Transactional
    @Override
    public TimesheetDTO getById(Long id) {
        Timesheet timesheet = timesheetRepository.findById(id).orElse(null);
        TimesheetDTO timesheetDTO;

        if(timesheet == null) {
            timesheetDTO = new TimesheetDTO();
        } else {
            return null;
        }
        return timesheetDTO;
    }

    @Transactional
    @Override
    public TimesheetDTO update(TimesheetDTO timesheetDTO) {
        Timesheet timesheet = timesheetDTO.convertToEntity();
        timesheetRepository.save(timesheet);
        return timesheetDTO;
    }

    @Transactional
    @Override
    public List<TimesheetDTO> getAll() {
        List<Timesheet> timesheetList = timesheetRepository.findAll();
        List<TimesheetDTO> timesheetDTOList = new ArrayList<>();

        for (Timesheet timesheet : timesheetList) {
            TimesheetDTO timesheetDTO = new TimesheetDTO(timesheet);
            timesheetDTOList.add(timesheetDTO);
        }
        return timesheetDTOList;
    }
}
