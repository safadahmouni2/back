DROP PROCEDURE IF EXISTS EXPORT_TICKET
$$
CREATE  PROCEDURE EXPORT_TICKET()
BEGIN
	    DECLARE MY_PROC_NAME VARCHAR(250) DEFAULT 'EXPORT_TICKET';
		DECLARE KUNDE_REF_PREFIX VARCHAR(250) DEFAULT 'PTS-QA_TICKET_';
        DECLARE  v_short_description VARCHAR(255) ;
        DECLARE  v_long_description longtext ;
        DECLARE  v_urgency INT ;   
        DECLARE  v_count INT ; 
        DECLARE  v_ticket_id BIGINT ;
		DECLARE  v_pts_ticket_id BIGINT ;
        DECLARE  v_creator VARCHAR(255) ;
        DECLARE  v_modifier VARCHAR(255) ;
        DECLARE  v_product_id INT;
        DECLARE  v_project VARCHAR(100);
        DECLARE  v_user_story_id BIGINT;
	    DECLARE  v_test_id BIGINT;
	    DECLARE  v_test_step_id BIGINT;
	    DECLARE  v_test_run_id BIGINT;
	    DECLARE  v_install_id BIGINT;
	    DECLARE  v_target_sprint_id BIGINT;
	    DECLARE  v_origin_sprint_id BIGINT;
        DECLARE  done_p INT ;



        DECLARE  cursor_data CURSOR FOR 
		  SELECT 
			 p.ticket_id, p.long_description,p.short_description,p.urgency,p.creator,p.modifier,p.product_id,
             t.user_story_id, p.test_id, p.test_step_id, t.test_run_id, tr.installed_id, p.target_sprint_id , p.origin_sprint_id,
             p.project
			 FROM ticket p , test t, test_run tr
             WHERE p.test_id=t.test_id and t.test_run_id = tr.test_run_id and
			 pts_ref IS NULL and product_id IS NOT NULL limit 10;
			 
        DECLARE CONTINUE HANDLER FOR NOT FOUND SET done_p = 1;
         
        OPEN cursor_data;
		
        read_loop: LOOP
         
			FETCH cursor_data INTO  v_ticket_id, v_long_description,v_short_description,v_urgency,
			v_creator,v_modifier,v_product_id ,v_user_story_id, v_test_id, v_test_step_id, v_test_run_id, v_install_id,
			v_target_sprint_id, v_origin_sprint_id, v_project;
			
			IF done_p = 1 THEN
				LEAVE read_loop;
			END IF;
				
			
			SELECT count(*) INTO v_count FROM pts_ticket_import 
				WHERE kunde_ref=CONCAT(KUNDE_REF_PREFIX, v_ticket_id);

			-- CALL pts_log.i0(MY_PROC_NAME ,concat('Try to Export ticket: ', v_ticket_id, ', US: ', ifnull(v_user_story_id, '-'), ', count entries in  pts_ticket_import ',v_count ));
			
			IF (v_count =0)THEN
			  IF v_target_sprint_id = v_origin_sprint_id THEN
			    IF v_user_story_id is null or v_user_story_id = 0 THEN
				   -- create a ticket with parent install id				    
				   INSERT  INTO pts_ticket_import 
						(long_description ,short_description ,urgency,type,kunde_ref,creator,editor,product_id, state
						,parent_ticket_id, cust_user_id, fl2, fl3, country_id, project)
						VALUES(
						  concat(
							ifnull(v_long_description,'-') , char(13), char(13) , 
							GET_TEST_DETAILS(v_test_id), char(13), char(13) , 
							ifnull(concat('TEST STEP: ', v_test_step_id) , '')
						  ),
						  v_short_description ,v_urgency,39, 
						  CONCAT(KUNDE_REF_PREFIX, v_ticket_id),v_creator,v_modifier,v_product_id,1025,
						  v_install_id, v_test_run_id ,v_test_id, v_test_step_id, 1, v_project);
				   -- CALL pts_log.i0(MY_PROC_NAME ,concat( 'ticket id : ', v_ticket_id, ', ticket inserted in pts_ticket_import and original sprint ',v_origin_sprint_id ,' ! parent Install=',v_install_id));	

			    -- US id is not null					   
			    ELSE 
				   -- create a ticket with parent us id
				   INSERT  INTO pts_ticket_import 
						(long_description ,short_description ,urgency,type,kunde_ref,creator,editor,product_id, state
						,parent_ticket_id,amount_1, cust_user_id, fl2, fl3, country_id)
						VALUES(
						  concat(
							ifnull(v_long_description,'-') , char(13), char(13) , 
							GET_TEST_DETAILS(v_test_id), char(13), char(13) , 
							ifnull(concat('TEST STEP: ', v_test_step_id) , '')
						  ),
						  v_short_description ,v_urgency,39, 
						  CONCAT(KUNDE_REF_PREFIX, v_ticket_id),v_creator,v_modifier,v_product_id,1025, v_user_story_id,
						  v_user_story_id, v_test_run_id ,v_test_id, v_test_step_id, 1);

				   -- CALL pts_log.i0(MY_PROC_NAME ,concat( 'ticket id : ', v_ticket_id, ', ticket inserted in pts_ticket_import and in original sprint ',v_origin_sprint_id ,' ! parent US=',v_user_story_id));

			    END IF;
			  -- v_target_sprint_id != v_origin_sprint_id 
			  ELSE
			    INSERT  INTO pts_ticket_import 
					(long_description ,short_description ,urgency,type,kunde_ref,creator,editor,product_id, state
					,parent_ticket_id,amount_1, fl1, fl2,ap_id,cust_user_id, fl3, country_id, matrix_id, project)
					VALUES(
					  concat(
					    ifnull(v_long_description,'-') , char(13), char(13) , 
						GET_TEST_DETAILS(v_test_id), char(13), char(13) , 
						ifnull(concat('TEST STEP: ', v_test_step_id) , '')
					  ),
					  v_short_description ,v_urgency,1017760, 
					  CONCAT(KUNDE_REF_PREFIX, v_ticket_id),v_creator,v_modifier,v_product_id,1017772, v_user_story_id,
					  if(v_user_story_id is null or v_user_story_id = 0,v_install_id,v_user_story_id),1,1, v_test_run_id ,v_test_id, v_test_step_id, 1, v_target_sprint_id,v_project);

                -- CALL pts_log.i0(MY_PROC_NAME ,concat( 'ticket id : ', v_ticket_id, ', US ID inserted in pts_ticket_import and intarget sprint id : ', v_target_sprint_id));					  

			  END IF;
			-- ELSE KUNDE_REF EXIST UPDATE PTS_REF WITH THE TICKET_ID VALUE IN TABLE TICKET
			ELSE
				SELECT ti.ticket_id into v_pts_ticket_id FROM pts_ticket_import ti
				  WHERE ti.kunde_ref=CONCAT(KUNDE_REF_PREFIX, v_ticket_id)
				  AND ti.ticket_id  IS NOT NULL LIMIT 1;
				
				-- CALL pts_log.i0(MY_PROC_NAME ,concat( 'ticket id : ', v_ticket_id, ', exported to ticket_id : ', ifnull(v_pts_ticket_id,'-')));
				
		    	UPDATE ticket SET pts_ref = v_pts_ticket_id WHERE ticket_id=v_ticket_id;
			 
		    END IF;
        END LOOP read_loop;
        CLOSE cursor_data;

      END;
      $$

