DROP PROCEDURE IF EXISTS EXPORT_INSTALL
$$
CREATE PROCEDURE EXPORT_INSTALL()
BEGIN
	    DECLARE MY_PROC_NAME VARCHAR(250) DEFAULT 'EXPORT_INSTALL';
		DECLARE KUNDE_REF_PREFIX VARCHAR(250) DEFAULT 'PTS-QA_INSTALL_';

        DECLARE  v_install_id BIGINT(20);	
        DECLARE  v_creator VARCHAR(255);
        DECLARE  v_modifier VARCHAR(255);
        DECLARE  v_deadline DATETIME;
		DECLARE  v_short_description VARCHAR(255);
		DECLARE  v_env_id BIGINT(20);
        DECLARE  v_product_id INT;
		DECLARE  v_project VARCHAR(100);
        DECLARE  v_install_version VARCHAR(255);
        DECLARE  v_requested_release VARCHAR(255);
        DECLARE  v_urgency INT; 		
	    DECLARE  v_target_sprint_id BIGINT(20);
	    DECLARE  v_user_story_id BIGINT(20);
		
        DECLARE  v_count INT ; 
		DECLARE  v_pts_ticket_id BIGINT ;		
        DECLARE  done_p INT ;

        DECLARE  cursor_data CURSOR FOR 
			SELECT 
			  install_id, 
			  creator, 
			  modifier,
			  date,
			  description,
			  env_id,
			  product_id,
			  install_version,
			  requested_release,
			  urgency,
			  target_sprint_id,
			  user_story_id,
			  project
			FROM `pts_quality`.`install` 
			WHERE pts_ref IS NULL and product_id IS NOT NULL and state = 'CREATED' limit 10;
			 
        DECLARE CONTINUE HANDLER FOR NOT FOUND SET done_p = 1;
         
        OPEN cursor_data;
		
        read_loop: LOOP
         
			FETCH cursor_data INTO  v_install_id ,  v_creator , v_modifier , v_deadline ,v_short_description, v_env_id , v_product_id, v_install_version , v_requested_release, v_urgency,v_target_sprint_id, v_user_story_id, v_project ;
			
			IF done_p = 1 THEN
				LEAVE read_loop;
			END IF;
					
			SELECT count(*) INTO v_count FROM pts_ticket_import 
				WHERE kunde_ref=CONCAT(KUNDE_REF_PREFIX, v_install_id);
			-- CALL pts_log.i0(MY_PROC_NAME ,concat('Try to Export install: ', v_install_id, ', US: ', ifnull(v_user_story_id, '-'), ', count entries in  pts_ticket_import ',v_count ));
			
			IF (v_count =0)THEN
			  -- US id null >> create US for the install	
			  IF v_user_story_id is null or v_user_story_id = 0 THEN
				INSERT  INTO pts_ticket_import 
					(solution_description
					, short_description ,urgency,type
					, kunde_ref,creator,editor,product_id, state
					, amount_1, fl1, fl2,  country_id, matrix_id, project)
					VALUES(
					  concat(
					    'Plan release : ', ifnull(install_version,'-') ,'<BR>' , 
					    'Requested release : ', ifnull(requested_release,'-') ,'<BR>' , 
						'Env ID : ', ifnull(env_id,'-') ,'<BR>' 
						'Deadline : ', ifnull(cast(v_deadline as char),'-') ,'<BR>' 
					  ),
					  v_short_description ,v_urgency,1017760, 
					  CONCAT(KUNDE_REF_PREFIX, v_install_id),v_creator,v_modifier,v_product_id,1017772, 
					  1, 1, 1,  1, v_target_sprint_id, v_project);

				-- CALL pts_log.i0(MY_PROC_NAME ,concat( 'install id : ', v_install_id, ', US ID inserted in pts_ticket_import and intarget sprint id : ', v_target_sprint_id));
			
			  -- US id is not null >> assign install to US					   
		      ELSE 
				-- create an install with parent us id
				INSERT  INTO pts_ticket_import 
					  (short_description ,urgency,type
					   , kunde_ref,creator,editor,product_id, state
					   , parent_ticket_id,amount_1, country_id
					   , fl1,text_s_1, text_s_2, deadline, project)
					VALUES(
					  v_short_description ,v_urgency,54, 
					  CONCAT(KUNDE_REF_PREFIX, v_install_id),v_creator,v_modifier,v_product_id,1113,
					  v_user_story_id, v_user_story_id, 1,
					  v_env_id, v_install_version, v_requested_release, v_deadline, v_project);
					  
				-- CALL pts_log.i0(MY_PROC_NAME ,concat( 'install id : ', v_install_id, ', install inserted in pts_ticket_import and parent US=',v_user_story_id));
              END IF;

				
			-- ELSE KUNDE_REF EXIST UPDATE PTS_REF WITH THE INSTALL_ID VALUE IN TABLE INSTALL
			ELSE
				SELECT ti.ticket_id into v_pts_ticket_id FROM pts_ticket_import ti
				  WHERE ti.kunde_ref=CONCAT(KUNDE_REF_PREFIX, v_install_id)
				  AND ti.ticket_id  IS NOT NULL LIMIT 1;
				
				-- CALL pts_log.i0(MY_PROC_NAME ,concat( 'ticket id : ', v_install_id, ', exported to ticket_id : ', ifnull(v_pts_ticket_id,'-')));
				
		    	UPDATE install SET pts_ref = v_pts_ticket_id WHERE install_id=v_install_id;
			 
		    END IF;
        END LOOP read_loop;
        CLOSE cursor_data;

      END;
      $$

