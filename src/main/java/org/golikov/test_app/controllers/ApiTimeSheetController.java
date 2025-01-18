package org.golikov.test_app.controllers;


import org.golikov.test_app.dto.TimesheetCreateRequest;
import org.golikov.test_app.dto.TimesheetDTO;
import org.golikov.test_app.exceptions.NoSuchValueException;
import org.golikov.test_app.service.timesheet.TimesheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
        try {
            timesheetService.create(timesheetCreateRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(timesheetCreateRequest);
        } catch (Exception e) {
            throw new NoSuchValueException("Incorrect data (Employee with id " + timesheetCreateRequest.getEmployeeId() + " not found)");
        }
    }

    @GetMapping
    public ResponseEntity<List<TimesheetDTO>> getTimesheets() {
        List<TimesheetDTO> list = timesheetService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimesheetDTO> getTimesheetById(@PathVariable Long id) {
        TimesheetDTO timesheetDTO = timesheetService.getById(id);

        if (timesheetDTO == null) {
            throw new NoSuchValueException("Timesheet with id " + id + " not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(timesheetDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTimesheetById(@PathVariable Long id) {
        timesheetService.delete(id);
        System.out.println("deleted timesheet");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TimesheetDTO> updateTimesheetById(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates
    ) {

        TimesheetDTO existingTimesheet = timesheetService.getById(id);

        if (existingTimesheet == null) {
            throw new NoSuchValueException("Timesheet with id " + id + " not found");
        }

        TimesheetCreateRequest timesheetCreateRequest = new TimesheetCreateRequest(existingTimesheet);
        updates.forEach((key, value) -> {
            switch (key) {
                case "reason":
                    timesheetCreateRequest.setReason((Integer) value);
                    break;
                case "employeeId":
                    timesheetCreateRequest.setEmployeeId(((Number) value).longValue());
                    break;
                case "startDate":
                    existingTimesheet.setStartDate(java.sql.Date.valueOf((String) value)); // Преобразуем строку в дату
                    break;
                case "duration":
                    existingTimesheet.setDuration((Integer) value);
                    break;
                case "description":
                    existingTimesheet.setDescription((String) value);
                    break;
                case "discounted":
                    existingTimesheet.setDiscounted((Boolean) value);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown field: " + key);
            }
        });

        TimesheetDTO updatedTimesheet = timesheetService.update(timesheetCreateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTimesheet);
    }

}
