-- V1__init_worktime_tables.sql
outputTime TIME NOT NULL,
prtId INT,
visible BOOLEAN DEFAULT TRUE,
created_date DATETIME DEFAULT NOW()
);


-- 2) WorkTimeHistory
CREATE TABLE IF NOT EXISTS Work_Time_History (
                                               id INT AUTO_INCREMENT PRIMARY KEY,
                                               profileId INT NOT NULL,
                                               inputTime TIME NOT NULL,
                                               outputTime TIME,
                                               workTimeStatus VARCHAR(50),
    createdDate DATETIME DEFAULT NOW()
    );


-- 3) WorkTimeDisruption
CREATE TABLE IF NOT EXISTS Work_Time_Disruption (
                                                  id INT AUTO_INCREMENT PRIMARY KEY,
                                                  profileId INT NOT NULL,
                                                  workTimeId INT NOT NULL,
                                                  missTime TIME,
                                                  earlyLeftTime TIME,
                                                  CONSTRAINT fk_worktimehistory FOREIGN KEY (workTimeId)
    REFERENCES Work_Time_History(id)
    );


-- ---------------------------
-- INITIAL TEST DATA
-- ---------------------------


-- PROFILE 1 uchun NORMAL SCHEDULE (08:00–17:00)
INSERT INTO Work_Time_Schedule(profileId, inputTime, outputTime, prtId, visible)
VALUES (1, '08:00', '17:00', 10, TRUE);


-- PROFILE 2 uchun KECH KELISHGA MUMKIN BO'LADIGAN SCHEDULE (09:00–18:00)
INSERT INTO Work_Time_Schedule(profileId, inputTime, outputTime, prtId, visible)
VALUES (2, '09:00', '18:00', 11, TRUE);


-- PROFILE 1 HISTORY — Kech kelgan (08:00 o'rniga 08:25)
INSERT INTO Work_Time_History(profileId, inputTime, outputTime, workTimeStatus)
VALUES (1, '08:25', '17:00', 'LATE');


-- PROFILE 2 HISTORY — Erta ketgan (18:00 o'rniga 17:20)
INSERT INTO Work_Time_History(profileId, inputTime, outputTime, workTimeStatus)
VALUES (2, '09:00', '17:20', 'EARLY_LEFT');


-- WorkTimeDisruption uchun FAKT bo'lgan kech kelishlar:
-- WorkTimeHistory.id = 1 -> kech kelgan (25 min)
INSERT INTO Work_Time_Disruption(profileId, workTimeId, missTime)
VALUES (1, 1, '00:25');


-- WorkTimeDisruption uchun FAKT bo'lgan erta ketishlar:
-- WorkTimeHistory.id = 2 -> 40 min oldin ketgan
INSERT INTO Work_Time_Disruption(profileId, workTimeId, earlyLeftTime)
VALUES (2, 2, '00:40');