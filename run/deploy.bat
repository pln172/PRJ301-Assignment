@ECHO OFF

REM set varible for program's name
SET "pname=ASSIGNMENT"

REM build .war file
CD ../build/web
CALL jar -cvf %pname%.war * 

REM copy that .war file to dist folder
MOVE %pname%.war ..\..\dist\

REM return to root folder
CD ../../

REM Start server and deploy .war file
CD glassfish4/glassfish/bin
CALL asadmin start-domain
CALL asadmin deploy --force=true ../../../dist/%pname%.war