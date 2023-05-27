DROP PROCEDURE IF EXISTS EXPORT_DAILY_SCRUM 
$$
CREATE  PROCEDURE `EXPORT_DAILY_SCRUM`()
BEGIN
	    DECLARE MY_PROC_NAME VARCHAR(250) DEFAULT 'EXPORT_DAILY_SCRUM';
		DECLARE KUNDE_REF_PREFIX VARCHAR(250) DEFAULT 'PTS-AGILE_DAILY_SCRUM_';       	

		DECLARE  v_daily_scrum_id BIGINT(20);  
		DECLARE  v_ticket_id BIGINT(20);  
		DECLARE  v_state_id BIGINT(20);
		DECLARE  v_sprint_progress DECIMAL(31,15);
		DECLARE  v_sprint_id BIGINT(20);
		DECLARE  v_finished_at TIMESTAMP;
		DECLARE  v_started_at TIMESTAMP;
		DECLARE  v_creator VARCHAR(250) CHARACTER SET UTF8;
		DECLARE  v_created TIMESTAMP ;
		DECLARE  v_modifier VARCHAR(250) CHARACTER SET UTF8;
		DECLARE  v_modified TIMESTAMP ;
		DECLARE  v_product_id VARCHAR(100) CHARACTER SET UTF8;
		DECLARE  v_client_ref VARCHAR(100) CHARACTER SET UTF8;

		

		DECLARE  done_p INT ;  
		DECLARE  v_count INT ;      


        DECLARE  cursor_data CURSOR FOR 
		  SELECT 
				DS.id,      
				DS.state_id,
				DS.sprint_progress,
				SP.ticket_id,
				DS.finished_at,
				DS.started_at,
				DS.creator,
				DS.created,
				DS.modifier,
				DS.modified,
				SP.product_id,
				COALESCE(DS.client_ref, CONCAT(KUNDE_REF_PREFIX, DS.id)) AS client_ref
				
			 FROM daily_scrum AS DS
			 INNER JOIN sprint AS SP ON DS.sprint_id = SP.id
			 WHERE  DS.ticket_id IS NULL AND SP.ticket_id IS NOT NULL   
			 ORDER BY DS.id DESC
             limit 10;
			 
        DECLARE CONTINUE HANDLER FOR NOT FOUND SET done_p = 1;
         
        OPEN cursor_data;
		
        read_loop: LOOP
         
			FETCH cursor_data INTO 
			v_daily_scrum_id,
			v_state_id,
			v_sprint_progress,
			v_sprint_id,
			v_finished_at,
			v_started_at,
			v_creator,
			v_created,
			v_modifier,
			v_modified,
			v_product_id,
			v_client_ref;
			
			IF done_p = 1 THEN
				LEAVE read_loop;
			END IF;
				
			
			SELECT count(*) INTO v_count FROM pts_ticket_import 
				WHERE kunde_ref= v_client_ref;
			
			IF (v_count =0)THEN
			 
				-- insert daily_scrum into pts_ticket_import			    
				INSERT INTO pts_ticket_import 
					(
					kunde_ref,
					parent_ticket_id,
					mail_date,
					state,
					lock_date,
					type,
					target_date,
					amount_1,
					creator,
					ts_insert,
					editor,
					ts_update,
				    short_description,
					product_id,
					country_id
					)
					VALUES(
						v_client_ref,	  
						v_sprint_id,
						v_started_at,
						v_state_id,
						v_started_at,
						1017896,
						v_finished_at,
						v_sprint_progress,
						v_creator,
						v_created,
						v_modifier,
						v_modified,
					    'PTS-AGILE_DAILY_SCRUM',
					    v_product_id,
						1
						);	
			    UPDATE daily_scrum SET exported_at = GREATEST(now(), modified), client_ref=v_client_ref WHERE id=v_daily_scrum_id ;	
			-- ELSE KUNDE_REF EXIST IN pts_ticket_import
			ELSE
				SELECT ticket_id INTO v_ticket_id FROM pts_ticket_import 
				WHERE kunde_ref= v_client_ref and ticket_id is not null limit 1;
				
		        UPDATE daily_scrum SET ticket_id = v_ticket_id WHERE id=v_daily_scrum_id  and ticket_id is null;

			 
		    END IF;
        END LOOP read_loop;
        CLOSE cursor_data;

      END;
$$

