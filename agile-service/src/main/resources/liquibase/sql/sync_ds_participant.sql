DROP PROCEDURE IF EXISTS SYNC_DS_PARTICIPANT 
$$
CREATE  PROCEDURE `SYNC_DS_PARTICIPANT`()
BEGIN
	    DECLARE MY_PROC_NAME VARCHAR(250) DEFAULT 'SYNC_DS_PARTICIPANT';
		DECLARE KUNDE_REF_PREFIX VARCHAR(250) DEFAULT 'PTS-AGILE_DS_PARTICIPANT_';       	

		DECLARE  v_ds_participant_id,
				 v_ds_ticket_id,
				 v_user_id,
				 v_ticket_id,
				 v_state_id   BIGINT(20);  
		DECLARE  v_user_code VARCHAR(250) CHARACTER SET UTF8;
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
				DSP.id,      
				DSP.state_id,
				DS.ticket_id,
				DSP.user_id,
				DSP.creator,
				DSP.created,
				DSP.modifier,
				DSP.modified,
				SP.product_id,
				COALESCE(DSP.client_ref, CONCAT(KUNDE_REF_PREFIX, DSP.id)) AS client_ref
				
			 FROM ds_participant AS DSP
			 INNER JOIN daily_scrum AS DS ON DSP.daily_scrum_id = DS.id
			 INNER JOIN sprint AS SP ON DS.sprint_id = SP.id
			 WHERE ( DSP.ticket_id IS NOT NULL AND DSP.modified > DSP.exported_at)
			 ORDER BY DSP.id DESC
             limit 10;
			 
        DECLARE CONTINUE HANDLER FOR NOT FOUND SET done_p = 1;
         
        OPEN cursor_data;
		
        read_loop: LOOP
         
			FETCH cursor_data INTO 
			v_ds_participant_id,
			v_state_id,
			v_ds_ticket_id,
			v_user_id,
			v_creator,
			v_created,
			v_modifier,
			v_modified,
			v_product_id,
			v_client_ref;
			
			IF done_p = 1 THEN
				LEAVE read_loop;
			END IF;
			 
				-- insert ds_participant into pts_ticket_import			    
				INSERT INTO pts_ticket_import 
					(
					kunde_ref,
					parent_ticket_id,
					responsible_id,
					state,
					type,
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
						v_ds_ticket_id,
						v_user_id,
						v_state_id,
						1017907,
						v_creator,
						v_created,
						v_modifier,
						v_modified,
					    'PTS-AGILE_DS_PARTICIPANT',
					    v_product_id,
						1
						);

			UPDATE ds_participant SET exported_at = GREATEST(now(), modified)  WHERE id=v_ds_participant_id ;							

        END LOOP read_loop;
        CLOSE cursor_data;

      END;
$$
