-- --------------------------------------------------------------------
-- THIS IS A SCRIPT TO EXPORT DATA TABLE FROM PTS-QUALITY TABLE(
--  Problems,Tickets,Install requests,US test case )TO TABLE(pts_ticket_import)
--  WITH PASSING TYPE OF TICKET 
--  Problems      type =41
--  Tickets       type =39
--  Install       type =54
--  US test case  type =42
-- --------------------------------------------------------------------
-- --------------------------------------------------------------------
-- -----SCHEDULED EVENT FOR EXPORT LUNCHED EVERY 2 SECOND -------------
DROP EVENT IF EXISTS PTS_EXPORT_SCHEDULE
$$
SET GLOBAL event_scheduler="ON"
$$
-- --------------------------------------------------------------------
CREATE EVENT IF NOT EXISTS PTS_EXPORT_SCHEDULE
ON SCHEDULE EVERY 5 SECOND
COMMENT 'EXPORT DATA TABLES EVERY 5 SECOND'
 DO
      BEGIN
-- CALL PROCEDURE TO EXPORT DATA AND PASSING TYPE IN PARAMETER --------
--      CALL pts_log.i0('PTS_EXPORT_SCHEDULE' ,'Entering ... ');

--      CALL pts_log.i0('PTS_EXPORT_SCHEDULE' ,'CALL  EXPORT_PROBLEM ... ');
      	CALL  EXPORT_PROBLEM(); 
--      CALL pts_log.i0('PTS_EXPORT_SCHEDULE' ,'EXPORT_PROBLEM DONE.');

--      CALL pts_log.i0('PTS_EXPORT_SCHEDULE' ,'CALL  EXPORT_TICKET ... ');
		CALL EXPORT_TICKET();
--      CALL pts_log.i0('PTS_EXPORT_SCHEDULE' ,'EXPORT_TICKET DONE.');

--      CALL pts_log.i0('PTS_EXPORT_SCHEDULE' ,'CALL  EXPORT_INSTALL ... ');
		CALL EXPORT_INSTALL();
--      CALL pts_log.i0('PTS_EXPORT_SCHEDULE' ,'EXPORT_INSTALL DONE.');


--      CALL pts_log.i0('PTS_EXPORT_SCHEDULE' ,'CALL  EXPORT_US_TEST_CASE ... ');
--      CALL  EXPORT_US_TEST_CASE(); 
--      CALL pts_log.i0('PTS_EXPORT_SCHEDULE' ,'EXPORT_US_TEST_CASE DONE.');
      
--      CALL pts_log.i0('PTS_EXPORT_SCHEDULE' ,'Done.');
      END;
$$