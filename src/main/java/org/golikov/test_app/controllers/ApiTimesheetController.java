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

/**
* REST-контроллер для управления таблицей опозданий
**/
@RestController
@RequestMapping("api/v1/timesheet")
public class ApiTimesheetController {

    private final TimesheetService timesheetService;

    @Autowired
    public ApiTimesheetController(TimesheetService timesheetService) {
        this.timesheetService = timesheetService;
    }

    /**
     * Создание нового опоздания
     * @param timesheetCreateRequest запрос для создания записи
     * @throws NoSuchValueException в случае неверных данных выбрасывает исключение и возвращает строку с сообщением
     */
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
        if(timesheetService.getById(id) == null) {
            throw new NoSuchValueException("Timesheet with id " + id + " not found");
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    /**
     * Создание нового опоздания
     * @param id id записи для изменения
     * @param updates объект, представляющий собой поле и данные для изменения
     * @throws NoSuchValueException в случае неверных данных выбрасывает исключение и возвращает строку с сообщением
     */
    @PatchMapping("/{id}")
    public ResponseEntity<TimesheetDTO> updateTimesheetById(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates
    ) {

        // находится запись по id
        TimesheetDTO existingTimesheet = timesheetService.getById(id);

        if (existingTimesheet == null) {
            throw new NoSuchValueException("Timesheet with id " + id + " not found");
        }

        // создается объект запроса (для удобства передачи Employee в виде id)
        TimesheetCreateRequest timesheetCreateRequest = new TimesheetCreateRequest(existingTimesheet);

        // проверка на соответствие ключа в Map и поля в таблице
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
