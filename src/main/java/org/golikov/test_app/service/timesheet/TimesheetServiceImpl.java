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

        Employee employee = employeeRepository.findById(timesheetCreateRequest.getEmployeeId())
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));

        timesheet.setEmployee(employee);
        timesheet.setDescription(timesheetCreateRequest.getDescription());
        timesheet.setStartDate(timesheetCreateRequest.getStartDate());
        timesheet.setDiscounted(timesheetCreateRequest.getDiscounted());
        timesheet.setReason(timesheetCreateRequest.getReason());
        timesheet.setDuration(timesheetCreateRequest.getDuration());

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
        if(timesheet == null) {
            return null;
        } else {
            return new TimesheetDTO(timesheet);
        }
    }

    @Transactional
    @Override
    public TimesheetDTO update(TimesheetCreateRequest timesheetDTO) {
        Timesheet existingTimesheet = timesheetRepository.findById(timesheetDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Timesheet not found"));

        Employee employee = employeeRepository.getReferenceById(timesheetDTO.getEmployeeId());

        if (timesheetDTO.getReason() != null) {
            existingTimesheet.setReason(timesheetDTO.getReason());
        }
        if (timesheetDTO.getDescription() != null) {
            existingTimesheet.setDescription(timesheetDTO.getDescription());
        }
        if (timesheetDTO.getStartDate() != null) {
            existingTimesheet.setStartDate(timesheetDTO.getStartDate());
        }
        if (timesheetDTO.getDuration() != null) {
            existingTimesheet.setDuration(timesheetDTO.getDuration());
        }
        if (timesheetDTO.getDiscounted() != null) {
            existingTimesheet.setDiscounted(timesheetDTO.getDiscounted());
        }

        if(timesheetDTO.getEmployeeId() != null) {
            existingTimesheet.setEmployee(employee);
        }

        timesheetRepository.save(existingTimesheet);

        return new TimesheetDTO(existingTimesheet);
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
