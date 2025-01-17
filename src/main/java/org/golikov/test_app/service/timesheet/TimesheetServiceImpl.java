package org.golikov.test_app.service.timesheet;


import org.golikov.test_app.dto.TimesheetDTO;
import org.golikov.test_app.entity.Timesheet;
import org.golikov.test_app.repos.TimesheetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TimesheetServiceImpl implements TimesheetService{

    private final TimesheetRepository timesheetRepository;
    public TimesheetServiceImpl(TimesheetRepository timesheetRepository) {
        this.timesheetRepository = timesheetRepository;
    }

    @Transactional
    @Override
    public TimesheetDTO create(TimesheetDTO timesheetDTO) {
        Timesheet timesheet = timesheetDTO.convertToEntity();
        timesheetRepository.save(timesheet);

        return timesheetDTO;
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
        return null;
    }

    @Transactional
    @Override
    public List<TimesheetDTO> getAll() {
        return List.of();
    }
}
