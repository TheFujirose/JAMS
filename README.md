# Just Another Movie Site

Just Another Movie Site that catalogs movies; how unique!

## Technology

* Spring Boot
* Spring Data JPA
* Thymeleaf
* MariaDB

## Database
There is a [file](./database.sql) containing SQL for creation of a table;
this is requirement.
There is also PL/SQL code for creation of dummy dat/**                                                                                                                                     
* Populate Dummy Movies                                                                                                                 
*                                                                                                                                       
* This is to populate the database with dummy data.                                                                                     
* You will need to set MariaDB to Oracle mode.                                                                                          
* @Parameter p_amoPLSQL is what dialectunt The amount of dummy data you want to insert.                                                 
*/                                                                                                                                      
CREATE OR REPLACE PROCEDURE Populate_Dummy_Movies(p_amount INTEGER) AS                                                                  
    TYPE char_array IS VARRAY(10) OF VARCHAR2(100);                                                                                     
    v_start  char_array := char_array('Return of the ', 'Hello ', 'A ', 'The ', 'Under ', 'The Chronicles of ');                        
    v_nouns  char_array := char_array('Generative AI', 'Humber Polytechnic', 'University of Guelph', 'Bob');                            
    v_end    char_array := char_array('2', 'II', ': A New Beginning', ': Exam Season', ': Whose Idea was This');                        
    v_genres char_array := char_array('action', 'adventure', 'comedy', 'documentary', 'sci-fi');                                        
    v_title  VARCHAR2(300);                                                                                                             
    v_genre  VARCHAR2(50);                                                                                                              
    v_year   NUMBER;                                                                                                                    
BEGIN                                                                                                                                   
    FOR counter IN 1 .. p_amount                                                                                                        
        LOOP                                                                                                                            
            -- Random indexes (collections are 1‑indexed)                                                                               
            DECLARE                                                                                                                     
                i_start PLS_INTEGER := TRUNC(DBMS_RANDOM.VALUE(1, v_start.COUNT + 1));                                                  
                i_noun  PLS_INTEGER := TRUNC(DBMS_RANDOM.VALUE(1, v_nouns.COUNT + 1));                                                  
                i_end   PLS_INTEGER := TRUNC(DBMS_RANDOM.VALUE(1, v_end.COUNT + 1));                                                    
                i_genre PLS_INTEGER := TRUNC(DBMS_RANDOM.VALUE(1, v_genres.COUNT + 1));                                                 
            BEGIN                                                                                                                       
                v_title := v_start(i_start) || v_nouns(i_noun) || ' ' || v_end(i_end);                                                  
                v_genre := v_genres(i_genre);                                                                                           
                v_year := TRUNC(DBMS_RANDOM.VALUE(1980, 2025));

                INSERT INTO movies (title, genre, releaseYear)                                                                          
                VALUES (v_title, v_genre, v_year);                                                                                      
            END;                                                                                                                        
        END LOOP;                                                                                                                       
END;                                                                                                                                    
/
a. This will only work for compatible databases.