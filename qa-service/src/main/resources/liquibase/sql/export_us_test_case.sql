DROP PROCEDURE IF EXISTS EXPORT_US_TEST_CASE
$$
CREATE  PROCEDURE EXPORT_US_TEST_CASE()
    BEGIN
    DECLARE  v_us_test_case_id BIGINT ; 
        DECLARE  v_long_description VARCHAR(255) ;
        DECLARE  v_short_description VARCHAR(255) ;
         DECLARE v_effort VARCHAR(255) ;   
        DECLARE  v_creator VARCHAR(255) ;
        DECLARE  v_modifier VARCHAR(255) ;
        DECLARE  v_user_story_id BIGINT;
       -- DECLARE  v_state BIGINT;
        DECLARE  v_execution_estimation_time VARCHAR(255) ;
        DECLARE  done_p INT ;
        DECLARE  v_count INT ; 
-- --------------------------------------------------------------------
        DECLARE  cursor_data CURSOR FOR 
            SELECT 
             us_test_case_id, description,short_description,creator,modifier,user_story_id
             ,EFFORT,EXECUTION_ESTIMATION_TIME
             FROM us_test_case WHERE pts_ref IS NULL;
     
        DECLARE CONTINUE HANDLER FOR NOT FOUND SET done_p = 1;
         
         OPEN cursor_data;
         read_loop: LOOP
           FETCH cursor_data INTO  v_us_test_case_id, v_long_description,v_short_description,
           v_creator,v_modifier,v_user_story_id,v_effort,v_execution_estimation_time;

            IF done_p = 1 THEN
                LEAVE read_loop;
            END IF;
               
        SELECT count(*) INTO v_count FROM pts_ticket_import 
        WHERE kunde_ref=CONCAT('PTS-QA_US_TEST_CASE_', v_us_test_case_id);
             IF (v_count =0)THEN
                 INSERT  INTO pts_ticket_import 
                 (long_description ,short_description,type,kunde_ref,creator,editor,
                parent_ticket_id,amount_1,effort,estimated_effort)
                 
                VALUES(v_long_description ,v_short_description,42, 
                  CONCAT('PTS-QA_US_TEST_CASE_', v_us_test_case_id),v_creator,v_modifier,
                 v_user_story_id,v_user_story_id ,v_effort,v_execution_estimation_time);

             ELSE

                  UPDATE us_test_case SET pts_ref = (SELECT ti.ticket_id FROM pts_ticket_import ti
                  WHERE ti.kunde_ref=CONCAT('PTS-QA_US_TEST_CASE_', v_us_test_case_id)
                  AND ti.ticket_id  IS NOT NULL LIMIT 1) 
                  WHERE us_test_case_id=v_us_test_case_id;
             END IF;
             commit;
             

        END LOOP read_loop;
        CLOSE cursor_data;
    END;
    $$
