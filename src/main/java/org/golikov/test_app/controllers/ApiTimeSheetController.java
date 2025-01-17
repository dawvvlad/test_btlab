package org.golikov.test_app.controllers;


import org.golikov.test_app.dto.TimesheetCreateRequest;
import org.golikov.test_app.dto.TimesheetDTO;
import org.golikov.test_app.service.timesheet.TimesheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/timesheet")
public class ApiTimeSheetController {

    private final TimesheetService timesheetService;

    @Autowired
    public ApiTimeSheetController(TimesheetService timesheetService) {
        this.timesheetService = timesheetService;
    }

    @PostMapping
    public ResponseEntity<TimesheetCreateRequest> createTimesheet(@RequestBody TimesheetCreateRequest timesheetCreateRequest) {
        timesheetService.create(timesheetCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(timesheetCreateRequest);
    }

    @GetMapping
    public ResponseEntity<List<TimesheetDTO>> getTimesheets() {
        List<TimesheetDTO> list = timesheetService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}
