INSERT INTO Work_Time_Schedule(profileId, inputTime, outputTime, prtId, visible)
VALUES (1, '08:00', '17:00', 10, TRUE);


INSERT INTO Work_Time_Schedule(profileId, inputTime, outputTime, prtId, visible)
VALUES (2, '09:00', '18:00', 11, TRUE);


INSERT INTO Work_Time_History(profileId, inputTime, outputTime, workTimeStatus)
VALUES (1, '08:25', '17:00', 'INPUT');


INSERT INTO Work_Time_History(profileId, inputTime, outputTime, workTimeStatus)
VALUES (2, '09:00', '17:20', 'OUTPUT');


INSERT INTO Work_Time_Disruption(profileId, workTimeId, missTime)
VALUES (1, 1, '00:25');


INSERT INTO Work_Time_Disruption(profileId, workTimeId, earlyLeftTime)
VALUES (2, 2, '00:40');