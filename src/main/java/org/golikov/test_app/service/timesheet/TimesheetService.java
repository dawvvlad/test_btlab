package org.golikov.test_app.service.timesheet;

import org.golikov.test_app.dto.TimesheetCreateRequest;
import org.golikov.test_app.dto.TimesheetDTO;

import java.util.List;

public interface TimesheetService {
    void create(TimesheetCreateRequest timesheetCreateRequest);
    boolean delete(Long id);
    TimesheetDTO getById(Long id);
    TimesheetDTO update(TimesheetDTO timesheetDTO);
    List<TimesheetDTO> getAll();

}
