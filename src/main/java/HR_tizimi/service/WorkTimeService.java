package HR_tizimi.service;

import HR_tizimi.config.CustomUserDetails;
import HR_tizimi.dto.ApiResponse;
import HR_tizimi.dto.WorkTimeDisruptionDTO;
import HR_tizimi.dto.WorkTimeHistoryDTO;
import HR_tizimi.dto.WorkTimeScheduleDTO;
import HR_tizimi.entity.ProfileEntity;
import HR_tizimi.entity.WorkTimeDisruptionEntity;
import HR_tizimi.entity.WorkTimeHistoryEntity;
import HR_tizimi.entity.WorkTimeScheduleEntity;
import HR_tizimi.enums.WorkTimeStatus;
import HR_tizimi.mapper.WorkTimeMapper;
import HR_tizimi.repository.ProfileRepository;
import HR_tizimi.repository.WorkTimeDisruptionRepository;
import HR_tizimi.repository.WorkTimeHistoryRepository;
import HR_tizimi.repository.WorkTimeScheduleRepository;
import HR_tizimi.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class WorkTimeService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private WorkTimeScheduleRepository workTimeScheduleRepository;
    @Autowired
    private WorkTimeHistoryRepository workTimeHistoryRepository;
    @Autowired
    private WorkTimeDisruptionRepository workTimeDisruptionRepository;
    @Autowired
    private WorkTimeMapper workTimeMapper;

    public WorkTimeScheduleDTO createSchedule(WorkTimeScheduleDTO dto) {
        Optional<ProfileEntity> profileOptional = profileRepository.findByProfileId(dto.getProfileId());
        if (profileOptional.isEmpty()) {
            return null;
        }

        CustomUserDetails customUserDetails = SpringSecurityUtil.getCurrentUser();
        WorkTimeScheduleEntity entity = new WorkTimeScheduleEntity();

        Optional<WorkTimeScheduleEntity> scheduleOptional = workTimeScheduleRepository.findByScheduleId(dto.getProfileId());
        if (scheduleOptional.isPresent()) {
            entity = scheduleOptional.get();
        }

        entity.setProfileId(dto.getProfileId());
        entity.setInputTime(dto.getInputTime());
        entity.setOutputTime(dto.getOutputTime());
        entity.setPrtId(customUserDetails.getProfile().getId());
        workTimeScheduleRepository.save(entity);

        dto.setId(entity.getId());
        dto.setPrtId(entity.getPrtId());

        return dto;
    }

    public List<WorkTimeScheduleDTO> getAllSchedule() {
        Optional<List<WorkTimeScheduleEntity>> optional = workTimeScheduleRepository.getScheduleList();
        if (optional.isEmpty()) {
            return null;
        }

        List<WorkTimeScheduleEntity> entityList = optional.get();

        List<WorkTimeScheduleDTO> dtoList = new LinkedList<>();
        for (WorkTimeScheduleEntity entity : entityList) {
            WorkTimeScheduleDTO dto = workTimeMapper.toWorkTimeScheduleDTO(entity);
            dtoList.add(dto);
        }

        return dtoList;
    }

    public List<WorkTimeDisruptionDTO> getAllDisruption() {
        Optional<List<WorkTimeDisruptionEntity>> optional = workTimeDisruptionRepository.getDisruptionList();
        if (optional.isEmpty()) {
            return null;
        }

        List<WorkTimeDisruptionEntity> entityList = optional.get();

        List<WorkTimeDisruptionDTO> dtoList = new LinkedList<>();
        for (WorkTimeDisruptionEntity entity : entityList) {
            WorkTimeDisruptionDTO dto = workTimeMapper.toWorkTimeDisruptionDTO(entity);
            dtoList.add(dto);
        }

        return dtoList;
    }

    public List<WorkTimeHistoryDTO> getAllHistory() {
        Optional<List<WorkTimeHistoryEntity>> optional = workTimeHistoryRepository.getHistoryList();
        if (optional.isEmpty()) {
            return null;
        }

        List<WorkTimeHistoryEntity> entityList = optional.get();

        List<WorkTimeHistoryDTO> dtoList = new LinkedList<>();
        for (WorkTimeHistoryEntity entity : entityList) {
            WorkTimeHistoryDTO dto = workTimeMapper.toWorkTimeHistoryDTO(entity);
            dtoList.add(dto);
        }

        return dtoList;
    }

    public List<WorkTimeHistoryDTO> getWeekHistory(LocalDateTime startDate, LocalDateTime endDate) {
        Optional<List<WorkTimeHistoryEntity>> optional = workTimeHistoryRepository.getWeekHistoryList(startDate, endDate);
        if (optional.isEmpty()) {
            return null;
        }

        List<WorkTimeHistoryEntity> entityList = optional.get();

        List<WorkTimeHistoryDTO> dtoList = new LinkedList<>();
        for (WorkTimeHistoryEntity entity : entityList) {
            WorkTimeHistoryDTO dto = workTimeMapper.toWorkTimeHistoryDTO(entity);
            dtoList.add(dto);
        }

        return dtoList;
    }

    public List<WorkTimeHistoryDTO> getMonthHistory(Integer year, Integer month) {
        Optional<List<WorkTimeHistoryEntity>> optional = workTimeHistoryRepository.getMonthHistoryList(year, month);
        if (optional.isEmpty()) {
            return null;
        }

        List<WorkTimeHistoryEntity> entityList = optional.get();

        List<WorkTimeHistoryDTO> dtoList = new LinkedList<>();
        for (WorkTimeHistoryEntity entity : entityList) {
            WorkTimeHistoryDTO dto = workTimeMapper.toWorkTimeHistoryDTO(entity);
            dtoList.add(dto);
        }

        return dtoList;
    }

    public ApiResponse updateSchedule(WorkTimeScheduleDTO dto) {
        Optional<ProfileEntity> profileOptional = profileRepository.findByProfileId(dto.getProfileId());
        if (profileOptional.isEmpty()) {
            return null;
        }

        Optional<WorkTimeScheduleEntity> scheduleOptional = workTimeScheduleRepository.findByProfileId(dto.getProfileId());
        if (scheduleOptional.isEmpty()) {
            return null;
        }

        CustomUserDetails customUserDetails = SpringSecurityUtil.getCurrentUser();
        int effectRows = workTimeScheduleRepository.updateSchedule(dto.getProfileId(), customUserDetails.getProfile().getId(), dto.getInputTime(), dto.getOutputTime());
        if (effectRows == 0) {
            return null;
        }

        return new ApiResponse(true, "WorkTimeSchedule Updated");
    }

    public ApiResponse deleteSchedule(WorkTimeScheduleDTO dto) {
        Optional<WorkTimeScheduleEntity> byName = workTimeScheduleRepository.findByScheduleId(dto.getId());
        if (byName.isEmpty()) {
            return null;
        }

        CustomUserDetails customUserDetails = SpringSecurityUtil.getCurrentUser();
        int effectRows = workTimeScheduleRepository.deleteSchedule(dto.getId(), customUserDetails.getProfile().getId());
        if (effectRows == 0) {
            return new ApiResponse(false, "Position Not Deleted");
        }
        return new ApiResponse(true, "Position Deleted");
    }

    public ApiResponse profileWorkTime() {
        CustomUserDetails customUserDetails = SpringSecurityUtil.getCurrentUser();
        ProfileEntity currentUser = customUserDetails.getProfile();

        Optional<WorkTimeHistoryEntity> optional = workTimeHistoryRepository.findByProfileIdAndWorkTimeStatus(currentUser.getId(), WorkTimeStatus.INPUT);
        WorkTimeHistoryEntity entity = new WorkTimeHistoryEntity();

        Optional<WorkTimeScheduleEntity> optionalSchedule = workTimeScheduleRepository.findByProfileId(currentUser.getId());

        if (optional.isPresent()) {
            if (optional.get().getCreatedDate().getDayOfYear() == LocalDateTime.now().getDayOfYear()) {
                entity = optional.get();
                entity.setOutputTime(LocalTime.now());
                entity.setWorkTimeStatus(WorkTimeStatus.OUTPUT);

                workTimeHistoryRepository.save(entity);

                LocalTime profileOutput = entity.getOutputTime();
                LocalTime scheduleOutput = optionalSchedule.get().getOutputTime();

                if (profileOutput.getHour() < scheduleOutput.getHour() || (profileOutput.getHour() == scheduleOutput.getHour() && profileOutput.getMinute() < scheduleOutput.getMinute())) {
                    WorkTimeEarlyLeft(currentUser.getId(), entity.getId(), profileOutput);
                }

                return new ApiResponse(true, "Output time created");
            } else {
                WorkTimeHistoryEntity entity2 = optional.get();
                entity2.setWorkTimeStatus(WorkTimeStatus.OUTPUT);
                workTimeHistoryRepository.save(entity2);
            }
        }

            entity.setProfileId(currentUser.getId());
            entity.setInputTime(LocalTime.now());
            entity.setWorkTimeStatus(WorkTimeStatus.INPUT);

            workTimeHistoryRepository.save(entity);

            LocalTime profileInput = entity.getInputTime();
            LocalTime scheduleInput = optionalSchedule.get().getInputTime();

            if (profileInput.getHour() > scheduleInput.getHour() || (profileInput.getHour() == scheduleInput.getHour() && profileInput.getMinute() > scheduleInput.getMinute())) {
                WorkTimeMiss(currentUser.getId(), entity.getId(), profileInput);
            }

            return new ApiResponse(true, "Input time created");
    }

    private void WorkTimeMiss(Integer profileId, Integer workTimeHistoryId, LocalTime missTime) {
        Optional<WorkTimeDisruptionEntity> optional = workTimeDisruptionRepository.getByWorkTimeHistoryId(workTimeHistoryId);
        WorkTimeDisruptionEntity entity = new WorkTimeDisruptionEntity();

        if (optional.isPresent()){
            entity = optional.get();
        }

        entity.setMissTime(missTime);
        entity.setProfileId(profileId);
        entity.setWorkTimeId(workTimeHistoryId);
        workTimeDisruptionRepository.save(entity);
    }
    private void WorkTimeEarlyLeft(Integer profileId, Integer workTimeHistoryId, LocalTime earlyLeftTime) {
        Optional<WorkTimeDisruptionEntity> optional = workTimeDisruptionRepository.getByWorkTimeHistoryId(workTimeHistoryId);
        WorkTimeDisruptionEntity entity = new WorkTimeDisruptionEntity();

        if (optional.isPresent()){
            entity = optional.get();
        }

        entity.setEarlyLeftTime(earlyLeftTime);
        entity.setProfileId(profileId);
        entity.setWorkTimeId(workTimeHistoryId);
        workTimeDisruptionRepository.save(entity);
    }

}
