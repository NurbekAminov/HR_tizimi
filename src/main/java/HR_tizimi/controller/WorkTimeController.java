package HR_tizimi.controller;

import HR_tizimi.dto.*;
import HR_tizimi.service.WorkTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/work-time")
public class WorkTimeController {
    @Autowired
    private WorkTimeService workTimeService;

    @PostMapping("/admin/create/schedule")
    public WorkTimeScheduleDTO createSchedule(@RequestBody WorkTimeScheduleDTO dto) {
        return workTimeService.createSchedule(dto);
    }

    @GetMapping("/admin/get/all/schedule")
    public List<WorkTimeScheduleDTO> getAllSchedule() {
        return workTimeService.getAllSchedule();
    }

    @PutMapping("/admin/update/schedule")
    public ApiResponse updateSchedule(@RequestBody WorkTimeScheduleDTO dto) {
        return workTimeService.updateSchedule(dto);
    }

    @DeleteMapping("/admin/delete/schedule")
    public ApiResponse deleteSchedule(@RequestBody WorkTimeScheduleDTO dto) {
        return workTimeService.deleteSchedule(dto);
    }

    @GetMapping("/admin/get/all/disruption")
    public List<WorkTimeDisruptionDTO> getAllDisruption() {
        return workTimeService.getAllDisruption();
    }

    @GetMapping("/admin/get/all/history")
    public List<WorkTimeHistoryDTO> getAllHistory() {
        return workTimeService.getAllHistory();
    }

    @GetMapping("/admin/get/interval/history")
    public List<WorkTimeHistoryDTO> getIntervalHistory(@RequestBody IntervalDTO interval) {
        return workTimeService.getIntervalHistory(interval);
    }

    @PutMapping("/user/worktime")
    public ApiResponse profileWorkTime() {
        return workTimeService.profileWorkTime();
    }

}
