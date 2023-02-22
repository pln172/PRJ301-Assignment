@ECHO OFF
REM set varible for program's name
SET "pname=ASSIGNMENT"

REM undeploy .war file and stop server
cd ../glassfish4/glassfish/bin
CALL asadmin undeploy %pname%
CALL asadmin stop-domain